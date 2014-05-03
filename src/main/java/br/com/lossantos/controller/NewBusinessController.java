package br.com.lossantos.controller;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.Validator;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.filters.Canvas;
import net.coobird.thumbnailator.geometry.Positions;
import net.sf.jmimemagic.Magic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.com.lossantos.dao.BusinessDao;
import br.com.lossantos.model.Business;
import br.com.lossantos.model.Service;

@Controller
public class NewBusinessController {

	private static final Logger logger = LoggerFactory
			.getLogger(NewBusinessController.class);

	@Autowired
	private BusinessDao businessDao;

	@Autowired
	private Validator validator;

	@Value("${upload.path.businesses}")
	private String uploadPath;

	@RequestMapping(value = "/cadastro.html", method = RequestMethod.GET)
	public String home(Business business, Model model) {
		model.addAttribute("business", business);

		return "new-business";
	}

	@RequestMapping(value = "/cadastro.html", method = RequestMethod.POST)
	public String home(@ModelAttribute @Valid Business business,
			BindingResult result, Model model,
			@RequestParam("image") MultipartFile uploadedImage) {

		validateUploadedImage(uploadedImage, result);

		model.addAttribute("business", business);
		model.addAttribute("validationResult", result);

		prepareServicesToDatabase(business);

		if (result.hasErrors() == false) {
			business.setImagesCount(1);
			logger.info("About to persist business object: {}", business);
			businessDao.add(business);

			handleImageUpload(business.getId(), uploadedImage);
		}

		return "new-business";
	}

	private void prepareServicesToDatabase(Business business) {
		List<Service> validServices = new ArrayList<Service>();
		for (Service service : business.getServices()) {
			if (validator.validate(service).size() == 0) {
				service.setBusiness(business);
				validServices.add(service);
			}
		}
		business.setServices(validServices);
	}

	private void validateUploadedImage(MultipartFile uploadedImage,
			BindingResult result) {
		if (uploadedImage.getSize() == 0) {
			result.addError(new FieldError("business", "image", "invalid-image"));
		} else {
			try {
				String mimeType = Magic.getMagicMatch(uploadedImage.getBytes(),
						false).getMimeType();
				if (!mimeType.startsWith("image")) {
					result.addError(new FieldError("business", "image",
							"invalid-image"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void handleImageUpload(Long businessId, MultipartFile uploadedImage) {
		// handling image upload
		File uploadDir = new File(uploadPath + File.separator + businessId);
		uploadDir.mkdirs();

		File targetFile = new File(uploadDir.getAbsolutePath() + File.separator
				+ "image1.jpg");

		try {
			Thumbnails
					.of(uploadedImage.getInputStream())
					.size(640, 480)
					.addFilter(
							new Canvas(640, 480, Positions.CENTER, true,
									Color.WHITE)).outputQuality(0.8)
					.toFile(targetFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields("title", "address", "email", "phoneNumbers",
				"city", "services*");
	}
}

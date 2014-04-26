package br.com.lossantos.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.validation.Valid;

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

@Controller
public class NewBusinessController {

	private static final Logger logger = LoggerFactory
			.getLogger(NewBusinessController.class);

	@Autowired
	private BusinessDao businessDao;

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

		if (result.hasErrors() == false) {
			logger.info("About to persist business object: {}", business);
			businessDao.add(business);

			handleImageUpload(business.getId(), uploadedImage);
		}

		return "new-business";
	}

	// TODO add resizing code here: http://code.google.com/p/java-image-scaling/
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
				+ uploadedImage.getOriginalFilename());

		try {
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(targetFile));
			stream.write(uploadedImage.getBytes());
			stream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields("title", "address", "email", "phoneNumbers");
	}
}

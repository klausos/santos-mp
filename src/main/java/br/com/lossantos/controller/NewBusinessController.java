package br.com.lossantos.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

	@RequestMapping(value = "/cadastro.html", method = RequestMethod.GET)
	public String home(Business business, Model model) {
		model.addAttribute("business", business);

		return "new-business";
	}

	@RequestMapping(value = "/cadastro.html", method = RequestMethod.POST)
	public String home(@ModelAttribute @Valid Business business,
			BindingResult result, Model model,
			@RequestParam("image") MultipartFile uploadedImage) {

		model.addAttribute("business", business);
		model.addAttribute("validationResult", result);

		if (result.hasErrors() == false) {
			logger.info("About to persist business object: {}", business);
			businessDao.add(business);
		}

		return "new-business";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields("title", "address", "email", "phoneNumbers");
	}
}

package br.com.lossantos.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.lossantos.dao.BusinessDao;
import br.com.lossantos.model.Business;

@Controller
public class ViewBusinessController {

	private static final Logger logger = LoggerFactory
			.getLogger(ViewBusinessController.class);

	@Autowired
	private BusinessDao businessDao;

	@RequestMapping(value = "/negocio-{id:[0-9]+}/{slug}.html", method = RequestMethod.GET)
	public String home(@PathVariable Long id, @PathVariable String slug,
			Model model) {
		logger.info("Requesting business id {}...", id);

		Business business = businessDao.findById(id);
		model.addAttribute("business", business);

		return "view-business";
	}

}

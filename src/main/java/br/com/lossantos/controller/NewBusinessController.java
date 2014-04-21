package br.com.lossantos.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.lossantos.dao.BusinessDao;
import br.com.lossantos.model.Business;

@Controller
public class NewBusinessController {

	private static final Logger logger = LoggerFactory
			.getLogger(NewBusinessController.class);

	@Autowired
	private BusinessDao businessDao;

	@RequestMapping(value = "/cadastro.html", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String home(Model model, HttpServletRequest request,
			HttpServletResponse response) {

		response.setCharacterEncoding("UTF-8");

		List<Business> list = businessDao.list();
		model.addAttribute("businesses", list);

		return "new-business";
	}

}

package br.com.lossantos.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.lossantos.dao.CityDao;
import br.com.lossantos.model.City;

@Controller
public class CityAutocompleteController {

	private static final Logger logger = LoggerFactory
			.getLogger(CityAutocompleteController.class);

	@Autowired
	private CityDao cityDao;

	@RequestMapping(value = "/city-autocomplete.json", method = RequestMethod.GET)
	public @ResponseBody CityJsonResponse showOptions(
			@RequestParam(value = "q", required = false, defaultValue = "") String q) {
		logger.info("City autocomplete called. Querying for: {}...", q);

		if (q.length() < 3)
			q = "empty";

		List<City> cities = cityDao.startsWith(q);
		logger.info("Found {} cities.", cities.size());

		return adaptCityResponse(q, cities);
	}

	private CityJsonResponse adaptCityResponse(String q, List<City> cities) {
		CityJsonResponse response = new CityJsonResponse();
		response.setQuery(q);

		for (City city : cities) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("value", city.getName() + " - "
					+ city.getState().getShortCode());
			map.put("data", city.getId().toString());
			response.getSuggestions().add(map);
		}

		return response;
	}

	class CityJsonResponse {

		private String query;
		private List<Map<String, String>> suggestions = new ArrayList<Map<String, String>>();

		public String getQuery() {
			return query;
		}

		public void setQuery(String query) {
			this.query = query;
		}

		public List<Map<String, String>> getSuggestions() {
			return suggestions;
		}

		public void setSuggestions(List<Map<String, String>> suggestions) {
			this.suggestions = suggestions;
		}
	}

}

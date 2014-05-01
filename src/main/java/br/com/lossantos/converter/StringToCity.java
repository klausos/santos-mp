package br.com.lossantos.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import br.com.lossantos.dao.CityDao;
import br.com.lossantos.model.City;

public class StringToCity implements Converter<String, City> {

	@Autowired
	CityDao cityDao;

	@Override
	public City convert(String cityId) {
		return cityDao.findById(Long.valueOf(cityId));
	}

}

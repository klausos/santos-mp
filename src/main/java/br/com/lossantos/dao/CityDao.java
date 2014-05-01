package br.com.lossantos.dao;

import java.util.List;

import br.com.lossantos.model.City;

public interface CityDao {

	void add(City city);

	City findById(Long id);

	List<City> startsWith(String q);

	City findFirst();

	List<City> findAll();

}

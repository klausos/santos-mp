package br.com.lossantos.dao;

import java.util.List;

import br.com.lossantos.model.City;

public interface CityDao {

	void add(City city);

	List<City> findAll();

}
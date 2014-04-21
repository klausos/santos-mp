package br.com.lossantos.dao;

import java.util.List;

import br.com.lossantos.model.Business;

public interface BusinessDao {

	void add(Business business);

	List<Business> list();

	Business findById(Long id);

}

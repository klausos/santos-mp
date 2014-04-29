package br.com.lossantos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.lossantos.model.City;

@Repository
public class JpaCityDao implements CityDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void add(City city) {
		em.persist(city);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<City> findAll() {
		return em.createQuery("SELECT c FROM City c ORDER BY id ASC LIMIT 10")
				.getResultList();
	}

}

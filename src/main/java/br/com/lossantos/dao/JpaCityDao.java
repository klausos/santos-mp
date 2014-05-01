package br.com.lossantos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.lossantos.helper.CustomStringUtils;
import br.com.lossantos.model.City;

@Repository
public class JpaCityDao implements CityDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void add(City city) {
		em.merge(city);
	}

	@Override
	public City findById(Long id) {
		return em.find(City.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<City> startsWith(String q) {
		q = CustomStringUtils.slugify(q);
		Query query = em
				.createQuery(
						"SELECT c FROM City c WHERE slug like :cityName ORDER BY slug ASC")
				.setParameter("cityName", q + "%");
		return query.getResultList();
	}

	@Override
	public City findFirst() {
		return (City) em.createQuery("SELECT c FROM City c ORDER BY id ASC")
				.setMaxResults(1).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<City> findAll() {
		return em.createQuery("SELECT c FROM City c ORDER BY id ASC")
				.getResultList();
	}

}

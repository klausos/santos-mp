package br.com.lossantos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.lossantos.model.Business;

@Repository
public class JpaBusinessDao implements BusinessDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void add(Business business) {
		em.persist(business);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Business> list() {
		return em.createQuery("SELECT b FROM Business b ORDER BY id DESC LIMIT 10")
				.getResultList();
	}

	@Override
	public Business findById(Long id) {
		return em.find(Business.class, id);
	}
}

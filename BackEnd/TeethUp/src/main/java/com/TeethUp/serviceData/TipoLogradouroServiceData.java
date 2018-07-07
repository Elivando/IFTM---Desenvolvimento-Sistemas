package com.TeethUp.serviceData;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.TeethUp.model.TipoLogradouro;

public class TipoLogradouroServiceData {

	@PersistenceContext(name="TeethUp-persistence-unit")
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<TipoLogradouro> getTipoLogradouros() {
		Query query = entityManager.createQuery("from TipoLogradouro p");
		return query.getResultList();
	}
	
	public TipoLogradouro getTipoLogradouroId(Integer id) {
		return entityManager.find(TipoLogradouro.class,id);
	}
	
	@Transactional
	public void excluirTipoLogradouro(Integer id) {
		TipoLogradouro e = getTipoLogradouroId(id);		
		entityManager.remove(e);
	}
	
	@Transactional
	public void inserirTipoLogradouro(TipoLogradouro tipoLogradouro) {
		entityManager.persist(tipoLogradouro);
	}
	
	@Transactional
	public void atualizarTipoLogradouro(TipoLogradouro tipoLogradouro) {
		tipoLogradouro = entityManager.merge(tipoLogradouro);
		entityManager.persist(tipoLogradouro);
	}
	
}

package com.TeethUp.serviceData;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.TeethUp.model.Estado;

public class EstadoServiceData {

	@PersistenceContext(name="TeethUp-persistence-unit")
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<Estado> getEstados() {
		Query query = entityManager.createQuery("from Estado p");
		return query.getResultList();
	}
	
	public Estado getEstadoId(Integer id) {
		return entityManager.find(Estado.class,id);
	}
	
	@Transactional
	public void excluirEstado(Integer id) {
		Estado e = getEstadoId(id);		
		entityManager.remove(e);
	}
	
	@Transactional
	public void inserirEstado(Estado estado) {
		entityManager.persist(estado);
	}
	
	@Transactional
	public void atualizarEstado(Estado estado) {
		estado = entityManager.merge(estado);
		entityManager.persist(estado);
	}
	
}

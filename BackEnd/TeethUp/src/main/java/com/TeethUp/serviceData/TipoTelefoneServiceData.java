package com.TeethUp.serviceData;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.TeethUp.model.TipoTelefone;

public class TipoTelefoneServiceData {

	@PersistenceContext(name="TeethUp-persistence-unit")
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<TipoTelefone> getTipoTelefones() {
		Query query = entityManager.createQuery("from TipoTelefone p");
		return query.getResultList();
	}
	
	public TipoTelefone getTipoTelefoneId(Integer id) {
		return entityManager.find(TipoTelefone.class,id);
	}
	
	@Transactional
	public void excluirTipoTelefone(Integer id) {
		TipoTelefone e = getTipoTelefoneId(id);		
		entityManager.remove(e);
	}
	
	@Transactional
	public void inserirTipoTelefone(TipoTelefone tipoTelefone) {
		entityManager.persist(tipoTelefone);
	}
	
	@Transactional
	public void atualizarTipoTelefone(TipoTelefone tipoTelefone) {
		tipoTelefone = entityManager.merge(tipoTelefone);
		entityManager.persist(tipoTelefone);
	}
	
}

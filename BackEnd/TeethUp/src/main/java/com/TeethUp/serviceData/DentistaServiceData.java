package com.TeethUp.serviceData;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.TeethUp.model.Dentista;

public class DentistaServiceData {

	@PersistenceContext(name="TeethUp-persistence-unit")
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<Dentista> getDentistas() {
		Query query = entityManager.createQuery("from Dentista p");
		return query.getResultList();
	}
	
	public Dentista getDentistaId(Integer id) {
		return entityManager.find(Dentista.class,id);
	}
	
	@Transactional
	public void excluirDentista(Integer id) {
		Dentista p = getDentistaId(id);		
		entityManager.remove(p);
	}
	
	@Transactional
	public void inserirDentista(Dentista dentista) {
		entityManager.persist(dentista);
	}
	
	@Transactional
	public void atualizarDentista(Dentista dentista) {
		dentista = entityManager.merge(dentista);
		entityManager.persist(dentista);
	}
	
}

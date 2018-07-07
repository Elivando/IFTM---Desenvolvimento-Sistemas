package com.TeethUp.serviceData;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.TeethUp.model.Clinica;

public class ClinicaServiceData {

	@PersistenceContext(name = "TeethUp-persistence-unit")
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Clinica> getClinicas() {
		Query query = entityManager.createQuery("from Clinica p");
		return query.getResultList();
	}

	public Clinica getClinicaId(Integer id) {
		return entityManager.find(Clinica.class, id);
	}

	@Transactional
	public void excluirClinica(Integer id) {
		Clinica c = getClinicaId(id);
		entityManager.remove(c);
	}

	@Transactional
	public void inserirClinica(Clinica clinica) {
		entityManager.persist(clinica);
	}

	@Transactional
	public void atualizarClinica(Clinica clinica) {
		clinica = entityManager.merge(clinica);
		entityManager.persist(clinica);
	}

}

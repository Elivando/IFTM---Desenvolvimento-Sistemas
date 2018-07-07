package com.TeethUp.serviceData;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.TeethUp.model.Paciente;

public class PacienteServiceData {

	@PersistenceContext(name = "TeethUp-persistence-unit")
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Paciente> getPacientes() {
		Query query = entityManager.createQuery("from Paciente p");
		return query.getResultList();
	}

	public Paciente getPacienteId(Integer id) {
		return entityManager.find(Paciente.class, id);
	}

	@Transactional
	public void excluirPaciente(Integer id) {
		Paciente c = getPacienteId(id);
		entityManager.remove(c);
	}

	@Transactional
	public void inserirPaciente(Paciente paciente) {
		entityManager.persist(paciente);
	}

	@Transactional
	public void atualizarPaciente(Paciente paciente) {
		paciente = entityManager.merge(paciente);
		entityManager.persist(paciente);
	}

}

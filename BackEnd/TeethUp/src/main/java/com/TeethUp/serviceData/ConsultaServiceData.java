package com.TeethUp.serviceData;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.TeethUp.model.Consulta;

public class ConsultaServiceData {

	@PersistenceContext(name = "TeethUp-persistence-unit")
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Consulta> getConsultas() {
		Query query = entityManager.createQuery("from Consulta p");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Consulta> getConsultas(Date dataConsulta) {
		Query query = entityManager.createQuery("from Consulta p where data_consulta =: dataConsulta", Consulta.class)
				.setParameter("dataConsulta", dataConsulta);
		return query.getResultList();
	}
	
	public Consulta getConsultaId(Integer id) {
		return entityManager.find(Consulta.class, id);
	}

	@Transactional
	public void excluirConsulta(Integer id) {
		Consulta p = getConsultaId(id);
		entityManager.remove(p);
	}

	@Transactional
	public void inserirConsulta(Consulta consulta) {
		entityManager.persist(consulta);
	}

	@Transactional
	public void atualizarConsulta(Consulta consulta) {
		consulta = entityManager.merge(consulta);
		entityManager.persist(consulta);
	}

}

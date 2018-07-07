package com.TeethUp.serviceData;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.TeethUp.model.Cidade;

public class CidadeServiceData {

	@PersistenceContext(name = "TeethUp-persistence-unit")
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Cidade> getCidades() {
		Query query = entityManager.createQuery("from Cidade p");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Cidade> getCidades(Integer idEstado) {
		Query query = entityManager.createQuery("from Cidade p where p.estado.id = :idEstado", Cidade.class)
				.setParameter("idEstado", idEstado);
		return query.getResultList();
	}

	public Cidade getCidadeId(Integer id) {
		return entityManager.find(Cidade.class, id);
	}

	@Transactional
	public void excluirCidade(Integer id) {
		Cidade c = getCidadeId(id);
		entityManager.remove(c);
	}

	@Transactional
	public void inserirCidade(Cidade cidade) {
		entityManager.persist(cidade);
	}

	@Transactional
	public void atualizarCidade(Cidade cidade) {
		cidade = entityManager.merge(cidade);
		entityManager.persist(cidade);
	}

}

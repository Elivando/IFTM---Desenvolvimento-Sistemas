package com.TeethUp.serviceData;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.TeethUp.model.Pessoa;

public class PessoaServiceData {

	@PersistenceContext(name="TeethUp-persistence-unit")
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<Pessoa> getPessoas() {
		Query query = entityManager.createQuery("from Pessoa p");
		return query.getResultList();
	}
	
	public Pessoa getPessoaId(Integer id) {
		return entityManager.find(Pessoa.class,id);
	}
	
	@Transactional
	public void excluirPessoa(Integer id) {
		Pessoa p = getPessoaId(id);		
		entityManager.remove(p);
	}
	
	@Transactional
	public void inserirPessoa(Pessoa pessoa) {
		entityManager.persist(pessoa);
	}
	
	@Transactional
	public void atualizarPessoa(Pessoa pessoa) {
		pessoa = entityManager.merge(pessoa);
		entityManager.persist(pessoa);
	}
	
}

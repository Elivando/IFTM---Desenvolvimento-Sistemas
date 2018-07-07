package com.TeethUp.serviceData;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.TeethUp.model.TipoEndereco;

public class TipoEnderecoServiceData {

	@PersistenceContext(name="TeethUp-persistence-unit")
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<TipoEndereco> getTipoEnderecos() {
		Query query = entityManager.createQuery("from TipoEndereco p");
		return query.getResultList();
	}
	
	public TipoEndereco getTipoEnderecoId(Integer id) {
		return entityManager.find(TipoEndereco.class,id);
	}
	
	@Transactional
	public void excluirTipoEndereco(Integer id) {
		TipoEndereco e = getTipoEnderecoId(id);		
		entityManager.remove(e);
	}
	
	@Transactional
	public void inserirTipoEndereco(TipoEndereco tipoEndereco) {
		entityManager.persist(tipoEndereco);
	}
	
	@Transactional
	public void atualizarTipoEndereco(TipoEndereco tipoEndereco) {
		tipoEndereco = entityManager.merge(tipoEndereco);
		entityManager.persist(tipoEndereco);
	}
	
}

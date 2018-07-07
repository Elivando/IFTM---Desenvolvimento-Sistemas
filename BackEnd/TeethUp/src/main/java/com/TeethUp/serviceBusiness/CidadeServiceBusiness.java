package com.TeethUp.serviceBusiness;

import java.util.List;
import javax.inject.Inject;

import com.TeethUp.model.Cidade;
import com.TeethUp.serviceData.CidadeServiceData;

public class CidadeServiceBusiness {

	@Inject
	private CidadeServiceData cidadeServiceData;

	public List<Cidade> getCidades() {
		List<Cidade> cidades = cidadeServiceData.getCidades();		
		return cidades;
	}
	
	public List<Cidade> getCidades(Integer idEstado) {
		List<Cidade> cidades = cidadeServiceData.getCidades(idEstado);		
		return cidades;
	}

	public Cidade getCidadeId(Integer id) {
		Cidade cidade = cidadeServiceData.getCidadeId(id);		
		return cidade;
	}

	public Cidade excluirCidade(Integer id) {
		Cidade cidade = getCidadeId(id);
		if (cidade == null) {
			return null;
		} else {
			cidadeServiceData.excluirCidade(id);
			return cidade;
		}
	}

	public void salvarCidade(Cidade cidade) {
		cidadeServiceData.inserirCidade(cidade);
	}

	public void atualizarCidade(Cidade cidade) {
		cidadeServiceData.atualizarCidade(cidade);
	}
}

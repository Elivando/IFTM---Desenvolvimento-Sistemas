package com.TeethUp.Facade.ws;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.TeethUp.model.Cidade;
import com.TeethUp.serviceBusiness.CidadeServiceBusiness;

@WebService(serviceName = "ws/cidade")
public class CidadeFacade {

	@Inject
	private CidadeServiceBusiness cidadeServiceBusiness;

	@WebMethod
	public List<Cidade> getCidades() {
		List<Cidade> cidades = cidadeServiceBusiness.getCidades();
		return cidades;
	}

	@WebMethod
	public Cidade getCidadeId(@WebParam(name = "codigoCidade") Integer id) {
		Cidade c = cidadeServiceBusiness.getCidadeId(id);
		return c;
	}

	@WebMethod
	public List<Cidade> getCidadeIdEstado(@WebParam(name = "codigoEstado") Integer codigoEstado) {
		List<Cidade> cidades = cidadeServiceBusiness.getCidades(codigoEstado);
		return cidades;
	}

	@WebMethod
	public void excluirCidade(@WebParam(name = "codigoCidade") Integer id) {
		cidadeServiceBusiness.excluirCidade(id);
	}

	@WebMethod
	public void atualizarCidade(@WebParam(name = "Cidade") Cidade cidade) {
		cidadeServiceBusiness.atualizarCidade(cidade);
	}

	@WebMethod
	public void salvarCidade(@WebParam(name = "Cidade") Cidade cidade) {
		cidadeServiceBusiness.salvarCidade(cidade);
	}

}

package com.TeethUp.Facade.ws;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.TeethUp.model.TipoEndereco;
import com.TeethUp.serviceBusiness.TipoEnderecoServiceBusiness;

@WebService(serviceName = "ws/tipoEndereco")
public class TipoEnderecoFacade {

	@Inject
	private TipoEnderecoServiceBusiness tipoEnderecoServiceBusiness;

	@WebMethod
	public List<TipoEndereco> getTipoEnderecos() {
		List<TipoEndereco> tipoEnderecos = tipoEnderecoServiceBusiness.getTipoEnderecos();
		return tipoEnderecos;
	}

	@WebMethod
	public TipoEndereco getTipoEnderecoId(@WebParam(name = "codigoTipoEndereco") Integer id) {
		TipoEndereco c = tipoEnderecoServiceBusiness.getTipoEnderecoId(id);
		return c;
	}
	
	@WebMethod
	public void excluirTipoEndereco(@WebParam(name = "codigoTipoEndereco") Integer id) {
		tipoEnderecoServiceBusiness.excluirTipoEndereco(id);
	}

	@WebMethod
	public void atualizarTipoEndereco(@WebParam(name = "TipoEndereco") TipoEndereco tipoEndereco) {
		tipoEnderecoServiceBusiness.atualizarTipoEndereco(tipoEndereco);
	}

	@WebMethod
	public void salvarTipoEndereco(@WebParam(name = "TipoEndereco") TipoEndereco tipoEndereco) {
		tipoEnderecoServiceBusiness.salvarTipoEndereco(tipoEndereco);
	}

}

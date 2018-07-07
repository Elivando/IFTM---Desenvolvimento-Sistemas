package com.TeethUp.Facade.ws;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.TeethUp.model.TipoLogradouro;
import com.TeethUp.serviceBusiness.TipoLogradouroServiceBusiness;

@WebService(serviceName = "ws/tipoLogradouro")
public class TipoLogradouroFacade {

	@Inject
	private TipoLogradouroServiceBusiness tipoLogradouroServiceBusiness;

	@WebMethod
	public List<TipoLogradouro> getTipoLogradouros() {
		List<TipoLogradouro> tipoLogradouros = tipoLogradouroServiceBusiness.getTipoLogradouros();
		return tipoLogradouros;
	}

	@WebMethod
	public TipoLogradouro getTipoLogradouroId(@WebParam(name = "codigoTipoLogradouro") Integer id) {
		TipoLogradouro c = tipoLogradouroServiceBusiness.getTipoLogradouroId(id);
		return c;
	}
	
	@WebMethod
	public void excluirTipoLogradouro(@WebParam(name = "codigoTipoLogradouro") Integer id) {
		tipoLogradouroServiceBusiness.excluirTipoLogradouro(id);
	}

	@WebMethod
	public void atualizarTipoLogradouro(@WebParam(name = "TipoLogradouro") TipoLogradouro tipoLogradouro) {
		tipoLogradouroServiceBusiness.atualizarTipoLogradouro(tipoLogradouro);
	}

	@WebMethod
	public void salvarTipoLogradouro(@WebParam(name = "TipoLogradouro") TipoLogradouro tipoLogradouro) {
		tipoLogradouroServiceBusiness.salvarTipoLogradouro(tipoLogradouro);
	}

}

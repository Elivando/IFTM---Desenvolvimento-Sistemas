package com.TeethUp.Facade.ws;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.TeethUp.model.TipoTelefone;
import com.TeethUp.serviceBusiness.TipoTelefoneServiceBusiness;

@WebService(serviceName = "ws/tipoTelefone")
public class TipoTelefoneFacade {

	@Inject
	private TipoTelefoneServiceBusiness tipoTelefoneServiceBusiness;

	@WebMethod
	public List<TipoTelefone> getTipoTelefones() {
		List<TipoTelefone> tipoTelefones = tipoTelefoneServiceBusiness.getTipoTelefones();
		return tipoTelefones;
	}

	@WebMethod
	public TipoTelefone getTipoTelefoneId(@WebParam(name = "codigoTipoTelefone") Integer id) {
		TipoTelefone c = tipoTelefoneServiceBusiness.getTipoTelefoneId(id);
		return c;
	}
	
	@WebMethod
	public void excluirTipoTelefone(@WebParam(name = "codigoTipoTelefone") Integer id) {
		tipoTelefoneServiceBusiness.excluirTipoTelefone(id);
	}

	@WebMethod
	public void atualizarTipoTelefone(@WebParam(name = "TipoTelefone") TipoTelefone tipoTelefone) {
		tipoTelefoneServiceBusiness.atualizarTipoTelefone(tipoTelefone);
	}

	@WebMethod
	public void salvarTipoTelefone(@WebParam(name = "TipoTelefone") TipoTelefone tipoTelefone) {
		tipoTelefoneServiceBusiness.salvarTipoTelefone(tipoTelefone);
	}

}

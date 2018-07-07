package com.TeethUp.Facade.ws;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

import com.TeethUp.model.Estado;
import com.TeethUp.serviceBusiness.EstadoServiceBusiness;

@WebService(serviceName = "ws/estado")
public class EstadoFacade {

	@Inject
	private EstadoServiceBusiness estadoServiceBusiness;

	@WebMethod
	public List<Estado> getEstados() {
		List<Estado> Estados = estadoServiceBusiness.getEstados();
		return Estados;
	}
}

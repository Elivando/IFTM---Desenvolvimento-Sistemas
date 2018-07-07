package com.TeethUp.Facade.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.TeethUp.model.Estado;
import com.TeethUp.serviceBusiness.EstadoServiceBusiness;

@Path(value = "/estado")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class EstadoFacade {

	@Inject
	private EstadoServiceBusiness estadoServiceBusiness;
	
	@GET
	@Produces("application/json")
	public List<Estado> listAll() {
		List<Estado> results = estadoServiceBusiness.getEstados();
		return results;
	}
	
}

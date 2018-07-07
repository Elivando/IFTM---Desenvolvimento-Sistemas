package com.TeethUp.Facade.rest;

import javax.ws.rs.core.MediaType;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.TeethUp.model.TipoLogradouro;
import com.TeethUp.serviceBusiness.TipoLogradouroServiceBusiness;

import javax.ws.rs.core.UriBuilder;

@Path(value = "/tipoLogradouro")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class TipoLogradouroFacade {

	@Inject
	private TipoLogradouroServiceBusiness tipoLogradouroServiceBusiness;

	@POST
	@Consumes("application/json")
	public Response create(TipoLogradouro tipoLogradouro) {
		tipoLogradouroServiceBusiness.salvarTipoLogradouro(tipoLogradouro);
		return Response
				.created(UriBuilder.fromResource(TipoLogradouroFacade.class).path(String.valueOf(tipoLogradouro.getId())).build())
				.build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") Integer id) {
		if (tipoLogradouroServiceBusiness.excluirTipoLogradouro(id) == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.noContent().build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces("application/json")
	public Response findById(@PathParam("id") Integer id) {
		TipoLogradouro tipoLogradouro = tipoLogradouroServiceBusiness.getTipoLogradouroId(id);
		if (tipoLogradouro == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(tipoLogradouro).build();
	}

	@GET
	@Produces("application/json")
	public List<TipoLogradouro> listAll() {
		List<TipoLogradouro> results = tipoLogradouroServiceBusiness.getTipoLogradouros();
		return results;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Consumes("application/json")
	public Response update(@PathParam("id") Integer id, TipoLogradouro tipoLogradouro) {
		if (tipoLogradouro == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (id == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (!id.equals(tipoLogradouro.getId())) {
			return Response.status(Status.CONFLICT).entity(tipoLogradouro).build();
		}
		if (tipoLogradouroServiceBusiness.getTipoLogradouroId(id) == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		try {
			tipoLogradouroServiceBusiness.atualizarTipoLogradouro(tipoLogradouro);
		} catch (OptimisticLockException e) {
			return Response.status(Response.Status.CONFLICT).entity(e.getEntity()).build();
		}

		return Response.noContent().build();
	}
}

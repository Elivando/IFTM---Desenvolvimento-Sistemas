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

import com.TeethUp.model.TipoTelefone;
import com.TeethUp.serviceBusiness.TipoTelefoneServiceBusiness;

import javax.ws.rs.core.UriBuilder;

@Path(value = "/tipoTelefone")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class TipoTelefoneFacade {

	@Inject
	private TipoTelefoneServiceBusiness tipoTelefoneServiceBusiness;

	@POST
	@Consumes("application/json")
	public Response create(TipoTelefone tipoTelefone) {
		tipoTelefoneServiceBusiness.salvarTipoTelefone(tipoTelefone);
		return Response
				.created(UriBuilder.fromResource(TipoTelefoneFacade.class).path(String.valueOf(tipoTelefone.getId())).build())
				.build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") Integer id) {
		if (tipoTelefoneServiceBusiness.excluirTipoTelefone(id) == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.noContent().build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces("application/json")
	public Response findById(@PathParam("id") Integer id) {
		TipoTelefone tipoTelefone = tipoTelefoneServiceBusiness.getTipoTelefoneId(id);
		if (tipoTelefone == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(tipoTelefone).build();
	}

	@GET
	@Produces("application/json")
	public List<TipoTelefone> listAll() {
		List<TipoTelefone> results = tipoTelefoneServiceBusiness.getTipoTelefones();
		return results;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Consumes("application/json")
	public Response update(@PathParam("id") Integer id, TipoTelefone tipoTelefone) {
		if (tipoTelefone == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (id == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (!id.equals(tipoTelefone.getId())) {
			return Response.status(Status.CONFLICT).entity(tipoTelefone).build();
		}
		if (tipoTelefoneServiceBusiness.getTipoTelefoneId(id) == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		try {
			tipoTelefoneServiceBusiness.atualizarTipoTelefone(tipoTelefone);
		} catch (OptimisticLockException e) {
			return Response.status(Response.Status.CONFLICT).entity(e.getEntity()).build();
		}

		return Response.noContent().build();
	}
}

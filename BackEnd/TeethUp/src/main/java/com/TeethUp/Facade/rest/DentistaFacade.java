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

import com.TeethUp.model.Dentista;
import com.TeethUp.serviceBusiness.DentistaServiceBusiness;

import javax.ws.rs.core.UriBuilder;

@Path(value = "/dentista")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class DentistaFacade {

	@Inject
	private DentistaServiceBusiness dentistaServiceBusiness;

	@POST
	@Consumes("application/json")
	public Response create(Dentista dentista) {
		dentistaServiceBusiness.salvarDentista(dentista);
		return Response
				.created(UriBuilder.fromResource(DentistaFacade.class).path(String.valueOf(dentista.getId())).build())
				.build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") Integer id) {
		if (dentistaServiceBusiness.excluirDentista(id) == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.noContent().build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces("application/json")
	public Response findById(@PathParam("id") Integer id) {
		Dentista dentista = dentistaServiceBusiness.getDentistaId(id);
		if (dentista == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(dentista).build();
	}

	@GET
	@Produces("application/json")
	public List<Dentista> listAll() {
		List<Dentista> results = dentistaServiceBusiness.getDentistas();
		return results;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Consumes("application/json")
	public Response update(@PathParam("id") Integer id, Dentista dentista) {
		if (dentista == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (id == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (!id.equals(dentista.getId())) {
			return Response.status(Status.CONFLICT).entity(dentista).build();
		}
		if (dentistaServiceBusiness.getDentistaId(id) == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		try {
			dentistaServiceBusiness.atualizarDentista(dentista);
		} catch (OptimisticLockException e) {
			return Response.status(Response.Status.CONFLICT).entity(e.getEntity()).build();
		}

		return Response.noContent().build();
	}
}

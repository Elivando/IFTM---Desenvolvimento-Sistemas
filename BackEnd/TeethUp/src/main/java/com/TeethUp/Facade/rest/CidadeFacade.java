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

import com.TeethUp.model.Cidade;
import com.TeethUp.serviceBusiness.CidadeServiceBusiness;

import javax.ws.rs.core.UriBuilder;

@Path(value = "/cidade")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class CidadeFacade {

	@Inject
	private CidadeServiceBusiness cidadeServiceBusiness;

	@POST
	@Consumes("application/json")
	public Response create(Cidade cidade) {
		cidadeServiceBusiness.salvarCidade(cidade);
		return Response
				.created(UriBuilder.fromResource(CidadeFacade.class).path(String.valueOf(cidade.getId())).build())
				.build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") Integer id) {
		if (cidadeServiceBusiness.excluirCidade(id) == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.noContent().build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces("application/json")
	public Response findById(@PathParam("id") Integer id) {
		Cidade cidade = cidadeServiceBusiness.getCidadeId(id);
		if (cidade == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(cidade).build();
	}

	@GET
	@Path("/findByIdEstado/{id:[0-9][0-9]*}")
	@Produces("application/json")
	public List<Cidade> findByIdEstado(@PathParam("id") Integer idEstado) {
		List<Cidade> results = cidadeServiceBusiness.getCidades(idEstado);
		return results;
	}

	@GET
	@Produces("application/json")
	public List<Cidade> listAll() {
		List<Cidade> results = cidadeServiceBusiness.getCidades();
		return results;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Consumes("application/json")
	public Response update(@PathParam("id") Integer id, Cidade cidade) {
		if (cidade == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (id == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (!id.equals(cidade.getId())) {
			return Response.status(Status.CONFLICT).entity(cidade).build();
		}
		if (cidadeServiceBusiness.getCidadeId(id) == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		try {
			cidadeServiceBusiness.atualizarCidade(cidade);
		} catch (OptimisticLockException e) {
			return Response.status(Response.Status.CONFLICT).entity(e.getEntity()).build();
		}

		return Response.noContent().build();
	}
}

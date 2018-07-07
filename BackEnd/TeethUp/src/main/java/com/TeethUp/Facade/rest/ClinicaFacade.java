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

import com.TeethUp.model.Clinica;
import com.TeethUp.serviceBusiness.ClinicaServiceBusiness;

import javax.ws.rs.core.UriBuilder;

@Path(value = "/clinica")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class ClinicaFacade {

	@Inject
	private ClinicaServiceBusiness clinicaServiceBusiness;

	@POST
	@Consumes("application/json")
	public Response create(Clinica clinica) {
		clinicaServiceBusiness.salvarClinica(clinica);
		return Response
				.created(UriBuilder.fromResource(ClinicaFacade.class).path(String.valueOf(clinica.getId())).build())
				.build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") Integer id) {
		if (clinicaServiceBusiness.excluirClinica(id) == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.noContent().build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces("application/json")
	public Response findById(@PathParam("id") Integer id) {
		Clinica Clinica = clinicaServiceBusiness.getClinicaId(id);
		if (Clinica == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(Clinica).build();
	}

	@GET
	@Produces("application/json")
	public List<Clinica> listAll() {
		List<Clinica> results = clinicaServiceBusiness.getClinicas();
		return results;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Consumes("application/json")
	public Response update(@PathParam("id") Integer id, Clinica clinica) {
		if (clinica == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (id == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (!id.equals(clinica.getId())) {
			return Response.status(Status.CONFLICT).entity(clinica).build();
		}
		if (clinicaServiceBusiness.getClinicaId(id) == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		try {
			clinicaServiceBusiness.atualizarClinica(clinica);
		} catch (OptimisticLockException e) {
			return Response.status(Response.Status.CONFLICT).entity(e.getEntity()).build();
		}

		return Response.noContent().build();
	}
}

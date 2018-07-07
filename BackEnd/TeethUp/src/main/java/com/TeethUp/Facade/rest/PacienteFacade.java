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

import com.TeethUp.model.Paciente;
import com.TeethUp.serviceBusiness.PacienteServiceBusiness;

import javax.ws.rs.core.UriBuilder;

@Path(value = "/paciente")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class PacienteFacade {

	@Inject
	private PacienteServiceBusiness pacienteServiceBusiness;

	@POST
	@Consumes("application/json")
	public Response create(Paciente paciente) {
		pacienteServiceBusiness.salvarPaciente(paciente);
		return Response
				.created(UriBuilder.fromResource(PacienteFacade.class).path(String.valueOf(paciente.getId())).build())
				.build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") Integer id) {
		if (pacienteServiceBusiness.excluirPaciente(id) == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.noContent().build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces("application/json")
	public Response findById(@PathParam("id") Integer id) {
		Paciente paciente = pacienteServiceBusiness.getPacienteId(id);
		if (paciente == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(paciente).build();
	}

	@GET
	@Produces("application/json")
	public List<Paciente> listAll() {
		List<Paciente> results = pacienteServiceBusiness.getPacientes();
		return results;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Consumes("application/json")
	public Response update(@PathParam("id") Integer id, Paciente paciente) {
		if (paciente == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (id == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (!id.equals(paciente.getId())) {
			return Response.status(Status.CONFLICT).entity(paciente).build();
		}
		if (pacienteServiceBusiness.getPacienteId(id) == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		try {
			pacienteServiceBusiness.atualizarPaciente(paciente);
		} catch (OptimisticLockException e) {
			return Response.status(Response.Status.CONFLICT).entity(e.getEntity()).build();
		}

		return Response.noContent().build();
	}
}

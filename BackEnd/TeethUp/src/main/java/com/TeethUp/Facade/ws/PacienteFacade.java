package com.TeethUp.Facade.ws;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.TeethUp.model.Paciente;
import com.TeethUp.serviceBusiness.PacienteServiceBusiness;

@WebService(serviceName = "ws/paciente")
public class PacienteFacade {

	@Inject
	private PacienteServiceBusiness pacienteServiceBusiness;

	@WebMethod
	public List<Paciente> getPacientes() {
		List<Paciente> pacientes = pacienteServiceBusiness.getPacientes();
		return pacientes;
	}

	@WebMethod
	public Paciente getPacienteId(@WebParam(name = "codigoPaciente") Integer id) {
		Paciente c = pacienteServiceBusiness.getPacienteId(id);
		return c;
	}
	
	@WebMethod
	public void excluirPaciente(@WebParam(name = "codigoPaciente") Integer id) {
		pacienteServiceBusiness.excluirPaciente(id);
	}

	@WebMethod
	public void atualizarPaciente(@WebParam(name = "Paciente") Paciente paciente) {
		pacienteServiceBusiness.atualizarPaciente(paciente);
	}

	@WebMethod
	public void salvarPaciente(@WebParam(name = "Paciente") Paciente paciente) {
		pacienteServiceBusiness.salvarPaciente(paciente);
	}

}

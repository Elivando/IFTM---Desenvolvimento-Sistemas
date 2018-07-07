package com.TeethUp.Facade.ws;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.TeethUp.model.Clinica;
import com.TeethUp.serviceBusiness.ClinicaServiceBusiness;

@WebService(serviceName = "ws/clinica")
public class ClinicaFacade {

	@Inject
	private ClinicaServiceBusiness ClinicaServiceBusiness;

	@WebMethod
	public List<Clinica> getClinicas() {
		List<Clinica> clinicas = ClinicaServiceBusiness.getClinicas();
		return clinicas;
	}

	@WebMethod
	public Clinica getClinicaId(@WebParam(name = "codigoClinica") Integer id) {
		Clinica c = ClinicaServiceBusiness.getClinicaId(id);
		return c;
	}
	
	@WebMethod
	public void excluirClinica(@WebParam(name = "codigoClinica") Integer id) {
		ClinicaServiceBusiness.excluirClinica(id);
	}

	@WebMethod
	public void atualizarClinica(@WebParam(name = "Clinica") Clinica clinica) {
		ClinicaServiceBusiness.atualizarClinica(clinica);
	}

	@WebMethod
	public void salvarClinica(@WebParam(name = "Clinica") Clinica clinica) {
		ClinicaServiceBusiness.salvarClinica(clinica);
	}

}

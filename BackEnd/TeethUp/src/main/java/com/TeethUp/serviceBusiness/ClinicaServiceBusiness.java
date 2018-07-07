package com.TeethUp.serviceBusiness;

import java.util.Date;
import java.util.List;
import javax.inject.Inject;

import com.TeethUp.model.Clinica;
import com.TeethUp.serviceData.ClinicaServiceData;

public class ClinicaServiceBusiness {

	@Inject
	private ClinicaServiceData ClinicaServiceData;

	public List<Clinica> getClinicas() {
		List<Clinica> clinicas = ClinicaServiceData.getClinicas();		
		return clinicas;
	}
		
	public Clinica getClinicaId(Integer id) {
		Clinica clinica = ClinicaServiceData.getClinicaId(id);		
		return clinica;
	}

	public Clinica excluirClinica(Integer id) {
		Clinica clinica = getClinicaId(id);
		if (clinica == null) {
			return null;
		} else {
			ClinicaServiceData.excluirClinica(id);
			return clinica;
		}
	}

	public void salvarClinica(Clinica clinica) {
		clinica.setDataCadastro(new Date());
		ClinicaServiceData.inserirClinica(clinica);
	}

	public void atualizarClinica(Clinica clinica) {
		ClinicaServiceData.atualizarClinica(clinica);
	}
}

package com.TeethUp.serviceBusiness;

import java.util.Date;
import java.util.List;
import javax.inject.Inject;

import com.TeethUp.model.Paciente;
import com.TeethUp.serviceData.PacienteServiceData;

public class PacienteServiceBusiness {

	@Inject
	private PacienteServiceData pacienteServiceData;

	public List<Paciente> getPacientes() {
		List<Paciente> pacientes = pacienteServiceData.getPacientes();		
		return pacientes;
	}
		
	public Paciente getPacienteId(Integer id) {
		Paciente Paciente = pacienteServiceData.getPacienteId(id);		
		return Paciente;
	}

	public Paciente excluirPaciente(Integer id) {
		Paciente paciente = getPacienteId(id);
		if (paciente == null) {
			return null;
		} else {
			pacienteServiceData.excluirPaciente(id);
			return paciente;
		}
	}

	public void salvarPaciente(Paciente paciente) {
		paciente.setDataCadastro(new Date());
		pacienteServiceData.inserirPaciente(paciente);
	}

	public void atualizarPaciente(Paciente paciente) {
		pacienteServiceData.atualizarPaciente(paciente);
	}
}

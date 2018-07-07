package com.TeethUp.serviceBusiness;

import java.util.Date;
import java.util.List;
import javax.inject.Inject;

import com.TeethUp.model.Consulta;
import com.TeethUp.serviceData.ConsultaServiceData;

public class ConsultaServiceBusiness {

	@Inject
	private ConsultaServiceData consultaServiceData;

	public List<Consulta> getConsultas() {
		List<Consulta> consultas = consultaServiceData.getConsultas();
		return consultas;
	}

	public Consulta getConsultaId(Integer id) {
		Consulta consulta = consultaServiceData.getConsultaId(id);
		return consulta;
	}

	public Consulta excluirConsulta(Integer id) {
		Consulta consulta = getConsultaId(id);
		if (consulta == null) {
			return null;
		} else {
			consultaServiceData.excluirConsulta(id);
			return consulta;
		}
	}

	public void salvarConsulta(Consulta consulta) {
		consulta.setDataCadastro(new Date());
		consultaServiceData.inserirConsulta(consulta);
	}

	public void atualizarConsulta(Consulta consulta) {
		consultaServiceData.atualizarConsulta(consulta);
	}
}

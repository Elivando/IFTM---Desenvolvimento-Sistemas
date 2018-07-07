package com.TeethUp.Facade.ws;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.TeethUp.model.Consulta;
import com.TeethUp.serviceBusiness.ConsultaServiceBusiness;

@WebService(serviceName = "ws/consulta")
public class ConsultaFacade {
	
	@Inject
	private ConsultaServiceBusiness ConsultaServiceBusiness;

	@WebMethod
	public List<Consulta> getConsultas() {
		List<Consulta> consultas = ConsultaServiceBusiness.getConsultas();		
		return consultas;
	}

	@WebMethod
	public Consulta getConsultaId(@WebParam(name = "codigoConsulta") Integer id) {
		return ConsultaServiceBusiness.getConsultaId(id);
	}

	@WebMethod
	public void excluirConsulta(@WebParam(name = "codigoConsulta") Integer id) {
		ConsultaServiceBusiness.excluirConsulta(id);
	}

	@WebMethod
	public void atualizarConsulta(@WebParam(name = "Consulta") Consulta consulta) {
		ConsultaServiceBusiness.atualizarConsulta(consulta);
	}

	@WebMethod
	public void salvarConsulta(@WebParam(name = "Consulta") Consulta consulta) {
		ConsultaServiceBusiness.salvarConsulta(consulta);
	}

}

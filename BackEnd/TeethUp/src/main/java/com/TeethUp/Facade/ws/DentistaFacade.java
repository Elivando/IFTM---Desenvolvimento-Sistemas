package com.TeethUp.Facade.ws;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.TeethUp.model.Dentista;
import com.TeethUp.serviceBusiness.DentistaServiceBusiness;

@WebService(serviceName = "ws/dentista")
public class DentistaFacade {
	
	@Inject
	private DentistaServiceBusiness dentistaServiceBusiness;

	@WebMethod
	public List<Dentista> getDentistas() {
		List<Dentista> dentistas = dentistaServiceBusiness.getDentistas();		
		return dentistas;
	}

	@WebMethod
	public Dentista getDentistaId(@WebParam(name = "codigoDentista") Integer id) {
		return dentistaServiceBusiness.getDentistaId(id);		
	}

	@WebMethod
	public void excluirDentista(@WebParam(name = "codigoDentista") Integer id) {
		dentistaServiceBusiness.excluirDentista(id);
	}

	@WebMethod
	public void atualizarDentista(@WebParam(name = "Dentista") Dentista dentista) {
		dentistaServiceBusiness.atualizarDentista(dentista);
	}

	@WebMethod
	public void salvarDentista(@WebParam(name = "Dentista") Dentista dentista) {
		dentistaServiceBusiness.salvarDentista(dentista);
	}

}

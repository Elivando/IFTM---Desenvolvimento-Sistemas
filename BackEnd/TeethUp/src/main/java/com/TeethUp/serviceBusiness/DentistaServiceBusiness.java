package com.TeethUp.serviceBusiness;

import java.util.Date;
import java.util.List;
import javax.inject.Inject;

import com.TeethUp.model.Dentista;
import com.TeethUp.serviceData.DentistaServiceData;

public class DentistaServiceBusiness {

	@Inject
	private DentistaServiceData dentistaServiceData;

	public List<Dentista> getDentistas() {
		List<Dentista> Dentistas = dentistaServiceData.getDentistas();		
		return Dentistas;
	}

	public Dentista getDentistaId(Integer id) {
		Dentista dentista = dentistaServiceData.getDentistaId(id);	
		return dentista;
	}

	public Dentista excluirDentista(Integer id) {
		Dentista Dentista = getDentistaId(id);
		if (Dentista == null) {
			return null;
		} else {
			dentistaServiceData.excluirDentista(id);
			return Dentista;
		}
	}

	public void salvarDentista(Dentista dentista) {
		dentista.setDataCadastro(new Date());
		dentistaServiceData.inserirDentista(dentista);
	}

	public void atualizarDentista(Dentista dentista) {
		dentistaServiceData.atualizarDentista(dentista);
	}
}

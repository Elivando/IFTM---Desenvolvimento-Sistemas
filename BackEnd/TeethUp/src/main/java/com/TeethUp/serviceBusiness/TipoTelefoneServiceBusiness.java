package com.TeethUp.serviceBusiness;

import java.util.List;
import javax.inject.Inject;

import com.TeethUp.model.TipoTelefone;
import com.TeethUp.serviceData.TipoTelefoneServiceData;

public class TipoTelefoneServiceBusiness {

	@Inject
	private TipoTelefoneServiceData tipoTelefoneServiceData;

	public List<TipoTelefone> getTipoTelefones() {
		List<TipoTelefone> tipoTelefones = tipoTelefoneServiceData.getTipoTelefones();		
		return tipoTelefones;
	}

	public TipoTelefone getTipoTelefoneId(Integer id) {
		TipoTelefone tipoTelefone = tipoTelefoneServiceData.getTipoTelefoneId(id);	
		return tipoTelefone;
	}

	public TipoTelefone excluirTipoTelefone(Integer id) {
		TipoTelefone TipoTelefone = getTipoTelefoneId(id);
		if (TipoTelefone == null) {
			return null;
		} else {
			tipoTelefoneServiceData.excluirTipoTelefone(id);
			return TipoTelefone;
		}
	}

	public void salvarTipoTelefone(TipoTelefone tipoTelefone) {
		tipoTelefoneServiceData.inserirTipoTelefone(tipoTelefone);
	}

	public void atualizarTipoTelefone(TipoTelefone tipoTelefone) {
		tipoTelefoneServiceData.atualizarTipoTelefone(tipoTelefone);
	}
}

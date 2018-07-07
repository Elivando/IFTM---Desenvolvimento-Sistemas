package com.TeethUp.serviceBusiness;

import java.util.List;
import javax.inject.Inject;

import com.TeethUp.model.TipoLogradouro;
import com.TeethUp.serviceData.TipoLogradouroServiceData;

public class TipoLogradouroServiceBusiness {

	@Inject
	private TipoLogradouroServiceData tipoLogradouroServiceData;

	public List<TipoLogradouro> getTipoLogradouros() {
		List<TipoLogradouro> tipoLogradouros = tipoLogradouroServiceData.getTipoLogradouros();		
		return tipoLogradouros;
	}

	public TipoLogradouro getTipoLogradouroId(Integer id) {
		TipoLogradouro tipoLogradouro = tipoLogradouroServiceData.getTipoLogradouroId(id);	
		return tipoLogradouro;
	}

	public TipoLogradouro excluirTipoLogradouro(Integer id) {
		TipoLogradouro tipoLogradouro = getTipoLogradouroId(id);
		if (tipoLogradouro == null) {
			return null;
		} else {
			tipoLogradouroServiceData.excluirTipoLogradouro(id);
			return tipoLogradouro;
		}
	}

	public void salvarTipoLogradouro(TipoLogradouro tipoLogradouro) {
		tipoLogradouroServiceData.inserirTipoLogradouro(tipoLogradouro);
	}

	public void atualizarTipoLogradouro(TipoLogradouro tipoLogradouro) {
		tipoLogradouroServiceData.atualizarTipoLogradouro(tipoLogradouro);
	}
}

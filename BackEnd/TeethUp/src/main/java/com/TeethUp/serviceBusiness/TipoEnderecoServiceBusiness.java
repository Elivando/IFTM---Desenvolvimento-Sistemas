package com.TeethUp.serviceBusiness;

import java.util.List;
import javax.inject.Inject;

import com.TeethUp.model.TipoEndereco;
import com.TeethUp.serviceData.TipoEnderecoServiceData;

public class TipoEnderecoServiceBusiness {

	@Inject
	private TipoEnderecoServiceData tipoEnderecoServiceData;

	public List<TipoEndereco> getTipoEnderecos() {
		List<TipoEndereco> tipoEnderecos = tipoEnderecoServiceData.getTipoEnderecos();		
		return tipoEnderecos;
	}

	public TipoEndereco getTipoEnderecoId(Integer id) {
		TipoEndereco tipoEndereco = tipoEnderecoServiceData.getTipoEnderecoId(id);	
		return tipoEndereco;
	}

	public TipoEndereco excluirTipoEndereco(Integer id) {
		TipoEndereco tipoEndereco = getTipoEnderecoId(id);
		if (tipoEndereco == null) {
			return null;
		} else {
			tipoEnderecoServiceData.excluirTipoEndereco(id);
			return tipoEndereco;
		}
	}

	public void salvarTipoEndereco(TipoEndereco tipoEndereco) {
		tipoEnderecoServiceData.inserirTipoEndereco(tipoEndereco);
	}

	public void atualizarTipoEndereco(TipoEndereco tipoEndereco) {
		tipoEnderecoServiceData.atualizarTipoEndereco(tipoEndereco);
	}
}

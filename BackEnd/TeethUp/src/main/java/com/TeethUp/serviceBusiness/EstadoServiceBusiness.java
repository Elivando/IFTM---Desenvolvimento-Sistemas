package com.TeethUp.serviceBusiness;

import java.util.List;
import javax.inject.Inject;

import com.TeethUp.model.Estado;
import com.TeethUp.serviceData.EstadoServiceData;

public class EstadoServiceBusiness {

	@Inject
	private EstadoServiceData estadoServiceData;

	public List<Estado> getEstados() {
		List<Estado> estados = estadoServiceData.getEstados();		
		return estados;
	}

	public Estado getEstadoId(Integer id) {
		Estado estado = estadoServiceData.getEstadoId(id);	
		return estado;
	}

	public Estado excluirEstado(Integer id) {
		Estado estado = getEstadoId(id);
		if (estado == null) {
			return null;
		} else {
			estadoServiceData.excluirEstado(id);
			return estado;
		}
	}

	public void salvarEstado(Estado estado) {
		estadoServiceData.inserirEstado(estado);
	}

	public void atualizarEstado(Estado estado) {
		estadoServiceData.atualizarEstado(estado);
	}
}

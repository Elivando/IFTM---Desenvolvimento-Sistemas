package com.TeethUp.serviceBusiness;

import java.util.Date;
import java.util.List;
import javax.inject.Inject;

import com.TeethUp.model.Pessoa;
import com.TeethUp.serviceData.PessoaServiceData;

public class PessoaServiceBusiness {

	@Inject
	private PessoaServiceData pessoaServiceData;

	public List<Pessoa> getPessoas() {
		List<Pessoa> pessoas = pessoaServiceData.getPessoas();
		for (Pessoa p : pessoas) {
			p.setEnderecos(null);
			p.setTelefones(null);
		}
		return pessoas;
	}

	public Pessoa getPessoaId(Integer id) {
		Pessoa pessoa = pessoaServiceData.getPessoaId(id);
		pessoa.setEnderecos(null);
		pessoa.setTelefones(null);
		return pessoa;
	}

	public Pessoa excluirPessoa(Integer id) {
		Pessoa pessoa = getPessoaId(id);
		if (pessoa == null) {
			return null;
		} else {
			pessoaServiceData.excluirPessoa(id);
			return pessoa;
		}
	}

	public void salvarPessoa(Pessoa pessoa) {
		pessoa.setDataCadastro(new Date());
		pessoaServiceData.inserirPessoa(pessoa);
	}

	public void atualizarPessoa(Pessoa pessoa) {
		pessoaServiceData.atualizarPessoa(pessoa);
	}
}

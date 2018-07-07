package com.TeethUp.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

@Entity
@Table(name="ENDERECO")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_TIPOENDERECO",referencedColumnName="ID")
	private TipoEndereco tipoEndereco;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_TIPO_LOGRADOURO",referencedColumnName="ID")
	private TipoLogradouro tipoLogradouro;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_CIDADE",referencedColumnName="ID")
	private Cidade cidade;
	
	@Column(name="LOGRADOURO",nullable=false,length=500)
	private String logradouro;
	
	@Column(name="NUMERO",nullable=false,length=50)
	private String numero;
	
	@Column(name="COMPELENTO",nullable=false,length=500)
	private String compelento;
	
	@Column(name="CEP",nullable=false,length=10)
	private String cep;
	
	@Column(name="ATIVO",nullable=false)
	private Boolean ativo;
		
	public TipoEndereco getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(TipoEndereco tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

	public TipoLogradouro getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCompelento() {
		return compelento;
	}

	public void setCompelento(String compelento) {
		this.compelento = compelento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (id != null)
			result += "id: " + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Endereco)) {
			return false;
		}
		Endereco other = (Endereco) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
}
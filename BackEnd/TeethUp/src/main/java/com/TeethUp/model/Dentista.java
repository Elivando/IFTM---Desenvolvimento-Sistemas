package com.TeethUp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "DENTISTA")
@PrimaryKeyJoinColumn(name="ID")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Dentista extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
		
	@Column(name = "CPF", nullable = false, length = 14)
	private String cpf;
	
	@Column(name = "CRO", nullable = false, length = 250)
	private String cro;
	
	@Column(name="ATIVO",nullable=false)
	private Boolean ativo;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="CLINICA_DENTISTA",
    		   joinColumns= @JoinColumn(name="ID_DENTISTA"),
    		   inverseJoinColumns = @JoinColumn(name="ID_CLINICA"))
	private List<Clinica> clinicas;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCro() {
		return cro;
	}

	public void setCro(String cro) {
		this.cro = cro;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public List<Clinica> getClinicas() {
		return clinicas;
	}

	public void setClinicas(List<Clinica> clinicas) {
		this.clinicas = clinicas;
	}

}
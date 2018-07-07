package com.TeethUp.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import java.io.Serializable;
import java.util.List;

import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;

@Entity
@Table(name = "CLINICA")
@PrimaryKeyJoinColumn(name="ID")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Clinica extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "CNPJ", nullable = false, length = 18)
	private String cnpj;
	
	@Column(name = "RAZAO_SOCIAL", nullable = false, length = 250)
	private String razaoSocial;
	
	@Column(name="ATIVO",nullable=false)
	private Boolean ativo;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="CLINICA_MEDICO",
    		   joinColumns= @JoinColumn(name="ID_CLINICA"),
    		   inverseJoinColumns = @JoinColumn(name="ID_MEDICO"))
	private List<Dentista> medicos;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public List<Dentista> getMedicos() {
		return medicos;
	}

	public void setMedicos(List<Dentista> medicos) {
		this.medicos = medicos;
	}
	
}
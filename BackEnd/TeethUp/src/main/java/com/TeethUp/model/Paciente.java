package com.TeethUp.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;

@Entity
@Table(name = "PACIENTE")
@PrimaryKeyJoinColumn(name="ID")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Paciente extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
		
	@Column(name = "CPF", nullable = false, length = 14)
	private String cpf;
	
	@Column(name = "EMAIL", nullable = false, length = 250)
	private String email;
	
	@Column(name = "USUARIO", nullable = false, length = 250)
	private String usuario;
	
	@Column(name = "SENHA", nullable = false, length = 250)
	private String senha;
	
	@Column(name="ATIVO",nullable=false)
	private Boolean ativo;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
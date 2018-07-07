package com.TeethUp.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

@Entity
@Table(name = "CONSULTA")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Consulta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_CLINICA",referencedColumnName="ID")
	private Clinica clinica;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_DENTISTA",referencedColumnName="ID")
	private Dentista dentista;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_PACIENTE",referencedColumnName="ID")
	private Paciente paciente;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_CADASTRO")
	private Date dataCadastro;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATA_CONSULTA")
	private Date dataConsulta;
	
	@Temporal(TemporalType.TIME)
	@Column(name="HORA")
	private Date hora;
	
	@Column(name="ATIVO",nullable=false)
	private Boolean ativo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Clinica getClinica() {
		return clinica;
	}

	public void setClinica(Clinica clinica) {
		this.clinica = clinica;
	}

	public Dentista getDentista() {
		return dentista;
	}

	public void setDentista(Dentista dentista) {
		this.dentista = dentista;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
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
		if (!(obj instanceof Consulta)) {
			return false;
		}
		Consulta other = (Consulta) obj;
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
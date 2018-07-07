package com.TeethUp.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;


@Entity
@Table(name = "CIDADE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id	
	@Column(name = "ID")
	private Integer id;

	@Column(name = "NOME", nullable = false, length = 250)
	private String nome;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ESTADO", referencedColumnName = "ID")
	private Estado estado;

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
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
		if (!(obj instanceof Cidade)) {
			return false;
		}
		Cidade other = (Cidade) obj;
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
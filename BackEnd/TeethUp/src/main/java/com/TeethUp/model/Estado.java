package com.TeethUp.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;

@Entity
@Table(name = "ESTADO")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Estado implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id	
	@Column(name = "ID")
	private Integer id;

	@Column(name = "NOME", nullable = false, length = 250)
	private String nome;

	@Column(name = "UF", nullable = false, length = 2)
	private String uf;

	@Column(name = "REGIAO", nullable = false, length = 250)
	private String regiao;

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

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getRegiao() {
		return regiao;
	}

	public void setRegiao(String regiao) {
		this.regiao = regiao;
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
		if (!(obj instanceof Estado)) {
			return false;
		}
		Estado other = (Estado) obj;
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
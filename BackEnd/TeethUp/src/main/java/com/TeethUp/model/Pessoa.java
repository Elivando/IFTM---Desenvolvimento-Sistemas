package com.TeethUp.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.JoinColumn;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="PESSOA")
@Inheritance(strategy = InheritanceType.JOINED)
@XmlRootElement
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;

	@Column(name="NOME",nullable=false,length=250)
	private String nome;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_CADASTRO")
	private Date dataCadastro;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="PESSOA_ENDERECO",
    		   joinColumns= @JoinColumn(name="ID_PESSOA"),
    		   inverseJoinColumns = @JoinColumn(name="ID_ENDERECO"))
	private List<Endereco> enderecos;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="PESSOA_TELEFONE",
    		   joinColumns= @JoinColumn(name="ID_PESSOA"),
    		   inverseJoinColumns = @JoinColumn(name="ID_TELEFONE"))
	private List<Telefone> telefones;
	
	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Pessoa)) {
			return false;
		}
		Pessoa other = (Pessoa) obj;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (nome != null && !nome.trim().isEmpty())
			result += "nome: " + nome;
		return result;
	}
}
package com.attornatus.crm.model;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String dataNascimento;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval= true)
	@JoinColumn(name="user_id")
	private List<Address> enderecos;
	
	public User() {
	}
	
	public User(String nome, String dataNascimento, List enderecos) {
		this.dataNascimento = dataNascimento;
		this.enderecos = enderecos;
		this.nome = nome;		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<Address> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Address> enderecos) {
		this.enderecos = enderecos;
	}
	
	
}

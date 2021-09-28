package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity //gera tabela no mysql
@Table(name = "tb_postagem") // definir nome da tabela 
public class Postagem {

	@Id //PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO INCREMENTO DA CHAVE PRIMARIA
	private long id;
	@NotNull // NOT NULL
	@Size(min = 5, max = 100) // DEFINE O TAMANHO
	private String titulo;
	@NotNull
	@Size(min = 10, max = 1000, message = " Texto deve conter no minimo 10 digitos a 1000 digitos")
	private String texto;
	@Temporal(TemporalType.TIMESTAMP) //Define o tempo atravez do times tamp
	private Date data = new java.sql.Date(System.currentTimeMillis()); //Define biblioteca e pega os milisegundos
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;
	
	
	public Tema getTema() {
		return tema;
	}
	public void setTema(Tema tema) {
		this.tema = tema;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}

	
}

package com.ziad.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Historique")
public class Historique implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id_historique;

	@Column(name = "historique")
	private String historique;

	@Column(name = "date")
	private Date date;

	public Historique() {

	}
	
	public Historique(Long id_historique, String historique, Date date) {
		super();
		this.id_historique = id_historique;
		this.historique = historique;
		this.date = date;
	}

	public Historique(String historique, Date date) {
		super();
		this.historique = historique;
		this.date = date;
	}

	public Long getId_historique() {
		return id_historique;
	}

	public void setId_historique(Long id_historique) {
		this.id_historique = id_historique;
	}

	public String getHistorique() {
		return historique;
	}

	public void setHistorique(String historique) {
		this.historique = historique;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}

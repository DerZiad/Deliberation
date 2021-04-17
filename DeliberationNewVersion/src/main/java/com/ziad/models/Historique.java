package com.ziad.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Historique")
public class Historique {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id_historique;

	@Column(name = "historique")
	private String historique;

	@Column(name = "date")
	private Date date;

	public Historique() {

	}
	
	public Historique(long id_historique, String historique, Date date) {
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

	public long getId_historique() {
		return id_historique;
	}

	public void setId_historique(long id_historique) {
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

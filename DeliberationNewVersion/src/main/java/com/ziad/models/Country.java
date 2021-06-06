package com.ziad.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CountryDeliberation")
public class Country {

	@Id
	@Column(length = 2)
	private String keyCountry;

	private String valueCountry;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "nationality")
	private List<Etudiant> etudiants = new ArrayList<Etudiant>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "nationality")
	private List<InscriptionEnLigne> inscriptionsEnLigne = new ArrayList<InscriptionEnLigne>();

	public Country() {

	}

	public Country(String key, String value) {
		this(value);
		this.keyCountry = key;
	}

	public Country(String value) {
		super();
		this.valueCountry = value;
	}

	public String getKeyCountry() {
		return keyCountry;
	}

	public void setKeyCountry(String keyCountry) {
		this.keyCountry = keyCountry;
	}

	public String getValueCountry() {
		return valueCountry;
	}

	public void setValueCountry(String valueCountry) {
		this.valueCountry = valueCountry;
	}

	public List<Etudiant> getEtudiants() {
		return etudiants;
	}

	public void setEtudiants(List<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}

	public List<InscriptionEnLigne> getInscriptionsEnLigne() {
		return inscriptionsEnLigne;
	}

	public void setInscriptionsEnLigne(List<InscriptionEnLigne> inscriptionsEnLigne) {
		this.inscriptionsEnLigne = inscriptionsEnLigne;
	}

}

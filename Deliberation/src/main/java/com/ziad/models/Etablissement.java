package com.ziad.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Etablissement")
public class Etablissement implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id_etablissement;

	@Column(name = "name")
	private String nom_etablissement;
	
	/**
	 * Relations
	 * */
	@OneToMany(targetEntity = Filiere.class, mappedBy = "etablissement", cascade = CascadeType.ALL)
	private List<Filiere> filieres = new ArrayList<Filiere>();
	
	@OneToMany(mappedBy = "etablissement",cascade = CascadeType.ALL)
	private List<InscriptionEnLigne> inscriptions_en_lignes = new ArrayList<InscriptionEnLigne>();

	public Etablissement() {

	}

	public Etablissement(long id_etablissement, String nom_etablissement) {
		super();
		this.id_etablissement = id_etablissement;
		this.nom_etablissement = nom_etablissement;
	}


	public Etablissement(String nom_etablissement) {
		super();
		this.nom_etablissement = nom_etablissement;
	}

	public long getId_etablissement() {
		return id_etablissement;
	}

	public void setId_etablissement(long id_etablissement) {
		this.id_etablissement = id_etablissement;
	}

	public String getNom_etablissement() {
		return nom_etablissement;
	}

	public void setNom_etablissement(String nom_etablissement) {
		this.nom_etablissement = nom_etablissement;
	}

	public List<Filiere> getFilieres() {
		return filieres;
	}

	public void setFilieres(List<Filiere> filieres) {
		this.filieres = filieres;
	}

	public List<InscriptionEnLigne> getInscriptions_en_lignes() {
		return inscriptions_en_lignes;
	}

	public void setInscriptions_en_lignes(List<InscriptionEnLigne> inscriptions_en_lignes) {
		this.inscriptions_en_lignes = inscriptions_en_lignes;
	}
	
	public void addFiliere(Filiere filiere) {
		filieres.add(filiere);
	}

}

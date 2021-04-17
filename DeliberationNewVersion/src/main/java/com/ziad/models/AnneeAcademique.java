package com.ziad.models;

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
@Table(name = "anneeacademique")
public class AnneeAcademique {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id_annee_academique;

	@Column(name = "annee_academique")
	private int annee_academique;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "")
	private List<InscriptionAdministrative> liste_inscription_administrative;

	public AnneeAcademique() {
		liste_inscription_administrative = new ArrayList<InscriptionAdministrative>();
	}

	public AnneeAcademique(int annee_academique, List<InscriptionAdministrative> liste_inscription_administrative) {
		super();
		this.annee_academique = annee_academique;
		this.liste_inscription_administrative = liste_inscription_administrative;
	}

	public AnneeAcademique(long id_annee_academique, int annee_academique,
			List<InscriptionAdministrative> liste_inscription_administrative) {
		super();
		this.id_annee_academique = id_annee_academique;
		this.annee_academique = annee_academique;
		this.liste_inscription_administrative = liste_inscription_administrative;
	}

	public long getId_annee_academique() {
		return id_annee_academique;
	}

	public void setId_annee_academique(long id_annee_academique) {
		this.id_annee_academique = id_annee_academique;
	}

	public int getAnnee_academique() {
		return annee_academique;
	}

	public void setAnnee_academique(int annee_academique) {
		this.annee_academique = annee_academique;
	}

	public List<InscriptionAdministrative> getListe_inscription_administrative() {
		return liste_inscription_administrative;
	}

	public void setListe_inscription_administrative(List<InscriptionAdministrative> liste_inscription_administrative) {
		this.liste_inscription_administrative = liste_inscription_administrative;
	}

}

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Filiere")
public class Filiere implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_filiere")
	private Long id_filiere;

	@Column(name = "nom_filiere")
	private String nom_filiere;

	/**
	 * Relations
	 */
	@OneToMany(targetEntity = Etape.class, cascade = CascadeType.ALL, mappedBy = "filiere")
	private List<Etape> etapes = new ArrayList<Etape>();

	@ManyToOne(targetEntity = Etablissement.class, cascade = CascadeType.DETACH)
	private Etablissement etablissement;

	@ManyToOne(targetEntity = Professeur.class, cascade = CascadeType.DETACH)
	private Professeur responsable_filiere;

	public Filiere() {

	}

	public Filiere(String nom_filiere, Etablissement etablissements, Professeur responsable_filiere) {
		super();
		this.nom_filiere = nom_filiere;
		this.etablissement = etablissements;
		this.responsable_filiere = responsable_filiere;
	}

	public Filiere(Long id_filiere, String nom_filiere, Etablissement etablissement, Professeur responsable_filiere) {
		super();
		this.id_filiere = id_filiere;
		this.nom_filiere = nom_filiere;
		this.etablissement = etablissement;
		this.responsable_filiere = responsable_filiere;
	}

	public Long getId_filiere() {
		return id_filiere;
	}

	public void setId_filiere(Long id_filiere) {
		this.id_filiere = id_filiere;
	}

	public String getNom_filiere() {
		return nom_filiere;
	}

	public void setNom_filiere(String nom_filiere) {
		this.nom_filiere = nom_filiere;
	}

	public List<Etape> getEtapes() {
		return etapes;
	}

	public void setEtapes(List<Etape> etapes) {
		this.etapes = etapes;
	}

	public Etablissement getEtablissement() {
		return etablissement;
	}

	public void setEtablissements(Etablissement etablissement) {
		this.etablissement = etablissement;
	}

	public Professeur getResponsable_filiere() {
		return responsable_filiere;
	}

	public void setResponsable_filiere(Professeur responsable_filiere) {
		this.responsable_filiere = responsable_filiere;
	}

	public void setEtablissement(Etablissement etablissement) {
		this.etablissement = etablissement;
	}
	
	public void addEtape(Etape etape) {
		etapes.add(etape);
	}
}
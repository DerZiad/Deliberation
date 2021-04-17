package com.ziad.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Element")
public class Element {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_element")
	private long id_element;

	@Column(name = "libelle_element")
	private String libelle_element;

	@Column(name = "coeficient")
	private Double coeficient = 1d;

	@Column(name = "validation")
	private Double validation = 10d;

	/**
	 * Modules Inscrit dedant
	 * 
	 */

	@ManyToOne(targetEntity = Modulee.class, cascade = CascadeType.PERSIST)
	private Modulee module;

	/*
	 *  
	 * */
	@ManyToMany(targetEntity = Professeur.class, cascade = CascadeType.PERSIST)
	private List<Professeur> professeurs;

	public Element() {
		professeurs = new ArrayList<Professeur>();
	}

	public Element(String libelle_element, Double coeficient, Double validation, Modulee module,
			List<Professeur> professeurs) {
		super();
		this.libelle_element = libelle_element;
		this.coeficient = coeficient;
		this.validation = validation;
		this.module = module;
		this.professeurs = professeurs;
	}

	public Element(long id_element, String libelle_element, Double coeficient, Double validation, Modulee module,
			List<Professeur> professeurs) {
		super();
		this.id_element = id_element;
		this.libelle_element = libelle_element;
		this.coeficient = coeficient;
		this.validation = validation;
		this.module = module;
		this.professeurs = professeurs;
	}

	public long getId_element() {
		return id_element;
	}

	public void setId_element(long id_element) {
		this.id_element = id_element;
	}

	public String getLibelle_element() {
		return libelle_element;
	}

	public void setLibelle_element(String libelle_element) {
		this.libelle_element = libelle_element;
	}

	public Double getCoeficient() {
		return coeficient;
	}

	public void setCoeficient(Double coeficient) {
		this.coeficient = coeficient;
	}

	public Double getValidation() {
		return validation;
	}

	public void setValidation(Double validation) {
		this.validation = validation;
	}

	public Modulee getModule() {
		return module;
	}

	public void setModule(Modulee module) {
		this.module = module;
	}

	public List<Professeur> getProfesseurs() {
		return professeurs;
	}

	public void setProfesseurs(List<Professeur> professeurs) {
		this.professeurs = professeurs;
	}
	
	public void addProfesseur(Professeur professeur) {
		professeurs.add(professeur);
	}

}
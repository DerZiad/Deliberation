 package com.ziad.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "Module")
public class Modulee implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_module")
	private long id_module;

	@Column(name = "libelle_module")
	private String libelle_module;

	@Column(name = "coeficient")
	private Double coeficient = 1d;

	@Column(name = "validation")
	private Double validation = 10d;

	@Column(name = "eliminatoire")
	private Double eliminatoire = 4d;
	
	private boolean composee = false;

	@OneToMany(targetEntity = Element.class, mappedBy = "module",cascade = CascadeType.ALL)
	private List<Element> elements;

	@ManyToOne(targetEntity = Semestre.class,cascade = CascadeType.ALL)
	@JoinColumn(name = "semestre", foreignKey = @ForeignKey(name = "fk_semestre"))
	@NotFound(action = NotFoundAction.IGNORE)
	private Semestre semestre;

	@ManyToOne(targetEntity = Professeur.class,cascade = CascadeType.PERSIST)
	private Professeur responsable_module;

	public Modulee() {
		
	}
	

	public Modulee(String libelle_module, Double coeficient, Double validation, Double eliminatoire, boolean composee,
			List<Element> elements, Semestre semestre, Professeur responsable_module) {
		super();
		this.libelle_module = libelle_module;
		this.coeficient = coeficient;
		this.validation = validation;
		this.eliminatoire = eliminatoire;
		this.composee = composee;
		this.elements = elements;
		this.semestre = semestre;
		this.responsable_module = responsable_module;
	}


	

	public Modulee(long id_module, String libelle_module, Double coeficient, Double validation, Double eliminatoire,
			boolean composee, List<Element> elements, Semestre semestre, Professeur responsable_module) {
		super();
		this.id_module = id_module;
		this.libelle_module = libelle_module;
		this.coeficient = coeficient;
		this.validation = validation;
		this.eliminatoire = eliminatoire;
		this.composee = composee;
		this.elements = elements;
		this.semestre = semestre;
		this.responsable_module = responsable_module;
	}


	
	
	public long getId_module() {
		return id_module;
	}


	public void setId_module(long id_module) {
		this.id_module = id_module;
	}


	public String getLibelle_module() {
		return libelle_module;
	}


	public void setLibelle_module(String libelle_module) {
		this.libelle_module = libelle_module;
	}


	public Double getCoeficient() {
		return coeficient;
	}


	public void setCoeficient(double coeficient) {
		this.coeficient = coeficient;
	}


	public Double getValidation() {
		return validation;
	}


	public void setValidation(Double validation) {
		this.validation = validation;
	}


	public Double getEliminatoire() {
		return eliminatoire;
	}


	public void setEliminatoire(Double eliminatoire) {
		this.eliminatoire = eliminatoire;
	}


	public boolean isComposee() {
		return composee;
	}


	public void setComposee(boolean composee) {
		this.composee = composee;
		if(!isComposed()) {
			elements = new ArrayList<Element>();
			Element element = new Element(libelle_module, coeficient, validation,this,new ArrayList<Professeur>());
			elements.add(element);
		}
	}


	public List<Element> getElements() {
		return elements;
	}


	public void setElements(List<Element> elements) {
		this.elements = elements;
	}


	public Semestre getSemestre() {
		return semestre;
	}


	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}


	public Professeur getResponsable_module() {
		return responsable_module;
	}


	public void setResponsable_module(Professeur responsable_module) {
		this.responsable_module = responsable_module;
	}

	
	public boolean isComposed() {
		return composee;
	}

}
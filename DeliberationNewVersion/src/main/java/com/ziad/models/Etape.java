package com.ziad.models;

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

@Entity
@Table(name = "Etape")
public class Etape {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_etape")
	private int id_etape;
	@Column(name = "libelle_etape")
	private String libelle_etape;
	@Column(name = "diplomante")
	private boolean diplomante;
	@Column(name = "validation")
	private Double validation = 10d;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "filiere", foreignKey = @ForeignKey(name = "fk_filiere"))
	private Filiere filiere;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "etape")
	private List<Semestre> semestres = new ArrayList<Semestre>();

	public Etape() {

	}

	public Etape(String libelle_etape, boolean diplomante, Double validation, Filiere filiere,
			List<Semestre> semestres) {
		super();
		this.libelle_etape = libelle_etape;
		this.diplomante = diplomante;
		this.validation = validation;
		this.filiere = filiere;
		this.semestres = semestres;
	}

	public Etape(int id_etape, String libelle_etape, boolean diplomante, Double validation, Filiere filiere,
			List<Semestre> semestres) {
		super();
		this.id_etape = id_etape;
		this.libelle_etape = libelle_etape;
		this.diplomante = diplomante;
		this.validation = validation;
		this.filiere = filiere;
		this.semestres = semestres;
	}

	public int getId_etape() {
		return id_etape;
	}

	public void setId_etape(int id_etape) {
		this.id_etape = id_etape;
	}

	public String getLibelle_etape() {
		return libelle_etape;
	}

	public void setLibelle_etape(String libelle_etape) {
		this.libelle_etape = libelle_etape;
	}

	public boolean isDiplomante() {
		return diplomante;
	}

	public void setDiplomante(boolean diplomante) {
		this.diplomante = diplomante;
	}

	public Double getValidation() {
		return validation;
	}

	public void setValidation(Double validation) {
		this.validation = validation;
	}

	public Filiere getFiliere() {
		return filiere;
	}

	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}

	public List<Semestre> getSemestres() {
		return semestres;
	}

	public void setSemestres(List<Semestre> semestres) {
		this.semestres = semestres;
	}
	
	public void addSemestre(Semestre semestre) {
		semestres.add(semestre);
	}

}
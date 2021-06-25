package com.ziad.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ziad.enums.TypeSemestre;

@Entity
@Table(name = "Semestre")
public class Semestre implements Serializable,Comparable<Semestre>,ElementNorm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_semestre;
	
	@Column(name = "validation")
	private Double validation = 10d;
	
	@Column(name = "libelle_semestre")
	private String libelle_semestre;

	@Enumerated(value = EnumType.STRING)
	private TypeSemestre type_semestre;

	/**
	 * Relations
	 */
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "etape", foreignKey = @ForeignKey(name = "fk_etape"))
	private Etape etape;

	@Column(name = "ordre")
	private int ordre;

	@OneToMany(mappedBy = "semestre", cascade = CascadeType.ALL)
	private List<Modulee> modules = new ArrayList<Modulee>();

	public Semestre() {

	}

	public Semestre(Double validation, String libelle_semestre, Etape etape, TypeSemestre type_semestre, int ordre) {
		super();
		this.validation = validation;
		this.libelle_semestre = libelle_semestre;
		this.etape = etape;
		this.type_semestre = type_semestre;
		this.ordre = ordre;
	}

	public Semestre(Long id_semestre, Double validation, String libelle_semestre, Etape etape,
			TypeSemestre type_semestre, int ordre) {
		super();
		this.id_semestre = id_semestre;
		this.validation = validation;
		this.libelle_semestre = libelle_semestre;
		this.etape = etape;
		this.type_semestre = type_semestre;
		this.ordre = ordre;
	}

	public Long getId_semestre() {
		return id_semestre;
	}

	public void setId_semestre(Long id_semestre) {
		this.id_semestre = id_semestre;
	}

	public Double getValidation() {
		return validation;
	}

	public void setValidation(Double validation) {
		this.validation = validation;
	}

	public String getLibelle_semestre() {
		return libelle_semestre;
	}

	public void setLibelle_semestre(String libelle_semestre) {
		this.libelle_semestre = libelle_semestre;
	}

	public Etape getEtape() {
		return etape;
	}

	public void setEtape(Etape etape) {
		this.etape = etape;
	}

	public TypeSemestre getType_semestre() {
		return type_semestre;
	}

	public void setType_semestre(TypeSemestre type_semestre) {
		this.type_semestre = type_semestre;
	}

	public int getOrdre() {
		return ordre;
	}

	public void setOrdre(int ordre) {
		this.ordre = ordre;
	}

	public List<Modulee> getModules() {
		return modules;
	}

	public void setModules(List<Modulee> modules) {
		this.modules = modules;
	}
	public void addModule(Modulee module) {
		modules.add(module);
	}

	@Override
	public int compareTo(Semestre o) {
		return ordre - o.getOrdre();
	}

	@Override
	public ElementNorm getElement() {
		return this;
	}

	@Override
	public String getType() {
		return ElementType.SEMESTRE.name();
	}

	@Override
	public String getName() {
		return libelle_semestre;
	}

	@Override
	public Long getId() {
		return id_semestre;
	}

}
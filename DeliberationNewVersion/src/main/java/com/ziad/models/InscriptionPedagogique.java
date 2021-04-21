package com.ziad.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.ziad.enums.TypeInscription;
import com.ziad.models.compositeid.ComposedEtudiantElementInscriptionPedagogique;

/**
 * 
 * Classe Associative
 * 
 */
@Entity
@Table(name = "InscriptionPedagogique")
public class InscriptionPedagogique {

	@EmbeddedId
	private ComposedEtudiantElementInscriptionPedagogique id_inscription_pedagogique;

	@Column(name = "annee_academique")
	private String annee_academique;

	@Column(name = "note_semestre")
	private Double note_semestre;

	@Column(name = "validation")
	private Double validation;

	// Par Module / Semestre / Element
	@Column(name = "type_inscription")
	@Enumerated(value = EnumType.STRING)
	private TypeInscription type_inscription;

	public InscriptionPedagogique() {

	}

	public InscriptionPedagogique(ComposedEtudiantElementInscriptionPedagogique id_inscription_pedagogique,
			String annee_academique, Double note_semestre, Double validation, TypeInscription type_inscription) {
		super();
		this.id_inscription_pedagogique = id_inscription_pedagogique;
		this.annee_academique = annee_academique;
		this.note_semestre = note_semestre;
		this.validation = validation;
		this.type_inscription = type_inscription;
	}

	public ComposedEtudiantElementInscriptionPedagogique getId_inscription_pedagogique() {
		return id_inscription_pedagogique;
	}

	public void setId_inscription_pedagogique(
			ComposedEtudiantElementInscriptionPedagogique id_inscription_pedagogique) {
		this.id_inscription_pedagogique = id_inscription_pedagogique;
	}

	public String getAnnee_academique() {
		return annee_academique;
	}

	public void setAnnee_academique(String annee_academique) {
		this.annee_academique = annee_academique;
	}

	public Double getNote_semestre() {
		return note_semestre;
	}

	public void setNote_semestre(Double note_semestre) {
		this.note_semestre = note_semestre;
	}

	public Double getValidation() {
		return validation;
	}

	public void setValidation(Double validation) {
		this.validation = validation;
	}
	public TypeInscription getType_inscription() {
		return type_inscription;
	}

	public void setType_inscription(TypeInscription type_inscription) {
		this.type_inscription = type_inscription;
	}

	public Etudiant getEtudiant() {
		return id_inscription_pedagogique.getEtudiant();
	}

	public void setEtudiant(Etudiant etudiant) {
		id_inscription_pedagogique.setEtudiant(etudiant);
	}

	public Element getElement() {
		return id_inscription_pedagogique.getElement();
	}

	public void setEtudiant(Element element) {
		id_inscription_pedagogique.setElement(element);
	}

}
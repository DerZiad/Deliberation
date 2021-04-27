package com.ziad.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ziad.enums.TypeInscription;
import com.ziad.models.compositeid.ComposedInscriptionPedagogique;

/**
 * 
 * Classe Associative
 * 
 */
@Entity
@Table(name = "InscriptionPedagogique")
public class InscriptionPedagogique implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ComposedInscriptionPedagogique id_inscription_pedagogique;

	@ManyToOne(cascade = CascadeType.DETACH)
	private AnneeAcademique annee_academique;

	@Column(name = "valide")
	private boolean isValid;

	@Column(name = "type_inscription")
	@Enumerated(value = EnumType.STRING)
	private TypeInscription type_inscription;

	public InscriptionPedagogique() {

	}

	public InscriptionPedagogique(AnneeAcademique annee_academique, boolean isValid, TypeInscription type_inscription) {
		super();
		this.annee_academique = annee_academique;
		this.isValid = isValid;
		this.type_inscription = type_inscription;
	}

	public InscriptionPedagogique(ComposedInscriptionPedagogique id_inscription_pedagogique,
			AnneeAcademique annee_academique, boolean isValid, TypeInscription type_inscription) {
		super();
		this.id_inscription_pedagogique = id_inscription_pedagogique;
		this.annee_academique = annee_academique;
		this.isValid = isValid;
		this.type_inscription = type_inscription;
	}

	public ComposedInscriptionPedagogique getId_inscription_pedagogique() {
		return id_inscription_pedagogique;
	}

	public void setId_inscription_pedagogique(ComposedInscriptionPedagogique id_inscription_pedagogique) {
		this.id_inscription_pedagogique = id_inscription_pedagogique;
	}

	public AnneeAcademique getAnnee_academique() {
		return annee_academique;
	}

	public void setAnnee_academique(AnneeAcademique annee_academique) {
		this.annee_academique = annee_academique;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public TypeInscription getType_inscription() {
		return type_inscription;
	}

	public void setType_inscription(TypeInscription type_inscription) {
		this.type_inscription = type_inscription;
	}

}
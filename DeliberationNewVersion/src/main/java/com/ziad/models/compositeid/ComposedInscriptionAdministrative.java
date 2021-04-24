package com.ziad.models.compositeid;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;

import com.ziad.models.Etudiant;
import com.ziad.models.Filiere;

@Embeddable
public class ComposedInscriptionAdministrative implements Serializable {
	/**
	 * 
	 * Classe qui se compose des cla
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne(cascade = CascadeType.ALL)
	private Etudiant etudiant;

	@OneToOne(cascade = CascadeType.DETACH)
	private Filiere filiere;

	public ComposedInscriptionAdministrative() {

	}

	public ComposedInscriptionAdministrative(Etudiant etudiant, Filiere filiere) {
		super();
		this.etudiant = etudiant;
		this.filiere = filiere;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	public Filiere getFiliere() {
		return filiere;
	}

	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}

}

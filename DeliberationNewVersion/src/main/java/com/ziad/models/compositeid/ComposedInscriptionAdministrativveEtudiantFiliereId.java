package com.ziad.models.compositeid;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;

import com.ziad.models.Etudiant;
import com.ziad.models.Filiere;
import com.ziad.models.InscriptionAdministrative;

@Embeddable
public class ComposedInscriptionAdministrativveEtudiantFiliereId implements Serializable {
	/**
	 * 
	 * Classe qui se compose des 3 Etudiants
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne(cascade = CascadeType.PERSIST)
	private Etudiant etudiant;

	@OneToOne(cascade = CascadeType.PERSIST)
	private InscriptionAdministrative inscription_administrative;

	@OneToOne(cascade = CascadeType.PERSIST)
	private Filiere filiere;

	public ComposedInscriptionAdministrativveEtudiantFiliereId(Etudiant etudiant,
			InscriptionAdministrative inscription_administrative, Filiere filiere) {
		super();
		this.etudiant = etudiant;
		this.inscription_administrative = inscription_administrative;
		this.filiere = filiere;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	public InscriptionAdministrative getInscription_administrative() {
		return inscription_administrative;
	}

	public void setInscription_administrative(InscriptionAdministrative inscription_administrative) {
		this.inscription_administrative = inscription_administrative;
	}

	public Filiere getFiliere() {
		return filiere;
	}

	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}

}

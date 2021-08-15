package com.ziad.models.compositeid;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.ziad.models.Etudiant;
import com.ziad.models.Filiere;

import lombok.Data;

@Embeddable
@Data
public class ComposedInscriptionAdministrative implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(nullable = true)
	private Etudiant etudiant;

	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(nullable = true)
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

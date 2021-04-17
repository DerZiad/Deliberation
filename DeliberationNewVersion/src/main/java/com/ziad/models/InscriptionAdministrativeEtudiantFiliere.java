package com.ziad.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ziad.models.compositeid.ComposedInscriptionAdministrativveEtudiantFiliereId;

/*
 * 
 *     Classe associative ternaire
 * 
 * 
 * **/

@Entity
@Table(name = "InscriptionAdministrativeEtudiantFiliere")
public class InscriptionAdministrativeEtudiantFiliere {

	@EmbeddedId
	private ComposedInscriptionAdministrativveEtudiantFiliereId composite_association_id;

	public InscriptionAdministrativeEtudiantFiliere(
			ComposedInscriptionAdministrativveEtudiantFiliereId composite_association_id) {
		super();
		this.composite_association_id = composite_association_id;
	}

	public ComposedInscriptionAdministrativveEtudiantFiliereId getComposite_association_id() {
		return composite_association_id;
	}

	public void setComposite_association_id(
			ComposedInscriptionAdministrativveEtudiantFiliereId composite_association_id) {
		this.composite_association_id = composite_association_id;
	}

	public Etudiant getEtudiant() {
		return composite_association_id.getEtudiant();
	}

	public void setEtudiant(Etudiant etudiant) {
		composite_association_id.setEtudiant(etudiant);
	}

	public Filiere getFiliere() {
		return composite_association_id.getFiliere();
	}

	public void setFiliere(Filiere filiere) {
		composite_association_id.setFiliere(filiere);
	}

	public InscriptionAdministrative get_inscription_administrative() {
		return composite_association_id.getInscription_administrative();
	}

	public void set_inscription_administrative(InscriptionAdministrative inscription_administrative) {
		composite_association_id.setInscription_administrative(inscription_administrative);
	}

}

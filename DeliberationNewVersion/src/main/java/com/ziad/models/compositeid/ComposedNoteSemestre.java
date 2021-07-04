package com.ziad.models.compositeid;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import com.ziad.models.AnneeAcademique;
import com.ziad.models.Etudiant;
import com.ziad.models.Semestre;

@Embeddable
public class ComposedNoteSemestre implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Semestre semestre;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Etudiant etudiant;
	@OneToOne(fetch = FetchType.EAGER, targetEntity = AnneeAcademique.class, cascade = CascadeType.PERSIST)
	private AnneeAcademique anneeAcaqemique;

	public ComposedNoteSemestre() {

	}

	public ComposedNoteSemestre(Semestre semestre, Etudiant etudiant, AnneeAcademique anneeAcaqemique) {
		super();
		this.semestre = semestre;
		this.etudiant = etudiant;
		this.anneeAcaqemique = anneeAcaqemique;
	}

	public AnneeAcademique getAnneeAcaqemique() {
		return anneeAcaqemique;
	}

	public void setAnneeAcaqemique(AnneeAcademique anneeAcaqemique) {
		this.anneeAcaqemique = anneeAcaqemique;
	}

	public Semestre getSemestre() {
		return semestre;
	}

	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	@Override
	public String toString() {
		return "ComposedNoteSemestre [semestre=" + semestre.getId_semestre() + ", etudiant=" + etudiant.getId_etudiant()
				+ "]";
	}

}

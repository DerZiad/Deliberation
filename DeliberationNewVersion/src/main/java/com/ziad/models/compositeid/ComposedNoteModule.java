package com.ziad.models.compositeid;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.ziad.models.AnneeAcademique;
import com.ziad.models.Etudiant;
import com.ziad.models.Modulee;

@Embeddable
public class ComposedNoteModule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(nullable = true)
	private Modulee module;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(nullable = true)
	private Etudiant etudiant;
	@OneToOne(fetch = FetchType.EAGER, targetEntity = AnneeAcademique.class, cascade = CascadeType.PERSIST)
	private AnneeAcademique anneeAcaqemique;

	public ComposedNoteModule() {

	}

	public ComposedNoteModule(Modulee module, Etudiant etudiant, AnneeAcademique anneeAcaqemique) {
		super();
		this.module = module;
		this.etudiant = etudiant;
		this.anneeAcaqemique = anneeAcaqemique;
	}

	public AnneeAcademique getAnneeAcaqemique() {
		return anneeAcaqemique;
	}

	public void setAnneeAcaqemique(AnneeAcademique anneeAcaqemique) {
		this.anneeAcaqemique = anneeAcaqemique;
	}

	public Modulee getModule() {
		return module;
	}

	public void setModule(Modulee module) {
		this.module = module;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	@Override
	public String toString() {
		return "ComposedNoteModule [module=" + module + ", etudiant=" + etudiant + "]";
	}

}

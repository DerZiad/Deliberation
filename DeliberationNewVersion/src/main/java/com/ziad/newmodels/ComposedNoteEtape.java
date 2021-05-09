package com.ziad.newmodels;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;

import com.ziad.models.Etape;
import com.ziad.models.Etudiant;

@Embeddable
public class ComposedNoteEtape {
	@OneToOne(cascade = CascadeType.ALL)
	private Etape etape;
	@OneToOne(cascade = CascadeType.ALL)
	private Etudiant etudiant;
	
	public ComposedNoteEtape() {
		
	}
	
	public ComposedNoteEtape(Etape etape, Etudiant etudiant) {
		super();
		this.etape = etape;
		this.etudiant = etudiant;
	}

	public Etape getEtape() {
		return etape;
	}

	public void setEtape(Etape etape) {
		this.etape = etape;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

}

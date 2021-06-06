package com.ziad.models.compositeid;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import com.ziad.models.Etape;
import com.ziad.models.Etudiant;

@Embeddable
public class ComposedNoteEtape implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private Etape etape;
	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
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

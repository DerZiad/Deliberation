package com.ziad.models.compositeid;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;

import com.ziad.models.Etudiant;
import com.ziad.models.Semestre;

@Embeddable
public class ComposedNoteSemestre implements Serializable{

	@OneToOne(cascade = CascadeType.ALL)
	private Semestre semestre;
	@OneToOne(cascade = CascadeType.ALL)
	private Etudiant etudiant;
	
	public ComposedNoteSemestre() {
		
	}
	
	public ComposedNoteSemestre(Semestre semestre, Etudiant etudiant) {
		super();
		this.semestre = semestre;
		this.etudiant = etudiant;
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

}

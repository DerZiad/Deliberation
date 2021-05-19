package com.ziad.models.compositeid;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.ziad.models.Etudiant;
import com.ziad.models.Modulee;

@Embeddable
public class ComposedNoteModule implements Serializable{
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(nullable = true)
	private Modulee module;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(nullable = true)
	private Etudiant etudiant;
	
	public ComposedNoteModule() {
		
	}
	
	public ComposedNoteModule(Modulee module, Etudiant etudiant) {
		super();
		this.module = module;
		this.etudiant = etudiant;
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

package com.ziad.newmodels;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;

import com.ziad.models.Etudiant;
import com.ziad.models.Modulee;

@Embeddable
public class ComposedNoteModule {
	
	@OneToOne(cascade = CascadeType.ALL)
	private Modulee module;
	@OneToOne(cascade = CascadeType.ALL)
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
	
	
	
	
}

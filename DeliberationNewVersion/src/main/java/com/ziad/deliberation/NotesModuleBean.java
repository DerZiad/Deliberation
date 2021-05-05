package com.ziad.deliberation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ziad.models.Etudiant;
import com.ziad.models.InscriptionPedagogique;
import com.ziad.models.Modulee;

public class NotesModuleBean {

	private Modulee module;
	private HashMap<Etudiant, Double> notes = new HashMap<Etudiant, Double>();
	public NotesModuleBean(Modulee module) {
		super();
		this.module = module;
	}
	public Modulee getModule() {
		return module;
	}
	public void setModule(Modulee module) {
		this.module = module;
	}
	public HashMap<Etudiant, Double> getNotes() {
		return notes;
	}
	public void setNotes(HashMap<Etudiant, Double> notes) {
		this.notes = notes;
	}
	
	public Double getNote(Etudiant etudiant) {
		return notes.get(etudiant);
	}
	public void addNote(Etudiant etudiant,Double note) {
		notes.put(etudiant, note);
	}
	
	public void addStructure(HashMap<Etudiant, ArrayList<InscriptionPedagogique>> structures) {
		for (Etudiant etudiant : structures.keySet()) {
			List<InscriptionPedagogique> listeParEtudiant = structures.get(etudiant);
			notes.put(etudiant, calculerNoteModule(listeParEtudiant));
		}
		
	}
	public Double calculerNoteModule(List<InscriptionPedagogique> listes) {
		Double noteModule = 0d;
		Double coefficient = 0d;
		for (InscriptionPedagogique inscriptionPedagogique : listes) {
			noteModule = noteModule + inscriptionPedagogique.getNoteElement() * inscriptionPedagogique.getElement().getCoeficient();
			coefficient = coefficient + inscriptionPedagogique.getElement().getCoeficient();
		}
		noteModule = noteModule / coefficient;
		return noteModule;
	}
	
}

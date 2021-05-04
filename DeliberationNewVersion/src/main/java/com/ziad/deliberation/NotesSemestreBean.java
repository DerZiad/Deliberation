package com.ziad.deliberation;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ziad.models.Etudiant;
import com.ziad.models.Modulee;
import com.ziad.models.Semestre;

public class NotesSemestreBean {

	private Semestre semestre;

	private HashMap<Etudiant, HashMap<Modulee, Double>> notesSemestre = new HashMap<Etudiant, HashMap<Modulee, Double>>();

	public NotesSemestreBean(Semestre semestre) {
		super();
		this.semestre = semestre;
	}

	public Semestre getSemestre() {
		return semestre;
	}

	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}

	public void extractNotesModule(NotesModuleBean notesParModule) {
		for (Etudiant etudiant : notesParModule.getNotes().keySet()) {
			HashMap<Modulee, Double> notes = null;
			if (!notesSemestre.keySet().contains(etudiant)) {
				notes = new HashMap<Modulee, Double>();
				notesSemestre.put(etudiant, notes);
			} else {
				notes = notesSemestre.get(etudiant);
			}
			notes.put(notesParModule.getModule(), notesParModule.getNote(etudiant));
		}
	}
}

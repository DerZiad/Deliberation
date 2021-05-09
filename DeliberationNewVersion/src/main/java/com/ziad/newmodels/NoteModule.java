package com.ziad.newmodels;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "notesmodule")
public class NoteModule {

	@EmbeddedId
	private ComposedNoteModule idComposed;

	private Double note;

	private boolean isValid = false;

	private String etat = "";// Compensé ou eliminé

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.DETACH })
	private Deliberation deliberation;

	public NoteModule() {

	}

	public NoteModule(ComposedNoteModule idComposed, Double note, Deliberation deliberation) {
		super();
		this.idComposed = idComposed;
		this.note = note;
		this.deliberation = deliberation;
	}

	public NoteModule(Double note, Deliberation deliberation) {
		super();
		this.note = note;
		this.deliberation = deliberation;
	}

	public ComposedNoteModule getIdComposed() {
		return idComposed;
	}

	public void setIdComposed(ComposedNoteModule idComposed) {
		this.idComposed = idComposed;
	}

	public Double getNote() {
		return note;
	}

	public void setNote(Double note) {
		this.note = note;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public Deliberation getDeliberation() {
		return deliberation;
	}

	public void setDeliberation(Deliberation deliberation) {
		this.deliberation = deliberation;
	}
	
	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public void delibererModule(DeliberationType session) {
		if (note >= idComposed.getModule().getValidation()) {
			isValid = true;
			etat = Etat.VALIDE.name();
		} else {
			if (note >= idComposed.getModule().getEliminatoire())
				etat = Etat.COMPONSE.name();
			else {
				etat = Etat.ELIMINIE.name();
			}
			if (session.equals(DeliberationType.ORDINAIRE)) {
				etat = DeliberationType.RATTRAPAGE.name();
			}
		}
	}

}

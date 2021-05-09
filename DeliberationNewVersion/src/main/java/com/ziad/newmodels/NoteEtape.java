package com.ziad.newmodels;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "notesetape")
public class NoteEtape {
	@EmbeddedId
	private ComposedNoteEtape idCompose;

	private Double note;

	private boolean isValid = false;

	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH})
	private Deliberation deliberation;

	public NoteEtape() {

	}

	public NoteEtape(Double note, Deliberation deliberation) {
		super();
		this.note = note;
		this.deliberation = deliberation;
	}

	public NoteEtape(ComposedNoteEtape idCompose, Double note, Deliberation deliberation) {
		this(note, deliberation);
		this.idCompose = idCompose;
	}

	public ComposedNoteEtape getIdCompose() {
		return idCompose;
	}

	public void setIdCompose(ComposedNoteEtape idCompose) {
		this.idCompose = idCompose;
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

}

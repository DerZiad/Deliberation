package com.ziad.newmodels;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "notessemestre")
public class NoteSemestre {

	private ComposedNoteSemestre idCompose;

	private Double note;
	private boolean isValid = false;

	private String etat = "";// Compensation ou elimine

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.DETACH })
	private Deliberation deliberation;

	public NoteSemestre() {

	}

	public NoteSemestre(ComposedNoteSemestre idCompose, Double note, Deliberation deliberation) {
		this(note, deliberation);
		this.idCompose = idCompose;

	}

	public NoteSemestre(Double note, Deliberation deliberation) {
		super();
		this.note = note;
		this.deliberation = deliberation;
	}

	public ComposedNoteSemestre getIdCompose() {
		return idCompose;
	}

	public void setIdCompose(ComposedNoteSemestre idCompose) {
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

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public void delibererSemestre() {

	}
}

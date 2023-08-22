package com.ziad.models;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ziad.enums.DeliberationType;
import com.ziad.enums.Etat;
import com.ziad.models.compositeid.ComposedNoteModule;

@Entity
@Table(name = "notesmodule")
public class NoteModule implements NoteNorm {

	@EmbeddedId
	private ComposedNoteModule idComposed;

	private Double note = 0d;

	private boolean isValid = false;

	private String etat = "";// Compensé ou eliminé

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.PERSIST })
	private AnneeAcademique anneeAcademique;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.DETACH })
	private Deliberation deliberation;

	public NoteModule() {

	}

	public NoteModule(ComposedNoteModule idComposed, Double note, Deliberation deliberation, AnneeAcademique annee) {
		this(note, deliberation, annee);
		this.idComposed = idComposed;
	}

	public NoteModule(Double note, Deliberation deliberation, AnneeAcademique annee) {
		super();
		this.note = arrondir(note);
		this.deliberation = deliberation;
		this.anneeAcademique = annee;
	}

	public ComposedNoteModule getIdComposed() {
		return idComposed;
	}

	public void setIdComposed(ComposedNoteModule idComposed) {
		this.idComposed = idComposed;
	}

	public Double getNote() {
		return arrondir(note);
	}

	public void setNote(Double note) {
		this.note = arrondir(note);
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

	public void delibererModule() {
		if (note >= idComposed.getModule().getValidation()) {
			isValid = true;
			etat = Etat.VALIDE.name();
		} else {
			isValid = false;
			etat = Etat.NONVALID.name();
		}
	}

	public AnneeAcademique getAnneeAcademique() {
		return anneeAcademique;
	}

	public void setAnneeAcademique(AnneeAcademique anneeAcademique) {
		this.anneeAcademique = anneeAcademique;
	}

	@Override
	public String toString() {
		return "NoteModule [idComposed=" + idComposed + ", note=" + note + ", isValid=" + isValid + ", etat=" + etat
				+ ", deliberation=" + deliberation + "]";
	}

	public double arrondir(Double note) {
		return Math.round(note * 100.0) / 100.0;
	}

	@Override
	public void calculState() {
		if (note >= idComposed.getModule().getValidation()) {
			isValid = true;
			etat = Etat.VALIDE.name();
		} else {
			if (note >= idComposed.getModule().getEliminatoire()) {
				etat = Etat.COMPONSE.name();
			} else {
				etat = Etat.NONVALID.name();
			}
		}

	}

	@Override
	public ElementNorm getElement() {
		return idComposed.getModule();
	}

	@Override
	public Etudiant getEtudiant() {
		return idComposed.getEtudiant();
	}

	@Override
	public Long getIdStudent() {
		return idComposed.getEtudiant().getId_etudiant();
	}

	@Override
	public Double getValidation() {
		return idComposed.getModule().getValidation();
	}

}

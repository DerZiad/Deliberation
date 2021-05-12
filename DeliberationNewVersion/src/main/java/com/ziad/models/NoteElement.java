package com.ziad.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ziad.enums.TypeNote;
import com.ziad.models.compositeid.ComposedInscriptionPedagogique;

@Entity
@Table(name = "note_element")
public class NoteElement implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ComposedInscriptionPedagogique idCompose;

	@Column(name = "note_element")
	private Double note_element;

	private boolean isValid = false;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "noteelement")
	private List<Note> notes = new ArrayList<Note>();

	@OneToOne(cascade = CascadeType.DETACH)
	private AnneeAcademique annee_academique;
	
	public NoteElement() {
		
	}
	
	public NoteElement(Double note_element, AnneeAcademique annee_academique) {
		super();
		this.note_element = note_element;
		this.annee_academique = annee_academique;
	}

	public NoteElement(ComposedInscriptionPedagogique idCompose, Double note_element,
			AnneeAcademique annee_academique) {
		super();
		this.idCompose = idCompose;
		this.note_element = note_element;
		this.annee_academique = annee_academique;
	}

	public ComposedInscriptionPedagogique getIdCompose() {
		return idCompose;
	}

	public void setIdCompose(ComposedInscriptionPedagogique idCompose) {
		this.idCompose = idCompose;
	}

	public Double getNote_element() {
		return note_element;
	}

	public void setNote_element(Double note_element) {
		this.note_element = note_element;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public AnneeAcademique getAnnee_academique() {
		return annee_academique;
	}

	public Element getElement() {
		return idCompose.getElement();
	}

	public Etudiant getEtudiant() {
		return idCompose.getEtudiant();
	}

	public void addNote(Note note) {
		notes.add(note);
	}

	public void setAnnee_academique(AnneeAcademique annee_academique) {
		this.annee_academique = annee_academique;
	}
	
	

	public void delibererElementOrdinaire() {
		note_element = 0d;
		double coefficient = 0;
		for (Note noteElementA : notes) {
			note_element = note_element + noteElementA.getCoeficient() * noteElementA.getNote();
			coefficient = noteElementA.getCoeficient();
		}
		note_element = note_element / coefficient;

		if (note_element >= getElement().getValidation()) {
			isValid = true;
		}

	}

	public void delibererElementRattrapage(Integer consideration) {
		double coefficient = 0;
		note_element = 0d;
		if (consideration == 1) {
			for (Note noteElementA : notes) {
				if (noteElementA.getType().equals(TypeNote.EXAM_ORDINAIRE))
					continue;
				note_element = note_element + noteElementA.getCoeficient() * noteElementA.getNote();
				coefficient = coefficient + noteElementA.getCoeficient();
			}
			note_element = note_element / coefficient;
		} else {
			for (Note noteElementA : notes) {
				if (noteElementA.getType().equals(TypeNote.EXAM_RATTRAPAGE)) {
					note_element = noteElementA.getNote();
					break;
				}
			}
			note_element = note_element / coefficient;
		}

	}
}

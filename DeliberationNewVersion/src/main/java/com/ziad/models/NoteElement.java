package com.ziad.models;

import static java.lang.Math.max;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ziad.enums.DeliberationType;
import com.ziad.enums.Etat;
import com.ziad.enums.TypeNote;
import com.ziad.models.compositeid.ComposedInscriptionPedagogique;

@Entity
@Table(name = "note_element")
public class NoteElement implements Serializable,NoteNorm {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ComposedInscriptionPedagogique idCompose;

	@Column(name = "note_element")
	private Double note_element = 0d;
	
	private Double ordinaireNote = 0d;
	
	private Double rattrapageNote = 0d;
		
	@Column(name = "etat")
	private String etat = "";
	

	private boolean isValid = false;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "noteelement")
	private List<Note> notes = new ArrayList<Note>();

	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.PERSIST})
	private AnneeAcademique annee_academique;
	
	public NoteElement() {
		
	}
	
	public NoteElement(Double note_element, AnneeAcademique annee_academique) {
		super();
		this.note_element = arrondir(note_element);
		this.annee_academique = annee_academique;
	}

	public NoteElement(ComposedInscriptionPedagogique idCompose, Double note_element,
			AnneeAcademique annee_academique) {
		super();
		this.idCompose = idCompose;
		this.note_element = arrondir(note_element);
		this.annee_academique = annee_academique;		
	}

	public ComposedInscriptionPedagogique getIdCompose() {
		return idCompose;
	}

	public void setIdCompose(ComposedInscriptionPedagogique idCompose) {
		this.idCompose = idCompose;
	}

	public Double getNote_element() {
		return arrondir(note_element);
	}

	public void setNote_element(Double note_element) {
		this.note_element = arrondir(note_element);
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
		ordinaireNote  = 0d;
		double coefficient = 0;
		etat = DeliberationType.ORDINAIRE.name();
		for (Note noteElementA : notes) {
			if (noteElementA.getType().equals(TypeNote.EXAM_RATTRAPAGE))
				continue;
			ordinaireNote = ordinaireNote + noteElementA.getCoeficient() * noteElementA.getNote();
			coefficient = coefficient +  noteElementA.getCoeficient();
		}
		ordinaireNote = ordinaireNote / coefficient;
		note_element = ordinaireNote;
		
		if (note_element >= getElement().getValidation()) {
			isValid = true;
		}else {
			isValid = false;
		}
	}
	
	

	public Double getOrdinaireNote() {
		return ordinaireNote;
	}

	public void setOrdinaireNote(Double ordinaireNote) {
		this.ordinaireNote = ordinaireNote;
	}

	public Double getRattrapageNote() {
		return rattrapageNote;
	}

	public void setRattrapageNote(Double rattrapageNote) {
		this.rattrapageNote = rattrapageNote;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public void delibererElementRattrapage(Integer consideration) {
		double coefficient = 0;
		rattrapageNote = 0d;
		etat = DeliberationType.RATTRAPAGE.name();
		if (consideration == 1) {
			for (Note noteElementA : notes) {
				if (noteElementA.getType().equals(TypeNote.EXAM_ORDINAIRE))
					continue;
				rattrapageNote = rattrapageNote + noteElementA.getCoeficient() * noteElementA.getNote();
				coefficient = coefficient + noteElementA.getCoeficient();
			}
			rattrapageNote = rattrapageNote / coefficient;
		} else {
			for (Note noteElementA : notes) {
				if (noteElementA.getType().equals(TypeNote.EXAM_RATTRAPAGE)) {
					rattrapageNote = noteElementA.getNote();
					break;
				}
			}
		}
		note_element = max(ordinaireNote, rattrapageNote);
	}
	
	
	
	@Override
	public String toString() {
		return "NoteElement [idCompose=" + idCompose + ", note_element=" + note_element + ", isValid=" + isValid
				+ ", notes=" + notes + ", annee_academique=" + annee_academique + "]";
	}
	
	public double arrondir(Double note) {
		return Math.round(note * 100.0)/100.0;
	}

	@Override
	public Double getNote() {
		return getNote_element();
	}

	@Override
	public void setNote(Double note) {
		setNote_element(note);
		
	}

	@Override
	public void calculState() {
		Element element = idCompose.getElement();
		if(note_element>=element.getValidation()) {
			etat = Etat.VALIDE.name();
		}else {
			etat = Etat.COMPONSE.name();
		}
		
	}

	@Override
	public Long getIdStudent() {
		return idCompose.getEtudiant().getId_etudiant();
	}



	
}

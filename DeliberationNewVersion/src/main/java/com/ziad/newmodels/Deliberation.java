package com.ziad.newmodels;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ziad.models.AnneeAcademique;

@Entity
@Table(name = "deliberation")
public class Deliberation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idDeliberation;
	@Enumerated(EnumType.STRING)
	private DeliberationType typeDeliberation;
	@Enumerated(EnumType.STRING)
	private DeliberationType typeExamen;

	@OneToOne(cascade = CascadeType.PERSIST)
	private AnneeAcademique anneeAcademique;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "deliberation")
	private ArrayList<NoteSemestre> notesSemestre = new ArrayList<NoteSemestre>();
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "deliberation")
	private ArrayList<NoteModule> notesModule = new ArrayList<NoteModule>();
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "deliberation")
	private ArrayList<NoteEtape> notesEtape = new ArrayList<NoteEtape>();

	public Deliberation(Long idDeliberation, DeliberationType typeDeliberation, DeliberationType typeExamen,
			AnneeAcademique anneeAcademique) {
		super();
		this.idDeliberation = idDeliberation;
		this.typeDeliberation = typeDeliberation;
		this.typeExamen = typeExamen;
		this.anneeAcademique = anneeAcademique;
	}

	public Deliberation() {

	}

	public Deliberation(DeliberationType typeDeliberation, DeliberationType typeExamen,
			AnneeAcademique anneeAcademique) {
		super();
		this.typeDeliberation = typeDeliberation;
		this.typeExamen = typeExamen;
		this.anneeAcademique = anneeAcademique;
	}

	public Long getIdDeliberation() {
		return idDeliberation;
	}

	public void setIdDeliberation(Long idDeliberation) {
		this.idDeliberation = idDeliberation;
	}

	public DeliberationType getTypeDeliberation() {
		return typeDeliberation;
	}

	public void setTypeDeliberation(DeliberationType typeDeliberation) {
		this.typeDeliberation = typeDeliberation;
	}

	public DeliberationType getTypeExamen() {
		return typeExamen;
	}

	public void setTypeExamen(DeliberationType typeExamen) {
		this.typeExamen = typeExamen;
	}

	public AnneeAcademique getAnneeAcademique() {
		return anneeAcademique;
	}

	public void setAnneeAcademique(AnneeAcademique anneeAcademique) {
		this.anneeAcademique = anneeAcademique;
	}

	public ArrayList<NoteSemestre> getNotesSemestre() {
		return notesSemestre;
	}

	public void setNotesSemestre(ArrayList<NoteSemestre> notesSemestre) {
		this.notesSemestre = notesSemestre;
	}

	public ArrayList<NoteModule> getNotesModule() {
		return notesModule;
	}

	public void setNotesModule(ArrayList<NoteModule> notesModule) {
		this.notesModule = notesModule;
	}

	public ArrayList<NoteEtape> getNotesEtape() {
		return notesEtape;
	}

	public void setNotesEtape(ArrayList<NoteEtape> notesEtape) {
		this.notesEtape = notesEtape;
	}
	
	
	public void addNoteModule(NoteModule note) {
		notesModule.add(note);
	}
	
	public void addNoteSemestre(NoteSemestre note) {
		notesSemestre.add(note);
	}
	
	public void addNoteEtape(NoteEtape note) {
		notesEtape.add(note);
	}
}

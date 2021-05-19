package com.ziad.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "deliberation")
public class Deliberation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idDeliberation;
	
	private String typeDeliberation;

	@OneToOne(cascade = CascadeType.PERSIST)
	private AnneeAcademique anneeAcademique;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(nullable=true)
	private Modulee module = null;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(nullable=true)
	private Semestre semestre = null;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(nullable=true)
	private Etape etape = null;
		
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "deliberation")
	private List<NoteSemestre> notesSemestre = new ArrayList<NoteSemestre>();
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "deliberation")
	private List<NoteModule> notesModule = new ArrayList<NoteModule>();
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "deliberation")
	private List<NoteEtape> notesEtape = new ArrayList<NoteEtape>();

	public Deliberation() {
		
	}

	public Deliberation(String typeDeliberation, AnneeAcademique anneeAcademique,
			Modulee module, Semestre semestre, Etape etape) {
		super();
		this.typeDeliberation = typeDeliberation;
		this.anneeAcademique = anneeAcademique;
		this.module = module;
		this.semestre = semestre;
		this.etape = etape;
	}

	public Deliberation(Long idDeliberation, String typeDeliberation,
			AnneeAcademique anneeAcademique, Modulee module, Semestre semestre, Etape etape) {
		super();
		this.idDeliberation = idDeliberation;
		this.typeDeliberation = typeDeliberation;
		this.anneeAcademique = anneeAcademique;
		this.module = module;
		this.semestre = semestre;
		this.etape = etape;
	}

	public Long getIdDeliberation() {
		return idDeliberation;
	}

	public void setIdDeliberation(Long idDeliberation) {
		this.idDeliberation = idDeliberation;
	}

	public String getTypeDeliberation() {
		return typeDeliberation;
	}

	public void setTypeDeliberation(String typeDeliberation) {
		this.typeDeliberation = typeDeliberation;
	}

	public AnneeAcademique getAnneeAcademique() {
		return anneeAcademique;
	}

	public void setAnneeAcademique(AnneeAcademique anneeAcademique) {
		this.anneeAcademique = anneeAcademique;
	}

	public List<NoteSemestre> getNotesSemestre() {
		return notesSemestre;
	}

	public void setNotesSemestre(ArrayList<NoteSemestre> notesSemestre) {
		this.notesSemestre = notesSemestre;
	}

	public List<NoteModule> getNotesModule() {
		return notesModule;
	}

	public void setNotesModule(ArrayList<NoteModule> notesModule) {
		this.notesModule = notesModule;
	}

	public List<NoteEtape> getNotesEtape() {
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

	@Override
	public String toString() {
		String ch = "";
		for (NoteModule notemodule : notesModule) {
			ch = ch + notemodule.getEtat() + " : " + notemodule.getNote();
		}
		
		return "Deliberation [idDeliberation=" + idDeliberation + ", typeDeliberation=" + typeDeliberation
				+ ", anneeAcademique=" + anneeAcademique + ", module=" + module + ", semestre=" + semestre + ", etape="
				+ etape + ch +  "]" ;
		
	}
	
	
	
}

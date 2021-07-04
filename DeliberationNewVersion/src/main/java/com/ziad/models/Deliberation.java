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

	private boolean delibered = false;

	@OneToOne(cascade = { CascadeType.DETACH })
	private AnneeAcademique anneeAcademique;
	@OneToOne(cascade = { CascadeType.DETACH })
	@JoinColumn(nullable = true)
	private Modulee module = null;
	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(nullable = true)
	private Semestre semestre = null;
	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(nullable = true)
	private Etape etape = null;

	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(nullable = true)
	private Element element = null;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "deliberation")
	private List<NoteSemestre> notesSemestre = new ArrayList<NoteSemestre>();
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "deliberation")
	private List<NoteModule> notesModule = new ArrayList<NoteModule>();
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "deliberation")
	private List<NoteEtape> notesEtape = new ArrayList<NoteEtape>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "deliberation")
	private List<NoteElement> notesElement = new ArrayList<NoteElement>();

	public Deliberation() {

	}

	public Deliberation(boolean delibered, AnneeAcademique anneeAcademique, Modulee module, Semestre semestre,
			Etape etape, Element element, List<NoteSemestre> notesSemestre, List<NoteModule> notesModule,
			List<NoteEtape> notesEtape, List<NoteElement> notesElement) {
		super();
		this.delibered = delibered;
		this.anneeAcademique = anneeAcademique;
		this.module = module;
		this.semestre = semestre;
		this.etape = etape;
		this.element = element;
		this.notesSemestre = notesSemestre;
		this.notesModule = notesModule;
		this.notesEtape = notesEtape;
		this.notesElement = notesElement;
	}

	public Deliberation(Long idDeliberation, boolean delibered, AnneeAcademique anneeAcademique, Modulee module,
			Semestre semestre, Etape etape, Element element, List<NoteSemestre> notesSemestre,
			List<NoteModule> notesModule, List<NoteEtape> notesEtape, List<NoteElement> notesElement) {
		super();
		this.idDeliberation = idDeliberation;
		this.delibered = delibered;
		this.anneeAcademique = anneeAcademique;
		this.module = module;
		this.semestre = semestre;
		this.etape = etape;
		this.element = element;
		this.notesSemestre = notesSemestre;
		this.notesModule = notesModule;
		this.notesEtape = notesEtape;
		this.notesElement = notesElement;
	}

	public Long getIdDeliberation() {
		return idDeliberation;
	}

	public void setIdDeliberation(Long idDeliberation) {
		this.idDeliberation = idDeliberation;
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

	public boolean isDelibered() {
		return delibered;
	}

	public void setDelibered(boolean delibered) {
		this.delibered = delibered;
	}

	public Modulee getModule() {
		return module;
	}

	public void setModule(Modulee module) {
		this.module = module;
	}

	public Semestre getSemestre() {
		return semestre;
	}

	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}

	public Etape getEtape() {
		return etape;
	}

	public void setEtape(Etape etape) {
		this.etape = etape;
	}

	public void setNotesSemestre(List<NoteSemestre> notesSemestre) {
		this.notesSemestre = notesSemestre;
	}

	public void setNotesModule(List<NoteModule> notesModule) {
		this.notesModule = notesModule;
	}

	public void setNotesEtape(List<NoteEtape> notesEtape) {
		this.notesEtape = notesEtape;
	}

	public Element getElement() {
		return element;
	}

	public void setElement(Element element) {
		this.element = element;
	}

	public List<NoteElement> getNotesElement() {
		return notesElement;
	}

	public void setNotesElement(List<NoteElement> notesElement) {
		this.notesElement = notesElement;
	}

	@Override
	public String toString() {
		String ch = "";
		for (NoteModule notemodule : notesModule) {
			ch = ch + notemodule.getEtat() + " : " + notemodule.getNote();
		}

		return "Deliberation [idDeliberation=" + idDeliberation + ", anneeAcademique=" + anneeAcademique + ", module="
				+ module + ", semestre=" + semestre + ", etape=" + etape + ch + "]";

	}

}

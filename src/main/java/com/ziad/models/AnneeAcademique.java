package com.ziad.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "anneeacademique")
public class AnneeAcademique implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_annee_academique;

	@Column(name = "annee_academique")
	private int annee_academique;
	
	
	
	/**
	 * Relations
	 * 
	 */
	@OneToMany(cascade = {CascadeType.ALL},mappedBy="annee_academique")
	private List<NoteElement> notesElement = new ArrayList<NoteElement>();
	
	@OneToMany(cascade = {CascadeType.ALL},mappedBy="anneeAcademique")
	private List<NoteModule> notesModule = new ArrayList<NoteModule>();
	
	@OneToMany(cascade = {CascadeType.ALL},mappedBy="anneeAcademique")
	private List<NoteSemestre> notesSemestre = new ArrayList<NoteSemestre>();
	
	@OneToMany(cascade = {CascadeType.ALL},mappedBy="anneeAcademique")
	private List<NoteEtape> notesEtape = new ArrayList<NoteEtape>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "annee_academique")
	private List<InscriptionAdministrative> liste_inscription_administrative = new ArrayList<InscriptionAdministrative>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "annee_academique")
	private List<InscriptionPedagogique> liste_inscription_pedagogique = new ArrayList<InscriptionPedagogique>();

	public AnneeAcademique() {

	}

	public AnneeAcademique(int annee_academique) {
		super();
		this.annee_academique = annee_academique;
	}

	public AnneeAcademique(Long id_annee_academique, int annee_academique) {
		super();
		this.id_annee_academique = id_annee_academique;
		this.annee_academique = annee_academique;
	}

	public Long getId_annee_academique() {
		return id_annee_academique;
	}

	public void setId_annee_academique(Long id_annee_academique) {
		this.id_annee_academique = id_annee_academique;
	}

	public int getAnnee_academique() {
		return annee_academique;
	}

	public void setAnnee_academique(int annee_academique) {
		this.annee_academique = annee_academique;
	}

	public List<InscriptionAdministrative> getListe_inscription_administrative() {
		return liste_inscription_administrative;
	}

	public void setListe_inscription_administrative(List<InscriptionAdministrative> liste_inscription_administrative) {
		this.liste_inscription_administrative = liste_inscription_administrative;
	}

	public List<InscriptionPedagogique> getListe_inscription_pedagogique() {
		return liste_inscription_pedagogique;
	}

	public void setListe_inscription_pedagogique(List<InscriptionPedagogique> liste_inscription_pedagogique) {
		this.liste_inscription_pedagogique = liste_inscription_pedagogique;
	}

	public List<NoteElement> getNotesElement() {
		return notesElement;
	}

	public void setNotesElement(List<NoteElement> notesElement) {
		this.notesElement = notesElement;
	}

	public List<NoteModule> getNotesModule() {
		return notesModule;
	}

	public void setNotesModule(List<NoteModule> notesModule) {
		this.notesModule = notesModule;
	}

	public List<NoteSemestre> getNotesSemestre() {
		return notesSemestre;
	}

	public void setNotesSemestre(List<NoteSemestre> notesSemestre) {
		this.notesSemestre = notesSemestre;
	}

	public List<NoteEtape> getNotesEtape() {
		return notesEtape;
	}

	public void setNotesEtape(List<NoteEtape> notesEtape) {
		this.notesEtape = notesEtape;
	}

	@Override
	public String toString() {
		return annee_academique + "/" + (annee_academique + 1);
	}	
	
	
}

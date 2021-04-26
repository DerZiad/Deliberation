package com.ziad.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ziad.models.compositeid.ComposeEtudiantElementId;
/*
 * 
 * 
 * 
 * 
 *    Classe associative entre etudiant et module qui contient ses notes
 * 
 * 
 * 
 * 
 * ***/
@Entity
@Table(name = "note_element")
public class NoteElement implements Serializable{

	/******
	 * 
	 * 
	 * Idée composé de l'id d'etudiant et d'element
	 * 
	 * 
	 *****/
	@EmbeddedId
	private ComposeEtudiantElementId id_noteelement;

	@Column(name = "note_element")
	private Double note_element;

	@Column(name = "coeficient")
	private Double coeficient;

	public NoteElement() {

	}

	public NoteElement(ComposeEtudiantElementId id_noteelement, Double note_element, Double coeficient) {
		super();
		this.id_noteelement = id_noteelement;
		this.note_element = note_element;
		this.coeficient = coeficient;
	}

	public ComposeEtudiantElementId getId_noteelement() {
		return id_noteelement;
	}

	public void setId_noteelement(ComposeEtudiantElementId id_noteelement) {
		this.id_noteelement = id_noteelement;
	}

	public Double getNote_element() {
		return note_element;
	}

	public void setNote_element(Double note_element) {
		this.note_element = note_element;
	}

	public Double getCoeficient() {
		return coeficient;
	}

	public void setCoeficient(Double coeficient) {
		this.coeficient = coeficient;
	}

}

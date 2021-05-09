package com.ziad.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ziad.enums.TypeNote;

@Entity
@Table(name = "notes")
public class Note {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idNote;

	@Column(name = "note")
	private Double note;

	@Column(name = "coeficient")
	private Double coeficient;

	@Column(name = "Type_note")
	@Enumerated(EnumType.STRING)
	private TypeNote type;

	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH })
	private NoteElement noteelement;

	public Note(Double note, Double coeficient, TypeNote type, NoteElement noteelement) {
		super();
		this.note = note;
		this.coeficient = coeficient;
		this.type = type;
		this.noteelement = noteelement;
	}

	public Note(Long idNote, Double note, Double coeficient, TypeNote type, NoteElement noteelement) {
		super();
		this.idNote = idNote;
		this.note = note;
		this.coeficient = coeficient;
		this.type = type;
		this.noteelement = noteelement;
	}

	public Long getIdNote() {
		return idNote;
	}

	public void setIdNote(Long idNote) {
		this.idNote = idNote;
	}

	public Double getNote() {
		return note;
	}

	public void setNote(Double note) {
		this.note = note;
	}

	public Double getCoeficient() {
		return coeficient;
	}

	public void setCoeficient(Double coeficient) {
		this.coeficient = coeficient;
	}

	public TypeNote getType() {
		return type;
	}

	public void setType(TypeNote type) {
		this.type = type;
	}

	public NoteElement getNoteelement() {
		return noteelement;
	}

	public void setNoteelement(NoteElement noteelement) {
		this.noteelement = noteelement;
	}

}

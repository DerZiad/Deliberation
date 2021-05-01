package com.ziad.models;

import java.io.Serializable;

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
@Table(name = "note_element")
public class NoteElement implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_note_element;

	@Column(name = "note_element")
	private Double note_element;

	@Column(name = "coeficient")
	private Double coeficient;

	@Column(name = "Type_note")
	@Enumerated(EnumType.STRING)
	private TypeNote type;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.PERSIST })
	private InscriptionPedagogique inscription_pedagogique;

	public NoteElement() {

	}

	public NoteElement(Long id_note_element, Double note_element, Double coeficient, TypeNote type,
			InscriptionPedagogique inscription_pedagogique) {
		this(note_element, coeficient, type, inscription_pedagogique);
		this.id_note_element = id_note_element;
	}

	public NoteElement(Double note_element, Double coeficient, TypeNote type,
			InscriptionPedagogique inscription_pedagogique) {
		super();
		this.note_element = note_element;
		this.coeficient = coeficient;
		this.type = type;
		this.inscription_pedagogique = inscription_pedagogique;
	}

	public Long getId_note_element() {
		return id_note_element;
	}

	public void setId_note_element(Long id_note_element) {
		this.id_note_element = id_note_element;
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

	public TypeNote getType() {
		return type;
	}

	public void setType(TypeNote type) {
		this.type = type;
	}

	public InscriptionPedagogique getInscription_pedagogique() {
		return inscription_pedagogique;
	}

	public void setInscription_pedagogique(InscriptionPedagogique inscription_pedagogique) {
		this.inscription_pedagogique = inscription_pedagogique;
	}
}

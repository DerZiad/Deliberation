package com.ziad.models.compositeid;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.ziad.models.Element;
import com.ziad.models.Etudiant;
/*
 * 
 *  Classe que sera Une composé de Primary Key
 * 
 * **/
@Embeddable
public class ComposeEtudiantElementId implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "element")
	private Element element;

	/**
	 * 
	 * Etudiant qui a cette note
	 * 
	 */
	@OneToOne
	@JoinColumn(name = "etudiant")
	private Etudiant etudiant;

	public ComposeEtudiantElementId(Element element, Etudiant etudiant) {
		super();
		this.element = element;
		this.etudiant = etudiant;
	}

	public Element getElement() {
		return element;
	}

	public void setElement(Element element) {
		this.element = element;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

}

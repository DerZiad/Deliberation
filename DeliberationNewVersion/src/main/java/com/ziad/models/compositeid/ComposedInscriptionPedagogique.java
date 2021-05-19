package com.ziad.models.compositeid;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;

import com.ziad.models.Element;
import com.ziad.models.Etudiant;

@Embeddable
public class ComposedInscriptionPedagogique implements Serializable {

	/**
	 * Class identity key
	 */
	private static final long serialVersionUID = 1L;
	@OneToOne(targetEntity = Etudiant.class,cascade = CascadeType.PERSIST)
	private Etudiant etudiant;
	@OneToOne(targetEntity = Element.class,cascade = CascadeType.PERSIST)
	private Element element;
	
	public ComposedInscriptionPedagogique() {
		
	}
	public ComposedInscriptionPedagogique(Etudiant etudiant, Element element) {
		super();
		this.etudiant = etudiant;
		this.element = element;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	public Element getElement() {
		return element;
	}

	public void setElement(Element element) {
		this.element = element;
	}
	@Override
	public String toString() {
		return "ComposedInscriptionPedagogique [etudiant=" + etudiant + ", element=" + element + "]";
	}
	
}

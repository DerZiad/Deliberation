package com.ziad.models.compositeid;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import com.ziad.models.AnneeAcademique;
import com.ziad.models.Element;
import com.ziad.models.Etudiant;

@Embeddable
public class ComposedInscriptionPedagogique implements Serializable {

	/**
	 * Class identity key
	 */
	private static final long serialVersionUID = 1L;
	@OneToOne(fetch = FetchType.EAGER,targetEntity = Etudiant.class,cascade = CascadeType.PERSIST)
	private Etudiant etudiant;
	@OneToOne(fetch = FetchType.EAGER,targetEntity = Element.class,cascade = CascadeType.PERSIST)
	private Element element;
	@OneToOne(fetch = FetchType.EAGER,targetEntity = AnneeAcademique.class,cascade = CascadeType.PERSIST)
	private AnneeAcademique anneeAcaqemique;
	
	public ComposedInscriptionPedagogique() {
		
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

	public AnneeAcademique getAnneeAcaqemique() {
		return anneeAcaqemique;
	}

	public void setAnneeAcaqemique(AnneeAcademique anneeAcaqemique) {
		this.anneeAcaqemique = anneeAcaqemique;
	}

	public ComposedInscriptionPedagogique(Etudiant etudiant, Element element, AnneeAcademique anneeAcaqemique) {
		super();
		this.etudiant = etudiant;
		this.element = element;
		this.anneeAcaqemique = anneeAcaqemique;
	}
	
	
}

package com.ziad.deliberation;

import javax.persistence.Embeddable;

import com.ziad.models.AnneeAcademique;

@Embeddable
public class ComposedKey {
	private AnneeAcademique annee;
	private Long idElement;

	public ComposedKey(AnneeAcademique annee, Long idElement) {
		super();
		this.annee = annee;
		this.idElement = idElement;
	}

	public AnneeAcademique getAnnee() {
		return annee;
	}

	public void setAnnee(AnneeAcademique annee) {
		this.annee = annee;
	}

	public Long getIdElement() {
		return idElement;
	}

	public void setIdElement(Long idElement) {
		this.idElement = idElement;
	}

}

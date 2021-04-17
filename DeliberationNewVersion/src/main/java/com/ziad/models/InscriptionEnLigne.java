package com.ziad.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "InscriptionEnLigne")
public class InscriptionEnLigne {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_inscription_en_ligne")
	private long id_inscription_en_ligne;

	@OneToOne(cascade = CascadeType.ALL)
	private Etudiant etudiant;

	/**
	 * L'inscription en ligne est accepté
	 */
	@Column(name = "accepted")
	private int accepted;
	
	
	public InscriptionEnLigne(Etudiant etudiant, int accepted) {
		super();
		this.etudiant = etudiant;
		this.accepted = accepted;
	}

	public InscriptionEnLigne(long id_inscription_en_ligne, Etudiant etudiant, int accepted) {
		super();
		this.id_inscription_en_ligne = id_inscription_en_ligne;
		this.etudiant = etudiant;
		this.accepted = accepted;
	}
	
	public InscriptionEnLigne() {
		
	}
	public long getId_inscription_en_ligne() {
		return id_inscription_en_ligne;
	}

	public void setId_inscription_en_ligne(long id_inscription_en_ligne) {
		this.id_inscription_en_ligne = id_inscription_en_ligne;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	public int getAccepted() {
		return accepted;
	}

	public void setAccepted(int accepted) {
		this.accepted = accepted;
	}

}
package com.ziad.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="adresses")
public class Adresse {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_adresse;

	@Column(name = "rue")
	private int rue;

	@Column(name = "ville")
	private String ville;

	@Column(name = "description_de_logement")
	private String description_de_logement;

	@ManyToOne(targetEntity = InscriptionAdministrative.class , cascade= CascadeType.PERSIST)
	private InscriptionAdministrative inscription_administrative;

	public Adresse(int rue, String ville, String description_de_logement,
			InscriptionAdministrative inscription_administrative) {
		super();
		this.rue = rue;
		this.ville = ville;
		this.description_de_logement = description_de_logement;
		this.inscription_administrative = inscription_administrative;
	}

	public Adresse(int id_adresse, int rue, String ville, String description_de_logement,
			InscriptionAdministrative inscription_administrative) {
		super();
		this.id_adresse = id_adresse;
		this.rue = rue;
		this.ville = ville;
		this.description_de_logement = description_de_logement;
		this.inscription_administrative = inscription_administrative;
	}

	public int getId_adresse() {
		return id_adresse;
	}

	public void setId_adresse(int id_adresse) {
		this.id_adresse = id_adresse;
	}

	public int getRue() {
		return rue;
	}

	public void setRue(int rue) {
		this.rue = rue;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getDescription_de_logement() {
		return description_de_logement;
	}

	public void setDescription_de_logement(String description_de_logement) {
		this.description_de_logement = description_de_logement;
	}

	public InscriptionAdministrative getInscription_administrative() {
		return inscription_administrative;
	}

	public void setInscription_administrative(InscriptionAdministrative inscription_administrative) {
		this.inscription_administrative = inscription_administrative;
	}

}

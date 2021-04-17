package com.ziad.models;

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

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "Etablissement")
public class Etablissement {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id_etablissement;

	@Column(name = "name")
	private String nom_etablissement;
	
	@OneToMany(targetEntity = Filiere.class,mappedBy = "etablissement",cascade = CascadeType.ALL)
	@NotFound(action = NotFoundAction.IGNORE)
	private List<Filiere> filieres = new ArrayList<Filiere>();
	
	
	public Etablissement(String nom_etablissement, List<Filiere> filieres) {
		super();
		this.nom_etablissement = nom_etablissement;
		this.filieres = filieres;
	}

	public Etablissement() {
	}

	public Etablissement(long id_etablissement, String nom_etablissement, List<Filiere> filieres) {
		super();
		this.id_etablissement = id_etablissement;
		this.nom_etablissement = nom_etablissement;
		this.filieres = filieres;
	}

	public long getId_etablissement() {
		return id_etablissement;
	}

	public void setId_etablissement(long id_etablissement) {
		this.id_etablissement = id_etablissement;
	}

	public String getNom_etablissement() {
		return nom_etablissement;
	}

	public void setNom_etablissement(String nom_etablissement) {
		this.nom_etablissement = nom_etablissement;
	}

	public List<Filiere> getFilieres() {
		return filieres;
	}

	public void setFilieres(List<Filiere> filieres) {
		this.filieres = filieres;
	}

}

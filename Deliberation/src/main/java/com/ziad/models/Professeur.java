package com.ziad.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ziad.enums.MonRole;

@Entity
@Table(name = "Professeur")
public class Professeur implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_professeur")
	private long id_professeur;

	@Column(name = "nom_professeur")
	private String nom_professeur;

	@Column(name = "prenom_professeur")
	private String prenom_professeur;

	@Column(name = "email_professeur")
	private String email_professeur;
	
	
	/**
	 * Relations
	 * */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user", foreignKey = @ForeignKey(name = "fk_user"))
	private User user;

	@OneToMany(targetEntity = Modulee.class, mappedBy = "responsable_module", cascade = CascadeType.ALL)
	private List<Modulee> modules = new ArrayList<Modulee>();

	@ManyToMany(targetEntity = Element.class, mappedBy = "professeurs", cascade = CascadeType.ALL)
	private List<Element> elements = new ArrayList<Element>();

	@OneToMany(targetEntity = Filiere.class, mappedBy = "responsable_filiere", cascade = CascadeType.ALL)
	private List<Filiere> filieres = new ArrayList<Filiere>();

	public Professeur() {

	}

	public Professeur(String nom_professeur, String prenom_professeur, String email_professeur, User user) {
		super();
		this.nom_professeur = nom_professeur;
		this.prenom_professeur = prenom_professeur;
		this.email_professeur = email_professeur;
		this.user = user;

	}

	public Professeur(long id_professeur, String nom_professeur, String prenom_professeur, String email_professeur,
			User user) {
		super();
		this.id_professeur = id_professeur;
		this.nom_professeur = nom_professeur;
		this.prenom_professeur = prenom_professeur;
		this.email_professeur = email_professeur;
		this.user = user;
	}

	public long getId_professeur() {
		return id_professeur;
	}

	public void setId_professeur(long id_professeur) {
		this.id_professeur = id_professeur;
	}

	public String getNom_professeur() {
		return nom_professeur;
	}

	public void setNom_professeur(String nom_professeur) {
		this.nom_professeur = nom_professeur;
	}

	public String getPrenom_professeur() {
		return prenom_professeur;
	}

	public void setPrenom_professeur(String prenom_professeur) {
		this.prenom_professeur = prenom_professeur;
	}

	public String getEmail_professeur() {
		return email_professeur;
	}

	public void setEmail_professeur(String email_professeur) {
		this.email_professeur = email_professeur;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Modulee> getModules() {
		return modules;
	}

	public void setModules(List<Modulee> modules) {
		this.modules = modules;
	}

	public List<Element> getElements() {
		return elements;
	}

	public void setElements(List<Element> elements) {
		this.elements = elements;
	}

	public List<Filiere> getFilieres() {
		return filieres;
	}

	public void setFilieres(List<Filiere> filieres) {
		this.filieres = filieres;
	}

	public void addFilere(Filiere filiere) {
		filieres.add(filiere);
	}

	public void addModule(Modulee module) {
		modules.add(module);
	}

	public void addElement(Element element) {
		elements.add(element);
	}
	public boolean isResponsableFiliere() {
		return user.getRoles().contains(MonRole.ROLERESPONSABLEFILIERE.getRole());
	}
	public boolean isResponsableModule() {
		return user.getRoles().contains(MonRole.ROLERESPONSABLEMODULE.getRole());
	}
}
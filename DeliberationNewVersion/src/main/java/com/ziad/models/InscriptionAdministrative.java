package com.ziad.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "InscriptionAdministrative")
public class InscriptionAdministrative {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_inscription_administrative")
	private long id_inscription_administrative;

	@ManyToOne(targetEntity = AnneeAcademique.class, cascade = CascadeType.ALL)
	private AnneeAcademique annee_academique;

	@Column(name = "date_pre_inscription")
	private Date date_pre_inscription;

	@Column(name = "date_valid_inscription")
	private Date date_valid_inscription;

	@Column(name = "operateur")
	private String operateur;

	@Column(name = "bourse")
	private boolean bourse;

	@Lob
	private byte[] photo;

	@Lob
	private byte[] bac;

	@Lob
	private byte[] releve_note;

	@Lob
	private byte[] acte_naissance;

	@Lob
	private byte[] cin;

	@Lob
	private byte[] document1;

	@Lob
	private byte[] document2;

	private String encodedPhoto;

	private String encodedBac;

	private String encodedRv;

	private String encodedAn;

	private String encodedCin;

	@OneToMany(cascade = CascadeType.ALL)
	private List<DocumentDePlus> documents;

	@OneToMany(targetEntity = Adresse.class,cascade = CascadeType.ALL,mappedBy = "inscription_administrative")
	private List<Adresse> adresses;

	public InscriptionAdministrative() {

	}

	public InscriptionAdministrative(AnneeAcademique annee_academique, Date date_pre_inscription,
			Date date_valid_inscription, String operateur, boolean bourse, byte[] photo, byte[] bac, byte[] releve_note,
			byte[] acte_naissance, byte[] cin, byte[] document1, byte[] document2, String encodedPhoto,
			String encodedBac, String encodedRv, String encodedAn, String encodedCin, List<DocumentDePlus> documents,
			List<Adresse> adresses) {
		super();
		this.annee_academique = annee_academique;
		this.date_pre_inscription = date_pre_inscription;
		this.date_valid_inscription = date_valid_inscription;
		this.operateur = operateur;
		this.bourse = bourse;
		this.photo = photo;
		this.bac = bac;
		this.releve_note = releve_note;
		this.acte_naissance = acte_naissance;
		this.cin = cin;
		this.document1 = document1;
		this.document2 = document2;
		this.encodedPhoto = encodedPhoto;
		this.encodedBac = encodedBac;
		this.encodedRv = encodedRv;
		this.encodedAn = encodedAn;
		this.encodedCin = encodedCin;
		this.documents = documents;
		this.adresses = adresses;
	}

	public InscriptionAdministrative(long id_inscription_administrative, AnneeAcademique annee_academique,
			Date date_pre_inscription, Date date_valid_inscription, String operateur, boolean bourse, byte[] photo,
			byte[] bac, byte[] releve_note, byte[] acte_naissance, byte[] cin, byte[] document1, byte[] document2,
			String encodedPhoto, String encodedBac, String encodedRv, String encodedAn, String encodedCin,
			List<DocumentDePlus> documents, List<Adresse> adresses) {
		super();
		this.id_inscription_administrative = id_inscription_administrative;
		this.annee_academique = annee_academique;
		this.date_pre_inscription = date_pre_inscription;
		this.date_valid_inscription = date_valid_inscription;
		this.operateur = operateur;
		this.bourse = bourse;
		this.photo = photo;
		this.bac = bac;
		this.releve_note = releve_note;
		this.acte_naissance = acte_naissance;
		this.cin = cin;
		this.document1 = document1;
		this.document2 = document2;
		this.encodedPhoto = encodedPhoto;
		this.encodedBac = encodedBac;
		this.encodedRv = encodedRv;
		this.encodedAn = encodedAn;
		this.encodedCin = encodedCin;
		this.documents = documents;
		this.adresses = adresses;
	}

	public long getId_inscription_administrative() {
		return id_inscription_administrative;
	}

	public void setId_inscription_administrative(long id_inscription_administrative) {
		this.id_inscription_administrative = id_inscription_administrative;
	}

	public AnneeAcademique getAnnee_academique() {
		return annee_academique;
	}

	public void setAnnee_academique(AnneeAcademique annee_academique) {
		this.annee_academique = annee_academique;
	}

	public Date getDate_pre_inscription() {
		return date_pre_inscription;
	}

	public void setDate_pre_inscription(Date date_pre_inscription) {
		this.date_pre_inscription = date_pre_inscription;
	}

	public Date getDate_valid_inscription() {
		return date_valid_inscription;
	}

	public void setDate_valid_inscription(Date date_valid_inscription) {
		this.date_valid_inscription = date_valid_inscription;
	}

	public String getOperateur() {
		return operateur;
	}

	public void setOperateur(String operateur) {
		this.operateur = operateur;
	}

	public boolean isBourse() {
		return bourse;
	}

	public void setBourse(boolean bourse) {
		this.bourse = bourse;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public byte[] getBac() {
		return bac;
	}

	public void setBac(byte[] bac) {
		this.bac = bac;
	}

	public byte[] getReleve_note() {
		return releve_note;
	}

	public void setReleve_note(byte[] releve_note) {
		this.releve_note = releve_note;
	}

	public byte[] getActe_naissance() {
		return acte_naissance;
	}

	public void setActe_naissance(byte[] acte_naissance) {
		this.acte_naissance = acte_naissance;
	}

	public byte[] getCin() {
		return cin;
	}

	public void setCin(byte[] cin) {
		this.cin = cin;
	}

	public byte[] getDocument1() {
		return document1;
	}

	public void setDocument1(byte[] document1) {
		this.document1 = document1;
	}

	public byte[] getDocument2() {
		return document2;
	}

	public void setDocument2(byte[] document2) {
		this.document2 = document2;
	}

	public String getEncodedPhoto() {
		return encodedPhoto;
	}

	public void setEncodedPhoto(String encodedPhoto) {
		this.encodedPhoto = encodedPhoto;
	}

	public String getEncodedBac() {
		return encodedBac;
	}

	public void setEncodedBac(String encodedBac) {
		this.encodedBac = encodedBac;
	}

	public String getEncodedRv() {
		return encodedRv;
	}

	public void setEncodedRv(String encodedRv) {
		this.encodedRv = encodedRv;
	}

	public String getEncodedAn() {
		return encodedAn;
	}

	public void setEncodedAn(String encodedAn) {
		this.encodedAn = encodedAn;
	}

	public String getEncodedCin() {
		return encodedCin;
	}

	public void setEncodedCin(String encodedCin) {
		this.encodedCin = encodedCin;
	}

	public List<DocumentDePlus> getDocuments() {
		return documents;
	}

	public void setDocuments(List<DocumentDePlus> documents) {
		this.documents = documents;
	}

	public List<Adresse> getAdresses() {
		return adresses;
	}

	public void setAdresses(List<Adresse> adresses) {
		this.adresses = adresses;
	}

}

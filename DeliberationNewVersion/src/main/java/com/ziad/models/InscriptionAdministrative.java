package com.ziad.models;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.codec.binary.Base64;

import com.ziad.models.compositeid.ComposedInscriptionAdministrative;

@Entity
@Table(name = "InscriptionAdministrative")
public class InscriptionAdministrative implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ComposedInscriptionAdministrative composite_association_id;

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

	@Transient
	private String encodedPhoto;

	@Transient
	private String encodedBac;

	@Transient
	private String encodedRv;

	@Transient
	private String encodedAn;

	@Transient
	private String encodedCin;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "inscription_administrative")
	private List<DocumentDePlus> documents;

	public InscriptionAdministrative() {

	}

	public InscriptionAdministrative(AnneeAcademique annee_academique, Date date_pre_inscription,
			Date date_valid_inscription, String operateur, boolean bourse, byte[] photo, byte[] bac, byte[] releve_note,
			byte[] acte_naissance, byte[] cin, String encodedPhoto, String encodedBac, String encodedRv,
			String encodedAn, String encodedCin, List<DocumentDePlus> documents) throws UnsupportedEncodingException {
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
		encodeAll();
	}

	public InscriptionAdministrative(ComposedInscriptionAdministrative composite_association_id,
			AnneeAcademique annee_academique, Date date_pre_inscription, Date date_valid_inscription, String operateur,
			boolean bourse, byte[] photo, byte[] bac, byte[] releve_note, byte[] acte_naissance, byte[] cin,
			String encodedPhoto, String encodedBac, String encodedRv, String encodedAn, String encodedCin,
			List<DocumentDePlus> documents) throws UnsupportedEncodingException {
		this(annee_academique, date_pre_inscription, date_valid_inscription, operateur, bourse, photo, bac, releve_note,
				acte_naissance, cin, encodedPhoto, encodedBac, encodedRv, encodedAn, encodedCin, documents);
		this.composite_association_id = composite_association_id;
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

	public String getEncodedPhoto() throws UnsupportedEncodingException {
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

	public Etudiant getEtudiant() {
		return composite_association_id.getEtudiant();
	}

	public void setEtudiant(Etudiant etudiant) {
		composite_association_id.setEtudiant(etudiant);
	}

	public Filiere getFiliere() {
		return composite_association_id.getFiliere();
	}

	public void setFiliere(Filiere filiere) {
		composite_association_id.setFiliere(filiere);
	}

	public ComposedInscriptionAdministrative getComposite_association_id() {
		return composite_association_id;
	}

	public void setComposite_association_id(ComposedInscriptionAdministrative composite_association_id) {
		this.composite_association_id = composite_association_id;
	}

	public void encodeAll() throws UnsupportedEncodingException {
		if (getPhoto() != null) {
			byte[] encodedphotobyte = Base64.encodeBase64(photo);
			String base64Encoded = new String(encodedphotobyte, "UTF-8");
			setEncodedPhoto(base64Encoded);
		}
		
		if (getBac() != null) {
			byte[] encodedbacbyte = Base64.encodeBase64(bac);
			String base64Encoded = new String(encodedbacbyte, "UTF-8");
			setEncodedBac(base64Encoded);
		}

		if (getReleve_note() != null) {

			byte[] encodeBase64Document1 = Base64.encodeBase64(releve_note);
			String base64Encoded = new String(encodeBase64Document1, "UTF-8");
			setEncodedRv(base64Encoded);
		}
		if (getActe_naissance() != null) {
			byte[] encodeBase64Document1 = Base64.encodeBase64(acte_naissance);
			String base64Encoded = new String(encodeBase64Document1, "UTF-8");
			setEncodedAn(base64Encoded);
		}
		if (getCin() != null) {
			byte[] encodeBase64Document1 = Base64.encodeBase64(cin);
			String base64Encoded = new String(encodeBase64Document1, "UTF-8");
			setEncodedCin(base64Encoded);
		}
	}

}

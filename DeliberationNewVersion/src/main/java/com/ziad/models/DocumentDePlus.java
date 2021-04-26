package com.ziad.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "document_en_plus")
public class DocumentDePlus implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Lob
	private byte[] document;

	private String encoded_document;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private InscriptionAdministrative inscription_administrative;
	
	public DocumentDePlus(int id, byte[] document, String encoded_document,
			InscriptionAdministrative inscription_administrative) {
		super();
		this.id = id;
		this.document = document;
		this.encoded_document = encoded_document;
		this.inscription_administrative = inscription_administrative;
	}

	public DocumentDePlus(byte[] document, String encoded_document,
			InscriptionAdministrative inscription_administrative) {
		super();
		this.document = document;
		this.encoded_document = encoded_document;
		this.inscription_administrative = inscription_administrative;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getDocument() {
		return document;
	}

	public void setDocument(byte[] document) {
		this.document = document;
	}

	public String getEncoded_document() {
		return encoded_document;
	}

	public void setEncoded_document(String encoded_document) {
		this.encoded_document = encoded_document;
	}

	public InscriptionAdministrative getInscription_administrative() {
		return inscription_administrative;
	}

	public void setInscription_administrative(InscriptionAdministrative inscription_administrative) {
		this.inscription_administrative = inscription_administrative;
	}

}

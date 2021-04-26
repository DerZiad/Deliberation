package com.ziad.administrateur.inscription.administrative;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.web.multipart.MultipartFile;

import com.ziad.administrateur.etablissement.DataNotFoundExceptions;
import com.ziad.services.CSVReaderOException;

public interface CrudInscriptionAdministrative {

	public void createInscriptionAdministrative(Long id_annee_academique, Long id_inscription_en_ligne, Long id_filiere,
			MultipartFile photo, MultipartFile bac, MultipartFile relevee_de_note, MultipartFile acte_naissance,
			MultipartFile cin) throws EntityNotFoundException, IOException;

	public ArrayList<Object> prepareInscriptionDatas() throws DataNotFoundExceptions;

	public void deleteInscriptionAdministrative(Long id_etudiant,Long id_filiere) throws EntityNotFoundException;

	public ArrayList<Object> listerInscriptionsAdministratives()
			throws DataNotFoundExceptions, UnsupportedEncodingException;

	public void modifierInscriptionAdministrative(Date date_pre_inscription, Date date_valid_inscription,
			Long id_etudiant, Long id_filiere, MultipartFile photo, MultipartFile bac,
			MultipartFile relevee_note, MultipartFile acte_de_naissance, MultipartFile cin, Long id_annee_academique)
			throws IOException;

	public ArrayList<Object> getInscriptionAdministrative(Long id_filiere,Long id_etudiant) throws EntityNotFoundException;


	public List<String> uploadInscriptionAdministrative(MultipartFile file) throws CSVReaderOException,IOException,FormatReaderException;
}

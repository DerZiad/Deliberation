package com.ziad.services.interfaces;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;

import org.springframework.web.multipart.MultipartFile;

import com.ziad.enums.Gender;
import com.ziad.exceptions.CSVReaderOException;
import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.exceptions.FormatReaderException;
import com.ziad.models.InscriptionAdministrative;

public interface InscritpionAdministrativeInterface {

	public void createInscriptionAdministrative(Long id_annee_academique, Long id_inscription_en_ligne, Long id_filiere,
			MultipartFile photo, MultipartFile bac, MultipartFile relevee_de_note, MultipartFile acte_naissance,
			MultipartFile cin) throws EntityNotFoundException, IOException,MessagingException;

	public ArrayList<Object> prepareInscriptionDatas() throws DataNotFoundExceptions;

	public void deleteInscriptionAdministrative(Long id_etudiant, Long id_filiere) throws EntityNotFoundException;

	public ArrayList<Object> listerInscriptionsAdministratives() throws DataNotFoundExceptions;

	public void modifierInscriptionAdministrative(String last_name_fr, String last_name_ar, String first_name_fr,
			String first_name_ar, String massar_edu, String cne, String nationality, Gender gender, String birth_date,
			String birth_place, String city, String province, Integer bac_year, String bac_type, String mention,
			String high_school, String bac_place, String academy, String date_pre_inscription,
			String date_valid_inscription, Long id_etudiant, Long id_filiere, MultipartFile photo, MultipartFile bac,
			MultipartFile relevee_note, MultipartFile acte_de_naissance, MultipartFile cin, Long id_annee_academique)
			throws IOException;

	public ArrayList<Object> getInscriptionAdministrative(Long id_filiere, Long id_etudiant)
			throws EntityNotFoundException, UnsupportedEncodingException;

	public List<InscriptionAdministrative> listInscriptionAdministrativeByFilter(Long idFiliere, Long idAnneeAcademique,
			Long idSemestre, Long idModule) throws DataNotFoundExceptions, EntityNotFoundException;

	public List<String> uploadInscriptionAdministrative(MultipartFile file)
			throws CSVReaderOException, IOException, FormatReaderException;
}

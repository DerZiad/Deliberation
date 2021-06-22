package com.ziad.service.inscriptionenligne;

import java.util.List;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;

import com.ziad.enums.Gender;
import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.Etablissement;

public interface InscriptionInterface {

	public void createEtudiant(String last_name_fr, String last_name_ar, String first_name_fr, String first_name_ar,
			String massar_edu, String cne, String nationality, Gender gender, String birth_date, String birth_place,
			String city, String province, Integer bac_year, String bac_type, String mention, String high_school,
			String bac_place, String academy,String email,Long id_etablissement) throws EntityNotFoundException,MessagingException;

	
	public void confirmerInscriptionEnLigne(Long id_inscription_en_ligne) throws EntityNotFoundException;

	public List<Etablissement> listerEtablissements() throws DataNotFoundExceptions;
}

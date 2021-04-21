package com.ziad.principale.controllers;

import javax.persistence.EntityNotFoundException;

import com.ziad.administrateur.etablissement.DataNotFoundExceptions;
import com.ziad.enums.Gender;
import com.ziad.models.InscriptionEnLigne;

public interface InscriptionInterface {

	public void createEtudiant(String last_name_fr, String last_name_ar, String first_name_fr, String first_name_ar,
			String massar_edu, String cne, String nationality, Gender gender, String birth_date, String birth_place,
			String city, String province, Integer bac_year, String bac_type, String mention, String high_school,
			String bac_place, String academy,String email) throws EntityNotFoundException;

	
	public void confirmerInscriptionEnLigne(Long id_inscription_en_ligne) throws EntityNotFoundException;
}

package com.ziad.principale.controllers;

import java.util.Date;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ziad.administrateur.etablissement.DataNotFoundExceptions;
import com.ziad.enums.Gender;
import com.ziad.models.Etudiant;
import com.ziad.models.Historique;
import com.ziad.models.InscriptionEnLigne;
import com.ziad.models.User;
import com.ziad.repositories.EtudiantRepository;
import com.ziad.repositories.HistoriqueRepository;
import com.ziad.repositories.InscriptionEnLigneRepository;
import com.ziad.repositories.UserRepository;

@Service
@Primary
public class InscriptionService implements InscriptionInterface {
	@Autowired
	private SendEmailService mailer;

	@Autowired
	private InscriptionEnLigneRepository inscription_en_ligne_repository;

	@Autowired
	private HistoriqueRepository historique_repository;

	@Autowired
	private UserRepository user_repository;
	
	
	private final static String SERVER_LINK = "http://localhost:8080";
	

	@Override
	public void createEtudiant(String last_name_fr, String last_name_ar, String first_name_fr, String first_name_ar,
			String massar_edu, String cne, String nationality, Gender gender, String birth_date, String birth_place,
			String city, String province, Integer bac_year, String bac_type, String mention, String high_school,
			String bac_place, String academy,String email) throws EntityNotFoundException{
		InscriptionEnLigne inscription_en_ligne = new InscriptionEnLigne();
		inscription_en_ligne.setLast_name_fr(last_name_fr);
		inscription_en_ligne.setLast_name_ar(last_name_ar);
		inscription_en_ligne.setFirst_name_fr(first_name_fr);
		inscription_en_ligne.setFirst_name_ar(first_name_ar);
		inscription_en_ligne.setMassar_edu(massar_edu);
		inscription_en_ligne.setCne(cne);
		inscription_en_ligne.setNationality(nationality);
		inscription_en_ligne.setGender(gender);
		inscription_en_ligne.setBirth_date(convertDate(birth_date));
		inscription_en_ligne.setBirth_place(birth_place);
		inscription_en_ligne.setCity(city);
		inscription_en_ligne.setProvince(province);
		inscription_en_ligne.setBac_year(bac_year);
		inscription_en_ligne.setBac_type(bac_type);
		inscription_en_ligne.setMention(mention);
		inscription_en_ligne.setHigh_school(high_school);
		inscription_en_ligne.setBac_place(bac_place);
		inscription_en_ligne.setAcademy(academy);
		inscription_en_ligne.setRegistration_date(new java.util.Date());
		inscription_en_ligne.setEmail(email);
		inscription_en_ligne.setAccepted(0);
		inscription_en_ligne_repository.save(inscription_en_ligne);
		historique_repository
				.save(new Historique("etudiant " + first_name_fr + " " + last_name_fr + " créé", new java.util.Date()));
		mailer.sendEmail(inscription_en_ligne.getEmail(),SERVER_LINK + "/student/confirmation/" + inscription_en_ligne.getId_inscription_en_ligne(),"Confirmation email");	
	}


	@SuppressWarnings({ "deprecation", "unused" })
	private Date convertDate(String dateString) throws EntityNotFoundException{
		try {
			Date date = new Date();
			String year = dateString.substring(0,4);
			String month = dateString.substring(5,7);
			String day = dateString.substring(8,10);
			date.setYear(Integer.parseInt(year));
			date.setMonth(Integer.parseInt(month));
			date.setDate(Integer.parseInt(day));
			return date;
		}catch (Exception e) {
			throw new EntityNotFoundException();
		}
	}

	@Override
	public void confirmerInscriptionEnLigne(Long id_inscription_en_ligne) throws EntityNotFoundException {
		InscriptionEnLigne inscription_en_ligne = inscription_en_ligne_repository.getOne(id_inscription_en_ligne);
		inscription_en_ligne.setAccepted(1);
		inscription_en_ligne_repository.save(inscription_en_ligne);
	}
}

package com.ziad.principale.controllers;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ziad.administrateur.etablissement.DataNotFoundExceptions;
import com.ziad.enums.Gender;

@Controller
public class InscrireController {
		
	private final static String PAGE_SUCCES = "etudiant/succes";
	private final static String PAGE_INSCRIPTION = "etudiant/createStudent";
	
	
	private final static String ATTRIBUT_MESSAGE = "message";
	//For inscription page
	private final static String ATTRIBUT_ETABLISSEMENTS = "etablissements";
	private final static String ATTRIBUT_ETUDIANT_ACTIVE = "createStudent";

	@Autowired
	private InscriptionInterface inscription_metier;

	@GetMapping("/signup")
	public ModelAndView createANewStudent() throws DataNotFoundExceptions{
		ModelAndView model = new ModelAndView(PAGE_INSCRIPTION);
		model.addObject(ATTRIBUT_ETUDIANT_ACTIVE, "mm-active");// will be used in the nav-bar
		model.addObject(ATTRIBUT_ETABLISSEMENTS,inscription_metier.listerEtablissements());
		return model;
	}

	@PostMapping("/signup")
	public ModelAndView receiveStudentData(@RequestParam("last_name_fr") String last_name_fr,
			@RequestParam("last_name_ar") String last_name_ar, @RequestParam("first_name_fr") String first_name_fr,
			@RequestParam("first_name_ar") String first_name_ar, @RequestParam("massar_edu") String massar_edu,
			@RequestParam("cne") String cne, @RequestParam("nationality") String nationality,
			@RequestParam("gender") Gender gender, @RequestParam("birth_date") String birth_date,
			@RequestParam("birth_place") String birth_place, @RequestParam("city") String city,
			@RequestParam("province") String province, @RequestParam("bac_year") Integer bac_year,
			@RequestParam("bac_type") String bac_type, @RequestParam("mention") String mention,
			@RequestParam("high_school") String high_school, @RequestParam("bac_place") String bac_place,
			@RequestParam("academy") String academy, @RequestParam("email") String email,
			@RequestParam("etablissement") Long id_etablissement, HttpServletRequest request)
			throws EntityNotFoundException, MessagingException {
		inscription_metier.createEtudiant(last_name_fr, last_name_ar, first_name_fr, first_name_ar, massar_edu, cne,
				nationality, gender, birth_date, birth_place, city, province, bac_year, bac_type, mention, high_school,
				bac_place, academy, email, id_etablissement);
		ModelAndView model = new ModelAndView(PAGE_SUCCES);
		model.addObject(ATTRIBUT_MESSAGE,"Vous allez recevoir un email de confirmation de votre inscription");
		return model;
	}

	@GetMapping("/student/confirmation/{id_inscription_en_ligne}")
	public ModelAndView confirmerPerSms(@PathVariable("id_inscription_en_ligne") Long id_inscription_en_ligne) {
		inscription_metier.confirmerInscriptionEnLigne(id_inscription_en_ligne);
		ModelAndView model = new ModelAndView(PAGE_SUCCES);
		model.addObject(ATTRIBUT_MESSAGE,"Votre inscription a été confirmé avec succes");
		return model;
	}
}

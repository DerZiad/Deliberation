package com.ziad.principale.controllers;

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
import com.ziad.models.InscriptionEnLigne;

@Controller
public class InscrireController {
	private final static String ATTRIBUT_ERROR = "error";
	private final static String PAGE_CODE_SMS = "etudiant/code.jsp";

	private final static String REDIRECT_CONFIRMER_SMS = "redirect:/student/confirmation/%d";
	private final static String REDIRECT_PAGE_HOME = "redirect:/student";

	@Autowired
	private InscriptionInterface inscription_metier;

	@GetMapping("/signup")
	public ModelAndView createANewStudent() {
		ModelAndView model = new ModelAndView("etudiant/createStudent");
		model.addObject("createStudent", "mm-active");// will be used in the nav-bar
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
			@RequestParam("academy") String academy, @RequestParam("email") String email, HttpServletRequest request)
			throws EntityNotFoundException {
		inscription_metier.createEtudiant(last_name_fr, last_name_ar, first_name_fr, first_name_ar, massar_edu, cne,
				nationality, gender, birth_date, birth_place, city, province, bac_year, bac_type, mention, high_school,
				bac_place, academy, email);
		return new ModelAndView("");
	}

	@GetMapping("/student/confirmation/{id_inscription_en_ligne}")
	public ModelAndView confirmerPerSms(@PathVariable("id_inscription_en_ligne") Long id_inscription_en_ligne) {
		ModelAndView model = new ModelAndView(PAGE_CODE_SMS);
		inscription_metier.confirmerInscriptionEnLigne(id_inscription_en_ligne);
		return model;
	}
}

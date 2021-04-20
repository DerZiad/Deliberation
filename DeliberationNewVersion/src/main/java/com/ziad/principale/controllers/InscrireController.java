package com.ziad.principale.controllers;

import java.util.Date;

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
	private final static String ATTRIBUT_ERROR = "error";
	private final static String PAGE_CODE_SMS = "etudiant/code.jsp";

	private final static String REDIRECT_CONFIRMER_SMS = "redirect:/student/confirmation";
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
			@RequestParam("academy") String academy) {
		inscription_metier.createEtudiant(last_name_fr, last_name_ar, first_name_fr, first_name_ar, massar_edu, cne,
				nationality, gender, birth_date, birth_place, city, province, bac_year, bac_type, mention, high_school,
				bac_place, academy);
		return new ModelAndView(REDIRECT_CONFIRMER_SMS);
	}

	@GetMapping("/student/confirmation")
	public ModelAndView confirmerPerSms() {
		ModelAndView model = new ModelAndView(PAGE_CODE_SMS);
		inscription_metier.envoyerSMS();
		return model;
	}

	@PostMapping("/student/confirmation")
	public ModelAndView confirmerSms(@RequestParam("sms") String sms) {
		ModelAndView model = null;
		try {
			inscription_metier.checkSms(sms);
			model = new ModelAndView(REDIRECT_PAGE_HOME);
		} catch (DataNotFoundExceptions e) {
			model = new ModelAndView(PAGE_CODE_SMS);
			model.addObject(ATTRIBUT_ERROR, e.getMessage());
		}
		return model;
	}

}

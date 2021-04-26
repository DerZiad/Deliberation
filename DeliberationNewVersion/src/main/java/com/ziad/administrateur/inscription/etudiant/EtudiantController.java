package com.ziad.administrateur.inscription.etudiant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ziad.repositories.EtudiantRepository;
import com.ziad.repositories.InscriptionAdministrativeRepository;

@Controller
public class EtudiantController {
	@Autowired
	private InscriptionAdministrativeRepository inscription_administrative_repository;
	@GetMapping("/admin/student/list")
	public ModelAndView listerEtudiant() {
		ModelAndView model = new ModelAndView("listStudent");
		model.addObject("inscriptions",inscription_administrative_repository.findAll());
		return model;
		
	}
}

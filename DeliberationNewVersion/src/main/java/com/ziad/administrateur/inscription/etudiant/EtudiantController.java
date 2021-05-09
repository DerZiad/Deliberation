package com.ziad.administrateur.inscription.etudiant;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.ziad.models.InscriptionEnLigne;
import com.ziad.repositories.InscriptionAdministrativeRepository;
import com.ziad.repositories.InscriptionEnLigneRepository;

@Controller
public class EtudiantController {
	@Autowired
	private InscriptionAdministrativeRepository inscription_administrative_repository;

	@Autowired
	private InscriptionEnLigneRepository inscriptionEnLigneRepository;

	@GetMapping("/admin/inscriptionenligne/list")
	public ModelAndView listerInscriptionsEnLigne() {
		ModelAndView model = new ModelAndView("Inscriptionenligne");
		model.addObject("inscriptions", inscriptionEnLigneRepository.findAll());
		return model;
	}

	@GetMapping("/admin/inscriptionenligne/accept/{idInscriptionEnLigne}")
	public ModelAndView accepterInscriptionEnLigne(@PathVariable("idInscriptionEnLigne") Long idInscriptionEnligne)
			throws EntityNotFoundException {
		InscriptionEnLigne inscription = inscriptionEnLigneRepository.getOne(idInscriptionEnligne);
		inscription.setAcceptedParAdmin(1);
		inscriptionEnLigneRepository.save(inscription);
		return new ModelAndView("redirect:/admin/inscriptionenligne/list");
	}

	@GetMapping("/admin/student/list")
	public ModelAndView listerEtudiant() {
		ModelAndView model = new ModelAndView("listStudent");
		model.addObject("inscriptions", inscription_administrative_repository.findAll());
		return model;

	}
}

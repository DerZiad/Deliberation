package com.ziad.controllers;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.service.etudiant.EspaceEtudiantInterface;

@Controller
@RequestMapping("/etudiant")
public class EspaceEtudiantController {
	
	private final static String PAGE_ETUDIANT = "espaceetudiant/pageetudiant";
	
	private final static String ATTRIBUT_SEMESTRES ="semestres";
	private final static String ATTRIBUT_NOTES_MODULE ="notes";
	private final static String ATTRIBUT_NOTE_SEMESTRE = "noteSemetre";
	
	@Autowired
	private EspaceEtudiantInterface etudiantMetier;
	
	@GetMapping("/consulter")
	public ModelAndView getPageEtudiant() throws EntityNotFoundException, DataNotFoundExceptions{
		ModelAndView model = new ModelAndView(PAGE_ETUDIANT);
		model.addObject(ATTRIBUT_SEMESTRES,etudiantMetier.listerSemestres());
		return model;
	}
	
	@PostMapping("/consulter")
	public ModelAndView filterSemestre(@RequestParam("id_semestre")Long id_semestre) throws EntityNotFoundException, DataNotFoundExceptions{
		System.out.println("LOl");
		ModelAndView model = new ModelAndView(PAGE_ETUDIANT);
		model.addObject(ATTRIBUT_SEMESTRES,etudiantMetier.listerSemestres());
		List<Object> besoins = etudiantMetier.getNotes(id_semestre);
		model.addObject(ATTRIBUT_NOTES_MODULE,besoins.get(1));
		model.addObject(ATTRIBUT_NOTE_SEMESTRE,besoins.get(0));
		return model;
	}
}

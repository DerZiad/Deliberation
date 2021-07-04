package com.ziad.controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lowagie.text.DocumentException;
import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.service.etudiant.EspaceEtudiantInterface;

@Controller
@RequestMapping("/etudiant")
public class EspaceEtudiantController {

	private final static String PAGE_ETUDIANT = "espaceetudiant/pageetudiant";

	private final static String REDIRECT_SCOLAR_CERTIFICAT = "redirect:/certificatscolarite";
	private final static String REDIRECT_CONSULT_CERTIFICAT = "redirect:/consulter";

	private final static String PAGE_CERTIFICAT_SCOLARITE = "espaceetudiant/certificat";

	private final static String ATTRIBUT_SEMESTRES = "semestres";
	private final static String ATTRIBUT_NOTES_MODULE = "notes";
	private final static String ATTRIBUT_NOTE_SEMESTRE = "noteSemetre";

	@Autowired
	private EspaceEtudiantInterface etudiantMetier;

	@GetMapping("/consulter")
	public ModelAndView getPageEtudiant() throws EntityNotFoundException, DataNotFoundExceptions {
		ModelAndView model = new ModelAndView(PAGE_ETUDIANT);
		model.addObject(ATTRIBUT_SEMESTRES, etudiantMetier.listerSemestres());
		return model;
	}

	@GetMapping("/consulter/download")
	public ModelAndView getPageEtudiant(@RequestParam("id_semestre") Long id_semestre,
			@RequestParam("idAnneeAcademique") Long idAnneeAcademique, HttpServletResponse response)
			throws EntityNotFoundException, DataNotFoundExceptions, DocumentException, IOException {
		etudiantMetier.generateReleveNote(id_semestre, idAnneeAcademique, response);
		ModelAndView model = new ModelAndView(REDIRECT_CONSULT_CERTIFICAT);
		return model;
	}

	@PostMapping("/consulter")
	public ModelAndView filterSemestre(@RequestParam("id_semestre") Long id_semestre,
			@RequestParam("idAnneeAcademique") Long idAnneeAcademique)
			throws EntityNotFoundException, DataNotFoundExceptions {
		ModelAndView model = new ModelAndView(PAGE_ETUDIANT);
		model.addObject(ATTRIBUT_SEMESTRES, etudiantMetier.listerSemestres());
		List<Object> besoins = etudiantMetier.getNotes(id_semestre, idAnneeAcademique);
		model.addObject(ATTRIBUT_NOTES_MODULE, besoins.get(1));
		model.addObject(ATTRIBUT_NOTE_SEMESTRE, besoins.get(0));
		model.addObject("url", "/etudiant/consulter/download?id_semestre=" + id_semestre);
		;
		return model;
	}

	@GetMapping("/certificatscolarite")
	public ModelAndView getCertificatPage() throws DataNotFoundExceptions {
		ModelAndView model = new ModelAndView(PAGE_CERTIFICAT_SCOLARITE);
		model.addObject(ATTRIBUT_SEMESTRES, etudiantMetier.getScolaritePage());
		return model;
	}

	@GetMapping("/certificatscolarite/download")
	public ModelAndView getCertificatPage(@RequestParam("id_semestre") Long idSemestre, HttpServletResponse response)
			throws DataNotFoundExceptions, EntityNotFoundException, DocumentException, IOException {
		ModelAndView model = new ModelAndView(REDIRECT_SCOLAR_CERTIFICAT);
		etudiantMetier.generateCertificatScolarite(idSemestre, response);
		return model;
	}
}

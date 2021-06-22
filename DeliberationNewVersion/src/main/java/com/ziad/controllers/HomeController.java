package com.ziad.controllers;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ziad.exceptions.AnonymousException;
import com.ziad.models.Etudiant;
import com.ziad.models.InscriptionAdministrative;
import com.ziad.models.NoteSemestre;
import com.ziad.models.Professeur;
import com.ziad.models.User;
import com.ziad.repositories.EtudiantRepository;
import com.ziad.repositories.FiliereRepository;
import com.ziad.repositories.InscriptionAdministrativeRepository;
import com.ziad.repositories.NotesSemestreRepository;
import com.ziad.repositories.ProfesseurRepository;
import com.ziad.repositories.UserRepository;

@Controller
public class HomeController {
	private final static String PAGE_PROFESSEUR = "espace_professeur/index-prof";
	private final static String PAGE_ADMINISTRATEUR = "index-admin";

	private final static String ATTRIBUT_SESSION = "utilisateur";

	private final static String ACTIVE = "mm-active";

	private final static String ATTRIBUT_FILIERES = "filieres";
	private final static String ATTRIBUT_ETUDIANTS = "etudiants";
	private final static String ATTRIBUT_PROFESSEURS = "professeurs";
	
	private final static String ATTRIBUT_DICTS = "dic";
	
	private final static String ATTRIBUT_NOTES_SEMESTRE = "notesSemestres";

	@Autowired
	private EtudiantRepository etudiantRepository;
	@Autowired
	private FiliereRepository filiereRepository;
	@Autowired
	private ProfesseurRepository ProfesseurRepository;
	@Autowired
	private NotesSemestreRepository noteSemestreRepository;

	@Autowired
	private InscriptionAdministrativeRepository inscriptionAdministrativeRepository;

	/**
	 * Je n'ai pas met de metier car on aura pas beaucoup de traitement <3
	 * 
	 **/

	@Autowired
	private UserRepository userRespository;

	@GetMapping("/admin")
	public ModelAndView getAdministrateurHomePage(HttpServletRequest req) throws AnonymousException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof AnonymousAuthenticationToken)
			throw new AnonymousException("Acces Denied");
		ModelAndView model = new ModelAndView(PAGE_ADMINISTRATEUR);
		saveSession(req);
		model.addObject("dashboard", ACTIVE);
		model.addObject("message", "HELLO WORLD");
		model.addObject(ATTRIBUT_FILIERES, filiereRepository.findAll());
		model.addObject(ATTRIBUT_ETUDIANTS, etudiantRepository.findAll());

		List<Professeur> listes = ProfesseurRepository.findAll();

		model.addObject(ATTRIBUT_PROFESSEURS, listes);

		List<NoteSemestre> notes = noteSemestreRepository.findAll();
		Collections.sort(notes);
		model.addObject(ATTRIBUT_NOTES_SEMESTRE, notes);
		model.addObject(ATTRIBUT_DICTS,loadImages());

		return model;
	}

	@GetMapping("/professeur")
	public ModelAndView getProfesseurHomePage(HttpServletRequest req) throws AnonymousException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof AnonymousAuthenticationToken)
			throw new AnonymousException("Acces Denied");
		/**
		 * Verifier le role de la connexion
		 **/

		String username = authentication.getName();
		User user = userRespository.getUserByUsername(username);
		System.out.println("i m professeur");
		saveSession(req);

		ModelAndView model = new ModelAndView(PAGE_PROFESSEUR);
		model.addObject("dashboard", ACTIVE);
		model.addObject("utilisateur", user);
		model.addObject("message", "HELLO WORLD");
		return model;
	}
	
	@GetMapping("/etudiant")
	public ModelAndView getEtudiantHomePage(HttpServletRequest req) throws AnonymousException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof AnonymousAuthenticationToken)
			throw new AnonymousException("Acces Denied");
		saveSession(req);

		ModelAndView model = new ModelAndView("redirect:/etudiant/consulter");
		return model;
	}

	public void saveSession(HttpServletRequest req) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserName = authentication.getName();
		User user = userRespository.getUserByUsername(currentUserName);
		req.getSession().setAttribute(ATTRIBUT_SESSION, user);
	}

	private HashMap<Etudiant, InscriptionAdministrative> loadImages() {
		HashMap<Etudiant, InscriptionAdministrative> dicts = new HashMap<Etudiant, InscriptionAdministrative>();
		List<InscriptionAdministrative> inscriptions = inscriptionAdministrativeRepository.findAll();
		for (InscriptionAdministrative inscriptionAdministrative : inscriptions) {
			dicts.put(inscriptionAdministrative.getEtudiant(), inscriptionAdministrative);
		}
		return dicts;
	}
}

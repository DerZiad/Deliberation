package com.ziad.principale.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ziad.enums.MonRole;
import com.ziad.exceptions.AnonymousException;
import com.ziad.models.User;
import com.ziad.repositories.ProfesseurRepository;
import com.ziad.repositories.UserRepository;

@Controller
public class HomeController {

	private final static String PAGE_RESPONSABLE_FILIERE = "espace_professeur/";
	private final static String PAGE_RESPONSABLE_MODULE = "espace_professeur/responsablemodule/index-resp";
	private final static String PAGE_PROFESSEUR = "espace_professeur/index-prof";
	private final static String PAGE_ADMINISTRATEUR = "index-admin";

	private final static String ACTIVE = "mm-active";

	/**
	 * Je n'ai pas met de metier car on aura pas beaucoup de traitement <3
	 * 
	 **/

	@Autowired
	private UserRepository userRespository;


	@GetMapping("/admin")
	public ModelAndView getAdministrateurHomePage() throws AnonymousException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof AnonymousAuthenticationToken)
			throw new AnonymousException("Acces Denied");
		ModelAndView model = new ModelAndView(PAGE_ADMINISTRATEUR);
		model.addObject("dashboard", ACTIVE);
		model.addObject("message", "HELLO WORLD");
		return model;
	}

	@GetMapping("/professeur")
	public ModelAndView getProfesseurHomePage() throws AnonymousException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof AnonymousAuthenticationToken)
			throw new AnonymousException("Acces Denied");
		/**
		 * Verifier le role de la connexion
		 **/
		String username = authentication.getName();
		User user = userRespository.getUserByUsername(username);
		ModelAndView model = null;
		if (user.getRoleList().contains(MonRole.ROLERESPONSABLEMODULE.getRole())) {
			model = new ModelAndView(PAGE_RESPONSABLE_MODULE);
		} else if (user.getRoleList().contains(MonRole.ROLERESPONSABLEFILIERE.getRole())) {
			model = new ModelAndView(PAGE_RESPONSABLE_FILIERE);
		} else if (user.getRoleList().contains(MonRole.ROLEPROFESSEUR.getRole())) {
			model = new ModelAndView(PAGE_PROFESSEUR);
		} else {
			throw new AnonymousException("Acces Denied");
		}
		model.addObject("dashboard", "mm-active");
		model.addObject("message", "HELLO WORLD");
		return model;
	}

	@GetMapping("/responsablefiliere")
	public ModelAndView getResponsableFiliereHomePage() throws AnonymousException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof AnonymousAuthenticationToken)
			throw new AnonymousException("Acces Denied");
		ModelAndView model = new ModelAndView("index-admin");
		model.addObject("dashboard", "mm-active");
		model.addObject("message", "HELLO WORLD");
		return model;
	}

	@GetMapping("/responsablemodule")
	public ModelAndView getResponsableModuleHomePage() throws AnonymousException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof AnonymousAuthenticationToken)
			throw new AnonymousException("Acces Denied");
		ModelAndView model = new ModelAndView("index-admin");
		model.addObject("dashboard", "mm-active");
		model.addObject("message", "HELLO WORLD");
		return model;
	}
}

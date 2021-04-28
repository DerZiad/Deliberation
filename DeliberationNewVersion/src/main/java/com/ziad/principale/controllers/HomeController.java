package com.ziad.principale.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ziad.exceptions.AnonymousException;

@Controller
public class HomeController {
	
	private final static String PAGE_RESPONSABLE_FILIERE = "espace_professeur/";
	private final static String PAGE_RESPONSABLE_MODULE = "";
	private final static String PAGE_PROFESSEUR = "espace_professeur/index-prof";
	private final static String PAGE_ADMINISTRATEUR = "index-admin";

	private final static String ACTIVE = "mm-active";

	
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
		ModelAndView model = new ModelAndView(PAGE_PROFESSEUR);
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

package com.ziad.principale.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ziad.exceptions.AnonymousException;
import com.ziad.models.User;
import com.ziad.repositories.UserRepository;

@Controller
public class HomeController {
	private final static String PAGE_PROFESSEUR = "espace_professeur/index-prof";
	private final static String PAGE_ADMINISTRATEUR = "index-admin";

	private final static String ATTRIBUT_SESSION = "utilisateur";
	
	private final static String ACTIVE = "mm-active";

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
		
		saveSession(req);
		
		ModelAndView model = new ModelAndView(PAGE_PROFESSEUR);
		model.addObject("dashboard", ACTIVE);
		model.addObject("utilisateur",user);
		model.addObject("message", "HELLO WORLD");
		return model;
	}
	
	public void saveSession(HttpServletRequest req) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserName = authentication.getName();
		User user = userRespository.getUserByUsername(currentUserName);
		req.getSession().setAttribute(ATTRIBUT_SESSION, user);
	}
}

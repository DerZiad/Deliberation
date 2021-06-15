package com.ziad.gestioncompte;

import java.util.Iterator;

import javax.persistence.EntityNotFoundException;

import org.apache.http.auth.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ziad.models.User;

@Controller
@RequestMapping("/gestioncompte")
public class GestionProfilCompte {

	private final static String PAGE_PROFIL = "gestionprofil/GestionProfile";

	private final static String ATTRIBUT_USER = "user";

	private final static String ATTRIBUT_ERROR = "error";
	private final static String ATTRIBUT_MESSAGE = "message";

	@Autowired
	private GestionCompteService gestioncompteservice;

	@GetMapping("")
	public ModelAndView getGestionProfil() {
		ModelAndView model = new ModelAndView(PAGE_PROFIL);
		User user = gestioncompteservice.getAccount();
		String ex = "";
		if (user.isAdministrator()) {
			ex = "../layout.jsp";
			model.addObject("extention", "");
		} else if (user.isProfesseur()) {
			ex = "../espace_professeur/layout-prof.jsp";
		} else {
			ex = "../etudiant/layout.jsp";
		}
		model.addObject("mero", ex);
		model.addObject(ATTRIBUT_USER, gestioncompteservice.getAccount());
		return model;
	}

	@PostMapping("")
	public ModelAndView changePassword(@RequestParam("action") String action,
			@RequestParam(name = "email", required = false) String email,
			@RequestParam(name = "password", required = false) String password,
			@RequestParam(name = "cfpassword", required = false) String cfpassword) throws EntityNotFoundException {
		ModelAndView model = null;
		if (action.equals("changeemail")) {
			model = new ModelAndView(PAGE_PROFIL);
			model.addObject(ATTRIBUT_USER, gestioncompteservice.getAccount());
			User user = gestioncompteservice.getAccount();
			String ex = "";
			if (user.isAdministrator()) {
				ex = "../layout.jsp";
				model.addObject("extention", "");
			} else if (user.isProfesseur()) {
				ex = "../espace_professeur/layout-prof.jsp";
			} else {
				ex = "../etudiant/layout.jsp";
			}
			model.addObject("mero", ex);
			try {
				gestioncompteservice.changePassword(password, cfpassword);
			} catch (InvalidCredentialsException e) {
				model.addObject(ATTRIBUT_ERROR, e.getMessage());
			}
		} else if (action.equals("changepassword")) {
			model = new ModelAndView(PAGE_PROFIL);
			model.addObject(ATTRIBUT_USER, gestioncompteservice.getAccount());
			User user = gestioncompteservice.getAccount();
			String ex = "";
			if (user.isAdministrator()) {
				ex = "../layout.jsp";
				model.addObject("extention", "");
			} else if (user.isProfesseur()) {
				ex = "../espace_professeur/layout-prof.jsp";
			} else {
				ex = "../espaceetudiant/layout.jsp";
			}
			model.addObject("mero", ex);
			try {
				gestioncompteservice.changePassword(password, cfpassword);
				model.addObject(ATTRIBUT_MESSAGE, "Le mot de passe a été changé avec succes");
			} catch (InvalidCredentialsException e) {
				model.addObject(ATTRIBUT_ERROR, e.getMessage());
			}
		} else {
			throw new EntityNotFoundException();
		}
		return model;

	}

	@GetMapping("/confirmer")
	public ModelAndView confirmerEmail(@RequestParam("code") String code, @RequestParam("id") Long id)
			throws EntityNotFoundException {
		ModelAndView model = new ModelAndView(PAGE_PROFIL);
		try {
			gestioncompteservice.confirmerEmail(id, code);
		} catch (InvalidCredentialsException e) {
			model.addObject(ATTRIBUT_ERROR, e.getMessage());
		}
		return model;
	}

}

package com.ziad.gestioncompte;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/gestioncompte")
public class GestionProfilCompte {
	
	private final static String PAGE_PROFIL="gestionprofil/gestion";
	
	private final static String ATTRIBUT_USER = "user";
	
	@Autowired
	private GestionCompteService gestioncompteservice;
	
	public ModelAndView getGestionProfil() {
		ModelAndView model = new ModelAndView(PAGE_PROFIL);
		model.addObject(ATTRIBUT_USER,gestioncompteservice.getAccount());
		return model;
	}
	
	public ModelAndView changePassword(@RequestParam("password")String password,@RequestParam("cfpassword")String cfpassword) {
		ModelAndView model = new ModelAndView(PAGE_PROFIL);
		model.addObject(ATTRIBUT_USER,gestioncompteservice.getAccount());
		return model;
	}
	/*public ModelAndView getGestionProfil() {
		ModelAndView model = new ModelAndView(PAGE_PROFIL);
		model.addObject(ATTRIBUT_USER,gestioncompteservice.getAccount());
		return model;
	}
	public ModelAndView getGestionProfil() {
		ModelAndView model = new ModelAndView(PAGE_PROFIL);
		model.addObject(ATTRIBUT_USER,gestioncompteservice.getAccount());
		return model;
	}*/
}

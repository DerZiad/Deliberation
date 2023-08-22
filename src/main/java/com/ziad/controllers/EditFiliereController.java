package com.ziad.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.Filiere;
import com.ziad.services.interfaces.EditFiliereInterface;

@Controller
public class EditFiliereController {

	private final static String ACTIVE = "mm-active";
	
	private final static String ATTRIBUT_NAVBAR_FILIERE = "mesfilieres";
	
	private final static String PAGE_LISTE_MODULE ="espace_professeur/responsablefiliere/mafiliere";
	private final static String PAGE_MODULE_PROFILE = "espace_professeur/responsablefiliere/ModuleProfile";
	
	private final static String REDIRECT_MODULE_PROFILE = "redirect:/professeur/responsablefiliere/listermodules/%d";
	
	private final static String ATTRIBUT_MODULE = "module";
	private final static String ATTRIBUT_FILIERES_JSON = "filieres";
	private final static String ATTRIBUT_MODULES_JSON = "modulesjson";
	
	@Autowired
	private EditFiliereInterface editFiliereMetier;
	
	@SuppressWarnings("unchecked")
	@GetMapping("/professeur/responsablefiliere/listermodules")
	public ModelAndView getModuleByFiliere() throws DataNotFoundExceptions{
		ModelAndView model = new ModelAndView(PAGE_LISTE_MODULE);
		ArrayList<Object> listes = editFiliereMetier.loadListModuleByFiliere();
		model.addObject(ATTRIBUT_NAVBAR_FILIERE,ACTIVE);
		model.addObject(ATTRIBUT_FILIERES_JSON,(List<Filiere>)listes.get(0));
		model.addObject(ATTRIBUT_MODULES_JSON,(String)listes.get(1));
		return model;
	}
	
	@GetMapping("/professeur/responsablefiliere/listermodules/{idModule}")
	public ModelAndView getModuleProfile(@PathVariable("idModule")Long idModule) throws EntityNotFoundException{
		ModelAndView model = new ModelAndView(PAGE_MODULE_PROFILE);
		model.addObject(ATTRIBUT_NAVBAR_FILIERE,ACTIVE);
		model.addObject(ATTRIBUT_MODULE,editFiliereMetier.getModuleById(idModule));
		return model;
	}
	
	@PostMapping("/professeur/responsablefiliere/listermodules/{idModule}")
	public ModelAndView saveInformationModule(@PathVariable("idModule")Long idModule,@RequestParam("coefficient")Double coefficient) throws EntityNotFoundException {
		ModelAndView model = new ModelAndView(String.format(REDIRECT_MODULE_PROFILE, idModule));
		model.addObject(ATTRIBUT_NAVBAR_FILIERE,ACTIVE);
		editFiliereMetier.updateModule(idModule, coefficient);
		return model;
	}
	
}

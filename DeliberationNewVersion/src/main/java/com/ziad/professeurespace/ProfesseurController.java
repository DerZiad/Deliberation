package com.ziad.professeurespace;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.AnneeAcademique;
import com.ziad.models.InscriptionPedagogique;

@Controller
@RequestMapping("/professeur")
public class ProfesseurController {
	@Autowired
	private ProfesseurInterface professeur_metier;
	
	
	private final static String PAGE_ELEMENTS = "espace_professeur/myModulesProf";
	private final static String PAGE_MES_ETUDIANTS = "espace_professeur/mesetudiants";
	
	
	private final static String ATTRIBUTS_INSCRIPTIONS_PEDAGOGIQUES = "inscriptions";
	private final static String ATTRIBUTS_ELEMENTS = "elements";
	private final static String ATTRIBUTS_ANNEES_ACADEMIQUES= "annees";



	@GetMapping("/listerElements")
	public ModelAndView listerElements() throws DataNotFoundExceptions{
		ModelAndView model = new ModelAndView(PAGE_ELEMENTS);
		model.addObject(ATTRIBUTS_ELEMENTS,professeur_metier.listerElements());
		return model;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/listerElements/{id_element}")
	public ModelAndView listerEtudiants(@PathVariable("id_element")Long id_element) throws DataNotFoundExceptions,EntityNotFoundException{
		ModelAndView model = new ModelAndView(PAGE_MES_ETUDIANTS);
		List<Object> besoins = professeur_metier.listerEtudiants(id_element);
		model.addObject(ATTRIBUTS_INSCRIPTIONS_PEDAGOGIQUES,(List<InscriptionPedagogique>)besoins.get(0));
		model.addObject(ATTRIBUTS_ANNEES_ACADEMIQUES,(List<AnneeAcademique>)besoins.get(1));
		return model;
	}
}

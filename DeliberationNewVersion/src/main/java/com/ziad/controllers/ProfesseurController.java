package com.ziad.controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ziad.enums.TypeNote;
import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.AnneeAcademique;
import com.ziad.models.Element;
import com.ziad.models.InscriptionPedagogique;
import com.ziad.service.professeurespace.ProfesseurInterface;

@Controller
@RequestMapping("/professeur")
public class ProfesseurController {
	@Autowired
	private ProfesseurInterface professeur_metier;

	private final static String PAGE_ELEMENTS = "espace_professeur/myModulesProf";
	private final static String PAGE_MES_ETUDIANTS = "espace_professeur/mesetudiants";
	
	private final static String ACTIVE ="mm-active";
	private final static String ATTRIBUT_NAVBAR_MES_ELEMENTS = "meselements";
	
	private final static String ATTRIBUTS_INSCRIPTIONS_PEDAGOGIQUES = "inscriptions";
	private final static String ATTRIBUTS_INSCRIPTIONS_PEDAGOGIQUES_JSON = "inscriptionsjson";
	private final static String ATTRIBUTS_ELEMENTS = "elements";
	private final static String ATTRIBUTS_ANNEES_ACADEMIQUES = "annees";
	private final static String ATTRIBUTS_ANNEES_ACADEMIQUES_JSON = "anneesjson";
	private final static String ATTRIBUT_ELEMENT = "element";
	private final static String ATTRIBUT_TYPES_NOTES = "types";
	@GetMapping("/listerElements")
	public ModelAndView listerElements() throws DataNotFoundExceptions {
		ModelAndView model = new ModelAndView(PAGE_ELEMENTS);
		model.addObject(ATTRIBUT_NAVBAR_MES_ELEMENTS,ACTIVE);
		List<Element> elements = professeur_metier.listerElements();
		System.out.println(elements.size());
		model.addObject(ATTRIBUTS_ELEMENTS, elements);
		return model;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/listerElements/{id_element}")
	public ModelAndView listerEtudiants(@PathVariable("id_element") Long id_element)
			throws DataNotFoundExceptions, EntityNotFoundException {
		ModelAndView model = new ModelAndView(PAGE_MES_ETUDIANTS);
		model.addObject(ATTRIBUT_NAVBAR_MES_ELEMENTS,ACTIVE);
		List<Object> besoins = professeur_metier.listerEtudiants(id_element);
		model.addObject(ATTRIBUTS_INSCRIPTIONS_PEDAGOGIQUES, (List<InscriptionPedagogique>) besoins.get(0));
		model.addObject(ATTRIBUTS_INSCRIPTIONS_PEDAGOGIQUES_JSON, (String) besoins.get(1));
		model.addObject(ATTRIBUTS_ANNEES_ACADEMIQUES, (List<AnneeAcademique>) besoins.get(2));
		model.addObject(ATTRIBUTS_ANNEES_ACADEMIQUES_JSON, (String) besoins.get(3));
		model.addObject(ATTRIBUT_ELEMENT,(Element)besoins.get(4));
		model.addObject(ATTRIBUT_TYPES_NOTES,TypeNote.values());		
		return model;
	}

	@PostMapping("/listerElements/{id_element}")
	public void makeListtoDownload(@PathVariable("id_element") Long id_element,
			@RequestParam("annee") Long id_annee,HttpServletResponse response) throws EntityNotFoundException,IOException {
		response.setContentType("application/octet-stream");
		String header_key = "Content-Disposition";
		String header_value = "attachement; filename=Etudiants.xlsx";
		response.setHeader(header_key, header_value);
		professeur_metier.generateExcel(id_element, id_annee, response);
		
	}
	
}

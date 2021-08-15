package com.ziad.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ziad.enums.TypeInscription;
import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.AnneeAcademique;
import com.ziad.models.Filiere;
import com.ziad.models.InscriptionAdministrative;
import com.ziad.models.InscriptionPedagogique;
import com.ziad.services.interfaces.InscriptionPedagogiqueInterface;

@Controller
@RequestMapping("/admin/inscriptionpedagogique")
public class InscriptionPedagogiqueController {

	@Autowired
	private InscriptionPedagogiqueInterface inscription_pedagogique_metier;

	private final static String ATTRIBUT_LIST_FILIERES = "filieres";
	private final static String ATTRIBUT_LIST_SEMESTRES_JSON = "semestresjson";
	private final static String ATTRIBUT_LIST_MODULES_JSON = "modulesjson";
	private final static String ATTRIBUT_LIST_ANNEE_ACADEMIQUES = "annees_academiques";
	private final static String ATTRIBUT_LIST_INSCRIPTIONS_PEDAGOGIQUES = "inscription";

	private final static String PAGE_INSCRIPTION_PEDAGOGIQUE = "inscription_pedagogique/InscriptionPedagogiqueIndividuelle";
	private final static String PAGE_LIST_INSCRIPTION_PEDAGOGIQUE = "inscription_pedagogique/ListInscriptionPedagogique";

	private final static String ATTRIBUT_INSCRIPTION_ADMINISTRATIVE = "inscription_administrative";
	private final static String ATTRIBUT_LIST_SEMESTRES = "semestres";
	private final static String ATTRIBUT_LIST_MODULES = "modules";
	private final static String ATTRIBUT_LIST_ELEMENTS = "elements";

	private final static String ATTRIBUT_INSCRIPTIONS_PEDAGOGIQUES = "inscriptions";
	private final static String ATTRIBUT_TYPES_INSCRIPTIONS = "types";

	@GetMapping("/listerinscriptions/{id_etudiant}")
	public ModelAndView listerInscriptionPedagogiqueByEtudiant(@PathVariable("id_etudiant") Long id_etudiant)
			throws DataNotFoundExceptions {
		ModelAndView model = new ModelAndView(PAGE_LIST_INSCRIPTION_PEDAGOGIQUE);
		TypeInscription types[] = TypeInscription.values();
		model.addObject(ATTRIBUT_TYPES_INSCRIPTIONS, types);
		model.addObject(ATTRIBUT_INSCRIPTIONS_PEDAGOGIQUES,
				inscription_pedagogique_metier.prepareInscriptionListpage(id_etudiant));
		return model;
	}

	@GetMapping("/listerinscriptions")
	public ModelAndView listerInscriptionPedagogique() throws DataNotFoundExceptions {
		ModelAndView model = new ModelAndView("inscription_pedagogique/ListInscriptionsPedagogiques");
		ArrayList<Object> list = inscription_pedagogique_metier.listerInscriptionsPedagogique();
		model.addObject(ATTRIBUT_LIST_ANNEE_ACADEMIQUES, (List<AnneeAcademique>) list.get(0));
		model.addObject(ATTRIBUT_LIST_FILIERES, (List<Filiere>) list.get(1));
		model.addObject(ATTRIBUT_LIST_SEMESTRES_JSON, (String) list.get(2));
		model.addObject(ATTRIBUT_LIST_MODULES_JSON, (String) list.get(3));
		List<InscriptionPedagogique> l = (List<InscriptionPedagogique>) list.get(4);
		model.addObject(ATTRIBUT_LIST_INSCRIPTIONS_PEDAGOGIQUES, l);
		return model;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/creer/{id_inscription_filiere}/{id_inscription_etudiant}")
	public ModelAndView PageInscriptionPedagogiqueIndividuelle(
			@PathVariable("id_inscription_filiere") Long id_inscription_filiere,
			@PathVariable("id_inscription_etudiant") Long id_inscription_etudiant) throws EntityNotFoundException {
		ModelAndView model = new ModelAndView(PAGE_INSCRIPTION_PEDAGOGIQUE);
		ArrayList<Object> besoins = inscription_pedagogique_metier
				.prepareInscriptionPedagogiquePage(id_inscription_filiere, id_inscription_etudiant);
		model.addObject("InscriptionPedagogique", "mm-active");
		model.addObject(ATTRIBUT_INSCRIPTION_ADMINISTRATIVE, (InscriptionAdministrative) besoins.get(0));
		model.addObject(ATTRIBUT_LIST_SEMESTRES, (String) besoins.get(1));
		model.addObject(ATTRIBUT_LIST_MODULES, (String) besoins.get(2));
		model.addObject(ATTRIBUT_LIST_ELEMENTS, (String) besoins.get(3));
		model.addObject("types", (List<String>) besoins.get(4));
		model.addObject("annees", besoins.get(5));
		return model;
	}

	@PostMapping("/creer/{id_inscription_filiere}/{id_inscription_etudiant}")
	public ModelAndView enregistrerInscriptionPedagogique(@PathVariable("id_inscription_filiere") Long id_filiere,
			@PathVariable("id_inscription_etudiant") Long id_etudiant, @RequestParam("annee") Long idAnneeAcademique,
			HttpServletRequest request) throws EntityNotFoundException, DataNotFoundExceptions {
		ModelAndView model = new ModelAndView();
		inscription_pedagogique_metier.enregistrerInformation(id_etudiant, idAnneeAcademique, request);
		return model;
	}

}

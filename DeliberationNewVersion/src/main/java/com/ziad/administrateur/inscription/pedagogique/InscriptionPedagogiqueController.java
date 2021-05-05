package com.ziad.administrateur.inscription.pedagogique;

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
import org.springframework.web.servlet.ModelAndView;

import com.ziad.enums.TypeInscription;
import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.InscriptionAdministrative;

@Controller
@RequestMapping("/admin/inscriptionpedagogique")
public class InscriptionPedagogiqueController {

	@Autowired
	private InscriptionPedagogiqueInterface inscription_pedagogique_metier;

	private final static String PAGE_INSCRIPTION_PEDAGOGIQUE = "inscription_pedagogique/InscriptionPedagogiqueIndividuelle";
	private final static String PAGE_LIST_INSCRIPTION_PEDAGOGIQUE = "inscription_pedagogique/ListInscriptionPedagogique";

	private final static String REDIRECT_LIST_INSCRIPTION_PEDAGOGIQUE = "redirect:/admin/inscriptionpedagogique/listerinscriptions/%d";

	private final static String ATTRIBUT_INSCRIPTION_ADMINISTRATIVE = "inscription_administrative";
	private final static String ATTRIBUT_LIST_SEMESTRES = "semestres";
	private final static String ATTRIBUT_LIST_MODULES = "modules";
	private final static String ATTRIBUT_LIST_ELEMENTS = "elements";

	private final static String ATTRIBUT_INSCRIPTIONS_PEDAGOGIQUES = "inscriptions";
	private final static String ATTRIBUT_TYPES_INSCRIPTIONS = "types";

	@GetMapping("/listerinscriptions/{id_etudiant}")
	public ModelAndView listerInscriptionPedagogique(@PathVariable("id_etudiant") Long id_etudiant)
			throws DataNotFoundExceptions {
		ModelAndView model = new ModelAndView(PAGE_LIST_INSCRIPTION_PEDAGOGIQUE);
		TypeInscription types[] = TypeInscription.values();
		model.addObject(ATTRIBUT_TYPES_INSCRIPTIONS, types);
		model.addObject(ATTRIBUT_INSCRIPTIONS_PEDAGOGIQUES,
				inscription_pedagogique_metier.prepareInscriptionListpage(id_etudiant));
		return model;
	}

	@GetMapping("/suprimer/{id_etudiant}/{id_element}")
	public ModelAndView suprimerInscriptionsPedagogique(@PathVariable("id_etudiant") Long id_etudiant,@PathVariable("id_element")Long id_element)
			throws DataNotFoundExceptions {
		inscription_pedagogique_metier.suprimerInscriptionPedagogique(id_etudiant, id_element);
		ModelAndView model = new ModelAndView(String.format(REDIRECT_LIST_INSCRIPTION_PEDAGOGIQUE, id_etudiant));
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
		return model;
	}

	@PostMapping("/creer/{id_inscription_filiere}/{id_inscription_etudiant}")
	public ModelAndView enregistrerInscriptionPedagogique(@PathVariable("id_inscription_filiere") Long id_filiere,
			@PathVariable("id_inscription_etudiant") Long id_etudiant, HttpServletRequest request)
			throws EntityNotFoundException, DataNotFoundExceptions {
		ModelAndView model = new ModelAndView();
		inscription_pedagogique_metier.enregistrerInformation(id_filiere, id_etudiant, request);
		return model;
	}
	
	/*@GetMapping("/modifier/{id_inscription_etudiant}/{id_element}")
	public ModelAndView editInscriptionPedagogique(@PathVariable("id_etudiant") Long id_etudiant,@PathVariable("id_element")Long id_element) {
		
	}*/

}

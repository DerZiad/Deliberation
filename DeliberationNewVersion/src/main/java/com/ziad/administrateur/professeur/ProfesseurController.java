package com.ziad.administrateur.professeur;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ziad.administrateur.etablissement.DataNotFoundExceptions;
import com.ziad.models.Element;
import com.ziad.models.Filiere;
import com.ziad.models.Professeur;

@Controller
@RequestMapping("/admin")
public class ProfesseurController {
	@Autowired
	private ProfesseurImplementation professeur_metier;

	/**
	 * Chemin au Html
	 **/
	private final static String PAGE_PROFESSEUR_CREATE = "Professeur/ProfesseurCreate";
	private final static String PAGE_PROFESSEUR_LIST = "Professeur/ListProfesseur";
	private final static String PAGE_PROFESSEUR_PROFILE = "Professeur/ProfesseurProfile";
	
	/**
	 * Redirect
	 **/
	private final static String REDIRECT_PROFESSEUR_LIST_LINK = "redirect:/admin/professeur/liste";
	private final static String REDIRECT_PROFESSEUR_PROFILE_LINK = "redirect:/admin/professeur/profile/";

	/**
	 * mm active attribut
	 **/
	private final static String ACTIVE = "mm-active";

	private final static String ATTRIBUT_PROFESSEUR_CREATE_PAGE = "ProfesseurCreate";

	// ***** Attributs de lister Professeur
	private final static String ATTRIBUT_PROFESSEUR_LIST_PAGE = "listProfesseur";
	private final static String ATTRIBUT_PROFESSEUR_LISTES = "professeurs";

	// ***** Attributs de profile de Professeur
	private final static String ATTRIBUT_PROFESSEUR_PROFILE_PAGE = "professeur";
	
	
	/**
	 * 
	 * Les élements du professeurs
	 * 
	 * */
	private final static String PAGE_AJOUT_ELEMENTS = "Professeur/AjoutElements";
	private final static String PAGE_ELEMENTS_LIST = "Professeur/ListElements";
	
	private final static String REDIRECT_ELEMENTS_LIST = "/professeur/profile/%l/elements";
	
	private final static String ATTRIBUT_ELEMENTS_PROFESSEUR = "elements";
	private final static String ATTRIBUT_FILIERES_PROFESSEUR = "filieres";
	
	@GetMapping("/professeur/creer")
	public ModelAndView professeurCreatePage() {
		ModelAndView model = new ModelAndView(PAGE_PROFESSEUR_CREATE);
		model.addObject(ATTRIBUT_PROFESSEUR_CREATE_PAGE, ACTIVE);
		return model;
	}

	@PostMapping("/professeur/creer")
	public ModelAndView professeurCreate(@RequestParam("first_name") String first_name,
			@RequestParam("last_name") String last_name, @RequestParam("email") String email,
			@RequestParam(name = "module", required = false) Integer module,
			@RequestParam(name = "filiere", required = false) Integer filiere) {
		professeur_metier.createProfesseur(first_name, last_name, email, module, filiere);
		return new ModelAndView(REDIRECT_PROFESSEUR_LIST_LINK);
	}

	@GetMapping("/professeur/liste")
	public ModelAndView listProfesseur() throws DataNotFoundExceptions {
		ModelAndView model = new ModelAndView(PAGE_PROFESSEUR_LIST);
		List<Professeur> professeurs = professeur_metier.listerProfesseurs();
		model.addObject(ATTRIBUT_PROFESSEUR_LISTES, professeurs);
		model.addObject(ATTRIBUT_PROFESSEUR_LIST_PAGE, "mm-active");
		return model;
	}

	@GetMapping("/professeur/profile/{id}")
	public ModelAndView professeurProfile(@PathVariable("id") Long id) throws EntityNotFoundException {
		ModelAndView model = new ModelAndView(PAGE_PROFESSEUR_PROFILE);
		model.addObject(ATTRIBUT_PROFESSEUR_PROFILE_PAGE, professeur_metier.getProfesseur(id));
		return model;
	}

	@PostMapping("/professeur/profile/{id}")
	public ModelAndView modifyProfesseur(@PathVariable("id") Long id, @RequestParam("last_name") String last_name,
			@RequestParam("first_name") String first_name, @RequestParam("email") String email,
			@RequestParam(name = "filiere", required = false) Integer filiere,
			@RequestParam(name = "module", required = false) Integer module) throws EntityNotFoundException {
		professeur_metier.modifierProfesseur(id, last_name, first_name, email, filiere, module);
		return new ModelAndView(REDIRECT_PROFESSEUR_PROFILE_LINK + id);
	}

	@GetMapping("/professeur/supprimer/{id}")
	public ModelAndView deleteProfesseur(@PathVariable("id") Long id) throws EntityNotFoundException {
		professeur_metier.deleteProfesseur(id);
		return new ModelAndView(REDIRECT_PROFESSEUR_LIST_LINK);
	}

	@GetMapping("/professeur/profile/{id}/updatepass")
	public ModelAndView renitialiserPassword(@PathVariable("id") Long id) throws EntityNotFoundException {
		ModelAndView model = new ModelAndView(REDIRECT_PROFESSEUR_PROFILE_LINK + id);
		professeur_metier.renitialiserPassword(id);
		return model;
	}

	/**
	 * 
	 * ADD and Delete professeurs element
	 * 
	 **/
	
	@GetMapping("/professeur/profile/{id_professeur}/elements")
	public ModelAndView listerElements(@PathVariable("id_professeur") Long id_professeur) throws EntityNotFoundException,DataNotFoundExceptions{
		ModelAndView model = new ModelAndView(PAGE_ELEMENTS_LIST);
		model.addObject(ATTRIBUT_ELEMENTS_PROFESSEUR,professeur_metier.listerElementsByProfesseur(id_professeur));
		return model;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/professeur/profile/{id_professeur}/elements/ajouter")
	public ModelAndView ajouterElementsProfesseur(@PathVariable("id_professeur") Long id_professeur) throws DataNotFoundExceptions{
		ModelAndView model = new ModelAndView(PAGE_AJOUT_ELEMENTS);
		HashMap<String, Object> composates = professeur_metier.listerFilieresElements();
		model.addObject(ATTRIBUT_ELEMENTS_PROFESSEUR,((List<Element>)composates.get(ProfesseurImplementation.ATTRIBUT_ELEMENTS)));
		model.addObject(ATTRIBUT_FILIERES_PROFESSEUR,((List<Element>)composates.get(ProfesseurImplementation.ATTRIBUT_FILIERES)));
		return model;
	}
	@SuppressWarnings("unchecked")
	@PostMapping("/professeur/profile/{id_professeur}/elements/ajouter")
	public ModelAndView filterElementsProfesseur(@PathVariable("id_filiere")Long id_filiere) throws DataNotFoundExceptions{
		ModelAndView model = new ModelAndView(PAGE_AJOUT_ELEMENTS);
		HashMap<String, Object> composates = professeur_metier.filterElement(id_filiere);
		model.addObject(ATTRIBUT_ELEMENTS_PROFESSEUR,((List<Element>)composates.get(ProfesseurImplementation.ATTRIBUT_ELEMENTS)));
		model.addObject(ATTRIBUT_FILIERES_PROFESSEUR,((List<Filiere>)composates.get(ProfesseurImplementation.ATTRIBUT_FILIERES)));
		return model;
	}

	@GetMapping("/professeur/profile/{id_professeur}/elements/suprimer/{id_element}")
	public ModelAndView suprimerElementByProfesseur(@PathVariable("id_professeur") Long id_professeur,@PathVariable("id_element") Long id_element) throws EntityNotFoundException{
		professeur_metier.suprimerElement(id_professeur,id_element);
		ModelAndView model = new ModelAndView(String.format(REDIRECT_ELEMENTS_LIST, id_professeur));
		return model;
	}

	@GetMapping("/professeur/profile/{id_professeur}/elements/addelement/{id_elements}")
	public ModelAndView patchElementByProfesseur(@PathVariable("id_professeur") Long id_professeur) {
		return null;
	}

}

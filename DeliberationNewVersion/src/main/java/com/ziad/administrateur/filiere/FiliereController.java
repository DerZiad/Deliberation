package com.ziad.administrateur.filiere;

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
import com.ziad.administrateur.etablissement.InvalidEntries;
import com.ziad.models.Etablissement;
import com.ziad.models.Filiere;

@Controller
@RequestMapping("/admin")
public class FiliereController {

	@Autowired
	private FiliereInterface filiere_metier;

	@GetMapping("/filiere/creer")
	public ModelAndView filiereCreatePage() throws DataNotFoundExceptions {
		ModelAndView model = new ModelAndView("Filiere/FiliereCreate");
		List<Etablissement> etablissements = filiere_metier.getEtablissement();
		model.addObject("professeurs",filiere_metier.listerResponsableFiliere());
		model.addObject("etablissements", etablissements);
		model.addObject("FiliereCreate", "mm-active");
		return model;
	}

	@PostMapping("/filiere/creer")
	public ModelAndView getFiliereCreate(@RequestParam("name") String nom_filiere,
			@RequestParam("etablissement") Long etablissement_id,
			@RequestParam(name="professeur",required = false) Long id_professeur,
			@RequestParam("semester_number") Integer semester_number) throws InvalidEntries,EntityNotFoundException {
		filiere_metier.createFiliere(etablissement_id, nom_filiere,id_professeur,semester_number);
		return new ModelAndView("redirect:/admin/filiere/liste");
	}

	@GetMapping("/filiere/liste")
	public ModelAndView getFiliereListe() throws DataNotFoundExceptions {
		List<Filiere> filieres = filiere_metier.getFiliereList();
		ModelAndView model = new ModelAndView("Filiere/ListFiliere");
		model.addObject("listFiliere", "mm-active");
		model.addObject("filieres", filieres);
		return model;
	}

	@GetMapping("/filiere/profile/{id}")
	public ModelAndView getFiliereProfile(@PathVariable("id") Long id) throws InvalidEntries, DataNotFoundExceptions {
		ModelAndView model = new ModelAndView("Filiere/FiliereProfile");
		filiere_metier.getFiliereProfile(model, id);
		model.addObject("professeurs",filiere_metier.listerResponsableFiliere());
		return model;
	}

	@PostMapping("/filiere/profile/{id}")
	public ModelAndView modifyFiliere(@PathVariable("id") Long id, @RequestParam("name") String name,
			@RequestParam("etablissement_id") Long etablissement_id,
			@RequestParam("semester_number") Integer semester_number) throws InvalidEntries {
		filiere_metier.modifyFiliereProfile(id, name, etablissement_id, semester_number);
		return new ModelAndView("redirect:/admin/filiere/profile/" + id);
	}

	@PostMapping("/filiere/supprimer/{id}")
	public ModelAndView deleteFiliere(@PathVariable("id") Long id) throws InvalidEntries {
		filiere_metier.suprimerFiliere(id);
		return new ModelAndView("redirect:/admin/filiere/liste");
	}
}

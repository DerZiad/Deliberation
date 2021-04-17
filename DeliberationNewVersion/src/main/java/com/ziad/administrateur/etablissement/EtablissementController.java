package com.ziad.administrateur.etablissement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ziad.models.Etablissement;
import com.ziad.models.Filiere;
import com.ziad.models.Professeur;

@Controller
@RequestMapping("/admin")
public class EtablissementController {
	@Autowired
	private EtablissementInterface etablissement_metier;

	@GetMapping("/etablissement/creer")
	public ModelAndView createEstablissmentPage() {
		ModelAndView model = new ModelAndView("/Etablissement/EtablissementCreate");
		model.addObject("EtablissementCreate", "mm-active");
		return model;
	}

	@PostMapping("/etablissement/creer")
	public ModelAndView createEstablissment(@RequestParam("name") String name) {
		etablissement_metier.createEtablissement(name);
		return new ModelAndView("redirect:/admin/etablissement/liste");
	}

	@GetMapping("/etablissement/liste")
	public ModelAndView listEtablissement() throws DataNotFoundExceptions {
		ModelAndView model = new ModelAndView("/Etablissement/ListEtablissement");
		List<Etablissement> etablissements = etablissement_metier.getEtablissements();
		model.addObject("etablissements", etablissements);
		model.addObject("listEtablissement", "mm-active");
		return model;

	}

	@GetMapping("/etablissement/profile/{id}")
	public ModelAndView etablissementProfile(@PathVariable("id") Long id) throws InvalidEntries {
		ModelAndView model = new ModelAndView("/Etablissement/EtablissementProfile");
		Etablissement etablissement = etablissement_metier.getEtablissementById(id);
		model.addObject("etablissement", etablissement);
		return model;
	}

	@PostMapping("/etablissement/modifier/{id}")
	public ModelAndView etablissementModify(@PathVariable("id") Long id, @RequestParam("name") String name)
			throws InvalidEntries {
		etablissement_metier.modifierEtablissmentById(id, name);
		return new ModelAndView("redirect:/admin/etablissement/profile/" + id);
	}

	@PostMapping("/etablissement/supprimer/{id}")
	public ModelAndView etablissemntDelete(@PathVariable("id") Long id) throws InvalidEntries {
		etablissement_metier.suprimerEtablissement(id);
		return new ModelAndView("redirect:/admin/etablissement/liste");
	}

	@GetMapping("/etablissement/profile/{id}/filiere/liste")
	public ModelAndView etablissementFiliereListe(@PathVariable("id") Long id)
			throws InvalidEntries, DataNotFoundExceptions {
		ModelAndView model = new ModelAndView("/Etablissement/EtablissementProfilesFiliereListe");
		List<Filiere> filieres = etablissement_metier.getFilieresListByEtablissement(id);
		model.addObject("filieres", filieres);
		model.addObject("etablissement", etablissement_metier.getEtablissementById(id));

		return model;
	}

	@GetMapping("/etablissement/profile/{id}/professeur/liste")
	public ModelAndView etablissementProfesseurListe(@PathVariable("id") Long id)
			throws InvalidEntries, DataNotFoundExceptions {
		ModelAndView model = new ModelAndView("/Etablissement/EtablissementProfilesProfesseurListe");
		List<Professeur> listes_de_professeurs = etablissement_metier.getProfesseursListByEtablissement(id);
		model.addObject("professeurs", listes_de_professeurs);
		model.addObject("etablissement", etablissement_metier.getEtablissementById(id));

		return model;
	}
	
	@GetMapping("/etablissement/profile/{id}/etudiant/liste")
	public ModelAndView etablissementEtudiantListe(@PathVariable("id") Long id)
			throws InvalidEntries, DataNotFoundExceptions {
		ModelAndView model = new ModelAndView("/Etablissement/EtablissementProfilesProfesseurListe");
		List<Professeur> listes_de_professeurs = etablissement_metier.getProfesseursListByEtablissement(id);
		model.addObject("professeurs", listes_de_professeurs);
		model.addObject("etablissement", etablissement_metier.getEtablissementById(id));

		return model;
	}

}

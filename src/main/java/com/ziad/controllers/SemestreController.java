package com.ziad.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.services.interfaces.SemestreInterface;

@Controller
@RequestMapping("/admin/filiere/profile/{id_filiere}/etapes/semestres")
public class SemestreController {

	private final static String ATTRIBUT_FILIERE = "filiere";
	private final static String ATTRIBUT_SEMESTRE = "semestre";

	private final static String PAGE_CREATE_SEMESTRE = "semestre/SemestreCreate";
	private final static String PAGE_PROFILE_SEMESTRE = "semestre/SemestreProfile";

	private final static String REDIRECT_LIST_ETAPES = "redirect:/admin/filiere/profile/%d/etapes";
	private final static String REDIRECT_PROFILE_SEMESTRE="redirect:/admin/filiere/profile/%d/etapes/semestres/modify/%d";

	@Autowired
	private SemestreInterface semestreMetier;

	@GetMapping("/create")
	public ModelAndView getPageCreateSemestre(@PathVariable("id_filiere") Long idfiliere)
			throws DataNotFoundExceptions {
		ModelAndView model = new ModelAndView(PAGE_CREATE_SEMESTRE);

		List<Object> besoins = semestreMetier.getPageCreateSemestre(idfiliere);
		model.addObject(ATTRIBUT_FILIERE, besoins.get(0));
		return model;
	}

	@PostMapping("/create")
	public ModelAndView createSemestre(@PathVariable("id_filiere")Long idFiliere,@RequestParam("etape")Long idEtape,@RequestParam("validation")Double validation,@RequestParam("libelle_semestre")String libelle_semestre) throws DataNotFoundExceptions {
		semestreMetier.createSemestre(idEtape, libelle_semestre, validation);		
		ModelAndView model = new ModelAndView(String.format(REDIRECT_LIST_ETAPES, idFiliere));
		return model;
	}

	@GetMapping("/modify/{id_semestre}")
	public ModelAndView getPageEditSemestre(@PathVariable("id_semestre") Long id_semestre)
			throws DataNotFoundExceptions {
		ModelAndView model = new ModelAndView(PAGE_PROFILE_SEMESTRE);
		model.addObject(ATTRIBUT_SEMESTRE, semestreMetier.getPageEditSemestre(id_semestre));
		return model;
	}

	@PostMapping("/modify/{id_semestre}")
	public ModelAndView modifySemestre(@PathVariable("id_filiere")Long idFiliere,@PathVariable("id_semestre")Long id_semestre,@RequestParam("etape")Long idEtape,@RequestParam("validation")Double validation,@RequestParam("libelle_semestre")String libelle_semestre) throws DataNotFoundExceptions{
		semestreMetier.modifierSemestre(id_semestre, libelle_semestre, validation, idEtape);
		ModelAndView model = new ModelAndView(String.format(REDIRECT_PROFILE_SEMESTRE, idFiliere,id_semestre));
		return model;
	}

	@GetMapping("/delete/{id_semestre}")
	public ModelAndView removeSemestre(@PathVariable("id_filiere") Long idFiliere,
			@PathVariable("id_semestre") Long idSemestre) {
		semestreMetier.removeSemestre(idSemestre);
		ModelAndView model = new ModelAndView(String.format(REDIRECT_LIST_ETAPES, idFiliere));
		return model;
	}
}

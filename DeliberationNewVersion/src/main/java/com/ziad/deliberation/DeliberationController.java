package com.ziad.deliberation;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.AnneeAcademique;
import com.ziad.models.Filiere;
import com.ziad.models.Semestre;

@Controller
@RequestMapping("/delib")
public class DeliberationController {

	private final static String PAGE_DELIBERATION = "admin/Ele";

	private final static String ATTRIBUT_FILIERES = "filieres";
	private final static String ATTRIBUT_ANNEES_ACADEMIQUES = "annees";
	private final static String ATTRIBUT_SEMESTRES = "semestres";
	private final static String ATTRIBUT_MODULES_JSON = "modulesjson";
	private final static String ATTRIBUT_ETAPES_JSON = "etapesjson";
	private final static String ATTRIBUT_SEMESTRES_JSON = "semestresjson";

	@Autowired
	private DeliberationInterface deliberationMetier;

	@SuppressWarnings("unchecked")
	@GetMapping("")
	public ModelAndView generatePageDelib() throws EntityNotFoundException, DataNotFoundExceptions {
		ModelAndView model = new ModelAndView(PAGE_DELIBERATION);

		List<Object> besoins = deliberationMetier.getPageBesoin();
		model.addObject(ATTRIBUT_FILIERES, (List<Filiere>) besoins.get(0));
		model.addObject(ATTRIBUT_ANNEES_ACADEMIQUES, (List<AnneeAcademique>) besoins.get(1));
		model.addObject(ATTRIBUT_SEMESTRES, (List<Semestre>) besoins.get(2));
		model.addObject(ATTRIBUT_MODULES_JSON, (String) besoins.get(3));
		model.addObject(ATTRIBUT_SEMESTRES_JSON, (String) besoins.get(4));
		model.addObject(ATTRIBUT_ETAPES_JSON, (String) besoins.get(5));
		return model;
	}

	@PostMapping("")
	public ModelAndView deliberer(@RequestParam("filiere") Long idFiliere,
			@RequestParam("annee") Long idAnneeAcademique, @RequestParam("type") String type,
			@RequestParam("element") Long id_element, @RequestParam("typedeliberation") String typeDeliberation,
			@RequestParam("consideration") Integer consideration) throws EntityNotFoundException,DataNotFoundExceptions{
		ModelAndView model = new ModelAndView();
		deliberationMetier.deliberer(idFiliere, idAnneeAcademique, type, id_element, typeDeliberation, consideration);
		return model;
	}

}

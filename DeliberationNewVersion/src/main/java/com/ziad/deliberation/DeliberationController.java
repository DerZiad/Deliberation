package com.ziad.deliberation;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lowagie.text.DocumentException;
import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.AnneeAcademique;
import com.ziad.models.Deliberation;
import com.ziad.models.Etape;
import com.ziad.models.Filiere;
import com.ziad.models.Semestre;
import com.ziad.utilities.JSONConverter;

@Controller
@RequestMapping("/delib")
public class DeliberationController {

	private final static String PAGE_DELIBERATION_PAR_MODULE = "admin/Ele";
	private final static String PAGE_DELIBERATION_PAR_SEMESTRE = "admin/DeliberationSemestre";
	private final static String PAGE_DELIBERATION_PAR_ETAPE = "admin/DeliberationEtape";

	private final static String ATTRIBUT_FILIERES = "filieres";
	private final static String ATTRIBUT_ANNEES_ACADEMIQUES = "annees";
	private final static String ATTRIBUT_SEMESTRES = "semestres";
	private final static String ATTRIBUT_ETAPES = "etapes";

	private final static String ATTRIBUT_MODULES_JSON = "modulesjson";
	private final static String ATTRIBUT_ETAPES_JSON = "etapesjson";
	private final static String ATTRIBUT_SEMESTRES_JSON = "semestresjson";

	private final static String ATTRIBUT_MODULE = "module";
	private final static String ATTRIBUT_SEMESTRE = "semestre";
	private final static String ATTRIBUT_ERROR = "error";

	private final static String ATTRIBUT_DELIBERATIONS = "deliberations";
	private final static String ATTRIBUT_DELIBERATION = "deliberation";
	private final static String ATTRIBUT_NOTES_MODULE = "notejson";

	private final static String PATH_DELIBERATION_LIST = "admin/ResultatModule";
	private final static String PATH_DELIBERATIONS_LIST = "admin/listesDeliberation";

	private final static String REDIRECT_DELIBERATION_LIST = "redirect:/delib/listerDelib?id=%d";
	private final static String REDIRECT_DELIBERATION_PAR_SEMEESTRE = "redirect:/delib/deliberationsemestre";

	@Autowired
	private DeliberationInterface deliberationMetier;

	@Autowired
	private JSONConverter converter;

	@SuppressWarnings("unchecked")
	@GetMapping("/deliberationmodule")
	public ModelAndView getPageParModule(HttpServletRequest req)
			throws EntityNotFoundException, DataNotFoundExceptions {
		ModelAndView model = new ModelAndView(PAGE_DELIBERATION_PAR_MODULE);
		List<Object> besoins = deliberationMetier.getBesoinPageDeliberationParModule(req);
		model.addObject(ATTRIBUT_FILIERES, (List<Filiere>) besoins.get(0));
		model.addObject(ATTRIBUT_ANNEES_ACADEMIQUES, (List<AnneeAcademique>) besoins.get(1));
		model.addObject(ATTRIBUT_SEMESTRES, (List<Semestre>) besoins.get(2));
		model.addObject(ATTRIBUT_MODULES_JSON, (String) besoins.get(3));
		model.addObject(ATTRIBUT_SEMESTRES_JSON, (String) besoins.get(4));
		return model;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/deliberationsemestre")
	public ModelAndView getPageParSemestre(@RequestParam(name = "message", required = false) String msg)
			throws EntityNotFoundException, DataNotFoundExceptions {
		ModelAndView model = new ModelAndView(PAGE_DELIBERATION_PAR_SEMESTRE);
		List<Object> besoins = deliberationMetier.getBesoinPageDeliberationParSemestre();
		if (msg != null) {
			model.addObject(ATTRIBUT_ERROR, msg);
		}
		model.addObject(ATTRIBUT_FILIERES, (List<Filiere>) besoins.get(0));
		model.addObject(ATTRIBUT_ANNEES_ACADEMIQUES, (List<AnneeAcademique>) besoins.get(1));
		model.addObject(ATTRIBUT_SEMESTRES, (List<Semestre>) besoins.get(2));
		model.addObject(ATTRIBUT_SEMESTRES_JSON, (String) besoins.get(3));
		return model;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/deliberationetape")
	public ModelAndView getPageParEtape() throws EntityNotFoundException, DataNotFoundExceptions {
		ModelAndView model = new ModelAndView(PAGE_DELIBERATION_PAR_ETAPE);
		List<Object> besoins = deliberationMetier.getBesoinPageDeliberationParEtape();
		model.addObject(ATTRIBUT_FILIERES, (List<Filiere>) besoins.get(0));
		model.addObject(ATTRIBUT_ANNEES_ACADEMIQUES, (List<AnneeAcademique>) besoins.get(1));
		model.addObject(ATTRIBUT_ETAPES, (List<Etape>) besoins.get(2));
		model.addObject(ATTRIBUT_ETAPES_JSON, (String) besoins.get(3));
		return model;
	}

	@PostMapping("")
	public ModelAndView deliberer(@RequestParam("annee") Long idAnneeAcademique, @RequestParam("type") String type,
			@RequestParam("element") Long id_element,
			@RequestParam(name = "typedeliberation", required = false) String typeDeliberation,
			@RequestParam(name = "consideration", required = false) Integer consideration)
			throws EntityNotFoundException, DataNotFoundExceptions {
		ModelAndView model = null;
		Deliberation deliberation = null;
		try {
			deliberation = deliberationMetier.deliberer(idAnneeAcademique, type, id_element, typeDeliberation,
					consideration);
		} catch (DeliberationEtapeNotAllowed e) {
			model = new ModelAndView(PAGE_DELIBERATION_PAR_ETAPE);
			model.addObject(ATTRIBUT_ERROR, e.getMessage());
			model.addObject(ATTRIBUT_SEMESTRE, e.getSemestre());
			return model;
		} catch (DeliberationSemestreNotAllowed e1) {
			String msg = e1.getModule().getLibelle_module() + " " + e1.getMessage();
			String link = "?error=1&message=" + msg;
			model = new ModelAndView(REDIRECT_DELIBERATION_PAR_SEMEESTRE + link);
			return model;
		}
		model = new ModelAndView(String.format(REDIRECT_DELIBERATION_LIST, deliberation.getIdDeliberation()));

		return model;
	}

	/**
	 * Resultat deliberation par module
	 **/
	@GetMapping("/listerDelib")
	public ModelAndView listerDeliberationParModule(@RequestParam(name = "id", required = false) Long idDelib)
			throws DataNotFoundExceptions {
		ModelAndView model = null;
		if (idDelib == null) {
			model = new ModelAndView(PATH_DELIBERATIONS_LIST);
			model.addObject(ATTRIBUT_DELIBERATIONS, deliberationMetier.listerDeliberation());
		} else {
			model = new ModelAndView(PATH_DELIBERATION_LIST);
			Deliberation delib = deliberationMetier.piocherDeliberation(idDelib);
			model.addObject(ATTRIBUT_DELIBERATION, delib);
			if (delib.getModule() != null) {
				model.addObject(ATTRIBUT_NOTES_MODULE, converter.convertNotesModule(delib.getNotesModule()));
			}

			if (delib.getSemestre() != null) {
				model.addObject(ATTRIBUT_NOTES_MODULE, converter.convertNotesSemestre(delib.getNotesSemestre()));
			}

			if (delib.getEtape() != null) {
				model.addObject(ATTRIBUT_NOTES_MODULE, converter.convertNotesEtape(delib.getNotesEtape()));
			}

		}
		return model;
	}

	@GetMapping("/generatePvModule")
	public ModelAndView generatePvModule(@RequestParam("idDeliberation") Long idDeliberation,
			@RequestParam(name = "ratt", required = false) Integer rattrapage, HttpServletResponse response)
			throws EntityNotFoundException, DocumentException, IOException {
		deliberationMetier.generateExcel(response, idDeliberation, rattrapage);
		ModelAndView model = new ModelAndView(String.format(REDIRECT_DELIBERATION_LIST, idDeliberation));
		return model;
	}

	@GetMapping("/generatePvEtape")
	public ModelAndView generatePvEtape(@RequestParam("idDeliberation") Long idDeliberation,
			HttpServletResponse response) throws EntityNotFoundException, DocumentException, IOException {
		deliberationMetier.generateExcelEtape(response, idDeliberation);
		ModelAndView model = new ModelAndView(String.format(REDIRECT_DELIBERATION_LIST, idDeliberation));
		return model;
	}

	@GetMapping("/generateUltimatePv")
	public ModelAndView generateUltimatePv(@RequestParam("idDeliberation") Long idDeliberation,
			@RequestParam(name = "ratt", required = false) Integer rattrapage, HttpServletResponse response)
			throws EntityNotFoundException, DocumentException, IOException {
		deliberationMetier.generateUltimatePv(idDeliberation, response);
		ModelAndView model = new ModelAndView(String.format(REDIRECT_DELIBERATION_LIST, idDeliberation));
		return model;
	}

}

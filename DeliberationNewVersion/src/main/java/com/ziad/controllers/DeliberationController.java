package com.ziad.controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.lowagie.text.DocumentException;
import com.ziad.enums.DeliberationType;
import com.ziad.enums.TypeNote;
import com.ziad.exceptions.CSVReaderOException;
import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.exceptions.DeliberationElementNotAllowed;
import com.ziad.exceptions.DeliberationEtapeNotAllowed;
import com.ziad.exceptions.DeliberationModuleNotAllowed;
import com.ziad.exceptions.DeliberationSemestreNotAllowed;
import com.ziad.exceptions.ErrorException;
import com.ziad.exceptions.InvalidCredinals;
import com.ziad.models.AnneeAcademique;
import com.ziad.models.Deliberation;
import com.ziad.models.Etape;
import com.ziad.models.Filiere;
import com.ziad.models.Semestre;
import com.ziad.services.interfaces.DeliberationInterface;
import com.ziad.utilities.JSONConverter;

@Controller
@RequestMapping("/delib")
@RestController
public class DeliberationController {

	private final static String PAGE_DELIBERATION_PAR_MODULE = "admin/Ele";
	private final static String PAGE_DELIBERATION_PAR_SEMESTRE = "admin/DeliberationSemestre";
	private final static String PAGE_DELIBERATION_PAR_ETAPE = "admin/DeliberationEtape";
	private final static String PAGE_DELIBERATION_PAR_ELEMENT = "admin/DeliberationElement";
	private final static String PAGE_RESULTAT_ELEMENT = "admin/ResultatElement";

	private final static String ATTRIBUT_FILIERES = "filieres";
	private final static String ATTRIBUT_ANNEE_ACADEMIQUE = "annee";
	private final static String ATTRIBUT_SEMESTRES = "semestres";
	private final static String ATTRIBUT_ETAPES = "etapes";

	private final static String ATTRIBUT_MODULES_JSON = "modulesjson";
	private final static String ATTRIBUT_ETAPES_JSON = "etapesjson";
	private final static String ATTRIBUT_SEMESTRES_JSON = "semestresjson";
	private final static String ATTRIBUT_ELEMENTS_JSON = "elementsjson";
	private final static String ATTRIBUT_NOTES_JSON = "notesjson";

	private final static String ATTRIBUT_SEMESTRE = "semestre";
	private final static String ATTRIBUT_ERROR = "error";

	private final static String ATTRIBUT_DELIBERATIONS = "deliberations";
	private final static String ATTRIBUT_DELIBERATION = "deliberation";
	private final static String ATTRIBUT_NOTES_MODULE = "notejson";

	private final static String PATH_DELIBERATION_LIST = "admin/ResultatDeliberation";
	private final static String PATH_DELIBERATIONS_LIST = "admin/listesDeliberation";

	private final static String REDIRECT_DELIBERATION_LIST = "redirect:/delib/listerDelib?id=%d";
	private final static String REDIRECT_DELIBERATION_PAR_SEMEESTRE = "redirect:/delib/deliberationsemestre";
	private final static String REDIRECT_DELIBERATION_PAR_MODULE = "redirect:/delib/deliberationmodule";
	private final static String REDIRECT_DELIBERATION_PAR_ELEMENT_AFFICHAGE = "redirect:/delib/deliberationelementprofile/%d";

	@Autowired
	private DeliberationInterface deliberationMetier;

	@Autowired
	private JSONConverter converter;

	/***
	 * 
	 * Deliberation d'element
	 * 
	 **/
	@SuppressWarnings("unchecked")
	@GetMapping("/deliberationelement")
	public ModelAndView getPageDeliberationByElement(HttpServletRequest req)
			throws EntityNotFoundException, DataNotFoundExceptions {
		ModelAndView model = new ModelAndView(PAGE_DELIBERATION_PAR_ELEMENT);
		List<Object> besoins = deliberationMetier.getBesoinPageDeliberationParElement(req);
		model.addObject(ATTRIBUT_FILIERES, (List<Filiere>) besoins.get(0));
		model.addObject(ATTRIBUT_ANNEE_ACADEMIQUE, (AnneeAcademique) besoins.get(1));
		model.addObject(ATTRIBUT_SEMESTRES, (List<Semestre>) besoins.get(2));
		model.addObject(ATTRIBUT_MODULES_JSON, (String) besoins.get(3));
		model.addObject(ATTRIBUT_SEMESTRES_JSON, (String) besoins.get(4));
		model.addObject(ATTRIBUT_ELEMENTS_JSON, (String) besoins.get(5));
		return model;
	}

	@PostMapping("/deliberationelement")
	public ModelAndView delibererElement(@RequestParam("annee") Long idAnneeAcademique,
			@RequestParam("type") String type, @RequestParam("element") Long id_element,
			@RequestParam("file") MultipartFile file, @RequestParam("coefficientControl") Double coefficentControl,
			HttpServletRequest req,@RequestParam("coefficientTp") Double coefficentTp, @RequestParam("coefficentExam") Double coefficentExam)
			throws EntityNotFoundException, DataNotFoundExceptions, IOException, CSVReaderOException {
		ModelAndView model = new ModelAndView(PAGE_DELIBERATION_PAR_ELEMENT);
		List<Object> besoins = deliberationMetier.getBesoinPageDeliberationParElement(req);
		model.addObject(ATTRIBUT_FILIERES, (List<Filiere>) besoins.get(0));
		model.addObject(ATTRIBUT_ANNEE_ACADEMIQUE, (AnneeAcademique) besoins.get(1));
		model.addObject(ATTRIBUT_SEMESTRES, (List<Semestre>) besoins.get(2));
		model.addObject(ATTRIBUT_MODULES_JSON, (String) besoins.get(3));
		model.addObject(ATTRIBUT_SEMESTRES_JSON, (String) besoins.get(4));
		model.addObject(ATTRIBUT_ELEMENTS_JSON, (String) besoins.get(5));
		Deliberation deliberation = null;
		try {
			deliberation = deliberationMetier.delibererElementOrdinaire(idAnneeAcademique, id_element, file,
					coefficentControl, coefficentTp, coefficentExam);
			model = new ModelAndView(
					String.format(REDIRECT_DELIBERATION_PAR_ELEMENT_AFFICHAGE, deliberation.getIdDeliberation()));
		} catch (InvalidCredinals e) {
			model.addObject(ATTRIBUT_ERROR, e);
		}

		return model;
	}

	@GetMapping("/deliberationelementprofile/{idDeliberation}")
	public ModelAndView getPageDeliberationByElementProfile(@PathVariable("idDeliberation") Long idDeliberation,
			HttpServletRequest req) throws EntityNotFoundException, DataNotFoundExceptions {
		ModelAndView model = new ModelAndView(PAGE_RESULTAT_ELEMENT);
		Deliberation deliberation = deliberationMetier.piocherDeliberation(idDeliberation);
		model.addObject(ATTRIBUT_DELIBERATION, deliberation);
		model.addObject(ATTRIBUT_NOTES_JSON, converter.convertNotesElement(deliberation.getNotesElement()));
		return model;
	}

	@PostMapping("/deliberationelementprofile/{idDeliberation}")
	public ModelAndView delibererElementRattrapage(@PathVariable("idDeliberation") Long idDeliberation,
			@RequestParam("file") MultipartFile file, @RequestParam("coefficentExam") Double coefficentExam,

			@RequestParam(name = "consideration", required = false) Integer consideration)
			throws EntityNotFoundException, DataNotFoundExceptions, IOException, CSVReaderOException {
		ModelAndView model = new ModelAndView(PAGE_RESULTAT_ELEMENT);
		Deliberation deliberation1 = deliberationMetier.piocherDeliberation(idDeliberation);
		model.addObject(ATTRIBUT_DELIBERATION, deliberation1);
		model.addObject(ATTRIBUT_NOTES_JSON, converter.convertNotesElement(deliberation1.getNotesElement()));
		Deliberation deliberation = null;
		try {
			deliberation = deliberationMetier.delibererElementRattrapage(idDeliberation, file, coefficentExam,
					consideration);
			model = new ModelAndView(
					String.format(REDIRECT_DELIBERATION_PAR_ELEMENT_AFFICHAGE, deliberation.getIdDeliberation()));
		} catch (InvalidCredinals e) {
			model = new ModelAndView(PAGE_RESULTAT_ELEMENT);
			model.addObject(ATTRIBUT_ERROR, e);
		}

		return model;
	}

	@GetMapping("/downloadordinaire")
	public void downloadListEtudiant(HttpServletResponse response, @RequestParam("element") Long id_element,
			@RequestParam("annee") Long id_annee) throws EntityNotFoundException, IOException {
		response.setContentType("application/octet-stream");
		String header_key = "Content-Disposition";
		String header_value = "attachement; filename=Etudiants.xlsx";
		response.setHeader(header_key, header_value);
		deliberationMetier.generateExcel(id_element, id_annee, response);
	}

	@GetMapping("/downloadrattrapage/{idDeliberation}")
	public void downloadListEtudiantRattrapage(HttpServletResponse response,
			@PathVariable("idDeliberation") Long idDeliberation) throws EntityNotFoundException, IOException {
		response.setContentType("application/octet-stream");
		String header_key = "Content-Disposition";
		String header_value = "attachement; filename=Etudiants.xlsx";
		response.setHeader(header_key, header_value);
		deliberationMetier.generateExcelRattrapage(idDeliberation, response);
	}

	/**
	 * Méthode REST API pour Ajax Request Deliberation Element
	 * 
	 **/
	@GetMapping("/listeretudiants")
	@JsonAnyGetter
	public String listerEtudiants(@RequestParam("element") Long id_element, @RequestParam("annee") Long id_annee) {
		String etudiants = deliberationMetier.getEtudiants(id_element, id_annee);
		return etudiants;
	}

	@GetMapping("/generatePvElement/{idDeliberation}")
	public ModelAndView generatePvElement(@PathVariable("idDeliberation") Long idDeliberation,
			HttpServletResponse response) throws EntityNotFoundException, DataNotFoundExceptions, DocumentException,
			IOException, DataNotFoundExceptions {
		deliberationMetier.generatePvDeliberationElement(idDeliberation, response);
		ModelAndView model = new ModelAndView(String.format(REDIRECT_DELIBERATION_LIST, idDeliberation));
		return model;
	}

	/**
	 * 
	 * *
	 * 
	 * 
	 * 
	 * 
	 */

	@GetMapping("/download/{id_module}")
	public void makeListtoDownload(@PathVariable("id_module") Long id_module, @RequestParam("annee") Long id_annee,
			HttpServletResponse response) throws EntityNotFoundException, IOException {
		response.setContentType("application/octet-stream");
		String header_key = "Content-Disposition";
		String header_value = "attachement; filename=Etudiants.xlsx";
		response.setHeader(header_key, header_value);
		deliberationMetier.generateExcel(id_module, id_annee, response);

	}

	/**
	 * Deliberation de Module
	 **/

	@SuppressWarnings("unchecked")
	@GetMapping("/deliberationmodule")
	public ModelAndView getPageParModule(HttpServletRequest req)
			throws EntityNotFoundException, DataNotFoundExceptions {
		ModelAndView model = new ModelAndView(PAGE_DELIBERATION_PAR_MODULE);
		List<Object> besoins = deliberationMetier.getBesoinPageDeliberationParModule(req);
		model.addObject(ATTRIBUT_FILIERES, (List<Filiere>) besoins.get(0));
		model.addObject(ATTRIBUT_ANNEE_ACADEMIQUE, (AnneeAcademique) besoins.get(1));
		model.addObject(ATTRIBUT_SEMESTRES, (List<Semestre>) besoins.get(2));
		model.addObject(ATTRIBUT_MODULES_JSON, (String) besoins.get(3));
		model.addObject(ATTRIBUT_SEMESTRES_JSON, (String) besoins.get(4));
		return model;
	}

	@PostMapping("/deliberationmodule")
	public ModelAndView delibererModule(@RequestParam("element") Long idModule,
			@RequestParam("annee") Long idAnneeAcademique, HttpServletRequest req)
			throws EntityNotFoundException, DataNotFoundExceptions {
		ErrorException error = null;
		ModelAndView model = new ModelAndView(PAGE_DELIBERATION_PAR_MODULE);
		try {
			Deliberation delib = deliberationMetier.delibererModule(idModule, idAnneeAcademique);
			model = new ModelAndView(String.format(REDIRECT_DELIBERATION_LIST, delib.getIdDeliberation()));
		} catch (DeliberationModuleNotAllowed e) {
			error = new ErrorException("Erreur dans " + e.getModule().getLibelle_module(), e.getMessage());
			model.addObject(ATTRIBUT_ERROR, error);
			List<Object> besoins = deliberationMetier.getBesoinPageDeliberationParModule(req);
			model.addObject(ATTRIBUT_FILIERES, (List<Filiere>) besoins.get(0));
			model.addObject(ATTRIBUT_ANNEE_ACADEMIQUE, (AnneeAcademique) besoins.get(1));
			model.addObject(ATTRIBUT_SEMESTRES, (List<Semestre>) besoins.get(2));
			model.addObject(ATTRIBUT_MODULES_JSON, (String) besoins.get(3));
			model.addObject(ATTRIBUT_SEMESTRES_JSON, (String) besoins.get(4));
			System.out.println("ErreurMod");
		} catch (DeliberationElementNotAllowed e1) {
			List<Object> besoins = deliberationMetier.getBesoinPageDeliberationParModule(req);
			model.addObject(ATTRIBUT_FILIERES, (List<Filiere>) besoins.get(0));
			model.addObject(ATTRIBUT_ANNEE_ACADEMIQUE, (AnneeAcademique) besoins.get(1));
			model.addObject(ATTRIBUT_SEMESTRES, (List<Semestre>) besoins.get(2));
			model.addObject(ATTRIBUT_MODULES_JSON, (String) besoins.get(3));
			model.addObject(ATTRIBUT_SEMESTRES_JSON, (String) besoins.get(4));
			model.addObject(ATTRIBUT_ERROR, error);
			return model;
		}
		return model;
	}

	/**
	 * 
	 * Déliberation Semestre
	 * 
	 **/

	@SuppressWarnings("unchecked")
	@GetMapping("/deliberationsemestre")
	public ModelAndView getPageParSemestre(@RequestParam(name = "message", required = false) String msg,
			HttpServletRequest req) throws EntityNotFoundException, DataNotFoundExceptions {
		ModelAndView model = new ModelAndView(PAGE_DELIBERATION_PAR_SEMESTRE);
		List<Object> besoins = deliberationMetier.getBesoinPageDeliberationParSemestre(req);
		if (msg != null) {
			model.addObject(ATTRIBUT_ERROR, msg);
		}
		model.addObject(ATTRIBUT_FILIERES, (List<Filiere>) besoins.get(0));
		model.addObject(ATTRIBUT_ANNEE_ACADEMIQUE, (AnneeAcademique) besoins.get(1));
		model.addObject(ATTRIBUT_SEMESTRES, (List<Semestre>) besoins.get(2));
		model.addObject(ATTRIBUT_SEMESTRES_JSON, (String) besoins.get(3));
		return model;
	}
	
	@PostMapping("/deliberationsemestre")
	public ModelAndView delibererSemestre(@RequestParam("element") Long idSemestre,
			@RequestParam("annee") Long idAnneeAcademique, HttpServletRequest req)
			throws EntityNotFoundException, DataNotFoundExceptions {
		ErrorException error = null;
		ModelAndView model = new ModelAndView(PAGE_DELIBERATION_PAR_MODULE);
		try {
			Deliberation delib = deliberationMetier.delibererSemestre(idSemestre, idAnneeAcademique);
			model = new ModelAndView(String.format(REDIRECT_DELIBERATION_LIST, delib.getIdDeliberation()));
		} catch (DeliberationSemestreNotAllowed e) {
			error = new ErrorException("Erreur dans " + e.getModule().getLibelle_module(), e.getMessage());
			model.addObject(ATTRIBUT_ERROR, error);
			List<Object> besoins = deliberationMetier.getBesoinPageDeliberationParSemestre(req);
			model.addObject(ATTRIBUT_FILIERES, (List<Filiere>) besoins.get(0));
			model.addObject(ATTRIBUT_ANNEE_ACADEMIQUE, (AnneeAcademique) besoins.get(1));
			model.addObject(ATTRIBUT_SEMESTRES, (List<Semestre>) besoins.get(2));
			model.addObject(ATTRIBUT_SEMESTRES_JSON, (String) besoins.get(3));
		}
		return model;
	}

	/**
	 * 
	 * Déliberation Etape
	 * 
	 **/

	@SuppressWarnings("unchecked")
	@GetMapping("/deliberationetape")
	public ModelAndView getPageParEtape(HttpServletRequest req) throws EntityNotFoundException, DataNotFoundExceptions {
		ModelAndView model = new ModelAndView(PAGE_DELIBERATION_PAR_ETAPE);
		List<Object> besoins = deliberationMetier.getBesoinPageDeliberationParEtape(req);
		model.addObject(ATTRIBUT_FILIERES, (List<Filiere>) besoins.get(0));
		model.addObject(ATTRIBUT_ANNEE_ACADEMIQUE, (List<AnneeAcademique>) besoins.get(1));
		model.addObject(ATTRIBUT_ETAPES, (List<Etape>) besoins.get(2));
		model.addObject(ATTRIBUT_ETAPES_JSON, (String) besoins.get(3));
		return model;
	}

	/**
	 * Resultat deliberation par module
	 **/
	@GetMapping("/listerDelib")
	public ModelAndView listerDeliberationParModule(@RequestParam(name = "id", required = false) Long idDelib,
			HttpServletRequest req) throws DataNotFoundExceptions {
		ModelAndView model = null;
		if (idDelib == null) {
			model = new ModelAndView(PATH_DELIBERATIONS_LIST);
			model.addObject(ATTRIBUT_DELIBERATIONS, deliberationMetier.listerDeliberation(req));
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

			deliberationMetier.saveExtendsLayout(req);
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

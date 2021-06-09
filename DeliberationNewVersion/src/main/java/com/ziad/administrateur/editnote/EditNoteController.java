package com.ziad.administrateur.editnote;

import java.util.ArrayList;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.NoteElement;

@Controller
@RequestMapping("/admin/gestionnote")
public class EditNoteController {

	private final static String PAGE_NOTE_LIST = "gestionnote/gestionnote";

	private final static String PAGE_NOTE_EDIT = "gestionnote/EditNote";

	private final static String ATTRIBUT_ELEMENTS_JSON = "elementsjson";
	private final static String ATTRIBUT_MODULES_JSON = "modulesjson";
	private final static String ATTRIBUT_SEMESTRES_JSON = "semestresjson";
	private final static String ATTRIBUT_ETAPES_JSON = "etapesjson";
	private final static String ATTRIBUT_FILIERES = "filieres";
	private final static String ATTRIBUT_ANNEES_ACADEMIQUE = "anneesAcademiques";
	private final static String ATTRIBUT_NOTES = "notes";
	private final static String ATTRIBUT_NOTE = "note";

	@Autowired
	private EditNoteInterface editNoteMetier;

	@GetMapping("")
	public ModelAndView getPageModification() throws DataNotFoundExceptions {
		ModelAndView model = new ModelAndView(PAGE_NOTE_LIST);

		ArrayList<Object> besoins = editNoteMetier.grabBesoins();

		model.addObject(ATTRIBUT_FILIERES, besoins.get(0));
		model.addObject(ATTRIBUT_ANNEES_ACADEMIQUE, besoins.get(1));
		model.addObject(ATTRIBUT_ELEMENTS_JSON, besoins.get(2));
		model.addObject(ATTRIBUT_MODULES_JSON, besoins.get(3));
		model.addObject(ATTRIBUT_SEMESTRES_JSON, besoins.get(4));
		model.addObject(ATTRIBUT_ETAPES_JSON, besoins.get(5));

		return model;

	}

	@PostMapping("")
	public ModelAndView getPageModificationWithFiler(@RequestParam("id_annee_academique") Long idAnneeAcademique,
			@RequestParam("id_filiere") Long idFiliere, @RequestParam("id_etape") Long idEtape,
			@RequestParam("id_semestre") Long idSemestre, @RequestParam("id_module") Long idModule,
			@RequestParam("id_element") Long idElement) throws DataNotFoundExceptions, EntityNotFoundException {

		ModelAndView model = new ModelAndView(PAGE_NOTE_LIST);

		ArrayList<Object> besoins = editNoteMetier.grabBesoinsByFilter(idAnneeAcademique, idEtape, idFiliere, idModule,
				idElement, idSemestre);

		model.addObject(ATTRIBUT_NOTES, besoins.get(0));
		/*
		 * model.addObject(ATTRIBUT_FILIERES, besoins.get(1));
		 * model.addObject(ATTRIBUT_ANNEES_ACADEMIQUE, besoins.get(2));
		 * model.addObject(ATTRIBUT_ELEMENTS_JSON, besoins.get(3));
		 * model.addObject(ATTRIBUT_MODULES_JSON, besoins.get(4));
		 * model.addObject(ATTRIBUT_SEMESTRES_JSON, besoins.get(5));
		 * model.addObject(ATTRIBUT_ETAPES_JSON, besoins.get(6));
		 */

		return model;

	}

	@GetMapping("/edit/{idEtudiant}/{idElement}")
	public ModelAndView getPageEditNote(@PathVariable("idEtudiant") Long idEtudiant,
			@PathVariable("idElement") Long idElement) throws EntityNotFoundException {
		ModelAndView model = new ModelAndView(PAGE_NOTE_EDIT);
		NoteElement note = editNoteMetier.getNoteElement(idEtudiant, idElement);
		model.addObject(ATTRIBUT_NOTE, note);
		return model;

	}

	@PostMapping("/edit/{idEtudiant}/{idElement}")
	public ModelAndView getPageModificationWithFiler(@PathVariable("idEtudiant") Long idEtudiant,
			@PathVariable("idElement") Long idElement, @RequestParam("etat") String etat,
			@RequestParam("note") Double note) throws DataNotFoundExceptions, EntityNotFoundException {

		ModelAndView model = new ModelAndView(PAGE_NOTE_EDIT);
		NoteElement noteElement = editNoteMetier.editNote(idEtudiant, idElement, note, etat);
		model.addObject(ATTRIBUT_NOTE, noteElement);
		return model;

	}
}
package com.ziad.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ziad.enums.TypeNote;
import com.ziad.exceptions.CSVReaderOException;
import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.NoteNorm;
import com.ziad.service.administrateur.editnote.EditNoteInterface;

@Controller
@RequestMapping("/gestionnote")
public class EditNoteController {

	private final static String PAGE_NOTE_LIST_GESTION = "gestionnote/gestionnote";
	private final static String PAGE_NOTE_LIST_GESTION_NOTE = "gestionnote/addNote";
	private final static String PAGE_NOTE_AJOUTER_NOTE = "gestionnote/ajouter";

	private final static String ATTRIBUT_TYPES = "types";

	private final static String ACTIVE = "mm-active";
	private final static String ATTRIBUT_NAVBAR_AJOUTER_NOTE = "ajoutnote";

	private final static String REDIRECT_TO_NOTE = "redirect:/gestionnote/ajouter";

	private final static String PAGE_NOTE_LIST = "gestionnote/ListeNote";

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

	@GetMapping("/addnote")
	public ModelAndView getNotePage() throws DataNotFoundExceptions {
		ModelAndView model = new ModelAndView(PAGE_NOTE_LIST_GESTION_NOTE);

		ArrayList<Object> besoins = editNoteMetier.grabBesoins();
		model.addObject(ATTRIBUT_FILIERES, besoins.get(0));
		model.addObject(ATTRIBUT_ANNEES_ACADEMIQUE, besoins.get(1));
		model.addObject(ATTRIBUT_ELEMENTS_JSON, besoins.get(2));
		model.addObject(ATTRIBUT_MODULES_JSON, besoins.get(3));
		model.addObject(ATTRIBUT_SEMESTRES_JSON, besoins.get(4));
		model.addObject(ATTRIBUT_ETAPES_JSON, besoins.get(5));
		return model;
	}

	@PostMapping("/addnote")
	public void downloadEtudiant(@RequestParam("id_annee_academique") Long idAnneeAcademique,
			@RequestParam(name = "id_etape") Long idEtape, @RequestParam(name = "id_semestre") Long idSemestre,
			@RequestParam(name = "id_module") Long idModule, @RequestParam(name = "id_element") Long idElement,
			HttpServletResponse resp) throws DataNotFoundExceptions, IOException {
		resp.setContentType("application/octet-stream");
		String header_key = "Content-Disposition";
		String header_value = "attachement; filename=Etudiants.xlsx";
		resp.setHeader(header_key, header_value);
		editNoteMetier.downloadExcel(resp, idElement, idAnneeAcademique);
	}

	@GetMapping("/ajouter")
	public ModelAndView createPageNote() {
		ModelAndView model = new ModelAndView(PAGE_NOTE_AJOUTER_NOTE);
		model.addObject(ATTRIBUT_NAVBAR_AJOUTER_NOTE, ACTIVE);
		model.addObject(ATTRIBUT_TYPES, TypeNote.values());
		return model;
	}

	@PostMapping("/ajouter")
	public ModelAndView uploadNote(@RequestParam("file") MultipartFile file, @RequestParam("type") String type_exam,
			@RequestParam("coeficient") Double coefficient)
			throws DataNotFoundExceptions, EntityNotFoundException, IOException, CSVReaderOException {
		ModelAndView model = new ModelAndView(REDIRECT_TO_NOTE);
		editNoteMetier.readExcel(file, type_exam, coefficient);
		return model;
	}

	@GetMapping("")
	public ModelAndView getPageModification() throws DataNotFoundExceptions {
		ModelAndView model = new ModelAndView(PAGE_NOTE_LIST_GESTION);

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
			@RequestParam(name = "id_etape", required = false) Long idEtape,
			@RequestParam(name = "id_semestre", required = false) Long idSemestre,
			@RequestParam(name = "id_module", required = false) Long idModule,
			@RequestParam(name = "id_element", required = false) Long idElement,
			@RequestParam(name = "action", required = false) String action)
			throws DataNotFoundExceptions, EntityNotFoundException {

		ModelAndView model = new ModelAndView(PAGE_NOTE_LIST);
		ArrayList<Object> besoins = editNoteMetier.grabBesoinsByFilter(idAnneeAcademique, idEtape, idModule, idElement,
				idSemestre, action);
		model.addObject(ATTRIBUT_NOTES, besoins.get(0));
		model.addObject("id_annee_academique",idAnneeAcademique);
		model.addObject("id_etape",idEtape);
		model.addObject("id_semestre",idSemestre);
		model.addObject("id_module",idModule);
		model.addObject("id_element",idElement);
		
		return model;

	}

	@GetMapping("/edit/{idEtudiant}/{idElement}")
	public ModelAndView getPageEditNote(@PathVariable("idEtudiant") Long idEtudiant,
			@PathVariable("idElement") Long idElement, @RequestParam("type") String type)
			throws EntityNotFoundException {
		ModelAndView model = new ModelAndView(PAGE_NOTE_EDIT);
		NoteNorm note = editNoteMetier.getNoteElement(idEtudiant, idElement, type);
		model.addObject(ATTRIBUT_NOTE, note);
		return model;

	}

	@PostMapping("/edit/{idEtudiant}/{idElement}")
	public ModelAndView getPageModificationWithFiler(@PathVariable("idEtudiant") Long idEtudiant,
			@PathVariable("idElement") Long idElement, @RequestParam("type") String type,
			@RequestParam("note") Double note) throws DataNotFoundExceptions, EntityNotFoundException {

		ModelAndView model = new ModelAndView(PAGE_NOTE_EDIT);
		NoteNorm noteElement = editNoteMetier.editNote(idEtudiant, idElement, note, type);
		model.addObject(ATTRIBUT_NOTE, noteElement);
		return model;

	}
}

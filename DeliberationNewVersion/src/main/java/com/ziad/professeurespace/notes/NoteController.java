package com.ziad.professeurespace.notes;

import java.io.IOException;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ziad.enums.TypeNote;
import com.ziad.exceptions.CSVReaderOException;
import com.ziad.exceptions.DataNotFoundExceptions;

@Controller
@RequestMapping("/professeur/note")
public class NoteController {

	@Autowired
	private NoteInterface note_metier;

	private final static String ATTRIBUT_TYPES = "types";
	
	private final static String REDIRECT_TO_NOTE = "redirect:/professeur/note/ajouter";

	@GetMapping("/ajouter")
	public ModelAndView createPageNote() {
		ModelAndView model = new ModelAndView("espace_professeur/ajouterNote");
		model.addObject(ATTRIBUT_TYPES, TypeNote.values());
		return model;
	}

	@PostMapping("/ajouter")
	public ModelAndView uploadNote(@RequestParam("file") MultipartFile file, @RequestParam("type") String type_exam,
			@RequestParam("coeficient") Double coefficient)
			throws DataNotFoundExceptions, EntityNotFoundException, IOException, CSVReaderOException {
		ModelAndView model = new ModelAndView(REDIRECT_TO_NOTE);
		note_metier.readExcel(file, type_exam,coefficient);
		return model;
	}
}

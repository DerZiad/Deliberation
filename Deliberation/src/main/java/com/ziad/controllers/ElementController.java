package com.ziad.controllers;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ziad.services.interfaces.ElementInterface;

@Controller
@RequestMapping("/admin")
public class ElementController {

	public static final String PATH_ELEMENTCREATE = "Element/ElementCreate";
	public static final String PATH_ELEMENTLIST = "Element/ListElement";
	public static final String PATH_ELEMENTPROFILE = "Element/ElementProfile";

	@Autowired
	private ElementInterface metier;

	@GetMapping("/module/profile/{id}/element/create")
	public ModelAndView createModule(@PathVariable("id") Long id) throws EntityNotFoundException {
		ModelAndView model = new ModelAndView(PATH_ELEMENTCREATE);
		model.addObject("id_module");
		metier.createElementsModule(id, model);
		return model;
	}

	@GetMapping("/module/profile/{idmodule}/element/modify/{idelement}")
	public ModelAndView modifyElement(@PathVariable("idmodule") Long idmodule,
			@PathVariable("idelement") Long idelement) {
		ModelAndView model = new ModelAndView(PATH_ELEMENTPROFILE);
		metier.createPageModification(idelement, idmodule, model);
		return model;
	}

	@GetMapping("/module/profile/{id}/element/{idelement}/delete")
	public ModelAndView deleteElement(@PathVariable("id") Long id,@PathVariable("idelement") Long idelement) throws EntityNotFoundException {
		metier.deleteElementsModule(idelement);
		return new ModelAndView("redirect:/admin/module/profile/" + id + "/element/create");
	}

	@PostMapping("/module/profile/{id}/element/modify")
	public ModelAndView modifyElement(@PathVariable("id") Long id,@RequestParam("idelement") Long idelement,
			@RequestParam(name = "professeur_id",required = false) Long idprofesseur, @RequestParam("coefficient") Double coefficient,
			@RequestParam("validation") Double validation, @RequestParam("name") String nom) throws EntityNotFoundException {
		ModelAndView model = new ModelAndView("redirect:/admin/module/profile/"+id+"/element/create");
		metier.modifyElementsModule(idelement, idprofesseur,id, nom,coefficient, validation);
		return model;
	}

	@PostMapping("/module/profile/{idmodule}/element/create")
	public ModelAndView createElement(@PathVariable("idmodule") Long idmodule,
			@RequestParam(name = "professeur_id",required = false) Long idprofesseur, @RequestParam("coefficient") Long coefficient,
			@RequestParam("validation") Double validation, @RequestParam("name") String nom) {
		ModelAndView model = new ModelAndView("redirect:/admin/module/profile/" + idmodule + "/element/create");
		metier.createElement(idmodule, idprofesseur, nom, validation, validation);
		return model;
	}

}

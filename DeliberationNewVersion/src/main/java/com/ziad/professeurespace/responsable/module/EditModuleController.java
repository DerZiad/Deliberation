package com.ziad.professeurespace.responsable.module;

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

import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.Element;
import com.ziad.models.Modulee;

@Controller
@RequestMapping("/professeur/responsablemodule")
public class EditModuleController {

	private final static String PAGE_PROFILE_ELEMENT = "espace_professeur/responsablemodule/ElementProfile";
	private final static String PAGE_LIST_MODULES = "espace_professeur/responsablemodule/mesmodules";

	private final static String ATTRIBUT_ELEMENT = "element";
	private final static String ATTRIBUT_ELEMENTS = "elements";
	private final static String ATTRIBUT_MODULES = "modules";
	private final static String ATTRIBUT_ELEMENTS_JSON = "elementsjson";
	
	private final static String ACTIVE = "mm-active";
	private final static String ATTRIBUT_NAVBAR_MES_MODULES = "mesmodules";
	
	
	@Autowired
	private EditModuleInterface editMetier;

	@SuppressWarnings("unchecked")
	@GetMapping("/listermodules")
	public ModelAndView listerModules() throws DataNotFoundExceptions{
		ModelAndView model = new ModelAndView(PAGE_LIST_MODULES);
		List<Object> besoins = editMetier.getModulesElementsProfesseur();
		model.addObject(ATTRIBUT_MODULES,(List<Modulee>)besoins.get(0));
		model.addObject(ATTRIBUT_ELEMENTS,(List<Element>)besoins.get(1));
		model.addObject(ATTRIBUT_ELEMENTS_JSON,(String)besoins.get(2));
		model.addObject(ATTRIBUT_NAVBAR_MES_MODULES,ACTIVE);
		return model;
	}

	@GetMapping("/element/{idElement}")
	public ModelAndView getElementProfile(@PathVariable("idElement") Long idElement) throws EntityNotFoundException {
		ModelAndView model = new ModelAndView(PAGE_PROFILE_ELEMENT);
		model.addObject(ATTRIBUT_ELEMENT, editMetier.getElement(idElement));
		model.addObject(ATTRIBUT_NAVBAR_MES_MODULES,ACTIVE);
		return model;
	}

	@PostMapping("/element/{idElement}")
	public ModelAndView editElement(@PathVariable("idElement") Long idElement,
			@RequestParam("validation") Double validation, @RequestParam("coeficient") Double coeficient)
			throws EntityNotFoundException {
		ModelAndView model = new ModelAndView(PAGE_PROFILE_ELEMENT);
		editMetier.saveInformations(idElement, validation, coeficient);
		model.addObject(ATTRIBUT_ELEMENT, editMetier.getElement(idElement));
		model.addObject(ATTRIBUT_NAVBAR_MES_MODULES,ACTIVE);
		return model;
	}
}

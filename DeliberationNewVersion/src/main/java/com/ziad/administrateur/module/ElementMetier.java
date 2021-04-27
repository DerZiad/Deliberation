package com.ziad.administrateur.module;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.ziad.models.Element;
import com.ziad.models.Modulee;
import com.ziad.models.Professeur;
import com.ziad.repositories.ElementRepository;
import com.ziad.repositories.ModuleRepository;
import com.ziad.repositories.ProfesseurRepository;

@Service
@Primary
public class ElementMetier implements ElementInterface {
	@Autowired
	private ModuleRepository moduleRepository;
	@Autowired
	private ProfesseurRepository professeurRepository;
	@Autowired
	private ElementRepository elementRepository;

	@Override
	public void modifyElementsModule(Long idelement, Long idprofesseur, Long idmodule, String libelle_element,
			Double coeficient, Double validation, ModelAndView model) throws EntityNotFoundException {
		Element element = elementRepository.findById(idelement).get();
		Modulee module = moduleRepository.findById(idmodule).get();
		if(idprofesseur != null) {
			Professeur professeur = professeurRepository.findById(idprofesseur).get();
			element.addProfesseur(professeur);
		}
		element.setModule(module);
		element.setCoeficient(coeficient);
		element.setLibelle_element(libelle_element);
		element.setValidation(validation);
		elementRepository.save(element);
	}

	@Override
	public void deleteElementsModule(Long id) throws EntityNotFoundException {
		Element element = elementRepository.findById(id).get();
		elementRepository.delete(element);

	}

	@Override
	public void createElementsModule(Long idmodule, ModelAndView model) throws EntityNotFoundException {
		Modulee module = moduleRepository.findById(idmodule).get();
		model.addObject("module", module);
		List<Professeur> professeurs = professeurRepository.findAll();
		model.addObject("professeurs", professeurs);
		model.addObject("elements",module.getElements());
	}

	@Override
	public void createPageModification(Long idelement, Long idmodule, ModelAndView model) {
		Element element = elementRepository.findById(idelement).get();
		Modulee module = moduleRepository.findById(idmodule).get();
		model.addObject("professeurs", professeurRepository.findAll());
		model.addObject("element", element);
		model.addObject("module", module);

	}

	@Override
	public void createElement(Long idmodule, Long idprofesseur, String libelle_element, Double coeficient,
			Double validation) {
		Element element = new Element();
		Modulee module = moduleRepository.findById(idmodule).get();
		if (module != null)
			element.setModule(module);
		if(idprofesseur != null) {
			Professeur professeur = professeurRepository.findById(idprofesseur).get();
			element.addProfesseur(professeur);
		}
		element.setLibelle_element(libelle_element);
		element.setCoeficient(coeficient);
		element.setValidation(validation);
		elementRepository.save(element);

	}
}

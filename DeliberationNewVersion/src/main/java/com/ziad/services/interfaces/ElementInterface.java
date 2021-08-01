package com.ziad.services.interfaces;

import javax.persistence.EntityNotFoundException;

import org.springframework.web.servlet.ModelAndView;

public interface ElementInterface {
	public void modifyElementsModule(Long idelement, Long idprofesseur, Long idmodule, String libelle_element,
			Double coeficient, Double validation) throws EntityNotFoundException;
	public void deleteElementsModule(Long id) throws EntityNotFoundException;
	public void createElementsModule(Long idmodule, ModelAndView model) throws EntityNotFoundException;
	public void createPageModification(Long idelement, Long idmodule, ModelAndView model);
	public void createElement(Long idmodule, Long idprofesseur, String libelle_element, Double coeficient,
			Double validation);
}

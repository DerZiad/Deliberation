package com.ziad.administrateur.module;

import org.springframework.web.servlet.ModelAndView;

import com.ziad.administrateur.etablissement.InvalidEntries;

public interface ElementAlgo {
	public void modifyElementsModule(Long idelement, Long idprofesseur, Long idmodule, String libelle_element,
			Double coeficient, Double validation, ModelAndView model) throws InvalidEntries;
	public void deleteElementsModule(Long id) throws InvalidEntries;
	public void createElementsModule(Long idmodule, ModelAndView model) throws InvalidEntries;
	public void createPageModification(Long idelement, Long idmodule, ModelAndView model);
	public void createElement(Long idmodule, Long idprofesseur, String libelle_element, Double coeficient,
			Double validation);
}

package com.ziad.administrateur.module;

import org.springframework.web.servlet.ModelAndView;

import com.ziad.administrateur.etablissement.DataNotFoundExceptions;
import com.ziad.administrateur.etablissement.InvalidEntries;

public interface ModuleMetier {

	public void makeFirstPage(ModelAndView model);

	public void creerModule(Long semestre_id, Long professeur_id, String name,Integer compose_seul_element)
			throws InvalidEntries;

	public void listerModules(ModelAndView model) throws DataNotFoundExceptions;

	public void getProfilModuleById(ModelAndView model, Long id) throws InvalidEntries;

	public void modifyModule(Long id, String name, Long semestre_id, Long professeur_id) throws InvalidEntries;

	public void deleteModule(Long id) throws InvalidEntries;

}

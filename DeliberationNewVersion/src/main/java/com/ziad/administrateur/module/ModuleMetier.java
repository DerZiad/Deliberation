package com.ziad.administrateur.module;

import javax.persistence.EntityNotFoundException;

import org.springframework.web.servlet.ModelAndView;

import com.ziad.administrateur.etablissement.DataNotFoundExceptions;

public interface ModuleMetier {

	public void makeFirstPage(ModelAndView model);

	public void creerModule(Long semestre_id, Long professeur_id, String name,Integer compose_seul_element)
			throws EntityNotFoundException;

	public void listerModules(ModelAndView model) throws DataNotFoundExceptions;

	public void getProfilModuleById(ModelAndView model, Long id) throws EntityNotFoundException;

	public void modifyModule(Long id, String name, Long semestre_id, Long professeur_id) throws EntityNotFoundException;

	public void deleteModule(Long id) throws EntityNotFoundException;

}

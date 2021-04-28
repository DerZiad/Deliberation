package com.ziad.administrateur.module;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.Element;
import com.ziad.models.Historique;
import com.ziad.models.Modulee;
import com.ziad.models.Professeur;
import com.ziad.models.Semestre;
import com.ziad.repositories.HistoriqueRepository;
import com.ziad.repositories.ModuleRepository;
import com.ziad.repositories.ProfesseurRepository;
import com.ziad.repositories.SemestreRepository;
import com.ziad.security.authentification.enums.MonRole;

@Primary
@Service
public class ModuleService implements ModuleInterface {

	@Autowired
	private ModuleRepository moduleRepository;
	@Autowired
	private ProfesseurRepository professeurRepository;
	@Autowired
	private SemestreRepository semestreRepository;
	@Autowired
	private HistoriqueRepository historiqueRepository;

	@Override
	public void makeFirstPage(ModelAndView model) {
		List<Semestre> semestres = semestreRepository.findAll();
		model.addObject("semestres", semestres);
		List<Professeur> professeurs = professeurRepository.findAll();
		model.addObject("professeurs", professeurs);
		model.addObject("ModuleCreate", "mm-active");
	}

	@Override
	public void creerModule(Long semestre_id, Long professeur_id, String name,Integer compose_seul_element)
			throws EntityNotFoundException {
		Modulee module = new Modulee();
		if(semestre_id != null) {
			Semestre semestre = semestreRepository.getOne(semestre_id);
			module.setSemestre(semestre);
		}
		if(professeur_id != null) {
			Professeur	professeur = professeurRepository.getOne(professeur_id);
			professeur.getUser().addRole(MonRole.ROLERESPONSABLEMODULE);
			professeurRepository.save(professeur);
			module.setResponsable_module(professeur);
		}
		module.setLibelle_module(name);
		try {
			module.setComposee(compose_seul_element != 1);
		}catch (Exception e) {
			/**
			 * 
			 * Exception en cas ou le module ne se compose pas d'un seul element
			 * 
			 **/
		}
		moduleRepository.save(module);

		/**
		 * 
		 * Save Historique
		 * 
		 **/
		historiqueRepository.save(new Historique("Module " + name + " créé", new java.util.Date()));
	}

	@Override
	public void listerModules(ModelAndView model) throws DataNotFoundExceptions {
		List<Modulee> modules = moduleRepository.findAll();
		if (modules.size() == 0)
			throw new DataNotFoundExceptions("La liste des modules est vide");
		model.addObject("modules", modules);
		model.addObject("listModules", "mm-active");
	}

	@Override
	public void getProfilModuleById(ModelAndView model, Long id) throws EntityNotFoundException {
		Modulee module = moduleRepository.getOne(id);
		model.addObject("module", module);
		List<Semestre> semestres = semestreRepository.findAll();
		model.addObject("semestres", semestres);
		List<Professeur> professeurs = professeurRepository.findAll();
		model.addObject("professeurs", professeurs);

	}

	@Override
	public void modifyModule(Long id, String name, Long semestre_id, Long professeur_id)
			throws EntityNotFoundException {
		
		Modulee module = moduleRepository.findById(id).get();
		Professeur professeur = null;
		if(professeur_id != null) {
			professeur = professeurRepository.findById(professeur_id).get();
			module.setResponsable_module(professeur);
		}
		Semestre semestre = semestreRepository.getOne(semestre_id);
		if (semestre != null)
			module.setSemestre(semestre);
		List<Element> elements = module.getElements();
		module.setLibelle_module(name);

		if (!module.isComposed()) {
			Element element = null;
			switch (elements.size()) {
			case 0:
				element = new Element();
				element.setLibelle_element(name);
				element.setModule(module);
				if (professeur != null)
					element.addProfesseur(professeur);
				break;
			case 1:
				element = module.getElements().get(0);
				element.setLibelle_element(name);
				element.setModule(module);
				element.addProfesseur(professeur);
				break;
			}
		}
		module = moduleRepository.save(module);
		historiqueRepository
				.save(new Historique("Module " + module.getLibelle_module() + " modifié en " + name, new Date()));
	}

	@Override
	public void deleteModule(Long id) throws EntityNotFoundException {
		Modulee module = moduleRepository.getOne(id);
		moduleRepository.delete(module);
		historiqueRepository
				.save(new Historique("Module " + module.getLibelle_module() + " supprimé", new java.util.Date()));
	}

}

package com.ziad.administrateur.module;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ziad.administrateur.etablissement.DataNotFoundExceptions;

@Controller
@RequestMapping("/admin/module")
public class ModuleController {
	
	@Autowired
	private ModuleMetier metier;

	@GetMapping("/creer")
	public ModelAndView moduleCreatePage() {
		ModelAndView model = new ModelAndView("Module/ModuleCreate");
		metier.makeFirstPage(model);
		return model;
	}

	@PostMapping("/creer")
	public ModelAndView moduleCreate(@RequestParam(name = "semestre_id",required = false) Long semestre_id,
			@RequestParam(name = "professeur_id",required = false) Long professeur_id, @RequestParam("name") String name,@RequestParam(name = "checktheelement",required = false) Integer compose_seul_element) throws EntityNotFoundException{
		metier.creerModule(semestre_id, professeur_id, name,compose_seul_element);
		return new ModelAndView("redirect:/admin/module/liste");
	}

	@GetMapping("/liste")
	public ModelAndView listModule() throws DataNotFoundExceptions{
		ModelAndView model = new ModelAndView("Module/ListModule");
		metier.listerModules(model);
		return model;
	}

	@GetMapping("/profile/{id}")
	public ModelAndView moduleProfile(@PathVariable("id") Long id) throws EntityNotFoundException{
		ModelAndView model = new ModelAndView("Module/ModuleProfile");
		metier.getProfilModuleById(model, id);
		return model;
	}

	@PostMapping("/profile/{id}")
	public ModelAndView modifyModule(@PathVariable("id") Long id, @RequestParam("name") String name,
			@RequestParam("semestre_id") Long semestre_id, @RequestParam(name="professeur_id",required = false) Long professeur_id) throws EntityNotFoundException{
		metier.modifyModule(id, name, semestre_id, professeur_id);
		return new ModelAndView("redirect:/admin/module/profile/" + id);
	}

	@GetMapping("/supprimer/{id}")
	public ModelAndView deleteModule(@PathVariable("id") Long id) throws EntityNotFoundException{
		metier.deleteModule(id);
		return new ModelAndView("redirect:/admin/module/liste");
	}

}

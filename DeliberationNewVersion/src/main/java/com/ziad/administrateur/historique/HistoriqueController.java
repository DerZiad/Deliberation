package com.ziad.administrateur.historique;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ziad.repositories.HistoriqueRepository;

@Controller
@RequestMapping("/admin/historique")
public class HistoriqueController {
	@Autowired
	private HistoriqueRepository historiqueRepostiroy;
	
	private final static String PAGE_HISTORIQUE ="Historique/historique";
	
	private final static String REDIRECT_HISTORIQUE = "redirect:/admin/historique";
	
	private final static String ATTRIBUT_LISTE_HISTORIQUE ="historiques";
	
	@GetMapping("")
	public ModelAndView getHistorique(){
		ModelAndView model = new ModelAndView(PAGE_HISTORIQUE);
		model.addObject(ATTRIBUT_LISTE_HISTORIQUE,historiqueRepostiroy.findAll());
		return model;
	}
	
	@GetMapping("/delete/{idHistorique}")
	public ModelAndView deleteHistorique(@PathVariable("idHistorique")Long idHistorique) throws EntityNotFoundException {
		historiqueRepostiroy.deleteById(idHistorique);
		return new ModelAndView(REDIRECT_HISTORIQUE);
	}
	
	@GetMapping("/vider")
	public ModelAndView deleteHistorique() {
		historiqueRepostiroy.deleteAll();
		return new ModelAndView(REDIRECT_HISTORIQUE);
	}
}

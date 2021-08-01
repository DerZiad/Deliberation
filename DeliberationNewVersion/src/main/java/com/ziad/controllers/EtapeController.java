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

import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.Filiere;
import com.ziad.services.interfaces.EtapeInterface;

@Controller
@RequestMapping("/admin//filiere/profile/{id_filiere}/etapes")
public class EtapeController {

	private final static String PAGE_PROFILE_ETAPE = "etape/EtapeProfile";
	private final static String PAGE_LIST_ETAPE= "etape/etapes";
	private final static String PAGE_CREATE_ETAPE = "etape/EtapeCreate";
	
	private final static String REDIRECT_PROFILE_ETAPE ="redirect:/admin/filiere/profile/%d/etapes/modify/%d";
	private final static String REDIRECT_LISTER_ETAPE = "redirect:/admin/filiere/profile/%d/etapes";


	private final static String ATTRIBUT_ETAPE = "etape";
	private final static String ATTRIBUT_FILIERE = "filiere";

	@Autowired
	private EtapeInterface etapeMetier;

	@GetMapping("/modify/{id_etape}")
	public ModelAndView getModificationEtape(@PathVariable("id_etape")Long idEtape) throws EntityNotFoundException{
		ModelAndView model = new ModelAndView(PAGE_PROFILE_ETAPE);
		model.addObject(ATTRIBUT_ETAPE,etapeMetier.getEtape(idEtape));
		return model;	
	}

	@PostMapping("/modify/{id_etape}")
	public ModelAndView modifyEtape(@PathVariable("id_filiere")Long id_filiere,@RequestParam("libelle_etape")String nom_etape,@RequestParam("validation") Double validation,@PathVariable("id_etape")Long id_etape) throws EntityNotFoundException{
		etapeMetier.modifyEtape(id_etape, nom_etape, validation);
		ModelAndView model = new ModelAndView(String.format(REDIRECT_PROFILE_ETAPE,id_filiere,id_etape));
		return model;
	}
	
	@GetMapping("")
	public ModelAndView listerEtapes(@PathVariable("id_filiere") Long id_filiere)throws DataNotFoundExceptions {
		ModelAndView model = new ModelAndView(PAGE_LIST_ETAPE);
		System.out.println("I m hona");
		Filiere filiere = etapeMetier.listerEtapes(id_filiere);
		model.addObject("etapes",filiere.getEtapes());
		model.addObject(ATTRIBUT_FILIERE,filiere);
		return model;
	}
	@GetMapping("/diplomer/{id_etape}")
	public ModelAndView diplomerEtape(@PathVariable("id_etape")Long id_etape,@RequestParam("diplomante")Integer diplomante) throws EntityNotFoundException{
		Filiere filiere = etapeMetier.diplomerEtapes(id_etape,diplomante);
		ModelAndView model = new ModelAndView(String.format(REDIRECT_LISTER_ETAPE, filiere.getId_filiere()));
		return model;
	}
	
	@GetMapping("/delete/{id_etape}")
	public ModelAndView deleteEtape(@PathVariable("id_filiere")Long id_filiere,@PathVariable("id_etape")Long id_etape) throws EntityNotFoundException{
		etapeMetier.deleteEtape(id_etape);
		ModelAndView model = new ModelAndView(String.format(REDIRECT_LISTER_ETAPE,id_filiere));
		return model;
	}
	
	@GetMapping("/create")
	public ModelAndView getCreatePage(@PathVariable("id_filiere")Long id_filiere) throws EntityNotFoundException{
		Filiere filiere = etapeMetier.getPageCreateEtape(id_filiere);
		ModelAndView model = new ModelAndView(PAGE_CREATE_ETAPE);
		model.addObject(ATTRIBUT_FILIERE,filiere);
		return model;
	}
	
	@PostMapping("/create")
	public ModelAndView createEtape(@PathVariable("id_filiere")Long id_filiere,@RequestParam("libelle_etape")String libelle_etape,@RequestParam("validation")Double validation,@RequestParam(name = "diplomation",required = false)Integer diplomation) throws EntityNotFoundException{
		etapeMetier.createEtape(id_filiere, libelle_etape, validation,diplomation);
		ModelAndView model = new ModelAndView(String.format(REDIRECT_LISTER_ETAPE,id_filiere));
		return model;
	}
	
}

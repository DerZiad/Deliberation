package com.ziad.administrateur.structures;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ziad.administrateur.etablissement.DataNotFoundExceptions;

@Controller
@RequestMapping("/admin")
public class StructureController {
	
	private final static String ATTRIBUT_LIST_ETAPES = "etapes";
	private final static String ATTRIBUT_LIST_SEMESTRES = "semestres";
	private final static String ATTRIBUT_LIST_MODULES = "modules";
	private final static String ATTRIBUT_LIST_FILIERES = "filieres";

	private final static String ACTIVE = "mm-active";
	private final static String ATTRIBUT_STRUCTURE_ENSEIGNEMENT = "StructureEnseignement";
	
	private final static String PAGE_LIST_ANNEES = "Structure/ListAnnees";
	
	private final static String REDIRECT_STRUCTURE_PAGE = "redirect:/inscription/StructureEnseignement";
	@Autowired
	private StructureInterface structure_metier;

	// -------+++++++++-----------------++++++++------------++++++++----------++++++++-------//
	//+*+*+*+*+*+*+*+*+*++*+*+*+*+*+**+*+*+*+*+*+*+*+*+*+/ PARTIE STRUCTURE D'ENSEIGNEMENT /*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+**+*+*+//
		// -------+++++++++-----------------++++++++------------++++++++----------++++++++-------//

	//------------------------------------------allez vers page structure d'enseignement--------------------------------------------------//

		@GetMapping("/inscription/StructureEnseignement")
		public ModelAndView structureDEnseignement() throws DataNotFoundExceptions{
			ModelAndView model = new ModelAndView(PAGE_LIST_ANNEES);
			model.addObject(ATTRIBUT_LIST_FILIERES, structure_metier.listerFilieres());
			model.addObject(ATTRIBUT_LIST_ETAPES, structure_metier.listerEtapes());
			model.addObject(ATTRIBUT_LIST_SEMESTRES, structure_metier.listerSemestres());
			model.addObject(ATTRIBUT_LIST_MODULES, structure_metier.listerModules());
			model.addObject(ATTRIBUT_STRUCTURE_ENSEIGNEMENT, ACTIVE);
			return model;
		}

	//-------------------------------------modification d'une (plusieurs) années : diplomante ou pas------------------------------------//		

		@PostMapping("/inscription/StructureEnseignement")
		public ModelAndView ModifierAnneeDiplomante(@RequestParam("id_ip") String ids) throws EntityNotFoundException{
			structure_metier.diplomerAnnee(ids);
			return new ModelAndView(REDIRECT_STRUCTURE_PAGE);
		}

	//-------------------------------------------------Details semestre---------------------------------------------------//

		@GetMapping("/inscription/VoirSemestre")
		public ModelAndView VoirSemestre(@RequestParam("idS") int idS) {
			/*ModelAndView model = new ModelAndView("voirSemestre");
			Semestre semestre = semestreRepository.getOne(idS);
			List<Module> modules = moduleRepository.getModuleBySemestre(semestre);
			model.addObject("modules", modules);
			model.addObject("semestre", semestre);
			return model;*/
			return null;
		}
}

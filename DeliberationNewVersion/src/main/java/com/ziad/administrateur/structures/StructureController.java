package com.ziad.administrateur.structures;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ziad.models.Etape;
import com.ziad.models.Historique;
import com.ziad.models.Semestre;
import com.ziad.repositories.EtapeRepository;
import com.ziad.repositories.EtudiantRepository;
import com.ziad.repositories.FiliereRepository;
import com.ziad.repositories.HistoriqueRepository;
import com.ziad.repositories.InscriptionAdministrativeRepository;
import com.ziad.repositories.InscriptionEnLigneRepository;
import com.ziad.repositories.InscriptionPedagogiqueModuleRepository;
import com.ziad.repositories.InscriptionPedagogiqueRepository;
import com.ziad.repositories.ModuleRepository;
import com.ziad.repositories.SemestreRepository;

@Controller
public class StructureController {
	

	@PersistenceContext
	private EntityManager entiryManager;
	private FiliereRepository filiereRepository;
	private SemestreRepository semestreRepository;
	private ModuleRepository moduleRepository;
	private EtapeRepository etapeRepository;
	private HistoriqueRepository historiqueRepository;

	public StructureController(InscriptionPedagogiqueModuleRepository inscriptionPedagogiqueModuleRepository,
			EtapeRepository etapeRepository, ModuleRepository moduleRepository,
			InscriptionPedagogiqueRepository inscriptionPedagogiqueRepository, EtudiantRepository studentRepository,
			SemestreRepository semestreRepository, InscriptionAdministrativeRepository inscriptionAdministrative,
			InscriptionEnLigneRepository inscriptionEnLigne, FiliereRepository filiereRepository,
			HistoriqueRepository historiqueRepository) {
		this.filiereRepository = filiereRepository;
		this.semestreRepository = semestreRepository;
		this.moduleRepository = moduleRepository;
		this.etapeRepository = etapeRepository;
		this.historiqueRepository = historiqueRepository;
	}
	// -------+++++++++-----------------++++++++------------++++++++----------++++++++-------//
	//+*+*+*+*+*+*+*+*+*++*+*+*+*+*+**+*+*+*+*+*+*+*+*+*+/ PARTIE STRUCTURE D'ENSEIGNEMENT /*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+**+*+*+//
		// -------+++++++++-----------------++++++++------------++++++++----------++++++++-------//

	//------------------------------------------allez vers page structure d'enseignement--------------------------------------------------//

		@GetMapping("/inscription/StructureEnseignement")
		public ModelAndView structureDEnseignement() {
			ModelAndView model = new ModelAndView("ListAnnees");
			model.addObject("f", filiereRepository.getAllFiliere());
			model.addObject("etape", etapeRepository.findAll());
			model.addObject("semestres", semestreRepository.getAllSemestre());
			model.addObject("modules", moduleRepository.findAll());
			model.addObject("StructureEnseignement", "mm-active");
			return model;
		}

	//-------------------------------------modification d'une (plusieurs) années : diplomante ou pas------------------------------------//		

		@PostMapping("/inscription/ModifierAnneeDiplomante")
		public ModelAndView ModifierAnneeDiplomante(@RequestParam("id_ip") String ids) {
			String idstring[] = ids.split(",");

			List<Integer> id = new ArrayList<Integer>();
			for (int i = 0; i < idstring.length; i++) {
				id.add(Integer.parseInt(idstring[i]));
			}
			List<Etape> etapesA = etapeRepository.findAllById(id);
			for (int i = 0; i < etapesA.size(); i++) {
				etapeRepository.activerDiplomante(etapesA.get(i).getId_etape(), 1);
				historiqueRepository.save(new Historique(
						"année " + etapesA.get(i).getLibelle_etape() + " est desormait diplomante", new java.util.Date()));
			}

			List<Etape> etapesD = etapeRepository.findAll();
			int trouver = 0;
			for (int i = 0; i < etapesD.size(); i++) {
				trouver = 0;
				for (int j = 0; j < etapesA.size(); j++) {
					if (etapesD.get(i) == etapesA.get(j)) {
						trouver = 1;
					}
				}
				if (trouver == 0) {
					//etapeRepository.activerDiplomante(etapesD.get(i).getId_etape(), 0);
					historiqueRepository.save(new Historique(
							"année " + etapesD.get(i).getLibelle_etape() + " n'est desormait plus diplomante",
							new java.util.Date()));
				}
			}
			return new ModelAndView("redirect:/inscription/StructureEnseignement");
		}

	//-------------------------------------------------Details semestre---------------------------------------------------//

		@GetMapping("/inscription/VoirSemestre")
		public ModelAndView VoirSemestre(@RequestParam("idS") int idS) {
			ModelAndView model = new ModelAndView("voirSemestre");
			Semestre semestre = semestreRepository.getOne(idS);
			List<Module> modules = moduleRepository.getModuleBySemestre(semestre);
			model.addObject("modules", modules);
			model.addObject("semestre", semestre);
			return model;
		}
}

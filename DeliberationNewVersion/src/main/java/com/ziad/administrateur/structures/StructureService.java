package com.ziad.administrateur.structures;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.ziad.models.Etape;
import com.ziad.models.Filiere;
import com.ziad.models.Historique;
import com.ziad.models.Modulee;
import com.ziad.models.Semestre;
import com.ziad.repositories.EtapeRepository;
import com.ziad.repositories.FiliereRepository;
import com.ziad.repositories.HistoriqueRepository;
import com.ziad.repositories.ModuleRepository;
import com.ziad.repositories.SemestreRepository;

@Service
@Primary
public class StructureService implements StructureInterface {
	@Autowired
	private FiliereRepository filiereRepository;
	@Autowired
	private SemestreRepository semestreRepository;
	@Autowired
	private ModuleRepository moduleRepository;
	@Autowired
	private EtapeRepository etapeRepository;
	@Autowired
	private HistoriqueRepository historiqueRepository;

	@Override
	public List<Etape> listerEtapes() {
		return etapeRepository.findAll();
	}

	@Override
	public List<Filiere> listerFilieres() {
		return filiereRepository.findAll();
	}

	@Override
	public List<Modulee> listerModules() {
		return moduleRepository.findAll();
	}

	@Override
	public List<Semestre> listerSemestres() {
		return semestreRepository.findAll();
	}

	@Override
	public void diplomerAnnee(String ids) throws EntityNotFoundException {
		String idstring[] = ids.split(",");

		List<Long> id = new ArrayList<Long>();
		for (int i = 0; i < idstring.length; i++) {
			id.add(Long.parseLong(idstring[i]));
		}
		List<Etape> etapesA = etapeRepository.findAllById(id);
		for (Etape etape : etapesA) {
			Filiere filiere = etape.getFiliere();
			List<Etape> etapes = filiere.getEtapes();
			for (Etape etapeupdate : etapes) {
				if (!etapesA.contains(etapeupdate)) {
					etapeupdate.setDiplomante(false);
				}
			}
			etapeRepository.saveAll(etapes);
		}
		Consumer<Etape> diplomer = etape -> {
			etape.setDiplomante(true);
			historiqueRepository.save(new Historique("année " + etape.getLibelle_etape() + " est desormait diplomante",
					new java.util.Date()));
		};
		etapesA.stream().forEach(diplomer);

	}

}

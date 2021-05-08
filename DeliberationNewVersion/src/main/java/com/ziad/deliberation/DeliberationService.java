package com.ziad.deliberation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.AnneeAcademique;
import com.ziad.models.Etape;
import com.ziad.models.Filiere;
import com.ziad.models.Modulee;
import com.ziad.models.Semestre;
import com.ziad.repositories.AnnneAcademiqueRepository;
import com.ziad.repositories.EtapeRepository;
import com.ziad.repositories.FiliereRepository;
import com.ziad.repositories.ModuleRepository;
import com.ziad.repositories.SemestreRepository;
import com.ziad.utilities.JSONConverter;

@Service
@Primary
public class DeliberationService implements DeliberationInterface {

	@Autowired
	private EtapeRepository etapeRepository;

	@Autowired
	private ModuleRepository moduleRepository;

	@Autowired
	private SemestreRepository semestreRepository;

	@Autowired
	private FiliereRepository filiereRepository;

	@Autowired
	private AnnneAcademiqueRepository anneeAcademiqueRepository;

	@Autowired
	private Algorithme algorithme;

	@Autowired
	private JSONConverter converter;

	private static final String TYPE_DELIBERATION_MODULE = "parmodule";
	private static final String TYPE_DELIBERATION_SEMESTRE = "parsemestre";
	private static final String TYPE_DELIBERATION_ETAPE = "paretape";

	private static final String TYPE_DELIBERATION_ORDINAIRE = "ordinaire";
	private static final String TYPE_DELIBERATION_RATTRAPAGE = "rattrapage";

	@Override
	public ArrayList<Object> getPageBesoin() throws DataNotFoundExceptions, EntityNotFoundException {
		List<Filiere> filieres = filiereRepository.findAll();
		List<AnneeAcademique> anneesAcademiques = anneeAcademiqueRepository.findAll();
		List<Semestre> semestres = semestreRepository.findAll();

		ArrayList<Object> besoins = new ArrayList<Object>();
		besoins.add(filieres);
		besoins.add(anneesAcademiques);
		besoins.add(semestres);
		besoins.add(converter.convertModulee(moduleRepository.findAll()));
		besoins.add(converter.convertSemestre(semestreRepository.findAll()));
		besoins.add(converter.convertEtape(etapeRepository.findAll()));
		return besoins;
	}

	@Override
	public void deliberer(Long idFiliere, Long idAnneeAcademique, String type, Long id_element, String typeDeliberation,
			Integer consideration) throws DataNotFoundExceptions, EntityNotFoundException {
		boolean delibpermis = false;
		AnneeAcademique annee = anneeAcademiqueRepository.getOne(idAnneeAcademique);

		
		if (typeDeliberation.equals(TYPE_DELIBERATION_ORDINAIRE)) {
			algorithme.enableOrdinaire();
			delibpermis = !algorithme.isDeliberedOrdinaire(annee, id_element);
		} else if (typeDeliberation.equals(TYPE_DELIBERATION_RATTRAPAGE)) {
			algorithme.enableConsiderationDesNotes(consideration == 1);
			algorithme.enableRattrapage();
			delibpermis = !algorithme.isDeliberedRattrapage(annee, id_element);
		}
		if(delibpermis) {
			if (type.equals(TYPE_DELIBERATION_ETAPE)) {
				Etape etape = etapeRepository.getOne(id_element);
				algorithme.delibererEtape(etape, annee);
			} else if (type.equals(TYPE_DELIBERATION_MODULE)) {
				Modulee module = moduleRepository.getOne(id_element);
				algorithme.delibererModule(module, annee);
			} else if (type.equals(TYPE_DELIBERATION_SEMESTRE)) {
				Semestre semestre = semestreRepository.getOne(id_element);
				algorithme.delibererSemestre(semestre, annee);
			}
		}
				
	}
	
	

}

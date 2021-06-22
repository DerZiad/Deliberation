package com.ziad.service.administrateur.semestre;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.Etape;
import com.ziad.models.Filiere;
import com.ziad.models.Semestre;
import com.ziad.repositories.EtapeRepository;
import com.ziad.repositories.FiliereRepository;
import com.ziad.repositories.SemestreRepository;

@Service
@Primary
public class SemestreService implements SemestreInterface{

	@Autowired
	private FiliereRepository filiereRepository;
	
	@Autowired
	private EtapeRepository etapeRepository;
	
	@Autowired
	private SemestreRepository semestreRepository;
	
	@Override
	public List<Object> getPageCreateSemestre(Long idFiliere) throws DataNotFoundExceptions {
		
		Filiere filiere = filiereRepository.getOne(idFiliere);
		ArrayList<Object> listes = new ArrayList<Object>();
		listes.add(filiere);
		return listes;
	}

	@Override
	public void removeSemestre(Long idSemestre) throws EntityNotFoundException {
		semestreRepository.deleteById(idSemestre);	
	}

	@Override
	public Semestre getPageEditSemestre(Long idSemestre) throws EntityNotFoundException {
		return semestreRepository.getOne(idSemestre);
	}

	@Override
	public void modifierSemestre(Long idSemestre, String libelle_Semestre, Double validation, Long idEtape)
			throws EntityNotFoundException {
		Semestre semestre = semestreRepository.getOne(idSemestre);
		Etape etape = etapeRepository.getOne(idEtape);
		semestre.setLibelle_semestre(libelle_Semestre);
		semestre.setValidation(validation);
		semestre.setEtape(etape);
		if(!etape.getSemestres().contains(semestre))
			etape.addSemestre(semestre);
		etapeRepository.save(etape);
		
	}

	@Override
	public void createSemestre(Long idEtape, String libelle_Semestre, Double validation)
			throws EntityNotFoundException {
		Etape etape = etapeRepository.getOne(idEtape);
		List<Semestre> semestres = etape.getSemestres();
		Collections.sort(semestres,new Comparator<Semestre>() {

			@Override
			public int compare(Semestre o1, Semestre o2) {
				Integer om1 = o1.getOrdre();
				Integer om2 = o2.getOrdre();
				return om1.compareTo(om2);
			}
			
		});
		
		Semestre semestre = new Semestre(validation, libelle_Semestre, etape, null, semestres.get(semestres.size() - 1).getOrdre() + 1);
		etape.addSemestre(semestre);
		etapeRepository.save(etape);
	}

	
	
}

package com.ziad.administration.etape;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.Etape;
import com.ziad.models.Filiere;
import com.ziad.repositories.EtapeRepository;
import com.ziad.repositories.FiliereRepository;

@Service
@Primary
public class EtapeService implements EtapeInterface{
	@Autowired
	private EtapeRepository etapeRepository;
	
	@Autowired
	private FiliereRepository filiereRepository;
	
	@Override
	public Etape getEtape(Long idEtape) throws EntityNotFoundException {
		Etape etape = etapeRepository.getOne(idEtape);
		return etape;
	}
	
	@Override
	public void modifyEtape(Long idEtape,String nomEtape,Double validation) throws EntityNotFoundException{
		Etape etape = etapeRepository.getOne(idEtape);
		etape.setValidation(validation);
		etape.setLibelle_etape(nomEtape);
		etapeRepository.save(etape);
	}

	@Override
	public void deleteEtape(Long id_etape) throws EntityNotFoundException {
		etapeRepository.deleteById(id_etape);
	}
	
	@Override
	public Filiere listerEtapes(Long id_filire) throws DataNotFoundExceptions, EntityNotFoundException {
		Filiere filiere = filiereRepository.getOne(id_filire);
		return filiere;
	}

	@Override
	public Filiere diplomerEtapes(Long id_etape,Integer action) throws EntityNotFoundException {
		Etape etape = etapeRepository.getOne(id_etape);
		etape.setDiplomante(action == 1);
		etapeRepository.save(etape);
		return etape.getFiliere();
	}

	@Override
	public Filiere getPageCreateEtape(Long idFiliere) throws EntityNotFoundException {
		return filiereRepository.getOne(idFiliere);
	}

	@Override
	public void createEtape(Long id_filiere, String libelle_etape, Double validation,Integer diplomation) throws EntityNotFoundException {
		Filiere filiere = filiereRepository.getOne(id_filiere);
		
		Etape etape = new Etape();
		etape.setLibelle_etape(libelle_etape);
		etape.setFiliere(filiere);
		etape.setValidation(validation);
		etape.setDiplomante(diplomation != null);
		
		etapeRepository.save(etape);
		
	}
}

package com.ziad.administration.etape;

import javax.persistence.EntityNotFoundException;

import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.Etape;
import com.ziad.models.Filiere;

public interface EtapeInterface {

	
	public Etape getEtape(Long idEtape) throws EntityNotFoundException;
	
	public Filiere getPageCreateEtape(Long idFiliere) throws EntityNotFoundException;
	
	public void modifyEtape(Long idEtape,String nomEtape,Double validation) throws EntityNotFoundException;
	
	public void deleteEtape(Long id_etape) throws EntityNotFoundException;
	
	public Filiere listerEtapes(Long id_filire) throws DataNotFoundExceptions;
	
	public Filiere diplomerEtapes(Long id_etape,Integer action) throws EntityNotFoundException;
	
	public void createEtape(Long id_filiere,String libelle_etape,Double validation,Integer diplomation)throws EntityNotFoundException;
}

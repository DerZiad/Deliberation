package com.ziad.administrateur.semestre;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.Semestre;

public interface SemestreInterface {

	
	public List<Object> getPageCreateSemestre(Long idFiliere) throws DataNotFoundExceptions;
	
	public Semestre getPageEditSemestre(Long idSemestre)throws EntityNotFoundException;
	
	public void removeSemestre(Long idSemestre) throws EntityNotFoundException;
	
	
	public void modifierSemestre(Long idSemestre,String libelle_Semestre,Double validation,Long idEtape)throws EntityNotFoundException;
	
	public void createSemestre(Long idEtape,String libelle_Semestre,Double validation)throws EntityNotFoundException;

}

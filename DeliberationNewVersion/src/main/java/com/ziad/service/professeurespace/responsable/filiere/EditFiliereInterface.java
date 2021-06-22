package com.ziad.service.professeurespace.responsable.filiere;

import java.util.ArrayList;

import javax.persistence.EntityNotFoundException;

import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.Modulee;

public interface EditFiliereInterface {

	public ArrayList<Object> loadListModuleByFiliere() throws DataNotFoundExceptions;
	
	public Modulee getModuleById(Long idModule) throws EntityNotFoundException;
	
	public void updateModule(Long idModule,Double coefficient) throws EntityNotFoundException;
}

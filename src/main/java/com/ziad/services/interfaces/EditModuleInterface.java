package com.ziad.services.interfaces;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.Element;

public interface EditModuleInterface {
	
	public List<Object> getModulesElementsProfesseur() throws DataNotFoundExceptions;
	
	public Element getElement(Long idElement) throws EntityNotFoundException;
	public void saveInformations(Long idElement,Double validation,Double coeficient) throws EntityNotFoundException;
}

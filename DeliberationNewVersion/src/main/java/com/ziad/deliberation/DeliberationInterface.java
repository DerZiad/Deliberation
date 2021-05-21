package com.ziad.deliberation;

import java.util.ArrayList;

import javax.persistence.EntityNotFoundException;

import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.Deliberation;

public interface DeliberationInterface {

	public ArrayList<Object> getPageBesoin() throws DataNotFoundExceptions, EntityNotFoundException;

	public void deliberer(Long idFiliere, Long idAnneeAcademique, String type, Long id_element, String typeDeliberation,
			Integer consideration) throws DataNotFoundExceptions, EntityNotFoundException;


	public Deliberation piocherDeliberation(Long idDelib) throws EntityNotFoundException;
}

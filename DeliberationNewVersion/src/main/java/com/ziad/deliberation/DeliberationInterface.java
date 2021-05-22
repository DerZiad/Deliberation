package com.ziad.deliberation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.DocumentException;
import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.Deliberation;

public interface DeliberationInterface {

	public ArrayList<Object> getBesoinPageDeliberationParModule() throws DataNotFoundExceptions, EntityNotFoundException;

	public ArrayList<Object> getBesoinPageDeliberationParSemestre() throws DataNotFoundExceptions, EntityNotFoundException;
	
	public ArrayList<Object> getBesoinPageDeliberationParEtape() throws DataNotFoundExceptions, EntityNotFoundException;
	
	public Deliberation deliberer(Long idFiliere, Long idAnneeAcademique, String type, Long id_element, String typeDeliberation,
			Integer consideration) throws DataNotFoundExceptions, EntityNotFoundException;

	public Deliberation piocherDeliberation(Long idDelib) throws EntityNotFoundException;

	public void generateExcel(HttpServletResponse response, String type, Long idDeliberation, Integer rattrapage)
			throws EntityNotFoundException, DocumentException, IOException;
	
	public List<Deliberation> listerDeliberation() throws DataNotFoundExceptions;
}

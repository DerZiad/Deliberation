package com.ziad.service.deliberation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.DocumentException;
import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.exceptions.DeliberationEtapeNotAllowed;
import com.ziad.exceptions.DeliberationModuleNotAllowed;
import com.ziad.exceptions.DeliberationSemestreNotAllowed;
import com.ziad.models.Deliberation;

public interface DeliberationInterface {

	public ArrayList<Object> getBesoinPageDeliberationParModule(HttpServletRequest req)
			throws DataNotFoundExceptions, EntityNotFoundException;

	public ArrayList<Object> getBesoinPageDeliberationParSemestre(HttpServletRequest req)
			throws DataNotFoundExceptions, EntityNotFoundException;

	public ArrayList<Object> getBesoinPageDeliberationParEtape(HttpServletRequest req) throws DataNotFoundExceptions, EntityNotFoundException;

	public Deliberation deliberer( Long idAnneeAcademique, String type, Long id_element,
			String typeDeliberation, Integer consideration) throws DataNotFoundExceptions, EntityNotFoundException,
			DeliberationEtapeNotAllowed, DeliberationSemestreNotAllowed,DeliberationModuleNotAllowed;

	public Deliberation piocherDeliberation(Long idDelib) throws EntityNotFoundException;

	public void generateExcel(HttpServletResponse response, Long idDeliberation, Integer rattrapage)
			throws EntityNotFoundException, DocumentException, IOException;

	public List<Deliberation> listerDeliberation(HttpServletRequest req) throws DataNotFoundExceptions;

	public void generateUltimatePv(Long idDeliberation, HttpServletResponse response)
			throws EntityNotFoundException, DocumentException, IOException;

	public void generateExcelEtape(HttpServletResponse response, Long idDeliberation)
			throws EntityNotFoundException, DocumentException, IOException;
	
	public void saveExtendsLayout(HttpServletRequest req);
}



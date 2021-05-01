package com.ziad.professeurespace;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;

import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.Element;

public interface ProfesseurInterface {

	/*
	 * Listage
	 **/

	public List<Element> listerElements() throws DataNotFoundExceptions;

	public ArrayList<Object> listerEtudiants(Long id_element) throws DataNotFoundExceptions, EntityNotFoundException;

	/**
	 * Excel generater
	 **/

	public void generateExcel(Long id_element, Long id_annee, HttpServletResponse response)
			throws EntityNotFoundException, IOException;

}

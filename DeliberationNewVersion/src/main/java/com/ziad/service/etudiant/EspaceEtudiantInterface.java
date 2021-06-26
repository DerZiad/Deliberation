package com.ziad.service.etudiant;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.DocumentException;
import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.Semestre;

public interface EspaceEtudiantInterface {

	public List<Semestre> listerSemestres() throws DataNotFoundExceptions;
	
	public List<Object> getNotes(Long idSemestre) throws EntityNotFoundException;
	
	public List<Semestre> getScolaritePage() throws DataNotFoundExceptions;
	
	public void generateCertificatScolarite(Long idSemestre, HttpServletResponse response)
			throws EntityNotFoundException, DocumentException, IOException;
	
	public void generateReleveNote(Long idSemestre, HttpServletResponse response)
			throws DocumentException, IOException;
}

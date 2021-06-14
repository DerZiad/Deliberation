package com.ziad.etudiant;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.Semestre;

public interface EspaceEtudiantInterface {

	public List<Semestre> listerSemestres() throws DataNotFoundExceptions;
	
	public List<Object> getNotes(Long idSemestre) throws EntityNotFoundException;
}

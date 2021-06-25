package com.ziad.service.administrateur.editnote;

import java.util.ArrayList;

import javax.persistence.EntityNotFoundException;

import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.NoteNorm;

public interface EditNoteInterface {

	public NoteNorm getNoteElement(Long idEtudiant,Long idElement,String type) throws EntityNotFoundException;
	
	public NoteNorm editNote(Long idEtudiant,Long idElement,Double note,String type) throws EntityNotFoundException;
	
	public ArrayList<Object> grabBesoins() throws DataNotFoundExceptions;
	
	public ArrayList<Object> grabBesoinsByFilter(Long idAnneeAcademique, Long idEtape, Long idModule,
			Long idElement, Long idSemestre) throws EntityNotFoundException, DataNotFoundExceptions;
}

package com.ziad.service.administrateur.editnote;

import java.util.ArrayList;

import javax.persistence.EntityNotFoundException;

import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.NoteElement;

public interface EditNoteInterface {

	public NoteElement getNoteElement(Long idEtudiant,Long idElement) throws EntityNotFoundException;
	
	public NoteElement editNote(Long idEtudiant,Long idElement,Double note,String Etat) throws EntityNotFoundException;
	
	public ArrayList<Object> grabBesoins() throws DataNotFoundExceptions;
	
	public ArrayList<Object> grabBesoinsByFilter(Long idAnneeAcademique,Long idEtape,Long idFiliere,Long idModule,Long idElement,Long idSemestre) throws EntityNotFoundException,DataNotFoundExceptions;
}

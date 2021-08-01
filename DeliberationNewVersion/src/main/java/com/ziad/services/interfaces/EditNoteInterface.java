package com.ziad.services.interfaces;

import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.ziad.exceptions.CSVReaderOException;
import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.NoteNorm;

public interface EditNoteInterface {

	public NoteNorm getNoteElement(Long idEtudiant, Long idElement, String type) throws EntityNotFoundException;

	public NoteNorm editNote(Long idEtudiant, Long idElement, Double note, String type) throws EntityNotFoundException;

	public ArrayList<Object> grabBesoins() throws DataNotFoundExceptions;

	public ArrayList<Object> grabBesoinsByFilter(Long idAnneeAcademique, Long idEtape, Long idModule, Long idElement,
			Long idSemestre,String action) throws EntityNotFoundException, DataNotFoundExceptions;

	public void downloadExcel(HttpServletResponse resp, Long idElement, Long idAnneeAcademique)
			throws DataNotFoundExceptions, IOException;
	
	public void readExcel(MultipartFile file, String type,Double coefficient)
			throws DataNotFoundExceptions, EntityNotFoundException, IOException, CSVReaderOException;
}

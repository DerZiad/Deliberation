package com.ziad.services.interfaces;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.lowagie.text.DocumentException;
import com.ziad.exceptions.CSVReaderOException;
import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.exceptions.DeliberationElementNotAllowed;
import com.ziad.exceptions.DeliberationEtapeNotAllowed;
import com.ziad.exceptions.DeliberationModuleNotAllowed;
import com.ziad.exceptions.DeliberationSemestreNotAllowed;
import com.ziad.exceptions.InvalidCredinals;
import com.ziad.models.Deliberation;

public interface DeliberationInterface {
	
	/**
	 * 
	 * Déliberations Méthodes
	 * 
	 * **/
	public Deliberation delibererModule(Long idModule,Long idAnne) throws DeliberationModuleNotAllowed, DeliberationElementNotAllowed;
	
	public Deliberation delibererElementRattrapage(Long idDeliberation,MultipartFile file,Double coefficientExamRattrapage, Integer consideration)
			throws InvalidCredinals, CSVReaderOException, IOException;
	public Deliberation delibererEtape(Long idEtape, Long idAnne) throws DeliberationEtapeNotAllowed;
	
	
	
	
	public ArrayList<Object> getBesoinPageDeliberationParModule(HttpServletRequest req)
			throws DataNotFoundExceptions, EntityNotFoundException;

	public ArrayList<Object> getBesoinPageDeliberationParSemestre(HttpServletRequest req)
			throws DataNotFoundExceptions, EntityNotFoundException;

	public ArrayList<Object> getBesoinPageDeliberationParEtape(HttpServletRequest req)
			throws DataNotFoundExceptions, EntityNotFoundException;

	public Deliberation piocherDeliberation(Long idDelib) throws EntityNotFoundException;

	public void generateExcel(HttpServletResponse response, Long idDeliberation, Integer rattrapage)
			throws EntityNotFoundException, DocumentException, IOException;

	public List<Deliberation> listerDeliberation(HttpServletRequest req) throws DataNotFoundExceptions;

	public void generateUltimatePv(Long idDeliberation, HttpServletResponse response)
			throws EntityNotFoundException, DocumentException, IOException;

	public void generateExcelEtape(HttpServletResponse response, Long idDeliberation)
			throws EntityNotFoundException, DocumentException, IOException;

	public void saveExtendsLayout(HttpServletRequest req);

	public void generateExcel(Long id_element, Long id_annee, HttpServletResponse response)
			throws EntityNotFoundException, IOException;
	
	public void generateExcelRattrapage(Long idDeliberation, HttpServletResponse response)
			throws EntityNotFoundException, IOException;

	public ArrayList<Object> getBesoinPageDeliberationParElement(HttpServletRequest req)
			throws DataNotFoundExceptions, EntityNotFoundException;

	
	
	public String getEtudiants(Long idElement,Long idAnneeAcademique);
	
	public Deliberation delibererSemestre(Long idSemestre,Long idAnneeAcademique) throws DeliberationSemestreNotAllowed;
	
	public Deliberation delibererElementOrdinaire(Long idAnneeAcademique, Long id_element,MultipartFile file,Double coefficientControl,Double coefficientTp,Double coefficientExam)
			throws EntityNotFoundException, InvalidCredinals, DataNotFoundExceptions, IOException, CSVReaderOException;
	
	public Deliberation generatePvDeliberationElement(Long idDeliberation,HttpServletResponse response)
			throws EntityNotFoundException, DataNotFoundExceptions, MalformedURLException, IOException, DocumentException;

}

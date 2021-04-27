package com.ziad.administrateur.professeur;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.Element;
import com.ziad.models.Professeur;

public interface ProfesseurInterface {
	
	public void createProfesseur(String first_name, String last_name, String email);
	
	public List<Professeur> listerProfesseurs() throws DataNotFoundExceptions;
	
	public Professeur getProfesseur(Long id) throws EntityNotFoundException;
	public void modifierProfesseur(Long id_professeur,String last_name,String first_name,String email) throws EntityNotFoundException;
	public void deleteProfesseur(Long id) throws EntityNotFoundException;

	public void renitialiserPassword(Long id) throws EntityNotFoundException;
	
	/**
	 * 
	 * Elements par professeur opérations
	 * 
	 * */
	public static final String ATTRIBUT_ELEMENTS = "elements";
	public static final String ATTRIBUT_FILIERES = "filieres";
	
	public List<Element> listerElementsByProfesseur(Long id) throws EntityNotFoundException,DataNotFoundExceptions;
	
	public HashMap<String,Object> listerFilieresElements() throws DataNotFoundExceptions;
	public HashMap<String,Object> filterElement(Long id_filiere);
	
	public void suprimerElement(Long id_professeur,Long id_element) throws EntityNotFoundException;
	
	public void ajouterElement(Long id_professeur,Long id_element) throws EntityNotFoundException;
}

package com.ziad.service.administrateur.filiere;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.web.servlet.ModelAndView;

import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.Etablissement;
import com.ziad.models.Filiere;
import com.ziad.models.Professeur;

public interface FiliereInterface {
	
	public List<Professeur> listerResponsableFiliere() throws DataNotFoundExceptions;
	public List<Etablissement> getEtablissement() throws DataNotFoundExceptions;

	public void createFiliere(Long id_etablissement, String nom_filiere, Long id_professeur,Integer semester_number)
			throws EntityNotFoundException;

	public List<Filiere> getFiliereList() throws DataNotFoundExceptions;

	public Filiere getFiliereProfile(ModelAndView model,Long id) throws EntityNotFoundException,DataNotFoundExceptions;

	public void modifyFiliereProfile(Long id_filiere, String name, Long etablissement_id, Integer semester_number) throws EntityNotFoundException;
	
	public void suprimerFiliere(Long id) throws EntityNotFoundException;
	

	
}

package com.ziad.administrateur.etablissement;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.Etablissement;
import com.ziad.models.Etudiant;
import com.ziad.models.Filiere;
import com.ziad.models.Professeur;

public interface EtablissementInterface {
	
	public void createEtablissement(String name);
	
	public Etablissement getEtablissementById(Long id) throws EntityNotFoundException;
	
	public void modifierEtablissmentById(Long id,String name) throws EntityNotFoundException;
	
	public List<Etablissement> getEtablissements() throws DataNotFoundExceptions;
	
	public void suprimerEtablissement(Long id) throws EntityNotFoundException;
	
	public List<Filiere> getFilieresListByEtablissement(Long id) throws EntityNotFoundException, DataNotFoundExceptions;
	
	public List<Professeur> getProfesseursListByEtablissement(Long id) throws EntityNotFoundException, DataNotFoundExceptions;
	
	public List<Etudiant> getEtudiantListByEtablissement(Long id)  throws EntityNotFoundException,DataNotFoundExceptions;
}

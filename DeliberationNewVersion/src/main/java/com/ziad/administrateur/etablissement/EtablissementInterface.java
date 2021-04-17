package com.ziad.administrateur.etablissement;

import java.util.List;

import com.ziad.models.Etablissement;
import com.ziad.models.Etudiant;
import com.ziad.models.Filiere;
import com.ziad.models.Professeur;

public interface EtablissementInterface {
	
	public void createEtablissement(String name);
	
	public Etablissement getEtablissementById(Long id) throws InvalidEntries;
	
	public void modifierEtablissmentById(Long id,String name) throws InvalidEntries;
	
	public List<Etablissement> getEtablissements() throws DataNotFoundExceptions;
	
	public void suprimerEtablissement(Long id) throws InvalidEntries;
	
	public List<Filiere> getFilieresListByEtablissement(Long id) throws InvalidEntries, DataNotFoundExceptions;
	
	public List<Professeur> getProfesseursListByEtablissement(Long id) throws InvalidEntries, DataNotFoundExceptions;
	
	public List<Etudiant> getEtudiantListByEtablissement(Long id)  throws InvalidEntries,DataNotFoundExceptions;
}

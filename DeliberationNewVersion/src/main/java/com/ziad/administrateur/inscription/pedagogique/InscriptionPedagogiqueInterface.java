package com.ziad.administrateur.inscription.pedagogique;

import java.util.ArrayList;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import com.ziad.exceptions.DataNotFoundExceptions;

public interface InscriptionPedagogiqueInterface {
	
	public ArrayList<Object> prepareInscriptionPedagogiquePage(Long id_inscription_filiere,Long id_inscription_etudiant) throws EntityNotFoundException;
	public void enregistrerInformation(Long id_filiere,Long id_etudiant,HttpServletRequest request) throws DataNotFoundExceptions,EntityNotFoundException;

	
}

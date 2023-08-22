package com.ziad.services.interfaces;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.InscriptionPedagogique;

public interface InscriptionPedagogiqueInterface {
	/*
	 * Créer inscription pédagogique
	 * **/
	public ArrayList<Object> prepareInscriptionPedagogiquePage(Long id_inscription_filiere,Long id_inscription_etudiant) throws EntityNotFoundException;
	public void enregistrerInformation(Long id_etudiant,Long idAnneeAcademique, HttpServletRequest request)
			throws DataNotFoundExceptions, EntityNotFoundException;
	
	public ArrayList<Object> listerInscriptionsPedagogique() throws DataNotFoundExceptions;
	
	/**
	 * Lister inscription pédagogique
	 * */
	public List<InscriptionPedagogique> prepareInscriptionListpage(Long id_etudiant) throws EntityNotFoundException,DataNotFoundExceptions;


	/**
	 * Suprimer Inscription pédagogique
	 * */
	
}

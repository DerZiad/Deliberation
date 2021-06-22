package com.ziad.service.administrateur.inscription.pedagogique;

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
	public void enregistrerInformation(Long id_filiere,Long id_etudiant,HttpServletRequest request) throws DataNotFoundExceptions,EntityNotFoundException;
	
	/**
	 * Lister inscription pédagogique
	 * */
	public List<InscriptionPedagogique> prepareInscriptionListpage(Long id_etudiant) throws EntityNotFoundException,DataNotFoundExceptions;


	/**
	 * Suprimer Inscription pédagogique
	 * */
	
	public void suprimerInscriptionPedagogique(Long id_etudiant,Long id_element) throws EntityNotFoundException;
}

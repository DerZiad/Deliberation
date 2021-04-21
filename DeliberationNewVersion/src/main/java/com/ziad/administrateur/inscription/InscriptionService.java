package com.ziad.administrateur.inscription;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.ziad.repositories.EtapeRepository;
import com.ziad.repositories.EtudiantRepository;
import com.ziad.repositories.Excel2DbRepository;
import com.ziad.repositories.FiliereRepository;
import com.ziad.repositories.HistoriqueRepository;
import com.ziad.repositories.InscriptionAdministrativeRepository;
import com.ziad.repositories.InscriptionEnLigneRepository;
import com.ziad.repositories.InscriptionPedagogiqueRepository;
import com.ziad.repositories.ModuleRepository;


public class InscriptionService {

	@Autowired
	private InscriptionAdministrativeRepository inscriptionAdministrative;
	@Autowired
	private EtudiantRepository etudiantRepository;
	@Autowired
	private FiliereRepository filiereRepository;
	@Autowired
	private InscriptionEnLigneRepository inscriptionEnLigne;
	@Autowired
	private InscriptionPedagogiqueRepository inscriptionPedagogiqueRepository;
	@Autowired
	private ModuleRepository moduleRepository;
	@Autowired
	private Excel2DbRepository excel2Db = new Excel2DbRepository();
	@Autowired
	private HistoriqueRepository historiqueRepository;

	// Modification
	private EtapeRepository etaperepository;
}

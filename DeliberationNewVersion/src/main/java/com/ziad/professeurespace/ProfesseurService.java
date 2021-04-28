package com.ziad.professeurespace;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ziad.models.Element;
import com.ziad.models.InscriptionPedagogique;
import com.ziad.models.Professeur;
import com.ziad.models.User;
import com.ziad.repositories.AnnneAcademiqueRepository;
import com.ziad.repositories.DocumentDePlusRepository;
import com.ziad.repositories.ElementRepository;
import com.ziad.repositories.EtablissementRepository;
import com.ziad.repositories.EtapeRepository;
import com.ziad.repositories.EtudiantRepository;
import com.ziad.repositories.FiliereRepository;
import com.ziad.repositories.InscriptionAdministrativeRepository;
import com.ziad.repositories.InscriptionEnLigneRepository;
import com.ziad.repositories.InscriptionPedagogiqueRepository;
import com.ziad.repositories.ModuleRepository;
import com.ziad.repositories.NoteElementRepository;
import com.ziad.repositories.ProfesseurRepository;
import com.ziad.repositories.SemestreRepository;
import com.ziad.repositories.UserRepository;

@Service
@Primary
public class ProfesseurService {
	@Autowired
	private AnnneAcademiqueRepository annee_academique;
	@Autowired
	private DocumentDePlusRepository documentDePlusRepository;
	@Autowired
	private ElementRepository elementRepository;
	@Autowired
	private EtablissementRepository etablissementRepository;
	@Autowired
	private EtapeRepository etapeRepository;
	@Autowired
	private EtudiantRepository etudiantRepository;
	@Autowired
	private FiliereRepository filiereRepository;
	@Autowired
	private InscriptionAdministrativeRepository inscriptionAdministrativeRepository;
	@Autowired
	private InscriptionEnLigneRepository inscriptionEnLigneRepository;
	@Autowired
	private InscriptionPedagogiqueRepository inscriptionPedagogiqueRepository;
	@Autowired
	private ModuleRepository moduleRepository;
	@Autowired
	private NoteElementRepository noteElementRepository;
	@Autowired
	private ProfesseurRepository professeurRepository;
	@Autowired
	private SemestreRepository semestreRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public ArrayList<Object> listerEtudiants() {

		/**
		 * Chercher le professeur de cette session
		 */
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		User user = userRepository.getUserByUsername(username);
		Professeur professeur = professeurRepository.getProfesseurByUser(user);

		/**
		 * Géner les inscriptions pédagogiques
		 **/
		List<InscriptionPedagogique> lists = new ArrayList<InscriptionPedagogique>();
		for (Element element : professeur.getElements()) {
			lists.addAll(inscriptionPedagogiqueRepository.getInscriptionsPedagogiqueByElement(element));
		}

		/**
		 * Géneration du besoin
		 */

		ArrayList<Object> besoins = new ArrayList<Object>();
		besoins.add(lists);
		besoins.add(annee_academique.findAll());
		besoins.add(professeur.getElements());
		return besoins;
	}
	
	

}

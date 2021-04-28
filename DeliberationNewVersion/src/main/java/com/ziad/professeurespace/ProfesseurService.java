package com.ziad.professeurespace;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ziad.exceptions.DataNotFoundExceptions;
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
public class ProfesseurService implements ProfesseurInterface {
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

	public List<Element> listerElements() throws DataNotFoundExceptions {
		List<Element> elements = elementRepository.findAll();
		if (elements.size() == 0)
			throw new DataNotFoundExceptions("Vous n'avez pas encore une liste des élements");
		return elements;
	}

	public ArrayList<Object> listerEtudiants(Long id_element) throws DataNotFoundExceptions, EntityNotFoundException {
		Element element = elementRepository.getOne(id_element);
		List<InscriptionPedagogique> inscriptions_pedagogiques = inscriptionPedagogiqueRepository
				.getInscriptionsPedagogiqueByElement(element);
		if (inscriptions_pedagogiques.size() == 0)
			throw new DataNotFoundExceptions("La liste des étudiants est vide");
		ArrayList<Object> besoins = new ArrayList<Object>();
		besoins.add(inscriptions_pedagogiques);
		besoins.add(annee_academique.findAll());
		return besoins;
	}

}

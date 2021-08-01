package com.ziad.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ziad.enums.TypeNote;
import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.AnneeAcademique;
import com.ziad.models.Element;
import com.ziad.models.Etudiant;
import com.ziad.models.InscriptionPedagogique;
import com.ziad.models.Professeur;
import com.ziad.models.User;
import com.ziad.repositories.AnnneAcademiqueRepository;
import com.ziad.repositories.ElementRepository;
import com.ziad.repositories.InscriptionPedagogiqueRepository;
import com.ziad.repositories.ProfesseurRepository;
import com.ziad.repositories.UserRepository;
import com.ziad.services.interfaces.ProfesseurInterface;
import com.ziad.utilities.Algorithms;
import com.ziad.utilities.ExcelExport;
import com.ziad.utilities.JSONConverter;

@Service
@Primary
public class ProfesseurService implements ProfesseurInterface {
	
	
	
	@Autowired
	private AnnneAcademiqueRepository anneeAcademiqueRepository;
	@Autowired
	private ElementRepository elementRepository;
	@Autowired
	private InscriptionPedagogiqueRepository inscriptionPedagogiqueRepository;
	@Autowired
	private JSONConverter converter;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProfesseurRepository professeurRepository;

	private ExcelExport generator_excel;
	
	@Autowired
	private Algorithms algorithmeRepository;
	
	public List<Element> listerElements() throws DataNotFoundExceptions {
		/**
		 * Recuperer l'utilisateur
		 **/
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserName = authentication.getName();
		User user = userRepository.getUserByUsername(currentUserName);
		Professeur professeur = professeurRepository.getProfesseurByUser(user);

		List<Element> elements = elementRepository.findAll();

		elements = elements.stream().filter(e -> e.getProfesseurs().contains(professeur)).collect(Collectors.toList());
		if (elements.size() == 0)
			throw new DataNotFoundExceptions("Vous n'avez pas encore une liste des élements");
		return elements;
	}

	public ArrayList<Object> listerEtudiants(Long id_element) throws DataNotFoundExceptions, EntityNotFoundException {
		AnneeAcademique anneeActuel = algorithmeRepository.grabAnneeAcademiqueActuel();
		
		Element element = elementRepository.getOne(id_element);
		List<InscriptionPedagogique> inscriptions_pedagogiques = inscriptionPedagogiqueRepository
				.getInscriptionPedagogiquesByElementAndAnneeAcademique(element,anneeActuel);
		ArrayList<Object> besoins = new ArrayList<Object>();
		besoins.add(inscriptions_pedagogiques);
		besoins.add(anneeActuel);
		besoins.add(element);
		return besoins;
	}

	@Override
	public void generateExcel(Long id_element, Long id_annee, HttpServletResponse response,String typeNote)
			throws EntityNotFoundException, IOException {
		Element element = elementRepository.getOne(id_element);
		AnneeAcademique annee = anneeAcademiqueRepository.getOne(id_annee);
		List<Etudiant> etudiants = inscriptionPedagogiqueRepository.getEtudiantsByElementAndAnneeAcademique(element,
				annee);
		TypeNote note = null;
		if(typeNote.equals(TypeNote.EXAM_ORDINAIRE.name())) {
			note = TypeNote.EXAM_ORDINAIRE;
		}else {
			note = TypeNote.EXAM_RATTRAPAGE;
		}
		
		
		generator_excel = new ExcelExport(etudiants);
		generator_excel.export(response,note);

	}

}

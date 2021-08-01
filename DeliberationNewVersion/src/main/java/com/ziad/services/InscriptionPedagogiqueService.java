package com.ziad.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.ziad.enums.TypeInscription;
import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.AnneeAcademique;
import com.ziad.models.Element;
import com.ziad.models.Etape;
import com.ziad.models.Etudiant;
import com.ziad.models.Filiere;
import com.ziad.models.InscriptionAdministrative;
import com.ziad.models.InscriptionPedagogique;
import com.ziad.models.Modulee;
import com.ziad.models.NoteElement;
import com.ziad.models.Semestre;
import com.ziad.models.compositeid.ComposedInscriptionAdministrative;
import com.ziad.models.compositeid.ComposedInscriptionPedagogique;
import com.ziad.repositories.AnnneAcademiqueRepository;
import com.ziad.repositories.ElementRepository;
import com.ziad.repositories.EtapeRepository;
import com.ziad.repositories.EtudiantRepository;
import com.ziad.repositories.FiliereRepository;
import com.ziad.repositories.InscriptionAdministrativeRepository;
import com.ziad.repositories.InscriptionPedagogiqueRepository;
import com.ziad.repositories.ModuleRepository;
import com.ziad.repositories.NoteElementRepository;
import com.ziad.repositories.SemestreRepository;
import com.ziad.services.interfaces.InscriptionPedagogiqueInterface;
import com.ziad.utilities.Algorithms;
import com.ziad.utilities.JSONConverter;

@Service
@Primary
public class InscriptionPedagogiqueService implements InscriptionPedagogiqueInterface {
	@Autowired
	private InscriptionAdministrativeRepository inscriptionAdministrative;
	@Autowired
	private EtudiantRepository etudiantRepository;
	@Autowired
	private FiliereRepository filiereRepository;
	@Autowired
	private SemestreRepository semestreRepository;
	@Autowired
	private InscriptionPedagogiqueRepository inscriptionPedagogiqueRepository;
	@Autowired
	private ModuleRepository moduleRepository;
	@Autowired
	private EtapeRepository etapeRepository;
	@Autowired
	private ElementRepository elementRepository;
	@Autowired
	private NoteElementRepository noteElementRepository;
	@Autowired
	private AnnneAcademiqueRepository anneeAcademiqueRepository;

	@Autowired
	private Algorithms algorithmeRepository;

	@Autowired
	private JSONConverter converter;

	private final static String TYPE_ATTRIBUT = "type-inscription";

	@Override
	public ArrayList<Object> prepareInscriptionPedagogiquePage(Long id_inscription_filiere,
			Long id_inscription_etudiant) throws EntityNotFoundException {
		Filiere filiere = filiereRepository.getOne(id_inscription_filiere);

		Etudiant etudiant = etudiantRepository.getOne(id_inscription_etudiant);
		ComposedInscriptionAdministrative id_inscription = new ComposedInscriptionAdministrative(etudiant, filiere);
		InscriptionAdministrative inscription_administrative = inscriptionAdministrative.getOne(id_inscription);

		List<Etape> etapes = etapeRepository.getEtapeByFiliere(filiere);

		List<Semestre> semestres = new ArrayList<Semestre>();
		List<Modulee> modules = new ArrayList<Modulee>();
		List<Element> elements = new ArrayList<Element>();

		for (Etape etape : etapes) {
			semestres.addAll(etape.getSemestres());
			for (Semestre semestre : etape.getSemestres()) {
				modules.addAll(semestre.getModules());
				for (Modulee module : semestre.getModules()) {
					elements.addAll(module.getElements());
				}
			}
		}

		String semestres_string = converter.convertSemestre(semestres);
		String modules_string = converter.convertModulee(modules);
		String elements_string = converter.convertElements(elements);

		List<String> types = Arrays.asList("semestre", "element", "module");
		ArrayList<Object> besoins = new ArrayList<Object>();
		besoins.add(inscription_administrative);
		besoins.add(semestres_string);
		besoins.add(modules_string);
		besoins.add(elements_string);
		besoins.add(types);
		besoins.add(algorithmeRepository.getNextFiveYear());
		return besoins;
	}

	@Override
	public void enregistrerInformation(Long id_etudiant, Long idAnneeAcademique,
			HttpServletRequest request) throws DataNotFoundExceptions, EntityNotFoundException {
		Etudiant etudiant = etudiantRepository.getOne(id_etudiant);
		AnneeAcademique annee = anneeAcademiqueRepository.getOne(idAnneeAcademique);

		try {
			String type = request.getParameter(TYPE_ATTRIBUT);
			String id_chaine = request.getParameter("lists");
			Long id = Long.parseLong(id_chaine);
			if (type.equals("semestre")) {
				Semestre semestre = semestreRepository.getOne(id);
				for (Modulee module : semestre.getModules()) {
					for (Element element : module.getElements()) {
						ComposedInscriptionPedagogique id_inscription_pedagogique = new ComposedInscriptionPedagogique(
								etudiant, element, annee);
						InscriptionPedagogique inscription_pedagogique = new InscriptionPedagogique(
								id_inscription_pedagogique, annee, false, TypeInscription.SEMESTRE);
						inscriptionPedagogiqueRepository.save(inscription_pedagogique);
						NoteElement note = new NoteElement(id_inscription_pedagogique, 0d, annee);
						noteElementRepository.save(note);
					}
				}
			} else if (type.equals("module")) {
				Modulee module = moduleRepository.getOne(id);
				for (Element element : module.getElements()) {
					ComposedInscriptionPedagogique id_inscription_pedagogique = new ComposedInscriptionPedagogique(
							etudiant, element, annee);
					InscriptionPedagogique inscription_pedagogique = new InscriptionPedagogique(
							id_inscription_pedagogique, annee, false, TypeInscription.MODULE);
					inscriptionPedagogiqueRepository.save(inscription_pedagogique);

					NoteElement note = new NoteElement(id_inscription_pedagogique, 0d, annee);
					noteElementRepository.save(note);
				}
			} else if (type.equals("element")) {
				Element element = elementRepository.getOne(id);
				ComposedInscriptionPedagogique id_inscription_pedagogique = new ComposedInscriptionPedagogique(etudiant,
						element, annee);
				InscriptionPedagogique inscription_pedagogique = new InscriptionPedagogique(id_inscription_pedagogique,
						annee, false, TypeInscription.ELEMENT);
				inscriptionPedagogiqueRepository.save(inscription_pedagogique);
				NoteElement note = new NoteElement(id_inscription_pedagogique, 0d, annee);
				noteElementRepository.save(note);
			} else {
				System.out.println("sds");
				throw new Exception();
			}
		} catch (Exception e) {
			throw new DataNotFoundExceptions("Les données sont introuvables");
		}

	}

	@Override
	public List<InscriptionPedagogique> prepareInscriptionListpage(Long id_etudiant)
			throws DataNotFoundExceptions, EntityNotFoundException {
		Etudiant etudiant = etudiantRepository.getOne(id_etudiant);
		List<InscriptionPedagogique> listes_inscriptions = inscriptionPedagogiqueRepository.getInscriptionsPedagogiqueByEtudiant(etudiant);
		if (listes_inscriptions.size() <= 0)
			throw new DataNotFoundExceptions("La liste des inscription est vide");
		return listes_inscriptions;

	}
	
	@Override
	public ArrayList<Object> listerInscriptionsPedagogique() throws DataNotFoundExceptions {
		List<Filiere> filieres = filiereRepository.findAll();
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(anneeAcademiqueRepository.findAll());
		list.add(filieres);
		list.add(converter.convertSemestre(semestreRepository.findAll()));
		list.add(converter.convertModulee(moduleRepository.findAll()));
		list.add(inscriptionPedagogiqueRepository.findAll());

		return list;
	}
}

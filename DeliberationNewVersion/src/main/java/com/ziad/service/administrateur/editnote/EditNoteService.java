package com.ziad.service.administrateur.editnote;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.AnneeAcademique;
import com.ziad.models.Element;
import com.ziad.models.Etape;
import com.ziad.models.Etudiant;
import com.ziad.models.Filiere;
import com.ziad.models.Modulee;
import com.ziad.models.NoteElement;
import com.ziad.models.Semestre;
import com.ziad.models.compositeid.ComposedInscriptionPedagogique;
import com.ziad.repositories.AnnneAcademiqueRepository;
import com.ziad.repositories.ElementRepository;
import com.ziad.repositories.EtapeRepository;
import com.ziad.repositories.EtudiantRepository;
import com.ziad.repositories.FiliereRepository;
import com.ziad.repositories.ModuleRepository;
import com.ziad.repositories.NoteElementRepository;
import com.ziad.repositories.SemestreRepository;
import com.ziad.utilities.JSONConverter;

@Service
@Primary
public class EditNoteService implements EditNoteInterface {

	private JSONConverter converter;
	@Autowired
	private ModuleRepository moduleRepository;
	@Autowired
	private SemestreRepository semestreRepository;
	@Autowired
	private EtapeRepository etapeRepository;
	@Autowired
	private ElementRepository elementRepository;
	@Autowired
	private AnnneAcademiqueRepository anneeAcademiqueRepository;
	@Autowired
	private FiliereRepository filiereRepository;
	@Autowired
	private EtudiantRepository etudiantRepository;
	@Autowired
	private NoteElementRepository noteElementRepository;

	@Override
	public ArrayList<Object> grabBesoins() throws DataNotFoundExceptions {
		ArrayList<Object> besoins = new ArrayList<Object>();
		besoins.add(filiereRepository.findAll());
		besoins.add(anneeAcademiqueRepository.findAll());
		converter = new JSONConverter();
		besoins.add(converter.convertElements(elementRepository.findAll()));
		besoins.add(converter.convertModulee(moduleRepository.findAll()));
		besoins.add(converter.convertSemestre(semestreRepository.findAll()));
		besoins.add(converter.convertEtape(etapeRepository.findAll()));
		return besoins;
	}

	@Override
	public ArrayList<Object> grabBesoinsByFilter(Long idAnneeAcademique, Long idEtape, Long idFiliere, Long idModule,
			Long idElement, Long idSemestre) throws EntityNotFoundException, DataNotFoundExceptions {
		AnneeAcademique annee = anneeAcademiqueRepository.getOne(idAnneeAcademique);

		List<NoteElement> notesElement = null;

		if (idElement != null) {
			Element element = elementRepository.getOne(idElement);
			notesElement = noteElementRepository.getNoteElementAnnee(element, annee);
		} else if (idModule != null) {
			Modulee module = moduleRepository.getOne(idModule);
			notesElement = noteElementRepository.getNoteElementAnneeModule(module, annee);
		} else if (idSemestre != null) {
			Semestre semestre = semestreRepository.getOne(idSemestre);
			notesElement = noteElementRepository.getNoteElementAnneeSemestre(semestre, annee);
		} else if (idEtape != null) {
			Etape etape = etapeRepository.getOne(idEtape);
			notesElement = noteElementRepository.getNoteElementAnneeEtape(etape, annee);
		} else if (idFiliere != null) {
			Filiere filiere = filiereRepository.getOne(idFiliere);
			notesElement = noteElementRepository.getNoteElementAnneeFiliere(filiere, annee);
		} else {
			notesElement = noteElementRepository.findAll();
		}
		if (notesElement == null || notesElement.size() == 0)
			throw new DataNotFoundExceptions();

		ArrayList<Object> besoins = new ArrayList<Object>();
		besoins.add(notesElement);
		// besoins.add(grabBesoins());
		return besoins;
	}

	@Override
	public NoteElement getNoteElement(Long idEtudiant, Long idElement) throws EntityNotFoundException {
		Etudiant etudiant = etudiantRepository.getOne(idEtudiant);
		Element element = elementRepository.getOne(idElement);
		ComposedInscriptionPedagogique key = new ComposedInscriptionPedagogique(etudiant, element);
		return noteElementRepository.getOne(key);
	}

	@Override
	public NoteElement editNote(Long idEtudiant, Long idElement, Double note, String Etat) throws EntityNotFoundException {
		Etudiant etudiant = etudiantRepository.getOne(idEtudiant);
		Element element = elementRepository.getOne(idElement);
		ComposedInscriptionPedagogique key = new ComposedInscriptionPedagogique(etudiant, element);
		NoteElement noteElement = noteElementRepository.getOne(key);
		noteElement.setNote_element(note);
		noteElement.setEtat(Etat);
		noteElementRepository.save(noteElement);
		return noteElement;
	}

}

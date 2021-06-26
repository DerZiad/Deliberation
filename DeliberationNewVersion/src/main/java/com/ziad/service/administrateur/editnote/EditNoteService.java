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
import com.ziad.models.ElementType;
import com.ziad.models.Etape;
import com.ziad.models.Etudiant;
import com.ziad.models.Modulee;
import com.ziad.models.NoteElement;
import com.ziad.models.NoteEtape;
import com.ziad.models.NoteModule;
import com.ziad.models.NoteNorm;
import com.ziad.models.NoteSemestre;
import com.ziad.models.Semestre;
import com.ziad.models.compositeid.ComposedInscriptionPedagogique;
import com.ziad.models.compositeid.ComposedNoteEtape;
import com.ziad.models.compositeid.ComposedNoteModule;
import com.ziad.models.compositeid.ComposedNoteSemestre;
import com.ziad.repositories.AnnneAcademiqueRepository;
import com.ziad.repositories.ElementRepository;
import com.ziad.repositories.EtapeRepository;
import com.ziad.repositories.EtudiantRepository;
import com.ziad.repositories.FiliereRepository;
import com.ziad.repositories.ModuleRepository;
import com.ziad.repositories.NoteElementRepository;
import com.ziad.repositories.NotesEtapeRepository;
import com.ziad.repositories.NotesModuleRepository;
import com.ziad.repositories.NotesSemestreRepository;
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
	@Autowired
	private NotesModuleRepository noteModuleRepository;
	@Autowired
	private NotesSemestreRepository noteSemestreRepository;
	@Autowired
	private NotesEtapeRepository noteEtapeRepository;

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
	public ArrayList<Object> grabBesoinsByFilter(Long idAnneeAcademique, Long idEtape, Long idModule,
			Long idElement, Long idSemestre) throws EntityNotFoundException, DataNotFoundExceptions {
		AnneeAcademique annee = anneeAcademiqueRepository.getOne(idAnneeAcademique);

		List<NoteNorm> notesElement = new ArrayList<NoteNorm>();

		if (idElement != null) {
			Element element = elementRepository.getOne(idElement);
			List<NoteElement> notes = noteElementRepository.getNoteElementAnnee(element, annee);
			notesElement.addAll(notes);
			
		} else if (idModule != null) {
			Modulee module = moduleRepository.getOne(idModule);		
			List<NoteModule> notes = noteModuleRepository.getNoteModuleAnnee(module, annee);
			notesElement.addAll(notes);
		} else if (idSemestre != null) {
			Semestre semestre = semestreRepository.getOne(idSemestre);
			List<NoteSemestre> notes = noteSemestreRepository.getNoteSemestreAnnee(semestre, annee);
			notesElement.addAll(notes);
		} else {
			Etape etape = etapeRepository.getOne(idEtape);
			List<NoteEtape> notes = noteEtapeRepository.getNoteEtapeAnnee(etape,annee);
			notesElement.addAll(notes);		
		}
		if (notesElement.size() == 0)
			throw new DataNotFoundExceptions();

		ArrayList<Object> besoins = new ArrayList<Object>();
		JSONConverter cnv = new JSONConverter();
		besoins.add(cnv.convertNotesNormal(notesElement.get(0)));
		return besoins;
	}

	@Override
	public NoteNorm getNoteElement(Long idEtudiant, Long idElement,String type) throws EntityNotFoundException {
		Etudiant etudiant = etudiantRepository.getOne(idEtudiant);
		if(type.equals(ElementType.ELEMENT.name())) {
			ComposedInscriptionPedagogique id = new ComposedInscriptionPedagogique(etudiant,elementRepository.getOne(idElement));
			NoteElement noteElement = noteElementRepository.getOne(id);
			return noteElement;
		}else if(type.equals(ElementType.MODULE.name())) {
			ComposedNoteModule idCompose = new ComposedNoteModule(moduleRepository.getOne(idElement),etudiant);
			NoteModule noteModule = noteModuleRepository.getOne(idCompose);
			return noteModule;
		}else if(type.equals(ElementType.SEMESTRE.name())) {
			ComposedNoteSemestre idCompose = new ComposedNoteSemestre(semestreRepository.getOne(idElement),etudiant);
			return noteSemestreRepository.getOne(idCompose);
		}else{
			ComposedNoteEtape idCompose = new ComposedNoteEtape(etapeRepository.getOne(idElement), etudiant);
			return noteEtapeRepository.getOne(idCompose);
		}
	}

	@Override
	public NoteNorm editNote(Long idEtudiant, Long idElement, Double note,String type) throws EntityNotFoundException {
		Etudiant etudiant = etudiantRepository.getOne(idEtudiant);
		if(type.equals(ElementType.ELEMENT.name())) {
			ComposedInscriptionPedagogique id = new ComposedInscriptionPedagogique(etudiant,elementRepository.getOne(idElement));
			NoteElement noteElement = noteElementRepository.getOne(id);
			noteElement.setNote(note);
			noteElement.calculState();
			noteElementRepository.save(noteElement);
			return noteElement;
		}else if(type.equals(ElementType.MODULE.name())) {
			ComposedNoteModule idCompose = new ComposedNoteModule(moduleRepository.getOne(idElement),etudiant);
			NoteModule noteModule = noteModuleRepository.getOne(idCompose);
			noteModule.setNote(note);
			noteModule.calculState();
			noteModuleRepository.save(noteModule);
			return noteModule;
		}else if(type.equals(ElementType.SEMESTRE.name())) {
			ComposedNoteSemestre idCompose = new ComposedNoteSemestre(semestreRepository.getOne(idElement),etudiant);
			NoteSemestre noteSemestre =  noteSemestreRepository.getOne(idCompose);
			noteSemestre.setNote(note);
			noteSemestre.calculState();
			noteSemestreRepository.save(noteSemestre);
			return noteSemestre;
		}else{
			ComposedNoteEtape idCompose = new ComposedNoteEtape(etapeRepository.getOne(idElement), etudiant);
			NoteEtape noteEtape =  noteEtapeRepository.getOne(idCompose);
			noteEtape.setNote(note);
			noteEtape.calculState();
			noteEtapeRepository.save(noteEtape);
			return noteEtape;
		}
	}

}

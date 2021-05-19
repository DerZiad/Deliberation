package com.ziad.deliberation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ziad.enums.DeliberationType;
import com.ziad.enums.TypeInscription;
import com.ziad.models.AnneeAcademique;
import com.ziad.models.Deliberation;
import com.ziad.models.Element;
import com.ziad.models.Etape;
import com.ziad.models.Etudiant;
import com.ziad.models.Modulee;
import com.ziad.models.NoteElement;
import com.ziad.models.NoteEtape;
import com.ziad.models.NoteModule;
import com.ziad.models.NoteSemestre;
import com.ziad.models.Semestre;
import com.ziad.models.compositeid.ComposedInscriptionPedagogique;
import com.ziad.models.compositeid.ComposedNoteEtape;
import com.ziad.models.compositeid.ComposedNoteModule;
import com.ziad.models.compositeid.ComposedNoteSemestre;
import com.ziad.repositories.DeliberationRepository;
import com.ziad.repositories.InscriptionPedagogiqueRepository;
import com.ziad.repositories.NoteElementRepository;
import com.ziad.repositories.NotesModuleRepository;
import com.ziad.repositories.NotesSemestreRepository;

@Service
public class Algorithme {
	@Autowired
	private InscriptionPedagogiqueRepository inscriptionPedagogiqueRepository;
	@Autowired
	private NotesModuleRepository notesModuleRepository;
	@Autowired
	private NotesSemestreRepository notesSemestreRepository;
	@Autowired
	private NoteElementRepository noteElementRepository;
	@Autowired
	private DeliberationRepository deliberationRepository;

	private DeliberationType typeDelib = DeliberationType.ORDINAIRE;
	private Integer consideration = 1;// Consideration encas du rattrapage

	public void enableDeliberationRattrapage() {
		typeDelib = DeliberationType.RATTRAPAGE;
	}

	public void enableDeliberationOrdinaire() {
		typeDelib = DeliberationType.ORDINAIRE;
	}

	public void enableConsideration(Boolean choix) {
		consideration = choix ? 1 : 0;
	}

	public void delibererElement(Element element, AnneeAcademique annee) {
		List<NoteElement> notes = noteElementRepository.getNoteElementAnnee(element, annee);
		for (NoteElement noteElement : notes) {
			if (typeDelib.equals(DeliberationType.ORDINAIRE)) {
				noteElement.delibererElementOrdinaire();
			} else if (typeDelib.equals(DeliberationType.RATTRAPAGE)) {
				if (!noteElement.isValid()) {
					noteElement.delibererElementRattrapage(consideration);
				}
			}
		}
	}

	public void delibererModule(Modulee module, AnneeAcademique annee,TypeInscription type) {
		if(type == null) {
			type = TypeInscription.MODULE;
		}
		
		List<Deliberation> delibs = null;
		/**
		 * Etape1 -> Verifier si on a deja deliberer
		 * 
		 **/
		if (DeliberationType.ORDINAIRE.equals(typeDelib)) {
			delibs = deliberationRepository.getDeliberationByModuleOrdinaire(module, annee);
		} else {
			delibs = deliberationRepository.getDeliberationByModuleRattrapage(module, annee);
		}

		if (delibs.size() == 0) {
			Deliberation deliberation = new Deliberation(typeDelib.name(), annee, module, null, null);
			for (Element element : module.getElements()) {
				delibererElement(element, annee);
			}
			List<Etudiant> etudiants = inscriptionPedagogiqueRepository.getEtudiantParModule(module, annee,type);
			for (Etudiant etudiant : etudiants) {
				Double coefficient = 0d;
				Double noteDouble = 0d;
				for (Element element : module.getElements()) {
					NoteElement note = noteElementRepository
							.getOne(new ComposedInscriptionPedagogique(etudiant, element));
					System.out.println("Element object " + note);
					System.out.println("Note element " + note.getNote_element());
					System.out.println("Coefficient  " + element.getCoeficient());
					noteDouble = noteDouble + note.getNote_element() * element.getCoeficient();
					coefficient = coefficient + element.getCoeficient();
				}
				noteDouble = noteDouble / coefficient;
				NoteModule noteParModule = new NoteModule(new ComposedNoteModule(module, etudiant), 2d,
						deliberation);
				noteParModule.delibererModule(typeDelib);
				deliberation.addNoteModule(noteParModule);
			}
			deliberationRepository.save(deliberation);
		}
	}

	public HashMap<Modulee,HashMap<Etudiant,Double>> delibererSemestre(Semestre semestre, AnneeAcademique annee) {
		List<Deliberation> delibs = null;
		/**
		 * Etape1 -> Verifier si on a deja deliberer
		 * 
		 **/
		if (DeliberationType.ORDINAIRE.equals(typeDelib)) {
			delibs = deliberationRepository.getDeliberationBySemestreOrdinaire(semestre, annee);
		} else {
			delibs = deliberationRepository.getDeliberationBySemestreRattrapage(semestre, annee);
		}

		if (delibs.size() == 0) {
			Deliberation deliberation = new Deliberation(typeDelib.name(), annee, null, semestre, null);
			for (Modulee module : semestre.getModules()) {
				delibererModule(module, annee,TypeInscription.SEMESTRE);
			}

			List<Etudiant> etudiants = inscriptionPedagogiqueRepository.getEtudiantParSemestre(semestre, annee);

			for (Etudiant etudiant : etudiants) {
				Double coefficient = 0d;
				Double noteSemestre = 0d;
				List<NoteModule> notess = new ArrayList<NoteModule>();
				for (Modulee module : semestre.getModules()) {
					NoteModule noteParModule = notesModuleRepository.getOne(new ComposedNoteModule(module, etudiant));
					noteSemestre = noteSemestre + noteParModule.getNote() * module.getCoeficient();
					coefficient = coefficient + module.getCoeficient();
					notess.add(noteParModule);
				}
				noteSemestre = noteSemestre / coefficient;
				NoteSemestre noteSemestreO = new NoteSemestre(new ComposedNoteSemestre(semestre, etudiant),
						noteSemestre, deliberation);
				noteSemestreO.delibererSemestre(notess);
				deliberation.addNoteSemestre(noteSemestreO);
				//deliberationRepository.save(deliberation);
			}

		}
		HashMap<Modulee, HashMap<Etudiant, Double>> structures = new HashMap<Modulee, HashMap<Etudiant, Double>>();
		for (Modulee module : semestre.getModules()) {
			List<NoteModule> notes = null;
			if (typeDelib.equals(DeliberationType.ORDINAIRE)) {
				notes = notesModuleRepository.listerNotesModuleByAnneeOrdinaire(module, annee);
			} else {
				notes = notesModuleRepository.listerNotesModuleByAnneeRattrapage(module, annee);
			}
			HashMap<Etudiant, Double> EtudiantNotes = new HashMap<Etudiant, Double>();
			for (NoteModule note : notes) {
				EtudiantNotes.put(note.getIdComposed().getEtudiant(), note.getNote());
			}
			structures.put(module, EtudiantNotes);
		}
		return structures;

	}

	public void delibererEtape(Etape etape, AnneeAcademique annee) {
		List<Deliberation> delibs = null;
		/**
		 * Etape1 -> Verifier si on a deja deliberer
		 * 
		 **/
		if (DeliberationType.ORDINAIRE.equals(typeDelib)) {
			delibs = deliberationRepository.getDeliberationByEtapeOrdinaire(etape, annee);
		} else {
			delibs = deliberationRepository.getDeliberationByEtapeRattrapage(etape, annee);
		}

		if (delibs.size() == 0) {
			Deliberation deliberation = new Deliberation(typeDelib.name(), annee, null, null, etape);

			List<Etudiant> etudiants = new ArrayList<Etudiant>();
			for (Semestre semestre : etape.getSemestres()) {
				delibererSemestre(semestre, annee);
				etudiants.addAll(inscriptionPedagogiqueRepository.getEtudiantParSemestre(semestre, annee));
			}

			for (Etudiant etudiant : etudiants) {
				Double noteParEtapeD = 0d;
				for (Semestre semestre : etape.getSemestres()) {
					NoteSemestre noteSemestre = notesSemestreRepository
							.getOne(new ComposedNoteSemestre(semestre, etudiant));
					noteParEtapeD = noteParEtapeD + noteSemestre.getNote();
				}
				noteParEtapeD = noteParEtapeD / 2;
				NoteEtape noteEtape = new NoteEtape(new ComposedNoteEtape(etape, etudiant), noteParEtapeD,
						deliberation);
				deliberation.addNoteEtape(noteEtape);
				deliberationRepository.save(deliberation);
			}
		}

	}

}

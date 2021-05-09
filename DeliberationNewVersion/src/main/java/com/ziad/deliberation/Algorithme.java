package com.ziad.deliberation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ziad.models.AnneeAcademique;
import com.ziad.models.Element;
import com.ziad.models.Etape;
import com.ziad.models.Etudiant;
import com.ziad.models.Modulee;
import com.ziad.models.NoteElement;
import com.ziad.models.Semestre;
import com.ziad.models.compositeid.ComposedInscriptionPedagogique;
import com.ziad.newmodels.ComposedNoteEtape;
import com.ziad.newmodels.ComposedNoteModule;
import com.ziad.newmodels.ComposedNoteSemestre;
import com.ziad.newmodels.Deliberation;
import com.ziad.newmodels.DeliberationRepository;
import com.ziad.newmodels.DeliberationType;
import com.ziad.newmodels.NoteEtape;
import com.ziad.newmodels.NoteModule;
import com.ziad.newmodels.NoteSemestre;
import com.ziad.newmodels.NotesModuleRepository;
import com.ziad.newmodels.NotesSemestreRepository;
import com.ziad.repositories.InscriptionPedagogiqueRepository;
import com.ziad.repositories.NoteElementRepository;

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
		typeDelib= DeliberationType.ORDINAIRE;
	}
	
	public void enableConsideration(Boolean choix) {
		consideration = choix ? 1:0;
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

	public void delibererModule(Modulee module, AnneeAcademique annee) {
		for (Element element : module.getElements()) {
			delibererElement(element, annee);
		}
		Deliberation deliberation = new Deliberation(typeDelib, DeliberationType.MODULE, annee);
		List<Etudiant> etudiants = inscriptionPedagogiqueRepository.getEtudiantParModule(module, annee);
		for (Etudiant etudiant : etudiants) {
			double coefficient = 0d;
			double noteDouble = 0d;
			for (Element element : module.getElements()) {
				NoteElement note = noteElementRepository.getOne(new ComposedInscriptionPedagogique(etudiant, element));
				noteDouble = noteDouble + note.getNote_element() * element.getCoeficient();
				coefficient = coefficient + element.getCoeficient();
			}
			noteDouble = noteDouble / coefficient;
			NoteModule noteParModule = new NoteModule(new ComposedNoteModule(module, etudiant), noteDouble,
					deliberation);
			noteParModule.delibererModule(typeDelib);
			deliberation.addNoteModule(noteParModule);
			deliberationRepository.save(deliberation);
			// TODO - Ziad 2 Don't forget to save
		}
	}

	public void delibererSemestre(Semestre semestre, AnneeAcademique annee) {
		for (Modulee module : semestre.getModules()) {
			delibererModule(module, annee);
		}

		Deliberation deliberation = new Deliberation(typeDelib, DeliberationType.SEMESTRE, annee);
		List<Etudiant> etudiants = inscriptionPedagogiqueRepository.getEtudiantParSemestre(semestre, annee);

		for (Etudiant etudiant : etudiants) {
			double coefficient = 0d;
			double noteSemestre = 0d;
			for (Modulee module : semestre.getModules()) {
				List<Deliberation> deliberations = deliberationRepository.findAll();
				List<Deliberation> delibs = deliberations.stream()
						.filter(delib -> delib.getTypeDeliberation().equals(typeDelib)
								&& delib.getTypeExamen().equals(DeliberationType.MODULE)
								&& delib.getAnneeAcademique().equals(annee))
						.collect(Collectors.toList());
				if (delibs.size() == 0) {
					NoteModule noteParModule = notesModuleRepository.getOne(new ComposedNoteModule(module, etudiant));
					noteSemestre = noteSemestre + noteParModule.getNote() * module.getCoeficient();
					coefficient = coefficient + module.getCoeficient();
				}
			}
			noteSemestre = noteSemestre / coefficient;
			NoteSemestre noteSemestreO = new NoteSemestre(new ComposedNoteSemestre(semestre, etudiant), noteSemestre,
					deliberation);
			deliberation.addNoteSemestre(noteSemestreO);
			deliberationRepository.save(deliberation);
		}

	}

	public void delibererEtape(Etape etape, AnneeAcademique annee) {
		List<Etudiant> etudiants = new ArrayList<Etudiant>();
		for (Semestre semestre : etape.getSemestres()) {
			delibererSemestre(semestre, annee);
			etudiants.addAll(inscriptionPedagogiqueRepository.getEtudiantParSemestre(semestre, annee));
		}

		Deliberation deliberation = new Deliberation(typeDelib, DeliberationType.ETAPE, annee);

		for (Etudiant etudiant : etudiants) {
			double noteParEtapeD = 0d;
			for (Semestre semestre : etape.getSemestres()) {
				NoteSemestre noteSemestre = notesSemestreRepository
						.getOne(new ComposedNoteSemestre(semestre, etudiant));
				noteParEtapeD = noteParEtapeD + noteSemestre.getNote();
			}
			noteParEtapeD = noteParEtapeD / 2;
			NoteEtape noteEtape = new NoteEtape(new ComposedNoteEtape(etape, etudiant), noteParEtapeD, deliberation);
			deliberation.addNoteEtape(noteEtape);
			deliberationRepository.save(deliberation);
		}

	}

}

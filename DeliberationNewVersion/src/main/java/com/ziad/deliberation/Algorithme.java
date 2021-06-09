package com.ziad.deliberation;

import java.util.ArrayList;
import java.util.Collections;
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
import com.ziad.models.InscriptionPedagogique;
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
import com.ziad.repositories.AnnneAcademiqueRepository;
import com.ziad.repositories.DeliberationRepository;
import com.ziad.repositories.ElementRepository;
import com.ziad.repositories.InscriptionPedagogiqueRepository;
import com.ziad.repositories.NoteElementRepository;
import com.ziad.repositories.NotesModuleRepository;
import com.ziad.repositories.NotesSemestreRepository;
import com.ziad.repositories.SemestreRepository;

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
	@Autowired
	private ElementRepository elementRepository;;

	@Autowired
	private SemestreRepository semestreRepository;

	@Autowired
	private AnnneAcademiqueRepository anneeAcademiqueRepository;

	private DeliberationType typeDelib = DeliberationType.ORDINAIRE;
	private Integer consideration = 0;// Consideration encas du rattrapage

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
				if (!noteElement.isValid())
					noteElement.delibererElementRattrapage(consideration);
			}
		}
	}

	public Deliberation delibererModule(Modulee module, AnneeAcademique annee) throws DeliberationModuleNotAllowed {
		/**
		 * Etape1 -> Verifier si on a deja deliberer Avant de deliberer on doit
		 * récuperer quel etat de deliberation on est RATT OR ORDINARY
		 **/
		List<Deliberation> delibs = null;
		delibs = deliberationRepository.getDeliberationByModuleAnnneAcademique(module, annee);
		Deliberation deliberation = null;

		if (delibs.size() == 0) {
			deliberation = new Deliberation(typeDelib.name(), annee, module, null, null);
		} else {
			deliberation = delibs.get(0);
			boolean delibRatt = !deliberation.isDelibered() && typeDelib.equals(DeliberationType.RATTRAPAGE);
			// On a
			// pas
			// encore
			// deliberer
			// rattrapage
			deliberation.setDelibered(delibRatt);
		}
		for (Element element : elementRepository.getElementsByModule(module)) {
			delibererElement(element, annee);
		}
		List<InscriptionPedagogique> listeInscriptionsPedagogique = inscriptionPedagogiqueRepository
				.getInscriptionPedagogiqueParModule(module, annee);
		try {
			for (InscriptionPedagogique inscription : listeInscriptionsPedagogique) {
				Double coefficient = 0d;
				Double noteDouble = 0d;
				for (Element element : elementRepository.getElementsByModule(module)) {
					NoteElement note = noteElementRepository.getOne(inscription.getId_inscription_pedagogique());
					noteDouble = noteDouble + note.getNote_element() * element.getCoeficient();
					coefficient = coefficient + element.getCoeficient();
				}
				noteDouble = noteDouble / coefficient;
				NoteModule noteParModule = null;
				if (typeDelib.equals(DeliberationType.RATTRAPAGE)) {
					noteParModule = notesModuleRepository
							.getOne(new ComposedNoteModule(module, inscription.getEtudiant()));
					noteParModule.setNote(noteDouble);
				} else {
					noteParModule = new NoteModule(new ComposedNoteModule(module, inscription.getEtudiant()),
							noteDouble, deliberation);
				}
				noteParModule.delibererModule(typeDelib);
				deliberation.addNoteModule(noteParModule);
			}
			deliberationRepository.save(deliberation);
			return deliberation;
		} catch (Exception e) {
			System.out.println("Throwable");
			throw new DeliberationModuleNotAllowed(module, "Les notes ne sont pas encore prêtes");
		}
	}

	public Deliberation delibererSemestre(Semestre semestre, AnneeAcademique annee)
			throws DeliberationSemestreNotAllowed {
		List<Deliberation> delibs = deliberationRepository.getDeliberationBySemestreAnnneAcademique(semestre, annee);
		if (delibs.size() == 0) {
			Deliberation deliberation = null;
			isDeliberationSemestreAllowed(semestre, annee);
			deliberation = new Deliberation(typeDelib.name(), annee, null, semestre, null);
			List<InscriptionPedagogique> inscriptionsPedagogiques = inscriptionPedagogiqueRepository
					.getInscriptionPedagogiqueParSemestre(semestre, annee);
			inscriptionsPedagogiques = filterInscription(inscriptionsPedagogiques);

			for (InscriptionPedagogique inscription : inscriptionsPedagogiques) {
				boolean inscritpedagogique = true;
				Double coefficient = 0d;
				Double noteSemestre = 0d;
				List<NoteModule> notess = new ArrayList<NoteModule>();
				for (Modulee module : semestre.getModules()) {
					NoteModule noteParModule = notesModuleRepository
							.getOne(new ComposedNoteModule(module, inscription.getEtudiant()));
					noteSemestre = noteSemestre + noteParModule.getNote() * module.getCoeficient();
					coefficient = coefficient + module.getCoeficient();
					notess.add(noteParModule);
					inscritpedagogique = inscritpedagogique && noteParModule.isValid();
				}
				noteSemestre = noteSemestre / coefficient;
				NoteSemestre noteSemestreO = new NoteSemestre(
						new ComposedNoteSemestre(semestre, inscription.getEtudiant()), noteSemestre, deliberation);
				noteSemestreO.delibererSemestre(notess);
				deliberation.addNoteSemestre(noteSemestreO);

				if (inscritpedagogique) {
					/**
					 * Grab semestre
					 **/
					List<Semestre> listeSemestre = semestreRepository.findAll();
					Collections.sort(listeSemestre);

					int index = semestre.getOrdre() + 2;
					AnneeAcademique anneeSuivant = anneeAcademiqueRepository
							.getAnneeAcademique(inscription.getAnnee_academique().getAnnee_academique() + 1).get(0);
					if (index <= listeSemestre.size()) {
						Etudiant etudiant = inscription.getEtudiant();
						Semestre semestreAdelib = listeSemestre.get(index - 1);
						for (Modulee module : semestreAdelib.getModules()) {
							for (Element element : module.getElements()) {
								ComposedInscriptionPedagogique id_inscription_pedagogique = new ComposedInscriptionPedagogique(
										etudiant, element);
								InscriptionPedagogique inscription_pedagogique = new InscriptionPedagogique(
										id_inscription_pedagogique, anneeSuivant, false, TypeInscription.SEMESTRE);
								inscriptionPedagogiqueRepository.save(inscription_pedagogique);
								NoteElement note = new NoteElement(id_inscription_pedagogique, 0d, anneeSuivant);
								noteElementRepository.save(note);
							}
						}
					}

				}
			}
			deliberationRepository.save(deliberation);

			return deliberation;
		}
		return delibs.get(0);
	}

	private void isDeliberationSemestreAllowed(Semestre semestre, AnneeAcademique annee)
			throws DeliberationSemestreNotAllowed {
		for (Modulee module : semestre.getModules()) {
			List<Deliberation> delibs = deliberationRepository.getDeliberationByModuleAnnneAcademique(module, annee);
			if (delibs.size() == 0) {
				throw new DeliberationSemestreNotAllowed(module, "Le module suivant n'est pas deliberer");
			} else {
				Deliberation del = delibs.get(0);
				if (!del.isDelibered())
					throw new DeliberationSemestreNotAllowed(module,
							"Le module suivant n est pas deliberer en rattrapage");
			}
		}
	}

	public Deliberation delibererEtape(Etape etape, AnneeAcademique annee) throws DeliberationEtapeNotAllowed {
		List<Deliberation> delibs = deliberationRepository.getDeliberationByEtapeAnnneAcademique(etape, annee);
		/**
		 * Etape1 -> Verifier si on a deja deliberer
		 * 
		 **/
		Deliberation deliberation = null;
		if (delibs.size() == 0) {
			System.out.println("Allowed");
			isDeliberationEtapeAllowed(etape, annee);
			deliberation = new Deliberation(typeDelib.name(), annee, null, null, etape);
			System.out.println("Created");
			List<InscriptionPedagogique> inscriptions = inscriptionPedagogiqueRepository
					.getInscriptionPedagogiqueParEtape(etape, annee);
			inscriptions = filterInscription(inscriptions);
			for (InscriptionPedagogique inscription : inscriptions) {
				System.out.println("Inside boocle");
				Double noteParEtapeD = 0d;
				for (Semestre semestre : etape.getSemestres()) {
					NoteSemestre noteSemestre = notesSemestreRepository
							.getOne(new ComposedNoteSemestre(semestre, inscription.getEtudiant()));
					noteParEtapeD = noteParEtapeD + noteSemestre.getNote();
				}
				System.out.println("Notee ");
				noteParEtapeD = noteParEtapeD / 2;
				NoteEtape noteEtape = new NoteEtape(new ComposedNoteEtape(etape, inscription.getEtudiant()),
						noteParEtapeD, deliberation);
				noteEtape.delibererEtape();
				deliberation.addNoteEtape(noteEtape);
				deliberationRepository.save(deliberation);
			}
		} else {
			deliberation = delibs.get(0);
		}
		return deliberation;

	}

	private void isDeliberationEtapeAllowed(Etape etape, AnneeAcademique annee) throws DeliberationEtapeNotAllowed {
		for (Semestre semestre : etape.getSemestres()) {
			List<Deliberation> delibs = deliberationRepository.getDeliberationBySemestreAnnneAcademique(semestre,
					annee);
			if (delibs.size() == 0) {
				throw new DeliberationEtapeNotAllowed(semestre, "Le semestre n est pas deliberer");
			}
		}
	}

	private List<InscriptionPedagogique> filterInscription(List<InscriptionPedagogique> inscriptionsPedagogiques) {

		boolean check = true;
		List<InscriptionPedagogique> inscriptionsPedagogiquesFiltrer = new ArrayList<InscriptionPedagogique>();
		for (InscriptionPedagogique inscriptionPedagogique : inscriptionsPedagogiques) {
			check = true;
			for (InscriptionPedagogique inscriptionPedagogique1 : inscriptionsPedagogiquesFiltrer) {
				if (inscriptionPedagogique.getEtudiant().equals(inscriptionPedagogique1.getEtudiant()))
					check = check && false;
			}
			if (check) {
				inscriptionsPedagogiquesFiltrer.add(inscriptionPedagogique);
			}
		}
		return inscriptionsPedagogiquesFiltrer;
	}

}

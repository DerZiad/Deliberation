package com.ziad.service.deliberation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.http.auth.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ziad.enums.DeliberationType;
import com.ziad.enums.TypeInscription;
import com.ziad.exceptions.DeliberationElementNotAllowed;
import com.ziad.exceptions.DeliberationEtapeNotAllowed;
import com.ziad.exceptions.DeliberationModuleNotAllowed;
import com.ziad.exceptions.DeliberationSemestreNotAllowed;
import com.ziad.exceptions.InvalidCredinals;
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


	public Deliberation delibererElementOrdinaire(Element element, AnneeAcademique annee) throws InvalidCredinals {
		isDeliberationElementAllowed(element, annee);
		Deliberation deliberation = new Deliberation();
		deliberation.setDelibered(false);
		deliberation.setAnneeAcademique(annee);
		deliberation.setElement(element);
		List<NoteElement> notes = noteElementRepository.getNoteElementAnnee(element, annee);
		for (NoteElement noteElement : notes) {
			noteElement.delibererElementOrdinaire();
			noteElement.setDeliberation(deliberation);
		}
		deliberation.setNotesElement(notes);
		deliberationRepository.save(deliberation);
		return deliberation;
	}

	public Deliberation delibererElementRattrapage(Element element, AnneeAcademique annee, Integer consideration)
			throws InvalidCredinals {
		Deliberation deliberation = isDeliberationElementByRattrapageAllowed(element, annee);
		List<NoteElement> notes = noteElementRepository.getNoteElementAnnee(element, annee);
		for (NoteElement noteElement : notes) {
			if (!noteElement.isValid())
				noteElement.delibererElementRattrapage(consideration);
		}

		deliberation.setNotesElement(notes);
		deliberation.setDelibered(true);
		deliberationRepository.save(deliberation);
		return deliberation;
	}

	public void isDeliberationElementAllowed(Element element, AnneeAcademique annee) throws InvalidCredinals {
		InvalidCredinals invalidCredinals = new InvalidCredinals();

		List<Deliberation> deliberation = deliberationRepository.getDeliberationByElementAnnneAcademique(element,
				annee);
		if (deliberation.size() != 0) {
			invalidCredinals.addErreur("Erreur ", "Vous avez déja effectué une déliberation");
		}

		List<NoteElement> notes = noteElementRepository.getNoteElementAnnee(element, annee);
		for (NoteElement noteElement : notes) {
			if (noteElement.getNotes().size() < 3) {
				Etudiant etudiant = noteElement.getEtudiant();
				invalidCredinals.addErreur("Erreur ", "Les notes de l'etudiant " + etudiant.getLast_name_fr() + " "
						+ etudiant.getFirst_name_fr() + "doît être totalement saisi");
			}
		}
		if (!invalidCredinals.allow()) {
			throw invalidCredinals;
		}

	}

	public Deliberation isDeliberationElementByRattrapageAllowed(Element element, AnneeAcademique annee)
			throws InvalidCredinals {
		InvalidCredinals invalidCredinals = new InvalidCredinals();

		List<Deliberation> deliberation = deliberationRepository.getDeliberationByElementAnnneAcademique(element,
				annee);
		if (deliberation.size() == 0) {
			invalidCredinals.addErreur("Erreur ", "Vous avez pas encore fait une déliberation ordinaire");
			throw invalidCredinals;
		}

		if (deliberation.get(0).isDelibered()) {
			invalidCredinals.addErreur("Erreur ", "Vous avez déja effectué une déliberation en rattrapage");
			throw invalidCredinals;
		}

		List<NoteElement> notes = noteElementRepository.getNoteElementAnnee(element, annee);
		for (NoteElement noteElement : notes) {
			if (!noteElement.isValid() && noteElement.getNotes().size() < 4) {
				Etudiant etudiant = noteElement.getEtudiant();
				invalidCredinals.addErreur("Erreur ", "Les notes de l'etudiant " + etudiant.getLast_name_fr() + " "
						+ etudiant.getFirst_name_fr() + "doît être totalement saisi");
			}
		}
		if (!invalidCredinals.allow()) {
			throw invalidCredinals;
		}

		return deliberation.get(0);

	}

	public Deliberation delibererModule(Modulee module, AnneeAcademique annee) throws DeliberationModuleNotAllowed, DeliberationElementNotAllowed {
		isDeliberationModuleAllowed(module, annee);

		Deliberation deliberation = new Deliberation();
		deliberation.setModule(module);
		deliberation.setAnneeAcademique(annee);

		List<InscriptionPedagogique> listeInscriptionsPedagogique = inscriptionPedagogiqueRepository
				.getInscriptionPedagogiqueParModule(module, annee);

		for (InscriptionPedagogique inscription : listeInscriptionsPedagogique) {
			Double coefficient = 0d;
			Double noteDouble = 0d;
			for (Element element : elementRepository.getElementsByModule(module)) {
				NoteElement note = noteElementRepository.getOne(inscription.getId_inscription_pedagogique());
				noteDouble = noteDouble + note.getNote_element() * element.getCoeficient();
				coefficient = coefficient + element.getCoeficient();
			}
			noteDouble = noteDouble / coefficient;
			NoteModule noteParModule = new NoteModule(new ComposedNoteModule(module, inscription.getEtudiant(), annee),
					noteDouble, deliberation, annee);
			noteParModule.delibererModule();
			deliberation.addNoteModule(noteParModule);
		}
		deliberation.setDelibered(true);
		deliberationRepository.save(deliberation);
		return deliberation;
	}

	public Deliberation delibererSemestre(Semestre semestre, AnneeAcademique annee)
			throws DeliberationSemestreNotAllowed {

		isDeliberationSemestreAllowed(semestre, annee);
		
		Deliberation deliberation = new Deliberation();
		deliberation.setSemestre(semestre);
		deliberation.setAnneeAcademique(annee);
		
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
						.getOne(new ComposedNoteModule(module, inscription.getEtudiant(), annee));
				noteSemestre = noteSemestre + noteParModule.getNote() * module.getCoeficient();
				coefficient = coefficient + module.getCoeficient();
				notess.add(noteParModule);
				inscritpedagogique = inscritpedagogique && noteParModule.isValid();
			}
			noteSemestre = noteSemestre / coefficient;
			NoteSemestre noteSemestreO = new NoteSemestre(
					new ComposedNoteSemestre(semestre, inscription.getEtudiant(), annee), noteSemestre, deliberation,
					annee);
			noteSemestreO.delibererSemestre(notess);
			deliberation.addNoteSemestre(noteSemestreO);

			/*if (inscritpedagogique) {

				List<Semestre> listeSemestre = semestreRepository.findAll();
				Collections.sort(listeSemestre);

				int index = semestre.getOrdre() + 2;
				AnneeAcademique anneeSuivant = anneeAcademiqueRepository
						.getAnneeAcademique(inscription.getAnnee_academique().getAnnee_academique() + 1);
				if (index <= listeSemestre.size()) {
					Etudiant etudiant = inscription.getEtudiant();
					Semestre semestreAdelib = listeSemestre.get(index - 1);
					for (Modulee module : semestreAdelib.getModules()) {
						for (Element element : module.getElements()) {
							ComposedInscriptionPedagogique id_inscription_pedagogique = new ComposedInscriptionPedagogique(
									etudiant, element, null);
							InscriptionPedagogique inscription_pedagogique = new InscriptionPedagogique(
									id_inscription_pedagogique, anneeSuivant, false, TypeInscription.SEMESTRE);
							inscriptionPedagogiqueRepository.save(inscription_pedagogique);
							NoteElement note = new NoteElement(id_inscription_pedagogique, 0d, anneeSuivant);
							noteElementRepository.save(note);
						}
					}
				}

			}*/
		}
		deliberation.setDelibered(true);
		deliberationRepository.save(deliberation);

		return deliberation;

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

		isDeliberationEtapeAllowed(etape, annee);

		Deliberation deliberation = new Deliberation();
		deliberation.setEtape(etape);
		deliberation.setAnneeAcademique(annee);
		List<InscriptionPedagogique> inscriptions = inscriptionPedagogiqueRepository
				.getInscriptionPedagogiqueParEtape(etape, annee);
		inscriptions = filterInscription(inscriptions);
		for (InscriptionPedagogique inscription : inscriptions) {
			Double noteParEtapeD = 0d;
			for (Semestre semestre : etape.getSemestres()) {
				NoteSemestre noteSemestre = notesSemestreRepository
						.getOne(new ComposedNoteSemestre(semestre, inscription.getEtudiant(), annee));
				noteParEtapeD = noteParEtapeD + noteSemestre.getNote();
			}
			noteParEtapeD = noteParEtapeD / 2;
			NoteEtape noteEtape = new NoteEtape(new ComposedNoteEtape(etape, inscription.getEtudiant(), annee),
					noteParEtapeD, deliberation, annee);
			noteEtape.delibererEtape();
			deliberation.addNoteEtape(noteEtape);
			deliberationRepository.save(deliberation);
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

	private void isDeliberationModuleAllowed(Modulee module, AnneeAcademique annee)
			throws DeliberationModuleNotAllowed,DeliberationElementNotAllowed {
		List<Deliberation> dmodule = deliberationRepository.getDeliberationByModuleAnnneAcademique(module, annee);
		if (dmodule.size() > 0)
			throw new DeliberationModuleNotAllowed(null, "Le module est déja déliberer");
		for (Element element : module.getElements()) {
			List<Deliberation> delibs = deliberationRepository.getDeliberationByElementAnnneAcademique(element, annee);
			if (delibs.size() == 0) {
				throw new DeliberationElementNotAllowed(element, "Le element suivant n'est pas deliberer");
			} else {
				Deliberation del = delibs.get(0);
				if (!del.isDelibered())
					throw new DeliberationElementNotAllowed(element,
							"Le element suivant n est pas deliberer en rattrapage");
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

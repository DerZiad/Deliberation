package com.ziad.deliberation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ziad.models.AnneeAcademique;
import com.ziad.models.Element;
import com.ziad.models.Etape;
import com.ziad.models.Etudiant;
import com.ziad.models.InscriptionPedagogique;
import com.ziad.models.Modulee;
import com.ziad.models.Semestre;
import com.ziad.repositories.AnnneAcademiqueRepository;
import com.ziad.repositories.EtapeRepository;
import com.ziad.repositories.InscriptionPedagogiqueRepository;
import com.ziad.repositories.ModuleRepository;
import com.ziad.repositories.SemestreRepository;

@Service
public class Algorithme {
	@Autowired
	private InscriptionPedagogiqueRepository inscriptionPedagogiqueRepository;

	@Autowired
	private DeliberationRepository deliberationRepository;
	@Autowired
	private ModuleRepository moduleRepository;
	@Autowired
	private EtapeRepository etapeRepository;
	@Autowired
	private SemestreRepository semestreRepository;

	private String typeDelib = DeliberationType.ORDINAIRE.name();
	private Integer consideration = 1;

	public NotesModuleBean creerStructureDeModule(Modulee module, AnneeAcademique annee) {
		// Creation de strcutres de données
		NotesModuleBean notes = new NotesModuleBean(module);
		// Generation des inscriptions pédagogiques de ce module
		for (Element element : module.getElements()) {
			List<InscriptionPedagogique> listeInscriptionsPedagogiques = inscriptionPedagogiqueRepository
					.getInscriptionPedagogiquesByElementAndAnneeAcademique(element, annee);
			HashMap<Etudiant, ArrayList<InscriptionPedagogique>> structures = extractInscriptionPedagogiquesParEtudiant(
					listeInscriptionsPedagogiques);
			notes.addStructure(structures);
		}

		return notes;
	}

	private HashMap<Etudiant, ArrayList<InscriptionPedagogique>> extractInscriptionPedagogiquesParEtudiant(
			List<InscriptionPedagogique> listeInscriptions) {
		HashMap<Etudiant, ArrayList<InscriptionPedagogique>> structures = new HashMap<Etudiant, ArrayList<InscriptionPedagogique>>();
		for (InscriptionPedagogique inscription : listeInscriptions) {
			Etudiant etudiant = inscription.getEtudiant();
			structures.put(etudiant, new ArrayList<InscriptionPedagogique>());
			for (InscriptionPedagogique inscriptionPedagogique : listeInscriptions) {
				if (etudiant.equals(inscriptionPedagogique.getEtudiant())) {
					structures.get(etudiant).add(inscriptionPedagogique);
				}
			}
		}
		return structures;
	}

	public NotesSemestreBean creerStructureDeSemestre(Semestre semestre, AnneeAcademique annee) {
		NotesSemestreBean noteSemestre = new NotesSemestreBean(semestre);
		for (Modulee module : semestre.getModules()) {
			NotesModuleBean notesParModule = creerStructureDeModule(module, annee);
			noteSemestre.extractNotesModule(notesParModule);
		}
		return noteSemestre;
	}

	public void delibererModule(Modulee module, AnneeAcademique anneeAcademique) {
		List<InscriptionPedagogique> listesInscriptionsPedagogiques = null;
		DeliberationModel deliberation = new DeliberationModel(new ComposedKey(anneeAcademique, module.getId_module()));
		deliberation.setTypeDelibered(typeDelib);
		deliberation.setTypeDelibered(DeliberationType.MODULE.name());
		deliberationRepository.save(deliberation);
		/**
		 * Deliberations des elements
		 */
		for (Element element : module.getElements()) {
			listesInscriptionsPedagogiques = inscriptionPedagogiqueRepository
					.getInscriptionPedagogiquesByElementAndAnneeAcademique(element, anneeAcademique);
			listesInscriptionsPedagogiques
					.forEach(inscription -> inscription.delibererElement(typeDelib, consideration));
			inscriptionPedagogiqueRepository.saveAll(listesInscriptionsPedagogiques);
		}
	}

	public void delibererSemestre(Semestre semestre, AnneeAcademique anneeAcademique) {
		DeliberationModel deliberation = new DeliberationModel(
				new ComposedKey(anneeAcademique, semestre.getId_semestre()));
		deliberation.setTypeDelibered(typeDelib);
		deliberation.setTypeDelibered(DeliberationType.SEMESTRE.name());
		deliberationRepository.save(deliberation);
		for (Modulee module : semestre.getModules()) {
			delibererModule(module, anneeAcademique);
		}
	}

	public void delibererEtape(Etape etape, AnneeAcademique anneeAcademique) {
		DeliberationModel deliberation = new DeliberationModel(new ComposedKey(anneeAcademique, etape.getId_etape()));
		deliberation.setTypeDelibered(typeDelib);
		deliberation.setTypeDelibered(DeliberationType.ETAPE.name());
		deliberationRepository.save(deliberation);
		for (Semestre semestre : etape.getSemestres()) {
			delibererSemestre(semestre, anneeAcademique);
		}
	}

	public boolean isDelibered(AnneeAcademique annee, Long idElement) {
		try {
			deliberationRepository.getOne(new ComposedKey(annee, idElement));
			return true;
		} catch (EntityNotFoundException e) {
			return false;
		}
	}

	public boolean isDeliberedOrdinaire(AnneeAcademique annee, Long idElement) {
		try {
			DeliberationModel delib = deliberationRepository.getOne(new ComposedKey(annee, idElement));
			if (delib.getTypeDeliberation().equals(DeliberationType.ORDINAIRE.name())) {
				return true;
			}
		} catch (EntityNotFoundException e) {
		}
		return false;
	}

	public boolean isDeliberedRattrapage(AnneeAcademique annee, Long idElement) {
		try {
			DeliberationModel delib = deliberationRepository.getOne(new ComposedKey(annee, idElement));
			if (delib.getTypeDeliberation().equals(DeliberationType.RATTRAPAGE.name())) {
				return true;
			}
		} catch (EntityNotFoundException e) {
		}
		return false;
	}

	public void enableRattrapage() {
		typeDelib = "rattrapage";
	}

	public void enableOrdinaire() {
		typeDelib = DeliberationType.ORDINAIRE.name();
	}

	public void enableConsiderationDesNotes(boolean b) {
		consideration = b ? 1 : 0;
	}

}

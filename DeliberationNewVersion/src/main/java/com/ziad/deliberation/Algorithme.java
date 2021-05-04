package com.ziad.deliberation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ziad.models.AnneeAcademique;
import com.ziad.models.Element;
import com.ziad.models.Etape;
import com.ziad.models.Etudiant;
import com.ziad.models.InscriptionPedagogique;
import com.ziad.models.Modulee;
import com.ziad.models.Semestre;
import com.ziad.repositories.InscriptionPedagogiqueRepository;
@Service
public class Algorithme {
	@Autowired
	private InscriptionPedagogiqueRepository inscriptionPedagogiqueRepository;
	
	public NotesModuleBean creerStructureDeModule(Modulee module){
		//Creation de strcutres de données
		NotesModuleBean notes = new NotesModuleBean(module);
		// Generation des inscriptions pédagogiques de ce module
		for (Element element : module.getElements()) {
			List<InscriptionPedagogique> listeInscriptionsPedagogiques = inscriptionPedagogiqueRepository.getInscriptionsPedagogiqueByElement(element);
			HashMap<Etudiant, ArrayList<InscriptionPedagogique>> structures = extractInscriptionPedagogiquesParEtudiant(listeInscriptionsPedagogiques);
			notes.addStructure(structures);
		}
		
		return notes;
	}
	
	private HashMap<Etudiant, ArrayList<InscriptionPedagogique>> extractInscriptionPedagogiquesParEtudiant(List<InscriptionPedagogique> listeInscriptions){
		HashMap<Etudiant, ArrayList<InscriptionPedagogique>> structures = new HashMap<Etudiant, ArrayList<InscriptionPedagogique>>();
		for (InscriptionPedagogique inscription : listeInscriptions) {
			Etudiant etudiant = inscription.getEtudiant();
			structures.put(etudiant, new ArrayList<InscriptionPedagogique>());
			for (InscriptionPedagogique inscriptionPedagogique : listeInscriptions) {
				if(etudiant.equals(inscriptionPedagogique.getEtudiant())) {
					structures.get(etudiant).add(inscriptionPedagogique);
				}
			}
		}
		return structures;
	}
	
	
	
	public NotesSemestreBean creerStructureDeSemestre(Semestre semestre){
		NotesSemestreBean noteSemestre = new NotesSemestreBean(semestre);
		for (Modulee module : semestre.getModules()) {
			NotesModuleBean notesParModule = creerStructureDeModule(module);
			noteSemestre.extractNotesModule(notesParModule);
		}
		return noteSemestre;
	}
	
	public void delibererModule(Modulee module, AnneeAcademique anneeAcademique) {
		List<InscriptionPedagogique> listesInscriptionsPedagogiques = null;
		/**
		 * Deliberations des elements
		 * */
		for (Element element : module.getElements()) {
			listesInscriptionsPedagogiques = inscriptionPedagogiqueRepository
					.getInscriptionPedagogiquesByElementAndAnneeAcademique(element, anneeAcademique);
			listesInscriptionsPedagogiques.forEach(inscription -> inscription.delibererElement());
			inscriptionPedagogiqueRepository.saveAll(listesInscriptionsPedagogiques);
		}
	}

	public void delibererSemestre(Semestre semestre, AnneeAcademique anneeAcademique) {
		for (Modulee module : semestre.getModules()) {
			delibererModule(module, anneeAcademique);
		}
	}

	public void delibererEtape(Etape etape, AnneeAcademique anneeAcademique) {
		for (Semestre semestre : etape.getSemestres()) {
			delibererSemestre(semestre, anneeAcademique);
		}
	}

}

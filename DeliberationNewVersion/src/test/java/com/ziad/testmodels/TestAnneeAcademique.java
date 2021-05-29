package com.ziad.testmodels;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ziad.enums.DeliberationType;
import com.ziad.models.AnneeAcademique;
import com.ziad.models.Deliberation;
import com.ziad.models.Etape;
import com.ziad.models.Etudiant;
import com.ziad.models.InscriptionAdministrative;
import com.ziad.models.Modulee;
import com.ziad.models.NoteModule;
import com.ziad.models.Professeur;
import com.ziad.models.compositeid.ComposedNoteModule;
import com.ziad.repositories.AnnneAcademiqueRepository;
import com.ziad.repositories.DeliberationRepository;
import com.ziad.repositories.EtapeRepository;
import com.ziad.repositories.EtudiantRepository;
import com.ziad.repositories.FiliereRepository;
import com.ziad.repositories.InscriptionAdministrativeRepository;
import com.ziad.repositories.ModuleRepository;
import com.ziad.repositories.NotesModuleRepository;
import com.ziad.repositories.ProfesseurRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class TestAnneeAcademique {
	@Autowired
	private MockMvc mvc;

	@Autowired
	private FiliereRepository filiereRepository;
	
	@Autowired
	private DeliberationRepository deliberationRepository;
	
	@Autowired
	private EtudiantRepository etudiantRepository;

	@Autowired
	private ModuleRepository moduleRepository;

	@Autowired
	private AnnneAcademiqueRepository annee_academique_repository;

	@Autowired
	private NotesModuleRepository noteModuleRepository;

	@Autowired
	private AnnneAcademiqueRepository anneeAcademique;
	
	@Autowired
	private InscriptionAdministrativeRepository inscriptionAdministrativeRepo;

	@Test
	public void testCff() {

	}

	@Test
	public void test_annee_academique() throws Exception {
		List<Etape> etapes = filiereRepository.findAll().get(0).getEtapes();
		assertEquals(etapes.size(), 3);

	}

	@Test
	public void CreationNoteModule() {
		List<Etudiant> etudiants = etudiantRepository.findAll();
		List<Modulee> modules = moduleRepository.findAll();
		noteModuleRepository.deleteAll();
		AnneeAcademique annee = anneeAcademique.findAll().get(0);
		
		for (Modulee modulee : modules) {
			Deliberation deliberation = new Deliberation(DeliberationType.ORDINAIRE.name(), null, null, null, null);
			for (Etudiant etudiant : etudiants) {
				NoteModule note = new NoteModule(new ComposedNoteModule(modulee, etudiant), 18d, deliberation);
				deliberation.addNoteModule(note);
			}
			deliberationRepository.save(deliberation);
			
		}
	}
	
	@Test
	public void deleteInscriptionAdministrative() {
		List<InscriptionAdministrative> list = inscriptionAdministrativeRepo.findAll();
		
		InscriptionAdministrative ia = list.get(0);
		inscriptionAdministrativeRepo.deleteInscriptionAdministrative(ia.getFiliere(), ia.getEtudiant());
		List<InscriptionAdministrative> listes = inscriptionAdministrativeRepo.findAll();
		assertEquals(listes.size(), list.size() - 1);
		
	}
}

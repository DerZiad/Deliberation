package com.ziad;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ziad.enums.Gender;
import com.ziad.enums.MonRole;
import com.ziad.enums.TypeInscription;
import com.ziad.enums.TypeSemestre;
import com.ziad.models.AnneeAcademique;
import com.ziad.models.Element;
import com.ziad.models.Etablissement;
import com.ziad.models.Etape;
import com.ziad.models.Etudiant;
import com.ziad.models.Filiere;
import com.ziad.models.InscriptionAdministrative;
import com.ziad.models.InscriptionEnLigne;
import com.ziad.models.InscriptionPedagogique;
import com.ziad.models.Modulee;
import com.ziad.models.NoteElement;
import com.ziad.models.Professeur;
import com.ziad.models.Semestre;
import com.ziad.models.User;
import com.ziad.models.compositeid.ComposedInscriptionAdministrative;
import com.ziad.models.compositeid.ComposedInscriptionPedagogique;
import com.ziad.repositories.AnnneAcademiqueRepository;
import com.ziad.repositories.DocumentDePlusRepository;
import com.ziad.repositories.ElementRepository;
import com.ziad.repositories.EtablissementRepository;
import com.ziad.repositories.EtapeRepository;
import com.ziad.repositories.EtudiantRepository;
import com.ziad.repositories.FiliereRepository;
import com.ziad.repositories.InscriptionAdministrativeRepository;
import com.ziad.repositories.InscriptionEnLigneRepository;
import com.ziad.repositories.InscriptionPedagogiqueRepository;
import com.ziad.repositories.ModuleRepository;
import com.ziad.repositories.NoteElementRepository;
import com.ziad.repositories.ProfesseurRepository;
import com.ziad.repositories.SemestreRepository;
import com.ziad.repositories.UserRepository;

@Service
public class IntialiserBachelor {
	@Autowired
	private AnnneAcademiqueRepository annee_academique;
	@Autowired
	private DocumentDePlusRepository documentDePlusRepository;
	@Autowired
	private ElementRepository elementRepository;
	@Autowired
	private EtablissementRepository etablissementRepository;
	@Autowired
	private EtapeRepository etapeRepository;
	@Autowired
	private EtudiantRepository etudiantRepository;
	@Autowired
	private FiliereRepository filiereRepository;
	@Autowired
	private InscriptionAdministrativeRepository inscriptionAdministrativeRepository;
	@Autowired
	private InscriptionEnLigneRepository inscriptionEnLigneRepository;
	@Autowired
	private InscriptionPedagogiqueRepository inscriptionPedagogiqueRepository;
	@Autowired
	private ModuleRepository moduleRepository;
	@Autowired
	private NoteElementRepository noteElementRepository;
	@Autowired
	private ProfesseurRepository professeurRepository;
	@Autowired
	private SemestreRepository semestreRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public IntialiserBachelor() {

	}

	public void inistializeSchool() {
		try {
			List<User> users = createStructureScolaire();
			User admin = createAdministrator();
			System.out.println("===========>Le mot de passe principale des utilisateurs de tests est test123");
			System.out.println("============> Administrateur");
			System.out.println(admin);
			System.out.println("============> Professeurs ");
			users.stream().forEach(user -> System.out.println(user));
			createAnneeAcademique();
			createStudents();
		} catch (Exception e) {
			System.err.println("[ - ] - Error \n" + e.getMessage());
			e.printStackTrace();
			clearDatabase();
		}

	}

	public void clearDatabase() {
		annee_academique.deleteAll();
		documentDePlusRepository.deleteAll();
		elementRepository.deleteAll();
		etablissementRepository.deleteAll();
		etapeRepository.deleteAll();
		etudiantRepository.deleteAll();
		filiereRepository.deleteAll();
		inscriptionAdministrativeRepository.deleteAll();
		inscriptionEnLigneRepository.deleteAll();
		inscriptionPedagogiqueRepository.deleteAll();
		moduleRepository.deleteAll();
		noteElementRepository.deleteAll();
		professeurRepository.deleteAll();
		semestreRepository.deleteAll();
		userRepository.deleteAll();
	}

	private List<User> createStructureScolaire() throws Exception {
		System.out.println("[ + ] - Creating university structure");
		Etablissement etablissement = new Etablissement("Bachelor");

		Professeur bekri = new Professeur("Ali", "Bekri", "ali.bekri@gmail.com",
				new User("bekri", passwordEncoder.encode("test123"), ""));
		bekri.getUser().addRole(MonRole.ROLERESPONSABLEFILIERE);
		bekri.getUser().addRole(MonRole.ROLEPROFESSEUR);
		Filiere filiere = new Filiere("Informatique", etablissement, bekri);
		Filiere filiere1 = new Filiere("Chimie", etablissement, bekri);
		etablissement.addFiliere(filiere);
		etablissement.addFiliere(filiere1);

		int semester_number = 5;
		int years = (int) (semester_number / 2) + semester_number % 2;
		int ordre = 0;
		for (int i = 1; i <= years; i++) {
			Etape etape = new Etape((i == 1) ? "1 ère Année" : i + " ème Année", false, 10d, filiere);
			Semestre semestre1 = new Semestre(10d, "Semestre " + ++ordre, etape, TypeSemestre.HIVER, ordre);
			Semestre semestre2 = new Semestre(10d, "Semestre " + ++ordre, etape, TypeSemestre.PRINTEMPS, ordre);
			etape.addSemestre(semestre1);
			etape.addSemestre(semestre2);
			filiere.addEtape(etape);
		}
		
		
		semester_number = 6;
		years = (int) (semester_number / 2) + semester_number % 2;
		ordre = 0;
		for (int i = 1; i <= years; i++) {
			Etape etape = new Etape((i == 1) ? "1 ère Année" : i + " ème Année", false, 10d, filiere1);
			Semestre semestre1 = new Semestre(10d, "Semestre " + ++ordre, etape, TypeSemestre.HIVER, ordre);
			Semestre semestre2 = new Semestre(10d, "Semestre " + ++ordre, etape, TypeSemestre.PRINTEMPS, ordre);
			etape.addSemestre(semestre1);
			etape.addSemestre(semestre2);
			filiere1.addEtape(etape);
		}

		Professeur oubelkacem = new Professeur("Ali", "Oubelkacem", "ali.oubelkacem@gmail.com",
				new User("oubelkacem", passwordEncoder.encode("test123"), ""));
		oubelkacem.getUser().addRole(MonRole.ROLERESPONSABLEMODULE);
		oubelkacem.getUser().addRole(MonRole.ROLEPROFESSEUR);
		for (Etape etape : filiere.getEtapes()) {
			for (Semestre semestre : etape.getSemestres()) {
				for (int i = 0; i < 5; i++) {
					Modulee module = new Modulee("Algebre" + i, 1d, 10d, 4d, false, semestre, oubelkacem);
					Element element = new Element("Algebre" + i, 1d, 10d, module);
					element.addProfesseur(oubelkacem);
					module.addElement(element);
					semestre.addModule(module);

				}
			}
		}

		Professeur benhlima = new Professeur("Said", "Benhlima", "said.benhlima@gmail.com",
				new User("benhlima", passwordEncoder.encode("test123"), ""));
		benhlima.getUser().addRole(MonRole.ROLEPROFESSEUR);
		benhlima.getUser().addRole(MonRole.ROLERESPONSABLEMODULE);
		for (Etape etape : filiere1.getEtapes()) {
			for (Semestre semestre : etape.getSemestres()) {
				for (int i = 0; i < 5; i++) {
					Modulee module = new Modulee("Chimie organique", 1d, 10d, 4d, false, semestre, benhlima);
					Element element = new Element("Chimie organique", 1d, 10d, module);
					element.addProfesseur(benhlima);
					module.addElement(element);
					semestre.addModule(module);
					benhlima.addModule(module);

				}
			}
		}		
		etablissementRepository.save(etablissement);
		System.out.println("[ + ] - University structure created succesfully");
		List<User> users = Arrays.asList(benhlima.getUser(), bekri.getUser(), oubelkacem.getUser());
		
		return users;
	}

	private void createAnneeAcademique() throws Exception {
		System.out.println("[ + ] - Creating academic years succesfully");
		ArrayList<AnneeAcademique> listes = new ArrayList<AnneeAcademique>();
		for (int i = 2010; i <= 2030; i++) {
			listes.add(new AnneeAcademique(i));
		}
		annee_academique.saveAll(listes);
		System.out.println("[ + ] - Created academic year succesfully");
	}

	private User createAdministrator() throws Exception {
		System.out.println("[ + ] - Creating administrator");
		User user = new User("admin", passwordEncoder.encode("test123"), MonRole.ROLEADMIN.getRole());
		userRepository.save(user);
		System.out.println("[ + ] - Administrator created successfully");
		return user;

	}

	public boolean isEmpty() {
		System.out.println("[ ! ] - Checking data base");
		boolean test = etablissementRepository.findAll().size() > 0;
		test = test && filiereRepository.findAll().size() > 0;
		test = test && etapeRepository.findAll().size() > 0;
		test = test && semestreRepository.findAll().size() > 0;
		test = test && moduleRepository.findAll().size() > 0;
		test = test && elementRepository.findAll().size() > 0;
		test = test && professeurRepository.findAll().size() >= 3;
		test = test && userRepository.findAll().size() >= 4;
		if (test)
			System.out.println("[ ! ] - Data base not empty");
		return !test;
	}
	
	public void createStudents() {
		
		List<Filiere> filieres  = filiereRepository.findAll();
		Filiere filiere = filieres.get(0);
		Filiere chimie = filieres.get(1);
		
		for (int i = 0; i < 5; i++) {
			Etudiant etudiant = new Etudiant();
			InscriptionEnLigne inscriptionEnLigne = new InscriptionEnLigne();
			etudiant.setAcademy("Bachelor");
			etudiant.setBac_place("Meknes");
			etudiant.setBac_type("PC"+i);
			etudiant.setBac_year(2018);
			etudiant.setBirth_date(new java.util.Date());
			etudiant.setBirth_place("Meknes");
			etudiant.setCity("Meknes");
			etudiant.setCne("D896565"+i);
			etudiant.setFirst_name_ar("Ziad"+i);
			etudiant.setFirst_name_fr("Bougrine"+i);
			etudiant.setGender(Gender.HOMME);
			etudiant.setHigh_school("Tremplin");
			etudiant.setLast_name_ar("Bougrine");
			etudiant.setLast_name_fr("Ziad");
			etudiant.setMassar_edu("M13124589"+i);
			etudiant.setMention("Tres bien");
			etudiant.setProvince("Kamilia");
			etudiant.setRegistration_date(new java.util.Date());
			etudiant.setEmail("ziadbougrine@gmail.com");

			User user = new User();
			user.setUsername(etudiant.getEmail());
			// On met le mot de passe son prenom pour le changer apres
			user.setPassword(passwordEncoder.encode(etudiant.getLast_name_fr().toLowerCase()));
			user.setActive(1);
			user.addRole(MonRole.ROLEETUDIANT);
			etudiant.setUser(user);
			etudiant.setInscription_en_ligne(new InscriptionEnLigne());
			inscriptionEnLigne.setEtudiant(etudiant);
			etudiant.setInscription_en_ligne(inscriptionEnLigne);
			etudiantRepository.save(etudiant);
			inscriptionEnLigneRepository.save(inscriptionEnLigne);

			InscriptionAdministrative inscription_administrative = new InscriptionAdministrative();

			AnneeAcademique annee_academique = this.annee_academique.findAll().get(0);
			inscription_administrative.setAnnee_academique(annee_academique);
			inscription_administrative.setDate_pre_inscription(new java.util.Date());
			inscription_administrative.setComposite_association_id(new ComposedInscriptionAdministrative(etudiant, filiere));

			List<Etape> etapes = etapeRepository.getEtapeByFiliere(filiere);
			Etape firststep = etapes.get(0);	
			
			for (Semestre semestre : semestreRepository.getSemestreByEtape(firststep)) {
				for (Modulee module : moduleRepository.getModuleBySemestre(semestre)) {
					for (Element element : elementRepository.getElementsByModule(module)) {
						ComposedInscriptionPedagogique compsedId = new ComposedInscriptionPedagogique(etudiant, element);
						InscriptionPedagogique inscription_pedagogique = new InscriptionPedagogique(compsedId,
								annee_academique, false, TypeInscription.ELEMENT);
						this.inscriptionPedagogiqueRepository.save(inscription_pedagogique);
						NoteElement note = new NoteElement(compsedId, 0d,
								inscription_administrative.getAnnee_academique());
						noteElementRepository.save(note);
					}
				}
			}
			inscriptionAdministrativeRepository.save(inscription_administrative);

		}
		
		for (int i = 0; i < 5; i++) {
			Etudiant etudiant = new Etudiant();
			InscriptionEnLigne inscriptionEnLigne = new InscriptionEnLigne();
			etudiant.setAcademy("Bachelor");
			etudiant.setBac_place("Meknes");
			etudiant.setBac_type("PC"+i);
			etudiant.setBac_year(2018);
			etudiant.setBirth_date(new java.util.Date());
			etudiant.setBirth_place("Meknes");
			etudiant.setCity("Meknes");
			etudiant.setCne("D896565"+i);
			etudiant.setFirst_name_ar("Ayman"+i);
			etudiant.setFirst_name_fr("Ayman"+i);
			etudiant.setGender(Gender.HOMME);
			etudiant.setHigh_school("Tremplin");
			etudiant.setLast_name_ar("Anouhali"+i);
			etudiant.setLast_name_fr("Abouhali"+i);
			etudiant.setMassar_edu("M13124589"+i);
			etudiant.setMention("Tres bien");
			etudiant.setProvince("Kamilia");
			etudiant.setRegistration_date(new java.util.Date());
			etudiant.setEmail("ziadbougrine@gmail.com");

			User user = new User();
			user.setUsername(etudiant.getEmail());
			// On met le mot de passe son prenom pour le changer apres
			user.setPassword(passwordEncoder.encode(etudiant.getLast_name_fr().toLowerCase()));
			user.setActive(1);
			user.addRole(MonRole.ROLEETUDIANT);
			etudiant.setUser(user);
			etudiant.setInscription_en_ligne(new InscriptionEnLigne());
			inscriptionEnLigne.setEtudiant(etudiant);
			etudiant.setInscription_en_ligne(inscriptionEnLigne);
			etudiantRepository.save(etudiant);
			inscriptionEnLigneRepository.save(inscriptionEnLigne);

			InscriptionAdministrative inscription_administrative = new InscriptionAdministrative();

			AnneeAcademique annee_academique = this.annee_academique.findAll().get(0);
			inscription_administrative.setAnnee_academique(annee_academique);
			inscription_administrative.setDate_pre_inscription(new java.util.Date());
			inscription_administrative.setComposite_association_id(new ComposedInscriptionAdministrative(etudiant, chimie));

			List<Etape> etapes = etapeRepository.getEtapeByFiliere(chimie);
			Etape firststep = etapes.get(0);	
			
			for (Semestre semestre : semestreRepository.getSemestreByEtape(firststep)) {
				for (Modulee module : moduleRepository.getModuleBySemestre(semestre)) {
					for (Element element : elementRepository.getElementsByModule(module)) {
						ComposedInscriptionPedagogique compsedId = new ComposedInscriptionPedagogique(etudiant, element);
						InscriptionPedagogique inscription_pedagogique = new InscriptionPedagogique(compsedId,
								annee_academique, false, TypeInscription.ELEMENT);
						this.inscriptionPedagogiqueRepository.save(inscription_pedagogique);
						NoteElement note = new NoteElement(compsedId, 0d,
								inscription_administrative.getAnnee_academique());
						noteElementRepository.save(note);
					}
				}
			}
			inscriptionAdministrativeRepository.save(inscription_administrative);

		}
		
		
		
	}
}

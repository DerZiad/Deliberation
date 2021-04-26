package com.ziad;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ziad.models.AnneeAcademique;
import com.ziad.models.Etudiant;
import com.ziad.models.InscriptionAdministrative;
import com.ziad.models.InscriptionEnLigne;
import com.ziad.models.Modulee;
import com.ziad.models.Professeur;
import com.ziad.models.User;
import com.ziad.repositories.AnnneAcademiqueRepository;
import com.ziad.repositories.EtudiantRepository;
import com.ziad.repositories.ModuleRepository;
import com.ziad.repositories.ProfesseurRepository;
import com.ziad.repositories.UserRepository;
import com.ziad.security.authentification.enums.MonRole;

@SpringBootApplication
public class DeliberationNewVersionApplication implements CommandLineRunner {
	@Autowired
	private UserRepository user_repository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private EtudiantRepository etudiant_repository;
	@Autowired
	private ProfesseurRepository professeur_repository;
	@Autowired
	private ModuleRepository modulee_repo;
	@Autowired
	private AnnneAcademiqueRepository annee_academique_repo;
	private String principal_password = "test123";

	public static void main(String[] args) {
		SpringApplication.run(DeliberationNewVersionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/**
		 * 
		 * Creation des utilisateurs de test
		 * 
		 */
		List<User> users_test_principal = user_repository.findAll();
		if (users_test_principal.size() < 5) {
			System.out.println("[ + ] - Création des utilisateurs");
			User admin = new User("admin", passwordEncoder.encode(principal_password), MonRole.ROLEADMIN.getRole());
			User professeur = new User("benhlima", passwordEncoder.encode(principal_password),
					MonRole.ROLEPROFESSEUR.getRole());
			User responsable_filiere = new User("bekri", passwordEncoder.encode(principal_password),
					MonRole.ROLERESPONSABLEFILIERE.getRole());
			User responsable_module = new User("bouchakor", passwordEncoder.encode(principal_password),
					MonRole.ROLERESPONSABLEMODULE.getRole());
			User etudiant = new User("ziad", passwordEncoder.encode(principal_password),
					MonRole.ROLEETUDIANT.getRole());
			List<User> users = Arrays.asList(admin, responsable_filiere, responsable_module, etudiant,professeur);
			user_repository.saveAll(users);
			System.out.println("Le mot de passe principale est " + principal_password);
			System.out.println("============> Administrateur \n" + admin);
			System.out.println("============> Professeur \n" + professeur);
			System.out.println("============> Responsable de module \n" + responsable_module);
			System.out.println("============> Responsable de filiere \n" + responsable_filiere);
			System.out.println("============> Etudiant \n" + etudiant);
			

			
			
			/**
			 * Test
			 * 
			 * 
			 * *//*
			ArrayList<Modulee> listes = new ArrayList<Modulee>();
			listes.add(new Modulee("Algebre", null, null, null, false, null, null, null));
			listes.add(new Modulee("Algebre", null, null, null, false, null, null, null));
			listes.add(new Modulee("Algebre", null, null, null, false, null, null, null));
			listes.add(new Modulee("Algebre", null, null, null, false, null, null, null));
			prff.setModules(listes);
			professeur_repository.save(prff);
			*/
			
		} else {
			System.out.println("[ + ] - Les utilisateurs ont été deja crée");
		}
		
		/*
		InscriptionEnLigne ie = new InscriptionEnLigne();
		User user = new User();
		user.setUsername("ziad");
		user.setPassword("sdsdsd");
		Etudiant etudiant = new Etudiant();
		etudiant.setFirst_name_fr("ziad");
		etudiant.setUser(user);
		etudiant.setMassar_edu("M13566556");
		etudiant.setInscription_en_ligne(ie);
		//ie.setEtudiant(etudiant);
		
		etudiant_repository.save(etudiant);*/
		AnneeAcademique anneeAcademique = new AnneeAcademique(2019);
		annee_academique_repo.save(anneeAcademique);
		Etudiant etudiant = new Etudiant(principal_password, principal_password, principal_password, principal_password, principal_password, principal_password, principal_password, null, null, principal_password, principal_password, principal_password, null, principal_password, principal_password, principal_password, principal_password, principal_password, null, principal_password, null, null);
		etudiant_repository.save(etudiant);
	}


}

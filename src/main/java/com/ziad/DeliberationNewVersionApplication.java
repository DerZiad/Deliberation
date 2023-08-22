package com.ziad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ziad.enums.MonRole;
import com.ziad.models.AnneeAcademique;
import com.ziad.models.User;
import com.ziad.repositories.AnnneAcademiqueRepository;
import com.ziad.repositories.UserRepository;

@SpringBootApplication
public class DeliberationNewVersionApplication implements CommandLineRunner {

	@Autowired
	private ServerConfiguration server;

	public static String SERVER_LINK;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CountryInitialiser countryInitializer;
	
	@Autowired
	private  AnnneAcademiqueRepository anneeAcademiqueRepository;

	public static void main(String[] args) {
		SpringApplication.run(DeliberationNewVersionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(anneeAcademiqueRepository.findAll().size() == 0) {
			for (int i = 2010; i < 2050; i++) {
				anneeAcademiqueRepository.save(new AnneeAcademique(i));
			}
		}
		
		
		if (userRepository.findAll().size() == 0) {
			User user = new User("admin", passwordEncoder.encode("test123"), "");
			user.addRole(MonRole.ROLEADMIN);
			userRepository.save(user);
		}
		SERVER_LINK = server.getApiUrl();

		if (countryInitializer.isEmpty())
			countryInitializer.remplirBaseDonnee();
		System.out.println("Server Link =======+> " + SERVER_LINK);
	}

}

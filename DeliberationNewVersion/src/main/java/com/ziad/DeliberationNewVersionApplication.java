package com.ziad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DeliberationNewVersionApplication implements CommandLineRunner {
	@Autowired
	private IntialiserBachelor initializer;
	
	@Autowired
	private ServerConfiguration server;
	
	public static String SERVER_LINK;
	
	@Autowired
	private CountryInitialiser countryInitializer;
	
	public static void main(String[] args) {
		SpringApplication.run(DeliberationNewVersionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (initializer.isEmpty()) {
			initializer.inistializeSchool();
		}
			
		SERVER_LINK = server.getApiUrl();
		
		if(countryInitializer.isEmpty())
			countryInitializer.remplirBaseDonnee();
		System.out.println("Server Link =======+> " + SERVER_LINK);
	}
	

}

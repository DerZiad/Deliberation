package com.ziad;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ziad.enums.TypeNote;
import com.ziad.models.Element;
import com.ziad.models.Etudiant;
import com.ziad.models.Note;
import com.ziad.models.NoteElement;
import com.ziad.models.compositeid.ComposedInscriptionPedagogique;
import com.ziad.repositories.ElementRepository;
import com.ziad.repositories.EtudiantRepository;
import com.ziad.repositories.NoteElementRepository;
import com.ziad.repositories.NoteRepository;

@SpringBootApplication
public class DeliberationNewVersionApplication implements CommandLineRunner {
	@Autowired
	private IntialiserBachelor initializer;
	
	@Autowired
	private NoteElementRepository noteElementRepository;
		
	@Autowired
	private ElementRepository elementRepository;
	
	@Autowired
	private EtudiantRepository etudiantRepository;
	
	@Autowired
	private NoteRepository noteRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(DeliberationNewVersionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (initializer.isEmpty()) {
			initializer.inistializeSchool();
		}
	}
	

}

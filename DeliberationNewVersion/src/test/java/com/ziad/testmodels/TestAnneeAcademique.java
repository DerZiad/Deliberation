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

import com.ziad.models.AnneeAcademique;
import com.ziad.models.InscriptionAdministrative;
import com.ziad.models.Modulee;
import com.ziad.models.Professeur;
import com.ziad.repositories.AnnneAcademiqueRepository;
import com.ziad.repositories.ProfesseurRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
  locations = "classpath:application.properties")
public class TestAnneeAcademique {
	@Autowired
    private MockMvc mvc;
	
	@Autowired
	private ProfesseurRepository professeur_repository;
    
	@Autowired
    private AnnneAcademiqueRepository annee_academique_repository;

    
    @Test
    public void testCff() {
    	
    }
    
	@Test
	public void test_annee_academique() throws Exception {
		AnneeAcademique anneacedemique = new AnneeAcademique(2021);
		annee_academique_repository.save(anneacedemique);
		/*AnneeAcademique aneeo = annee_academique_repository.getOne(1l);
		//annee_academique_repository.delete(anneacedemique);
		assertEquals(2021, aneeo.getAnnee_academique());*/
		
	}
}

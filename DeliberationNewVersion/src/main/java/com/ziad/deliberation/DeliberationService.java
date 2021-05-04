package com.ziad.deliberation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.AnneeAcademique;
import com.ziad.models.Filiere;
import com.ziad.models.Semestre;
import com.ziad.repositories.AnnneAcademiqueRepository;
import com.ziad.repositories.ElementRepository;
import com.ziad.repositories.FiliereRepository;
import com.ziad.repositories.ModuleRepository;
import com.ziad.repositories.NoteElementRepository;
import com.ziad.repositories.SemestreRepository;
@Service
@Primary
public class DeliberationService implements DeliberationInterface{

	@Autowired
	private NoteElementRepository noteElementRepository;
	
	@Autowired
	private ElementRepository elementRepository;
	
	@Autowired
	private ModuleRepository moduleRepository;
	
	@Autowired
	private SemestreRepository semestreRepository;
	
	@Autowired
	private FiliereRepository filiereRepository;
	
	@Autowired
	private AnnneAcademiqueRepository anneeAcademiqueRepository;
	
	@Autowired
	private Algorithme algorithme;

	@Override
	public ArrayList<Object> getPageBesoin()
			throws DataNotFoundExceptions, EntityNotFoundException {
		List<Filiere> filieres = filiereRepository.findAll();
		List<AnneeAcademique> anneesAcademiques = anneeAcademiqueRepository.findAll();
		List<Semestre> semestres = semestreRepository.findAll();
		
		ArrayList<Object> besoins = new ArrayList<Object>();
		besoins.add(filieres);
		besoins.add(anneesAcademiques);
		besoins.add(semestres);
		return besoins;
	}

	@Override
	public void deliberer(Long idFilire, Long idAnneeAcademique, HttpServletRequest request)
			throws DataNotFoundExceptions, EntityNotFoundException {
		// TODO Auto-generated method stub
		
	}
	
	
	
}

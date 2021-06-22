package com.ziad.service.professeurespace.responsable.filiere;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.Etape;
import com.ziad.models.Filiere;
import com.ziad.models.Modulee;
import com.ziad.models.Professeur;
import com.ziad.models.Semestre;
import com.ziad.models.User;
import com.ziad.repositories.ModuleRepository;
import com.ziad.repositories.ProfesseurRepository;
import com.ziad.repositories.UserRepository;
import com.ziad.utilities.JSONConverter;

@Service
@Primary
public class EditFiliereService implements EditFiliereInterface {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProfesseurRepository professeurRepository;
	@Autowired
	private JSONConverter Converter;
	
	@Autowired
	private ModuleRepository moduleRepository;
	
	@Override
	public ArrayList<Object> loadListModuleByFiliere() throws DataNotFoundExceptions {
		Authentication authentifiaction = SecurityContextHolder.getContext().getAuthentication();
		String username = authentifiaction.getName();
		User user = userRepository.getUserByUsername(username);

		Professeur professeur = professeurRepository.getProfesseurByUser(user);

		ArrayList<Object> besoins = new ArrayList<Object>();

		/*
		 * Dumping liste module de chaque filiere
		 **/
		List<Modulee> modules = new ArrayList<Modulee>();

		getModuleByFilieres(professeur, modules);

		if (professeur.getFilieres().size() == 0)
			throw new DataNotFoundExceptions("La liste des filieres est vide");

		if (modules.size() == 0)
			throw new DataNotFoundExceptions("La liste des modules est vide");

		besoins.add(professeur.getFilieres());
		besoins.add(Converter.convertModulee(modules));
		return besoins;
	}
	
	

	private void getModuleByFilieres(Professeur professeur, List<Modulee> modules) {
		for (Filiere filiere : professeur.getFilieres()) {
			for (Etape etape : filiere.getEtapes()) {
				for (Semestre semestre : etape.getSemestres()) {
					modules.addAll(semestre.getModules());
				}
			}
		}
	}



	@Override
	public Modulee getModuleById(Long idModule) throws EntityNotFoundException {
		return moduleRepository.getOne(idModule);
	}



	@Override
	public void updateModule(Long idModule, Double coefficient) throws EntityNotFoundException {
		Modulee module = moduleRepository.getOne(idModule);
		module.setCoeficient(coefficient);
		moduleRepository.save(module);
	}

}

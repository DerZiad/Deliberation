package com.ziad.professeurespace.responsable.module;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.Element;
import com.ziad.models.Modulee;
import com.ziad.models.Professeur;
import com.ziad.models.User;
import com.ziad.repositories.ElementRepository;
import com.ziad.repositories.ProfesseurRepository;
import com.ziad.repositories.UserRepository;

@Service
@Primary
public class EditModuleService implements EditModuleInterface {
	@Autowired
	private ElementRepository elementRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProfesseurRepository professeurRespository;

	@Override
	public void saveInformations(Long idElement, Double validation, Double coeficient) throws EntityNotFoundException {
		Element element = elementRepository.getOne(idElement);
		element.setCoeficient(coeficient);
		element.setValidation(validation);
		elementRepository.save(element);
	}

	@Override
	public Element getElement(Long idElement) throws EntityNotFoundException {
		return elementRepository.getOne(idElement);
	}

	@Override
	public List<Object> getModulesElementsProfesseur() throws DataNotFoundExceptions {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		User user = userRepository.getUserByUsername(username);
		Professeur professeur = professeurRespository.getProfesseurByUser(user);
		if (professeur.getModules().size() == 0)
			throw new DataNotFoundExceptions("La liste des modules est vides");
		List<Element> elements = new ArrayList<Element>();
		for (Modulee module : professeur.getModules()) {
			elements.addAll(module.getElements());
		}
		if (elements.size() == 0)
			throw new DataNotFoundExceptions("La liste des elements des modules est vide");
		List<Object> besoins = new ArrayList<Object>();
		besoins.add(professeur.getModules());
		besoins.add(elements);
		return besoins;
	}

}

package com.ziad.administrateur.professeur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ziad.administrateur.etablissement.DataNotFoundExceptions;
import com.ziad.enums.Role;
import com.ziad.models.Element;
import com.ziad.models.Filiere;
import com.ziad.models.Historique;
import com.ziad.models.Modulee;
import com.ziad.models.Professeur;
import com.ziad.models.User;
import com.ziad.repositories.ElementRepository;
import com.ziad.repositories.FiliereRepository;
import com.ziad.repositories.HistoriqueRepository;
import com.ziad.repositories.ProfesseurRepository;

@Service
@Primary
public class ProfesseurService implements ProfesseurImplementation {
	@Autowired
	private ProfesseurRepository professeurRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private HistoriqueRepository historiqueRepository;
	@Autowired
	private FiliereRepository filiereRepository;
	@Autowired
	private ElementRepository elementRepository;

	@Override
	public void createProfesseur(String first_name, String last_name, String email, Integer module, Integer filiere) {
		User user = new User();
		user.setActive(1);
		user.setUsername(last_name.toLowerCase());
		user.setPassword(passwordEncoder.encode(last_name.toLowerCase()));
		Professeur professeur = new Professeur(first_name, last_name, email, user, new ArrayList<Modulee>(),
				new ArrayList<Element>(), new ArrayList<Filiere>());
		if (filiere != null) {
			user.addRole(Role.RESPONSABLE_FILIERE);
		}

		if (module != null) {
			user.addRole(Role.RESPONSABLE_MODULE);
		}
		user.addRole(Role.PROFESSEUR);
		professeurRepository.save(professeur);
		historiqueRepository
				.save(new Historique("Professeur " + first_name + " " + last_name + " créé", new java.util.Date()));
	}

	@Override
	public void modifierProfesseur(Long id_professeur, String last_name, String first_name, String email,
			Integer filiere, Integer module) throws EntityNotFoundException {
		Professeur professeur = professeurRepository.getOne(id_professeur);
		professeur.setPrenom_professeur(first_name);
		professeur.setNom_professeur(last_name);
		professeur.setEmail_professeur(email);

		User user = professeur.getUser();
		user.setRoles("");
		if (filiere != null) {
			user.addRole(Role.RESPONSABLE_FILIERE);
		}

		if (module != null) {
			user.addRole(Role.RESPONSABLE_MODULE);
		}
		user.addRole(Role.PROFESSEUR);
		historiqueRepository
				.save(new Historique("Professeur " + first_name + " " + last_name + " modifié", new java.util.Date()));
		professeurRepository.save(professeur);
	}

	@Override
	public List<Professeur> listerProfesseurs() throws DataNotFoundExceptions {
		List<Professeur> professeurs = professeurRepository.findAll();
		if (professeurs == null || professeurs.size() == 0)
			throw new DataNotFoundExceptions("La liste des professeurs est vide");
		return professeurs;
	}

	@Override
	public Professeur getProfesseur(Long id) throws EntityNotFoundException {
		return professeurRepository.getOne(id);
	}

	@Override
	public void deleteProfesseur(Long id) throws EntityNotFoundException {
		Professeur professeur = professeurRepository.getOne(id);
		historiqueRepository.save(new Historique(
				"Professeur " + professeur.getPrenom_professeur() + " " + professeur.getNom_professeur() + " supprimé",
				new java.util.Date()));
		professeurRepository.delete(professeur);
	}

	@Override
	public void renitialiserPassword(Long id) throws EntityNotFoundException {
		Professeur professeur = professeurRepository.getOne(id);
		professeur.getUser().setPassword(passwordEncoder.encode(professeur.getNom_professeur().toLowerCase()));
	}

	@Override
	public List<Element> listerElementsByProfesseur(Long id) throws EntityNotFoundException, DataNotFoundExceptions {
		Professeur professeur = professeurRepository.getOne(id);
		List<Element> elements = professeur.getElements();
		if (elements.size() == 0)
			throw new DataNotFoundExceptions("La liste des élements du professeur " + professeur.getNom_professeur()
					+ " " + professeur.getPrenom_professeur() + " est vide");
		return elements;
	}

	@Override
	public void suprimerElement(Long id_professeur, Long id_element) throws EntityNotFoundException {
		Professeur professeur = professeurRepository.getOne(id_professeur);
		Element element = elementRepository.getOne(id_element);

		professeur.getElements().remove(element);
		element.getProfesseurs().remove(professeur);

		professeurRepository.save(professeur);
		elementRepository.save(element);

	}

	@Override
	public void ajouterElement(Long id_professeur, Long id_element) throws EntityNotFoundException {
		Professeur professeur = professeurRepository.getOne(id_professeur);
		Element element = elementRepository.getOne(id_element);

		professeur.addElement(element);
		element.addProfesseur(professeur);

		professeurRepository.save(professeur);
		elementRepository.save(element);
	}
	

	@Override
	public HashMap<String,Object> listerFilieresElements() throws DataNotFoundExceptions{
		List<Filiere> filieres = filiereRepository.findAll();
		List<Element> elements = elementRepository.findAll();
		if(filieres.size() == 0) throw new DataNotFoundExceptions("La liste des filieres est vide");
		if(elements.size() == 0) throw new DataNotFoundExceptions("La liste des elements est vide");
		HashMap<String,Object> composantes = new HashMap<String,Object>();
		composantes.put(ATTRIBUT_FILIERES,filieres);
		composantes.put(ATTRIBUT_ELEMENTS, elements);
		return composantes;
	}

	@Override
	public HashMap<String, Object> filterElement(Long id_filiere) {
		
		return null;
	}

}

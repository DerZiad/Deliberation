package com.ziad.administrateur.etablissement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ziad.models.Element;
import com.ziad.models.Etablissement;
import com.ziad.models.Etape;
import com.ziad.models.Etudiant;
import com.ziad.models.Filiere;
import com.ziad.models.Historique;
import com.ziad.models.Modulee;
import com.ziad.models.Professeur;
import com.ziad.models.Semestre;
import com.ziad.repositories.EtablissementRepository;
import com.ziad.repositories.HistoriqueRepository;

@Primary
@Configuration
@EnableAutoConfiguration
@ComponentScan({ "com.ziad.repositories", "com.ziad.models" })
@EntityScan(basePackages = { "com.ziad.models" })
@EnableJpaRepositories(basePackages = { "com.ziad.repositories" })
@EnableTransactionManagement
@Service
public class EtablissementService implements EtablissementInterface {
	@Autowired
	private EtablissementRepository etablissementRepository;
	@Autowired
	private HistoriqueRepository historiqueRepository;

	public void createEtablissement(String name) {
		Etablissement etablissement = new Etablissement(name, new ArrayList<Filiere>());
		etablissementRepository.save(etablissement);

		/**
		 * On sauvegarde l'historique à chaque fois
		 * 
		 **/
		historiqueRepository.save(new Historique("établissement " + name + " créé", new Date()));
	}

	public List<Etablissement> getEtablissements() throws DataNotFoundExceptions {
		List<Etablissement> etablissements_listes = etablissementRepository.findAll();
		if (etablissements_listes.size() == 0)
			throw new DataNotFoundExceptions();
		return etablissements_listes;
	}

	public Etablissement getEtablissementById(Long id) throws InvalidEntries {
		Etablissement etablissement = etablissementRepository.getOne(id);
		if (etablissement == null)
			throw new InvalidEntries("Ne modifie pas l'id s'il vous plait");
		return etablissement;
	}

	public void suprimerEtablissement(Long id) throws InvalidEntries {
		Etablissement etablissement = etablissementRepository.getOne(id);
		if (etablissement == null)
			throw new InvalidEntries();
		String name = etablissement.getNom_etablissement();
		historiqueRepository.save(new Historique("etablissement " + name + " supprimé", new Date()));
		etablissementRepository.deleteById(id);
	}

	public List<Filiere> getFilieresListByEtablissement(Long id) throws InvalidEntries, DataNotFoundExceptions {
		Etablissement etablissement = etablissementRepository.getOne(id);
		/**
		 * je teste si le serveur a recu une Id non valide d'apres l'administrateur
		 * 
		 */
		if (etablissement == null)
			throw new InvalidEntries();
		//List<Filiere> listes_de_filieres = filiere_repository.getFilieresByEtablissement(etablissement);
		List<Filiere> listes_de_filieres = etablissement.getFilieres();
		if (listes_de_filieres.size() == 0)
			/**
			 * 
			 * *Si la liste des filieres récuperer est vide je retourne une exception
			 * 
			 **/
			throw new DataNotFoundExceptions();
		return listes_de_filieres;
	}

	public List<Professeur> getProfesseursListByEtablissement(Long id) throws InvalidEntries, DataNotFoundExceptions {
		Etablissement etablissement = etablissementRepository.getOne(id);

		if (etablissement == null)
			throw new InvalidEntries();
		/**
		 * On récupere la liste des filieres , si il n'ya pas de filiere , pas la peine
		 * de continuer throw new DataNotFoundException
		 */
		List<Filiere> listes_de_filieres = etablissement.getFilieres();
		if (listes_de_filieres.size() == 0)
			throw new DataNotFoundExceptions();

		/**
		 * Pour récuperer tous les profs on doit récuperer les elements de chaque
		 * filieres, ce n'est pas bien de la parcourir comme ca mais c'est la seule
		 * solution pour éviter les relations cyclique
		 */
		List<Professeur> listes_de_professeurs = new ArrayList<Professeur>();
		for (Filiere filiere : listes_de_filieres) {
			List<Etape> etapes = filiere.getEtapes();
			for (Etape etape : etapes) {
				List<Semestre> semestres = etape.getSemestres();
				for (Semestre semestre : semestres) {
					List<Modulee> modules = semestre.getModules();
					for (Modulee module : modules) {
						List<Element> elements = module.getElements();
						for (Element element : elements) {
							listes_de_professeurs.addAll(element.getProfesseurs());
						}
					}
				}
			}
		}
		if (listes_de_professeurs.size() == 0)
			throw new DataNotFoundExceptions();
		return listes_de_professeurs;
	}

	@Override
	public void modifierEtablissmentById(Long id, String name) throws InvalidEntries {
		Etablissement etablissement = etablissementRepository.getOne(id);
		if (etablissement == null)
			throw new InvalidEntries();
		historiqueRepository.save(new Historique(
				"etablissement " + etablissement.getNom_etablissement() + " modifié en " + name, new Date()));
		etablissement.setNom_etablissement(name);
		etablissementRepository.save(etablissement);// La modification n'est pas necessaire dans ce champs
	}

	@Override
	public List<Etudiant> getEtudiantListByEtablissement(Long id) throws InvalidEntries, DataNotFoundExceptions {
		Etablissement etablissement = etablissementRepository.getOne(id);

		if (etablissement == null)
			throw new InvalidEntries();
		/**
		 * On récupere la liste des filieres , si il n'ya pas de filiere , pas la peine
		 * de continuer throw new DataNotFoundException
		 */
		List<Filiere> listes_de_filieres = etablissement.getFilieres();
		if (listes_de_filieres.size() == 0)
			throw new DataNotFoundExceptions();

		/**
		 * Pour récuperer tous les profs on doit récuperer les elements de chaque
		 * filieres, ce n'est pas bien de la parcourir comme ca mais c'est la seule
		 * solution pour éviter les relations cyclique
		 */
		List<Professeur> listes_de_professeurs = new ArrayList<Professeur>();
		for (Filiere filiere : listes_de_filieres) {
			List<Etape> etapes = filiere.getEtapes();
			for (Etape etape : etapes) {
				List<Semestre> semestres = etape.getSemestres();
				for (Semestre semestre : semestres) {
					List<Modulee> modules = semestre.getModules();
					for (Modulee module : modules) {
						List<Element> elements = module.getElements();
						for (Element element : elements) {
							listes_de_professeurs.addAll(element.getProfesseurs());
						}
					}
				}
			}
		}
		if (listes_de_professeurs.size() == 0)
			throw new DataNotFoundExceptions();
		return null;
	}
}

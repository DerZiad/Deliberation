package com.ziad.administrateur.filiere;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.ziad.enums.TypeSemestre;
import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.Etablissement;
import com.ziad.models.Etape;
import com.ziad.models.Filiere;
import com.ziad.models.Historique;
import com.ziad.models.Professeur;
import com.ziad.models.Semestre;
import com.ziad.repositories.EtablissementRepository;
import com.ziad.repositories.EtapeRepository;
import com.ziad.repositories.FiliereRepository;
import com.ziad.repositories.HistoriqueRepository;
import com.ziad.repositories.ProfesseurRepository;
import com.ziad.repositories.SemestreRepository;
import com.ziad.security.authentification.enums.MonRole;

@Primary
@Service
public class FiliereService implements FiliereInterface {
	@Autowired
	private FiliereRepository filiereRepository;
	@Autowired
	private EtablissementRepository etablissementRepository;
	@Autowired
	private EtapeRepository etapeRepository;
	@Autowired
	private SemestreRepository semestreRepository;
	@Autowired
	private HistoriqueRepository historiqueRepository;
	@Autowired
	private ProfesseurRepository professeurRepository;

	@Override
	public List<Etablissement> getEtablissement() throws DataNotFoundExceptions {
		List<Etablissement> listes_etablissements = etablissementRepository.findAll();
		if (listes_etablissements.size() == 0)
			throw new DataNotFoundExceptions("Les listes des établissements sont vides");
		return listes_etablissements;
	}

	@Override
	public void createFiliere(Long id_etablissement, String nom_filiere, Long id_professeur, Integer semester_number)
			throws EntityNotFoundException {
		Etablissement etablissement = etablissementRepository.getOne(id_etablissement);
		Filiere filiere = new Filiere();
		filiere.setNom_filiere(nom_filiere);
		filiere.setEtablissements(etablissement);
		filiere = filiereRepository.save(filiere);
		if (id_professeur != null) {
			Professeur professeur = professeurRepository.getOne(id_professeur);
			professeur.addFilere(filiere);
			professeur.getUser().addRole(MonRole.ROLERESPONSABLEFILIERE);
			professeurRepository.save(professeur);
			filiere.setResponsable_filiere(professeur);
		}

		int years = (int) (semester_number / 2) + semester_number % 2;
		int ordre = 0;
		for (int i = 1; i <= years; i++) {
			Etape etape = new Etape((i == 1) ? "1 ère Année" : i + " ème Année", false, 10d, filiere);
			Semestre semestre1 = new Semestre(10d, "Semestre " + ++ordre, etape, TypeSemestre.HIVER, ordre);
			Semestre semestre2 = new Semestre(10d, "Semestre " + ++ordre, etape, TypeSemestre.PRINTEMPS, ordre);
			etapeRepository.save(etape);
			semestreRepository.save(semestre1);
			if (i != years || (i == years && semester_number % 2 == 0))
				semestreRepository.save(semestre2);
		}
		historiqueRepository.save(new Historique("filiere " + nom_filiere + " créée", new Date()));

	}

	@Override
	public List<Filiere> getFiliereList() throws DataNotFoundExceptions {
		List<Filiere> listes_filieres = filiereRepository.findAll();
		if (listes_filieres.size() == 0)
			throw new DataNotFoundExceptions("La liste des filière est vide");
		return listes_filieres;
	}

	@Override
	public Filiere getFiliereProfile(ModelAndView model, Long id) throws EntityNotFoundException,DataNotFoundExceptions {
		Filiere filiere = filiereRepository.getOne(id);
		model.addObject("filiere", filiere);
		List<Etablissement> etablissements = etablissementRepository.findAll();
		if (etablissements.size() == 0)
			throw new DataNotFoundExceptions("La liste des établissement est vide");
		model.addObject("etablissements", etablissements);
		List<Semestre> semestres = new ArrayList<Semestre>();
		for (Etape etape : filiere.getEtapes()) {
			for (Semestre semestre : etape.getSemestres()) {
				semestres.add(semestre);
			}
		}

		model.addObject("semester_number", (semestres == null) ? 0 : semestres.size());
		model.addObject("etab_fili", filiere.getEtablissement().getId_etablissement());
		return filiere;
	}

	@Override
	public void modifyFiliereProfile(Long id_filiere, String name, Long etablissement_id, Integer semester_number)
			throws EntityNotFoundException {
		Filiere filiere = filiereRepository.getOne(id_filiere);
		List<Semestre> old_semestres = semestreRepository.getSemestresByFiliere(filiere);
		int old_semester_num = (old_semestres == null) ? 0 : old_semestres.size();
		historiqueRepository.save(new Historique("filiere " + filiere.getNom_filiere() + " modifié en " + name
				+ ", nombre de semestre changé de " + old_semester_num + " en " + semester_number, new Date()));
		filiere.setNom_filiere(name);
		Etablissement etablissement = etablissementRepository.getOne(etablissement_id);
		if (etablissement != null)
			filiere.setEtablissements(etablissement);
		filiere = filiereRepository.save(filiere);
		List<Etape> etapes = etapeRepository.getEtapeByFiliere(filiere);
		List<Semestre> semestres = semestreRepository.getSemestresByFiliere(filiere);
		if (old_semester_num != semester_number)
			if (etapes != null) {
				for (Semestre semestre : semestres) {
					semestreRepository.delete(semestre);
				}
			}
		if (old_semester_num != semester_number)
			if (etapes != null) {
				for (Etape etape : etapes) {

					etapeRepository.delete(etape);
				}
			}
		long years = (long) (semester_number / 2) + semester_number % 2;
		if (old_semester_num != semester_number)
			for (int i = 1; i <= years; i++) {
				Etape etape = new Etape((i == 1) ? "1 ère Année" : i + " ème Année", false, 10d, filiere);
				Semestre semestre1 = new Semestre(10d, "Semesetre " + i, etape, TypeSemestre.HIVER, 1);
				Semestre semestre2 = new Semestre(10d, "Semestre " + (2 * i), etape, TypeSemestre.PRINTEMPS, 2);
				etape.addSemestre(semestre1);
				etape.addSemestre(semestre2);
				etapeRepository.save(etape);
				semestreRepository.save(semestre1);
				if (i != years || (i == years && semester_number % 2 == 0))
					semestreRepository.save(semestre2);
			}
	}

	@Override
	public void suprimerFiliere(Long id) throws EntityNotFoundException {
		Filiere filiere = filiereRepository.getOne(id);
		String name = filiere.getNom_filiere();
		historiqueRepository.save(new Historique("filiere " + name + " supprimée", new Date()));
		filiereRepository.delete(filiere);
	}

	@Override
	public List<Professeur> listerResponsableFiliere() throws DataNotFoundExceptions {
		return professeurRepository.findAll();
	}

	@Override
	public Filiere listerEtapes(Long id_filire) throws DataNotFoundExceptions, EntityNotFoundException {
		Filiere filiere = filiereRepository.getOne(id_filire);
		return filiere;
	}

	@Override
	public Filiere diplomerEtapes(HttpServletRequest request) throws EntityNotFoundException {
		String id_filiere_String = request.getParameter("id_filiere");
		Long id_filiere = null;
		try {
			id_filiere = Long.parseLong(id_filiere_String);
		} catch (Exception e) {
			throw new EntityNotFoundException();
		}
		Filiere filiere = filiereRepository.getOne(id_filiere);
		List<Etape> etapes = filiere.getEtapes();
		etapes.stream().forEach(etape -> etape.setDiplomante(false));
		etapeRepository.saveAll(etapes);

		ArrayList<Long> ips = new ArrayList<Long>();
		for (Etape etape : etapes) {
			String id_etape_String = request.getParameter(etape.getId_etape() + "");
			if (id_etape_String != null)
				ips.add(Long.parseLong(id_etape_String));
		}

		List<Etape> etape_recus = etapeRepository.findAllById(ips);
		etape_recus.stream().forEach(etape -> etape.setDiplomante(true));
		etapeRepository.saveAll(etape_recus);
		return filiere;
	}

}

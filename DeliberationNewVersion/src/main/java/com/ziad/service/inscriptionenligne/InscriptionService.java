package com.ziad.service.inscriptionenligne;

import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.ziad.enums.Gender;
import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.Country;
import com.ziad.models.Etablissement;
import com.ziad.models.Historique;
import com.ziad.models.InscriptionEnLigne;
import com.ziad.repositories.CountryRepository;
import com.ziad.repositories.EtablissementRepository;
import com.ziad.repositories.HistoriqueRepository;
import com.ziad.repositories.InscriptionEnLigneRepository;
import com.ziad.utilities.SendEmailService;
import com.ziad.utilities.beans.ContinueMessage;

@Service
@Primary
public class InscriptionService implements InscriptionInterface {
	@Autowired
	private SendEmailService mailer;

	@Autowired
	private InscriptionEnLigneRepository inscription_en_ligne_repository;

	@Autowired
	private HistoriqueRepository historique_repository;

	@Autowired
	private EtablissementRepository etablissement_repository;

	@Autowired
	private CountryRepository countryRepository;

	@Override
	public void createEtudiant(String last_name_fr, String last_name_ar, String first_name_fr, String first_name_ar,
			String massar_edu, String cne, String nationality, Gender gender, String birth_date, String birth_place,
			String city, String province, Integer bac_year, String bac_type, String mention, String high_school,
			String bac_place, String academy, String email, Long id_etablissement)
			throws EntityNotFoundException, MessagingException {
		InscriptionEnLigne inscription_en_ligne = new InscriptionEnLigne();
		Etablissement etablissement = etablissement_repository.getOne(id_etablissement);
		inscription_en_ligne.setLast_name_fr(last_name_fr);
		inscription_en_ligne.setLast_name_ar(last_name_ar);
		inscription_en_ligne.setFirst_name_fr(first_name_fr);
		inscription_en_ligne.setFirst_name_ar(first_name_ar);
		inscription_en_ligne.setMassar_edu(massar_edu);
		inscription_en_ligne.setCne(cne);
		System.out.println(birth_date);
		/**
		 * Grabbing country
		 */
		Country country = countryRepository.getOne(nationality);

		inscription_en_ligne.setNationality(country);
		inscription_en_ligne.setGender(gender);
		inscription_en_ligne.setBirth_date(convertDate(birth_date));
		inscription_en_ligne.setBirth_place(birth_place);
		inscription_en_ligne.setCity(city);
		inscription_en_ligne.setProvince(province);
		inscription_en_ligne.setBac_year(bac_year);
		inscription_en_ligne.setBac_type(bac_type);
		inscription_en_ligne.setMention(mention);
		inscription_en_ligne.setHigh_school(high_school);
		inscription_en_ligne.setBac_place(bac_place);
		inscription_en_ligne.setAcademy(academy);
		inscription_en_ligne.setRegistration_date(new java.util.Date());
		inscription_en_ligne.setEmail(email);
		inscription_en_ligne.setAccepted(0);
		inscription_en_ligne.setEtablissement(etablissement);
		inscription_en_ligne_repository.save(inscription_en_ligne);
		ContinueMessage message = new ContinueMessage(inscription_en_ligne.getEmail(),
				"Veuillez confirmer votre inscription en ligne", "Confirmation email",
				inscription_en_ligne.getFirst_name_fr() + " " + inscription_en_ligne.getLast_name_fr(),
				"/student/confirmation/" + inscription_en_ligne.getId_inscription_en_ligne());
		mailer.sendEmail(message);
		
		historique_repository
				.save(new Historique("etudiant " + first_name_fr + " " + last_name_fr + " créé", new java.util.Date()));
	}

	@SuppressWarnings({ "deprecation"})
	private Date convertDate(String dateString) throws EntityNotFoundException {
		try {
			Date date = new Date();
			String year = dateString.substring(0, 4);
			String month = dateString.substring(5, 7);
			String day = dateString.substring(8, 10);
			date.setYear(Integer.parseInt(year)); 
			date.setMonth(Integer.parseInt(month));
			date.setDate(Integer.parseInt(day));
			return date;
		} catch (Exception e) {
			throw new EntityNotFoundException();
		}
	}

	@Override
	public void confirmerInscriptionEnLigne(Long id_inscription_en_ligne) throws EntityNotFoundException {
		InscriptionEnLigne inscription_en_ligne = inscription_en_ligne_repository.getOne(id_inscription_en_ligne);
		inscription_en_ligne.setAccepted(1);
		inscription_en_ligne_repository.save(inscription_en_ligne);
	}

	@Override
	public List<Etablissement> listerEtablissements() throws DataNotFoundExceptions {
		List<Etablissement> etablissements = etablissement_repository.findAll();
		if (etablissements.size() == 0)
			throw new DataNotFoundExceptions("La liste des établissements est vide");
		return etablissements;
	}
}

package com.ziad.principale.controllers;

import java.util.Date;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ziad.administrateur.etablissement.DataNotFoundExceptions;
import com.ziad.enums.Gender;
import com.ziad.models.Etudiant;
import com.ziad.models.Historique;
import com.ziad.models.InscriptionEnLigne;
import com.ziad.models.User;
import com.ziad.repositories.EtudiantRepository;
import com.ziad.repositories.HistoriqueRepository;
import com.ziad.repositories.UserRepository;

@Service
@Primary
public class InscriptionService implements InscriptionInterface {
	@Autowired
	private SendEmailService mailer;

	@Autowired
	private EtudiantRepository etudiant_repository;

	@Autowired
	private HistoriqueRepository historique_repository;

	@Autowired
	private UserRepository user_repository;

	@Override
	public void envoyerSMS() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		User user = user_repository.getUserByUsername(username);
		if (user.getActive() != 1) {
			user.setSms(generateSmsNumber());
			mailer.sendEmail(user.getUsername(), user.getSms(), "Confirmer par SMS");
			user_repository.save(user);
		}
	}

	private String generateSmsNumber() {
		String code = "";
		for (int i = 0; i < 6; i++) {
			code = code + (int) (Math.random() * 10);
		}
		return code;
	}

	@Override
	public void createEtudiant(String last_name_fr, String last_name_ar, String first_name_fr, String first_name_ar,
			String massar_edu, String cne, String nationality, Gender gender, String birth_date, String birth_place,
			String city, String province, Integer bac_year, String bac_type, String mention, String high_school,
			String bac_place, String academy) throws EntityNotFoundException{
		Etudiant student = new Etudiant();
		student.setLast_name_fr(last_name_fr);
		student.setLast_name_ar(last_name_ar);
		student.setFirst_name_fr(first_name_fr);
		student.setFirst_name_ar(first_name_ar);
		student.setMassar_edu(massar_edu);
		student.setCne(cne);
		student.setNationality(nationality);
		student.setGender(gender);
		student.setBirth_date(convertDate(birth_date));
		student.setBirth_place(birth_place);
		student.setCity(city);
		student.setProvince(province);
		student.setBac_year(bac_year);
		student.setBac_type(bac_type);
		student.setMention(mention);
		student.setHigh_school(high_school);
		student.setBac_place(bac_place);
		student.setAcademy(academy);
		student.setRegistration_date(new java.util.Date());
		etudiant_repository.save(student);
		historique_repository
				.save(new Historique("etudiant " + first_name_fr + " " + last_name_fr + " créé", new java.util.Date()));

	}

	@Override
	public void checkSms(String sms) throws DataNotFoundExceptions {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		User user = user_repository.getUserByUsername(username);
		if (user.getSms().equals(sms)) {
			user.setSms("");
			user.setActive(1);
		} else {
			throw new DataNotFoundExceptions("Le code sms est incorrecte");
		}
	}

	@SuppressWarnings({ "deprecation", "unused" })
	private Date convertDate(String dateString) throws EntityNotFoundException{
		try {
			Date date = new Date();
			String year = dateString.substring(0,4);
			String month = dateString.substring(5,7);
			String day = dateString.substring(8,10);
			date.setYear(Integer.parseInt(year));
			date.setMonth(Integer.parseInt(month));
			date.setDate(Integer.parseInt(day));
			return date;
		}catch (Exception e) {
			throw new EntityNotFoundException();
		}
	}
}

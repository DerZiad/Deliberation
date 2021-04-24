package com.ziad.administrateur.inscription;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ziad.administrateur.etablissement.DataNotFoundExceptions;
import com.ziad.enums.Role;
import com.ziad.enums.TypeInscription;
import com.ziad.models.AnneeAcademique;
import com.ziad.models.Element;
import com.ziad.models.Etape;
import com.ziad.models.Etudiant;
import com.ziad.models.Filiere;
import com.ziad.models.Historique;
import com.ziad.models.InscriptionAdministrative;
import com.ziad.models.InscriptionEnLigne;
import com.ziad.models.InscriptionPedagogique;
import com.ziad.models.Modulee;
import com.ziad.models.Semestre;
import com.ziad.models.User;
import com.ziad.models.compositeid.ComposedEtudiantElementInscriptionPedagogique;
import com.ziad.models.compositeid.ComposedInscriptionAdministrative;
import com.ziad.repositories.AnnneAcademiqueRepository;
import com.ziad.repositories.EtudiantRepository;
import com.ziad.repositories.FiliereRepository;
import com.ziad.repositories.HistoriqueRepository;
import com.ziad.repositories.InscriptionAdministrativeRepository;
import com.ziad.repositories.InscriptionEnLigneRepository;
import com.ziad.repositories.InscriptionPedagogiqueRepository;

@Service
@Primary
public class InscriptionAdministrativeNormalService implements CrudInscriptionAdministrative {

	@Autowired
	private InscriptionAdministrativeRepository inscriptionAdministrative;
	@Autowired
	private EtudiantRepository etudiantRepository;
	@Autowired
	private FiliereRepository filiereRepository;
	@Autowired
	private InscriptionEnLigneRepository inscriptionEnLigne;
	@Autowired
	private InscriptionPedagogiqueRepository inscriptionPedagogiqueRepository;
	@Autowired
	private HistoriqueRepository historiqueRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AnnneAcademiqueRepository annee_academique_repository;
	@Autowired
	private InscriptionAdministrativeRepository inscription_admistrative_repository;

	@Override
	public ArrayList<Object> prepareInscriptionDatas() throws DataNotFoundExceptions {
		List<InscriptionEnLigne> etudiants = inscriptionEnLigne.getAllInscriptionsEnLigneAccepted();
		List<Filiere> filieres = filiereRepository.findAll();
		List<AnneeAcademique> annees_academiques = annee_academique_repository.findAll();
		ArrayList<Object> besoins = new ArrayList<Object>();
		besoins.add(etudiants);
		besoins.add(filieres);
		besoins.add(annees_academiques);
		return besoins;
	}

	@Override
	public void createInscriptionAdministrative(Long id_annee_academique, Long id_inscription_en_ligne, Long id_filiere,
			MultipartFile photo, MultipartFile bac, MultipartFile relevee_de_note, MultipartFile acte_naissance,
			MultipartFile cin) throws EntityNotFoundException, IOException {
		InscriptionAdministrative inscription_administrative = new InscriptionAdministrative();
		Etudiant etudiant = new Etudiant();
		Filiere filiere = filiereRepository.getOne(id_filiere);

		ComposedInscriptionAdministrative id_compose = new ComposedInscriptionAdministrative(etudiant, filiere);

		InscriptionEnLigne inscription_en_ligne = inscriptionEnLigne.getOne(id_inscription_en_ligne);
		etudiant.setAcademy(inscription_en_ligne.getAcademy());
		etudiant.setBac_place(inscription_en_ligne.getBac_place());
		etudiant.setBac_type(inscription_en_ligne.getBac_type());
		etudiant.setBac_year(inscription_en_ligne.getBac_year());
		etudiant.setBirth_date(inscription_en_ligne.getBirth_date());
		etudiant.setBirth_place(inscription_en_ligne.getBirth_place());
		etudiant.setCity(inscription_en_ligne.getCity());
		etudiant.setCne(inscription_en_ligne.getCne());
		etudiant.setFirst_name_ar(inscription_en_ligne.getFirst_name_ar());
		etudiant.setFirst_name_fr(inscription_en_ligne.getFirst_name_fr());
		etudiant.setGender(inscription_en_ligne.getGender());
		etudiant.setHigh_school(inscription_en_ligne.getHigh_school());
		etudiant.setLast_name_ar(inscription_en_ligne.getLast_name_ar());
		etudiant.setLast_name_fr(inscription_en_ligne.getLast_name_fr());
		etudiant.setMassar_edu(inscription_en_ligne.getMassar_edu());
		etudiant.setMention(inscription_en_ligne.getMention());
		etudiant.setNationality(inscription_en_ligne.getNationality());
		etudiant.setProvince(inscription_en_ligne.getProvince());
		etudiant.setRegistration_date(inscription_en_ligne.getRegistration_date());
		etudiant.setEmail(inscription_en_ligne.getEmail());
		/**
		 * Créeation de l'utilistaeur
		 */
		User user = new User();
		user.setUsername(etudiant.getEmail());
		// On met le mot de passe son prenom pour le changer apres
		user.setPassword(passwordEncoder.encode(inscription_en_ligne.getLast_name_fr().toLowerCase()));
		user.setActive(1);
		user.addRole(Role.ETUDIANT);
		etudiant.setUser(user);
		etudiant.setInscription_en_ligne(inscription_en_ligne);
		inscription_en_ligne.setEtudiant(etudiant);
		etudiant.setInscription_en_ligne(inscription_en_ligne);
		etudiantRepository.save(etudiant);
		inscriptionEnLigne.save(inscription_en_ligne);

		// --------------------partie creation d inscrip
		// administrative----------------------------//

		AnneeAcademique annee_academique = annee_academique_repository.getOne(id_annee_academique);
		inscription_administrative.setAnnee_academique(annee_academique);
		inscription_administrative.setDate_pre_inscription(inscription_en_ligne.getRegistration_date());
		LocalDate ld = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		java.util.Date date = Date.from(ld.atStartOfDay(defaultZoneId).toInstant());
		inscription_administrative.setDate_valid_inscription(date);
		if (!bac.isEmpty()) {
			inscription_administrative.setBac(bac.getBytes());
		}
		if (!acte_naissance.isEmpty()) {
			inscription_administrative.setActe_naissance(acte_naissance.getBytes());
		}
		if (!relevee_de_note.isEmpty()) {
			inscription_administrative.setReleve_note(relevee_de_note.getBytes());
		}
		if (!cin.isEmpty()) {
			inscription_administrative.setCin(cin.getBytes());
		}
		if (!photo.isEmpty()) {
			inscription_administrative.setPhoto(photo.getBytes());
		}
		inscription_administrative.setComposite_association_id(id_compose);
		inscriptionAdministrative.save(inscription_administrative);

		/*
		 * Inscription pédagogique automatique
		 */
		List<Etape> etapes = filiere.getEtapes();
		Etape firststep = etapes.get(0);

		for (Semestre semestre : firststep.getSemestres()) {
			for (Modulee module : semestre.getModules()) {
				for (Element element : module.getElements()) {
					ComposedEtudiantElementInscriptionPedagogique compsedId = new ComposedEtudiantElementInscriptionPedagogique(
							etudiant, element);
					InscriptionPedagogique inscription_pedagogique = new InscriptionPedagogique(compsedId, "", 0d, 0d,
							TypeInscription.ELEMENT);
					this.inscriptionPedagogiqueRepository.save(inscription_pedagogique);
				}
			}
		}

	}

	@Override
	public void deleteInscriptionAdministrative(Long id_etudiant, Long id_filiere) throws EntityNotFoundException {

		Etudiant etudiant = etudiantRepository.getOne(id_etudiant);
		Filiere filiere = filiereRepository.getOne(id_filiere);

		ComposedInscriptionAdministrative inscription_administrative = new ComposedInscriptionAdministrative(etudiant,
				filiere);
		InscriptionAdministrative inscription_adminisrative = inscriptionAdministrative
				.getOne(inscription_administrative);
		inscription_admistrative_repository.delete(inscription_adminisrative);

	}

	@Override
	public ArrayList<Object> listerInscriptionsAdministratives()
			throws DataNotFoundExceptions, UnsupportedEncodingException {
		List<Filiere> filieres = filiereRepository.findAll();
		List<InscriptionAdministrative> lia = inscriptionAdministrative.findAll();

		// --------------------les années universitaires utilisés comme filtre du
		// tableau-----------------------//
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		int ele[] = new int[3];
		for (int i = 0; i < ele.length; i++) {
			ele[i] = Integer.parseInt((dtf.format(localDate).toString().split("/")[i].trim()));
		}
		if (ele[1] > 8) {
			ele[0]++;
		}
		// -----------------------------------------------------------------------------------------------//

		for (int i = 0; i < lia.size(); i++) {
			if (lia.get(i).getPhoto() != null) {

				byte[] photo = lia.get(i).getPhoto();
				byte[] encodeBase64Photo = Base64.encodeBase64(photo);
				String base64Encoded = new String(encodeBase64Photo, "UTF-8");
				lia.get(i).setEncodedPhoto(base64Encoded);
			}
			if (lia.get(i).getBac() != null) {

				byte[] document1 = lia.get(i).getBac();
				byte[] encodeBase64Document1 = Base64.encodeBase64(document1);
				String base64Encoded = new String(encodeBase64Document1, "UTF-8");
				lia.get(i).setEncodedBac(base64Encoded);
			}
			if (lia.get(i).getReleve_note() != null) {

				byte[] document1 = lia.get(i).getReleve_note();
				byte[] encodeBase64Document1 = Base64.encodeBase64(document1);
				String base64Encoded = new String(encodeBase64Document1, "UTF-8");
				lia.get(i).setEncodedRv(base64Encoded);
			}
			if (lia.get(i).getActe_naissance() != null) {

				byte[] document1 = lia.get(i).getActe_naissance();
				byte[] encodeBase64Document1 = Base64.encodeBase64(document1);
				String base64Encoded = new String(encodeBase64Document1, "UTF-8");
				lia.get(i).setEncodedAn(base64Encoded);
			}
			if (lia.get(i).getCin() != null) {

				byte[] document1 = lia.get(i).getCin();
				byte[] encodeBase64Document1 = Base64.encodeBase64(document1);
				String base64Encoded = new String(encodeBase64Document1, "UTF-8");
				lia.get(i).setEncodedCin(base64Encoded);
			}
		}
		List<InscriptionAdministrative> inscription_administrative = inscription_admistrative_repository.findAll();
		ArrayList<Object> list = new ArrayList<Object>();
		list.add((Integer) ele[0]);// ele[0]
		list.add(inscription_administrative);
		list.add(filieres);
		return list;
	}

	@Override
	public void modifierInscriptionAdministrative(Date date_pre_inscription, Date date_valid_inscription,
			Long id_etudiant, Long id_filiere,MultipartFile photo, MultipartFile bac,
			MultipartFile relevee_note, MultipartFile acte_de_naissance, MultipartFile cin, Long id_annee_academique)
			throws IOException {
		Etudiant etudiant = etudiantRepository.getOne(id_etudiant);
		Filiere filiere = filiereRepository.getOne(id_filiere);

		ComposedInscriptionAdministrative composed_id = new ComposedInscriptionAdministrative(etudiant, filiere);
		InscriptionAdministrative inscription_administrative = inscription_admistrative_repository.getOne(composed_id);
		AnneeAcademique annee_academique = annee_academique_repository.getOne(id_annee_academique);

		inscription_administrative.setAnnee_academique(annee_academique);
		inscription_administrative.setDate_pre_inscription(date_pre_inscription);
		inscription_administrative.setDate_valid_inscription(date_valid_inscription);

		if (!bac.isEmpty()) {
			inscription_administrative.setBac(bac.getBytes());
		}
		if (!acte_de_naissance.isEmpty()) {
			inscription_administrative.setActe_naissance(acte_de_naissance.getBytes());
		}
		if (!relevee_note.isEmpty()) {
			inscription_administrative.setReleve_note(relevee_note.getBytes());
		}
		if (!cin.isEmpty()) {
			inscription_administrative.setCin(cin.getBytes());
		}
		if (!photo.isEmpty()) {
			inscription_administrative.setPhoto(photo.getBytes());
		}
		inscription_admistrative_repository.save(inscription_administrative);
		historiqueRepository.save(new Historique(
				"documents de l'étudiant " + inscription_administrative.getEtudiant().getFirst_name_fr() + " "
						+ inscription_administrative.getEtudiant().getLast_name_fr() + " modifié à l'administration",
				new java.util.Date()));

	}

	@Override
	public ArrayList<Object> getInscriptionAdministrative(Long id_filiere, Long id_etudiant)
			throws EntityNotFoundException {
		Filiere filiere = filiereRepository.getOne(id_filiere);
		Etudiant etudiant = etudiantRepository.getOne(id_etudiant);

		ComposedInscriptionAdministrative inscription_administrative_id = new ComposedInscriptionAdministrative(
				etudiant, filiere);
		InscriptionAdministrative inscription_administrative = inscription_admistrative_repository
				.getOne(inscription_administrative_id);
		ArrayList<Object> besoins = new ArrayList<Object>();
		besoins.add(inscription_administrative);
		besoins.add(inscriptionEnLigne.findAll());
		besoins.add(filiereRepository.findAll());
		besoins.add(annee_academique_repository.findAll());
		return besoins;
	}

}

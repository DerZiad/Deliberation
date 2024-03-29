package com.ziad.services;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;

import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ziad.enums.Gender;
import com.ziad.enums.MonRole;
import com.ziad.enums.TypeInscription;
import com.ziad.exceptions.CSVReaderOException;
import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.exceptions.FormatReaderException;
import com.ziad.models.AnneeAcademique;
import com.ziad.models.Country;
import com.ziad.models.Element;
import com.ziad.models.Etape;
import com.ziad.models.Etudiant;
import com.ziad.models.Filiere;
import com.ziad.models.Historique;
import com.ziad.models.InscriptionAdministrative;
import com.ziad.models.InscriptionEnLigne;
import com.ziad.models.InscriptionPedagogique;
import com.ziad.models.Modulee;
import com.ziad.models.NoteElement;
import com.ziad.models.Semestre;
import com.ziad.models.User;
import com.ziad.models.compositeid.ComposedInscriptionAdministrative;
import com.ziad.models.compositeid.ComposedInscriptionPedagogique;
import com.ziad.repositories.AnnneAcademiqueRepository;
import com.ziad.repositories.CountryRepository;
import com.ziad.repositories.EtudiantRepository;
import com.ziad.repositories.FiliereRepository;
import com.ziad.repositories.HistoriqueRepository;
import com.ziad.repositories.InscriptionAdministrativeRepository;
import com.ziad.repositories.InscriptionEnLigneRepository;
import com.ziad.repositories.InscriptionPedagogiqueRepository;
import com.ziad.repositories.ModuleRepository;
import com.ziad.repositories.NoteElementRepository;
import com.ziad.repositories.SemestreRepository;
import com.ziad.services.interfaces.InscritpionAdministrativeInterface;
import com.ziad.utilities.Algorithms;
import com.ziad.utilities.ExcelReader;
import com.ziad.utilities.JSONConverter;
import com.ziad.utilities.SendEmailService;
import com.ziad.utilities.beans.HtmlMessage;

@Service
@Primary
public class InscriptionAdministrativeService implements InscritpionAdministrativeInterface {

	@Autowired
	private InscriptionAdministrativeRepository inscriptionAdministrative;
	@Autowired
	private EtudiantRepository etudiantRepository;
	@Autowired
	private FiliereRepository filiereRepository;
	@Autowired
	private InscriptionEnLigneRepository inscriptionEnLigneRepository;
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
	@Autowired
	private ExcelReader excel_service;
	@Autowired
	private NoteElementRepository noteElementRepository;
	@Autowired
	private JSONConverter converter;
	@Autowired
	private ModuleRepository moduleRepository;

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private SemestreRepository semestreRespository;

	@Autowired
	private SendEmailService mailer;
	
	@Autowired
	private Algorithms algorithmeRepo;

	@Override
	public ArrayList<Object> prepareInscriptionDatas() throws DataNotFoundExceptions {
		List<InscriptionEnLigne> etudiants = inscriptionEnLigneRepository.getAllInscriptionsEnLigneAccepted();
		List<Filiere> filieres = filiereRepository.findAll();
		AnneeAcademique annees_academiques = algorithmeRepo.grabAnneeAcademiqueActuel();
		ArrayList<Object> besoins = new ArrayList<Object>();
		besoins.add(etudiants);
		besoins.add(filieres);
		besoins.add(annees_academiques);
		return besoins;
	}

	@Override
	public void createInscriptionAdministrative(Long id_annee_academique, Long id_inscription_en_ligne, Long id_filiere,
			MultipartFile photo, MultipartFile bac, MultipartFile relevee_de_note, MultipartFile acte_naissance,
			MultipartFile cin) throws EntityNotFoundException, IOException, MessagingException {
		InscriptionAdministrative inscription_administrative = new InscriptionAdministrative();
		Etudiant etudiant = new Etudiant();
		Filiere filiere = filiereRepository.getOne(id_filiere);

		ComposedInscriptionAdministrative id_compose = new ComposedInscriptionAdministrative(etudiant, filiere);

		InscriptionEnLigne inscriptionEnLigne = inscriptionEnLigneRepository.getOne(id_inscription_en_ligne);
		etudiant.setAcademy(inscriptionEnLigne.getAcademy());
		etudiant.setBac_place(inscriptionEnLigne.getBac_place());
		etudiant.setBac_type(inscriptionEnLigne.getBac_type());
		etudiant.setBac_year(inscriptionEnLigne.getBac_year());
		etudiant.setBirth_date(inscriptionEnLigne.getBirth_date());
		etudiant.setBirth_place(inscriptionEnLigne.getBirth_place());
		etudiant.setCity(inscriptionEnLigne.getCity());
		etudiant.setCne(inscriptionEnLigne.getCne());
		etudiant.setFirst_name_ar(inscriptionEnLigne.getFirst_name_ar());
		etudiant.setFirst_name_fr(inscriptionEnLigne.getFirst_name_fr());
		etudiant.setGender(inscriptionEnLigne.getGender());
		etudiant.setHigh_school(inscriptionEnLigne.getHigh_school());
		etudiant.setLast_name_ar(inscriptionEnLigne.getLast_name_ar());
		etudiant.setLast_name_fr(inscriptionEnLigne.getLast_name_fr());
		etudiant.setMassar_edu(inscriptionEnLigne.getMassar_edu());
		etudiant.setMention(inscriptionEnLigne.getMention());
		etudiant.setNationality(inscriptionEnLigne.getNationality());
		etudiant.setProvince(inscriptionEnLigne.getProvince());
		etudiant.setRegistration_date(inscriptionEnLigne.getRegistration_date());
		etudiant.setEmail(inscriptionEnLigne.getEmail());
		/**
		 * Créeation de l'utilistaeur
		 */
		User user = new User();
		user.setUsername(etudiant.getEmail());
		// On met le mot de passe son prenom pour le changer apres
		user.setPassword(passwordEncoder.encode(inscriptionEnLigne.getLast_name_fr().toLowerCase()));
		user.setActive(1);
		user.addRole(MonRole.ROLEETUDIANT);
		etudiant.setUser(user);
		etudiantRepository.save(etudiant);

		// --------------------partie creation d inscrip
		// administrative----------------------------//

		AnneeAcademique annee_academique = annee_academique_repository.getOne(id_annee_academique);
		inscription_administrative.setAnnee_academique(annee_academique);
		inscription_administrative.setDate_pre_inscription(inscriptionEnLigne.getRegistration_date());
		
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
		inscription_administrative.encodeAll();

		inscriptionAdministrative.save(inscription_administrative);

		inscriptionEnLigneRepository.delete(inscriptionEnLigne);

		String body = "Vous avez été bien inscrit administrativement, le compte de Déliberation \n Username :"
				+ inscription_administrative.getEtudiant().getUser().getUsername() + "\n Password :"
				+ inscription_administrative.getEtudiant().getLast_name_fr().toLowerCase();

		HtmlMessage htmlmessage = new HtmlMessage(inscription_administrative.getEtudiant().getEmail(), body,
				"L'inscription administrative faites", inscription_administrative.getEtudiant().getLast_name_fr() + " "
						+ inscription_administrative.getEtudiant().getFirst_name_fr());
		mailer.sendEmail(htmlmessage);

		/*
		 * Inscription pédagogique automatique
		 */
		List<Etape> etapes = filiere.getEtapes();
		Etape firststep = etapes.get(0);

		for (Semestre semestre : firststep.getSemestres()) {
			for (Modulee module : semestre.getModules()) {
				for (Element element : module.getElements()) {
					ComposedInscriptionPedagogique compsedId = new ComposedInscriptionPedagogique(etudiant, element,annee_academique);
					InscriptionPedagogique inscription_pedagogique = new InscriptionPedagogique(compsedId,
							annee_academique, false, TypeInscription.SEMESTRE);
					this.inscriptionPedagogiqueRepository.save(inscription_pedagogique);
					NoteElement note = new NoteElement(compsedId, -1d,
							inscription_administrative.getAnnee_academique());
					noteElementRepository.save(note);
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
		inscription_admistrative_repository.deleteById(inscription_administrative);
	}

	@Override
	public ArrayList<Object> listerInscriptionsAdministratives() throws DataNotFoundExceptions {
		List<Filiere> filieres = filiereRepository.findAll();
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(annee_academique_repository.findAll());
		list.add(filieres);
		list.add(converter.convertSemestre(semestreRespository.findAll()));
		list.add(converter.convertModulee(moduleRepository.findAll()));
		list.add(inscription_admistrative_repository.findAll());

		return list;
	}

	@Override
	public ArrayList<Object> getInscriptionAdministrative(Long id_filiere, Long id_etudiant)
			throws EntityNotFoundException, UnsupportedEncodingException {
		Filiere filiere = filiereRepository.getOne(id_filiere);
		Etudiant etudiant = etudiantRepository.getOne(id_etudiant);

		ComposedInscriptionAdministrative inscription_administrative_id = new ComposedInscriptionAdministrative(
				etudiant, filiere);
		InscriptionAdministrative inscription_administrative = inscription_admistrative_repository
				.getOne(inscription_administrative_id);
		inscription_administrative.encodeAll();
		ArrayList<Object> besoins = new ArrayList<Object>();
		besoins.add(inscription_administrative);
		besoins.add(inscriptionEnLigneRepository.findAll());
		besoins.add(filiereRepository.findAll());
		besoins.add(annee_academique_repository.findAll());
		besoins.add(countryRepository.findAll());

		return besoins;
	}

	@Override
	public List<String> uploadInscriptionAdministrative(MultipartFile file)
			throws CSVReaderOException, IOException, FormatReaderException {
		List<String> erreurs = new ArrayList<String>();
		Iterator<Row> rows = excel_service.readInscriptionAdministrative(file);
		rows.next();
		while (rows.hasNext()) {
			try {
				Row row = rows.next();
				String annee_academique_chaine = row.getCell(0).getStringCellValue();
				String massar = row.getCell(1).getStringCellValue();
				String nom = row.getCell(2).getStringCellValue();
				String prenom = row.getCell(3).getStringCellValue();
				String filiere = row.getCell(4).getStringCellValue();
				Double bourse = row.getCell(5).getNumericCellValue();

				/***
				 * Recherche la filiere , j'utilise pas de jointure car l'utilisateur peut
				 * entrer la lettre de la filiere en miniscule
				 */
				List<Filiere> filieres = filiereRepository.findAll();
				List<Filiere> filieres_filtred = filieres.stream()
						.filter(filiereI -> filiereI.getNom_filiere().toLowerCase().equals(filiere.toLowerCase()))
						.collect(Collectors.toList());
				Filiere filiereObject = filieres_filtred.get(0);

				/**
				 * Meme chose pour inscription en ligne et annee academique
				 */
				List<InscriptionEnLigne> inscriptions_en_lignes = inscriptionEnLigneRepository.findAll();
				List<InscriptionEnLigne> inscriptions_en_lignes_filtered = inscriptions_en_lignes.stream()
						.filter(inscription -> inscription.getFirst_name_fr().toLowerCase().equals(nom.toLowerCase())
								&& inscription.getLast_name_fr().toLowerCase().equals(prenom.toLowerCase())
								&& inscription.getMassar_edu().toLowerCase().equals(massar.toLowerCase()))
						.collect(Collectors.toList());

				InscriptionEnLigne inscrptionEnLigneObject = inscriptions_en_lignes_filtered.get(0);
				Integer annee = convertAnnee(annee_academique_chaine);
				List<AnneeAcademique> annees_academiques = annee_academique_repository.findAll();
				List<AnneeAcademique> annees_academiques_filtered = annees_academiques.stream()
						.filter(annee_academique -> annee_academique.getAnnee_academique() == annee)
						.collect(Collectors.toList());
				AnneeAcademique annee_academique = null;
				if (annees_academiques_filtered.size() != 0) {
					annee_academique = annees_academiques_filtered.get(0);
				} else {
					annee_academique = new AnneeAcademique(annee);
					annee_academique_repository.save(annee_academique);
				}

				InscriptionAdministrative inscription_administrative = new InscriptionAdministrative();
				Etudiant etudiant = new Etudiant();

				ComposedInscriptionAdministrative id_compose = new ComposedInscriptionAdministrative(etudiant,
						filiereObject);

				etudiant.setAcademy(inscrptionEnLigneObject.getAcademy());
				etudiant.setBac_place(inscrptionEnLigneObject.getBac_place());
				etudiant.setBac_type(inscrptionEnLigneObject.getBac_type());
				etudiant.setBac_year(inscrptionEnLigneObject.getBac_year());
				etudiant.setBirth_date(inscrptionEnLigneObject.getBirth_date());
				etudiant.setBirth_place(inscrptionEnLigneObject.getBirth_place());
				etudiant.setCity(inscrptionEnLigneObject.getCity());
				etudiant.setCne(inscrptionEnLigneObject.getCne());
				etudiant.setFirst_name_ar(inscrptionEnLigneObject.getFirst_name_ar());
				etudiant.setFirst_name_fr(inscrptionEnLigneObject.getFirst_name_fr());
				etudiant.setGender(inscrptionEnLigneObject.getGender());
				etudiant.setHigh_school(inscrptionEnLigneObject.getHigh_school());
				etudiant.setLast_name_ar(inscrptionEnLigneObject.getLast_name_ar());
				etudiant.setLast_name_fr(inscrptionEnLigneObject.getLast_name_fr());
				etudiant.setMassar_edu(inscrptionEnLigneObject.getMassar_edu());
				etudiant.setMention(inscrptionEnLigneObject.getMention());
				etudiant.setNationality(inscrptionEnLigneObject.getNationality());
				etudiant.setProvince(inscrptionEnLigneObject.getProvince());
				etudiant.setRegistration_date(inscrptionEnLigneObject.getRegistration_date());
				etudiant.setEmail(inscrptionEnLigneObject.getEmail());
				/**
				 * Créeation de l'utilistaeur
				 */
				User user = new User();
				user.setUsername(etudiant.getEmail());
				// On met le mot de passe son prenom pour le changer apres
				user.setPassword(passwordEncoder.encode(inscrptionEnLigneObject.getLast_name_fr().toLowerCase()));
				user.setActive(1);
				user.addRole(MonRole.ROLEETUDIANT);
				etudiant.setUser(user);
				etudiantRepository.save(etudiant);
				inscriptionEnLigneRepository.save(inscrptionEnLigneObject);

				// --------------------partie creation d inscrip
				// administrative----------------------------//

				inscription_administrative.setAnnee_academique(annee_academique);
				inscription_administrative.setDate_pre_inscription(inscrptionEnLigneObject.getRegistration_date());
				LocalDate ld = LocalDate.now();
				ZoneId defaultZoneId = ZoneId.systemDefault();
				java.util.Date date = Date.from(ld.atStartOfDay(defaultZoneId).toInstant());
				inscription_administrative.setDate_valid_inscription(date);
				inscription_administrative.setBourse(bourse == 1);
				inscription_administrative.setComposite_association_id(id_compose);
				inscriptionAdministrative.save(inscription_administrative);

				/*
				 * Inscription pédagogique automatique
				 */
				List<Etape> etapes = filiereObject.getEtapes();
				Etape firststep = etapes.get(0);

				for (Semestre semestre : firststep.getSemestres()) {
					for (Modulee module : semestre.getModules()) {
						for (Element element : module.getElements()) {
							ComposedInscriptionPedagogique compsedId = new ComposedInscriptionPedagogique(etudiant,
									element,annee_academique);
							InscriptionPedagogique inscription_pedagogique = new InscriptionPedagogique(compsedId,
									annee_academique, false, TypeInscription.SEMESTRE);
							this.inscriptionPedagogiqueRepository.save(inscription_pedagogique);
							NoteElement note = new NoteElement(compsedId, 0d,
									inscription_administrative.getAnnee_academique());
							noteElementRepository.save(note);
						}
					}
				}

			} catch (IndexOutOfBoundsException e) {
				erreurs.add("Votre fichier ne respecte pas le format des élements");
				break;
			} catch (FormatReaderException e1) {
				erreurs.add(e1.getMessage());
			} catch (NumberFormatException e2) {
				erreurs.add("La bourse doit être 1 ou 0");
			}
		}
		return erreurs;
	}

	private Integer convertAnnee(String annnee) throws FormatReaderException {
		Integer indexofslash = annnee.indexOf("/");
		if (indexofslash == -1)
			throw new FormatReaderException("Le format de la date est aaaa/aaaa");
		Integer first_part = null;
		try {
			first_part = Integer.parseInt(annnee.substring(0, indexofslash));
		} catch (NumberFormatException e) {
			throw new FormatReaderException("La date doit être un entier");
		}
		return first_part;
	}

	@Override
	public List<InscriptionAdministrative> listInscriptionAdministrativeByFilter(Long idFiliere, Long idAnneeAcademique,
			Long idSemestre, Long idModule) throws DataNotFoundExceptions, EntityNotFoundException {
		List<InscriptionAdministrative> listesInscription = new ArrayList<InscriptionAdministrative>();
		
		if (idModule != null) {
			Modulee module = moduleRepository.getOne(idModule);
			listesInscription = inscription_admistrative_repository.listerInscriptionsAdministrativesByModule(module);
			System.out.println(listesInscription.size());
		} else if (idSemestre != null) {
			Semestre semestre = semestreRespository.getOne(idSemestre);
			listesInscription = inscription_admistrative_repository
					.listerInscriptionsAdministrativesBySemestre(semestre);
		} else if (idFiliere != null) {
			Filiere filiere = filiereRepository.getOne(idFiliere);
			listesInscription = inscription_admistrative_repository.listerInscriptionsAdministrativesByFiliere(filiere);
		}

		if (idAnneeAcademique != null) {
			listesInscription = listesInscription.stream()
					.filter(inscri -> inscri.getAnnee_academique().getId_annee_academique() == idAnneeAcademique)
					.collect(Collectors.toList());
		}

		return listesInscription;
	}

	@Override
	public void modifierInscriptionAdministrative(String last_name_fr, String last_name_ar, String first_name_fr,
			String first_name_ar, String massar_edu, String cne, String nationality, Gender gender, String birth_date,
			String birth_place, String city, String province, Integer bac_year, String bac_type, String mention,
			String high_school, String bac_place, String academy, String date_pre_inscription,
			String date_valid_inscription, Long id_etudiant, Long id_filiere, MultipartFile photo, MultipartFile bac,
			MultipartFile relevee_note, MultipartFile acte_de_naissance, MultipartFile cin, Long id_annee_academique)
			throws IOException {
		Etudiant etudiant = etudiantRepository.getOne(id_etudiant);
		Filiere filiere = filiereRepository.getOne(id_filiere);

		ComposedInscriptionAdministrative composed_id = new ComposedInscriptionAdministrative(etudiant, filiere);
		InscriptionAdministrative inscription_administrative = inscription_admistrative_repository.getOne(composed_id);
		AnneeAcademique annee_academique = annee_academique_repository.getOne(id_annee_academique);

		inscription_administrative.setAnnee_academique(annee_academique);
		inscription_administrative.setDate_pre_inscription(convertDate(date_pre_inscription));
		inscription_administrative.setDate_valid_inscription(convertDate(date_valid_inscription));

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

		inscription_administrative.getEtudiant();

		inscription_administrative.getEtudiant().setLast_name_fr(last_name_fr);
		inscription_administrative.getEtudiant().setLast_name_ar(last_name_ar);
		inscription_administrative.getEtudiant().setFirst_name_fr(first_name_fr);
		inscription_administrative.getEtudiant().setFirst_name_ar(first_name_ar);
		inscription_administrative.getEtudiant().setMassar_edu(massar_edu);
		inscription_administrative.getEtudiant().setCne(cne);

		/**
		 * Grabbing country
		 */
		Country country = countryRepository.getOne(nationality);

		inscription_administrative.getEtudiant().setNationality(country);
		inscription_administrative.getEtudiant().setGender(gender);
		inscription_administrative.getEtudiant().setBirth_date(convertDate(birth_date));
		inscription_administrative.getEtudiant().setBirth_place(birth_place);
		inscription_administrative.getEtudiant().setCity(city);
		inscription_administrative.getEtudiant().setProvince(province);
		inscription_administrative.getEtudiant().setBac_year(bac_year);
		inscription_administrative.getEtudiant().setBac_type(bac_type);
		inscription_administrative.getEtudiant().setMention(mention);
		inscription_administrative.getEtudiant().setHigh_school(high_school);
		inscription_administrative.getEtudiant().setBac_place(bac_place);
		inscription_administrative.getEtudiant().setAcademy(academy);		
		
		inscription_admistrative_repository.save(inscription_administrative);
		historiqueRepository.save(new Historique(
				"documents de l'étudiant " + inscription_administrative.getEtudiant().getFirst_name_fr() + " "
						+ inscription_administrative.getEtudiant().getLast_name_fr() + " modifié à l'administration",
				new java.util.Date()));

	}

	@SuppressWarnings({ "deprecation", "unused" })
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

}

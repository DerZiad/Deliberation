package com.ziad.administrateur.inscription;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.codec.binary.Base64;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ziad.enums.Role;
import com.ziad.enums.TypeInscription;
import com.ziad.models.AnneeAcademique;
import com.ziad.models.Element;
import com.ziad.models.Etape;
import com.ziad.models.Etudiant;
import com.ziad.models.Filiere;
import com.ziad.models.Historique;
import com.ziad.models.InscriptionAdministrative;
import com.ziad.models.InscriptionAdministrativeEtudiantFiliere;
import com.ziad.models.InscriptionEnLigne;
import com.ziad.models.InscriptionPedagogique;
import com.ziad.models.Modulee;
import com.ziad.models.Semestre;
import com.ziad.models.User;
import com.ziad.models.compositeid.ComposedEtudiantElementInscriptionPedagogique;
import com.ziad.models.compositeid.ComposedInscriptionAdministrativveEtudiantFiliereId;
import com.ziad.repositories.AnnneAcademiqueRepository;
import com.ziad.repositories.EtapeRepository;
import com.ziad.repositories.EtudiantRepository;
import com.ziad.repositories.Excel2DbRepository;
import com.ziad.repositories.FiliereRepository;
import com.ziad.repositories.HistoriqueRepository;
import com.ziad.repositories.InscriptionAdministrativeEtudiantFiliereRepository;
import com.ziad.repositories.InscriptionAdministrativeRepository;
import com.ziad.repositories.InscriptionEnLigneRepository;
import com.ziad.repositories.InscriptionPedagogiqueRepository;
import com.ziad.repositories.ModuleRepository;

@Controller
public class InscriptionAdministrativeController {

	@PersistenceContext
	private EntityManager entiryManager;
	private InscriptionAdministrativeRepository inscriptionAdministrative;
	private EtudiantRepository etudiantRepository;
	private FiliereRepository filiereRepository;
	private InscriptionEnLigneRepository inscriptionEnLigne;
	private InscriptionPedagogiqueRepository inscriptionPedagogiqueRepository;
	private ModuleRepository moduleRepository;
	//private Excel2DbRepository excel2Db = new Excel2DbRepository();
	private HistoriqueRepository historiqueRepository;
	private PasswordEncoder passwordEncoder;
	private AnnneAcademiqueRepository annee_academique_repository;
	private InscriptionAdministrativeEtudiantFiliereRepository inscription_administrative_etudiant_filiere_repository;

	// Modification
	//private EtapeRepository etaperepository;
	
	
	// -------+++++++++-----------------++++++++------------++++++++----------++++++++-------//
//+*+*+*+*+*+*+*+*+*++*+*+*+*+*+**+*+*+*+*+*+*+*+*+*+/ PARTIE INSCRIPTION ADMINISTRATIVE /*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+**+*+*+//
	// -------+++++++++-----------------++++++++------------++++++++----------++++++++-------//

//---------------------------------------aller à page d'inscription administrative----------------------------------//

	@GetMapping("/admin/inscription/InscriptionAdministrative")
	public ModelAndView InscriptionAdministrative() {
		ModelAndView model = new ModelAndView("InscriptionAdministrative");
		model.addObject("InscriptionAdministrative", "mm-active");// will be used in the nav-bar
		List<InscriptionEnLigne> etudiants = inscriptionEnLigne.getAllInscriptionsEnLigneAccepted();
		List<Filiere> filieres = filiereRepository.findAll();
		List<AnneeAcademique> annees_academiques = annee_academique_repository.findAll();
		
		model.addObject("annees_academiques", annees_academiques);
		model.addObject("etudiants", etudiants);
		model.addObject("filieres", filieres);
		return model;
	}

//---------------------------------action :création d'une nouvelle inscription administrative-----------------------------//

	public InscriptionAdministrativeController(EntityManager entiryManager,
			InscriptionAdministrativeRepository inscriptionAdministrative, EtudiantRepository etudiantRepository,
			FiliereRepository filiereRepository, InscriptionEnLigneRepository inscriptionEnLigne,
			InscriptionPedagogiqueRepository inscriptionPedagogiqueRepository, ModuleRepository moduleRepository,
			HistoriqueRepository historiqueRepository, PasswordEncoder passwordEncoder,
			AnnneAcademiqueRepository annee_academique_repository,
			InscriptionAdministrativeEtudiantFiliereRepository inscription_administrative_etudiant_filiere_repository) {
		super();
		this.entiryManager = entiryManager;
		this.inscriptionAdministrative = inscriptionAdministrative;
		this.etudiantRepository = etudiantRepository;
		this.filiereRepository = filiereRepository;
		this.inscriptionEnLigne = inscriptionEnLigne;
		this.inscriptionPedagogiqueRepository = inscriptionPedagogiqueRepository;
		this.moduleRepository = moduleRepository;
		this.historiqueRepository = historiqueRepository;
		this.passwordEncoder = passwordEncoder;
		this.annee_academique_repository = annee_academique_repository;
		this.inscription_administrative_etudiant_filiere_repository = inscription_administrative_etudiant_filiere_repository;
	}

	@PostMapping("/admin/inscription/createANewInscriptionAdministrative")
	public ModelAndView createANewInscriptionAdministrative(@RequestParam("annee_academique") Long id_annee_academique,
			@RequestParam("id_inscription_en_ligne") Long id_inscription_en_ligne,
			@RequestParam("filiere") Long id_filiere,
			@RequestParam("photo") MultipartFile photo, @RequestParam("bac") MultipartFile bac,
			@RequestParam("rn") MultipartFile relevee_de_note, @RequestParam("an") MultipartFile acte_naissance,
			@RequestParam("cin") MultipartFile cin) throws IOException {

		InscriptionAdministrative inscription_administrative = new InscriptionAdministrative();
		Etudiant etudiant = new Etudiant();
		Filiere filiere = filiereRepository.getOne(id_filiere);

		ComposedInscriptionAdministrativveEtudiantFiliereId id_compose = new ComposedInscriptionAdministrativveEtudiantFiliereId(
				etudiant, inscription_administrative, filiere);
		InscriptionAdministrativeEtudiantFiliere inscription_admistrative_associative = new InscriptionAdministrativeEtudiantFiliere(
				id_compose);

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
		inscriptionAdministrative.save(inscription_administrative);
		inscription_administrative_etudiant_filiere_repository.save(inscription_admistrative_associative);
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
					InscriptionPedagogique inscription_pedagogique = new InscriptionPedagogique(compsedId, "",
							0d, 0d, TypeInscription.ELEMENT);
					this.inscriptionPedagogiqueRepository.save(inscription_pedagogique);
				}
			}
		}
		return new ModelAndView("redirect:/student/list");
	}

//-----------------------------------action : modifier inscription administrative---------------------------------------//

	@PostMapping("/inscription/ModifierInscriptionAdministrative/{id_inscription_administrative}")
	public ModelAndView ModifierInscriptionAdministrative(@PathVariable("id_ia") Long id_inscription_administrative,
			@RequestParam("date_pre_inscription") Date date_pre_inscription,
			@RequestParam("date_valid_inscription") Date date_valid_inscription,
			@RequestParam("id_etudiant") Long id_etudiant, @RequestParam("filiere") Long id_filiere,
			@RequestParam("operateur") String operateur, @RequestParam("photo") MultipartFile photo,
			@RequestParam("bac") MultipartFile bac, @RequestParam("rn") MultipartFile relevee_note,
			@RequestParam("an") MultipartFile acte_de_naissance, @RequestParam("cin") MultipartFile cin,
			@RequestParam("annee_academique") Long id_annee_academique) throws IOException {

		InscriptionAdministrative ia = inscriptionAdministrative.getOne(id_inscription_administrative);
		Etudiant etudiant = etudiantRepository.getOne(id_etudiant);
		Filiere filiere = filiereRepository.getOne(id_filiere);

		ComposedInscriptionAdministrativveEtudiantFiliereId composed_id = new ComposedInscriptionAdministrativveEtudiantFiliereId(
				etudiant, ia, filiere);
		InscriptionAdministrativeEtudiantFiliere inscription_administrative_composed = inscription_administrative_etudiant_filiere_repository
				.getOne(composed_id);

		AnneeAcademique annee_academique = annee_academique_repository.getOne(id_annee_academique);

		ia.setAnnee_academique(annee_academique);
		ia.setDate_pre_inscription(date_pre_inscription);
		ia.setDate_valid_inscription(date_valid_inscription);

		if (!bac.isEmpty()) {
			ia.setBac(bac.getBytes());
		}
		if (!acte_de_naissance.isEmpty()) {
			ia.setActe_naissance(acte_de_naissance.getBytes());
		}
		if (!relevee_note.isEmpty()) {
			ia.setReleve_note(relevee_note.getBytes());
		}
		if (!cin.isEmpty()) {
			ia.setCin(cin.getBytes());
		}
		if (!photo.isEmpty()) {
			ia.setPhoto(photo.getBytes());
		}
		inscription_administrative_etudiant_filiere_repository.save(inscription_administrative_composed);
		historiqueRepository.save(new Historique("documents de l'étudiant "
				+ inscription_administrative_composed.getEtudiant().getFirst_name_fr() + " "
				+ inscription_administrative_composed.getEtudiant().getLast_name_fr() + " modifié à l'administration",
				new java.util.Date()));
		return new ModelAndView("redirect:/inscription/ListInscriptionAdministrative");
	}

//---------------------------------------action : supprimer inscrip administrative----------------------------------------//

	@GetMapping("/inscription/SupprimerInscriptionAdministrative/{id_ia}")
	public ModelAndView SupprimerInscriptionAdministrative(@PathVariable("id_ia") Long id_inscription_administrative) {

		InscriptionAdministrative inscription_adminisrative = inscriptionAdministrative
				.getOne(id_inscription_administrative);

		List<InscriptionAdministrativeEtudiantFiliere> inscription_administrative_etudiant_filiere_all = inscription_administrative_etudiant_filiere_repository
				.findAll();
		
		InscriptionAdministrativeEtudiantFiliere inscription_administrative_etudiant_filiere = inscription_administrative_etudiant_filiere_all
				.stream()
				.filter(inscription -> inscription.get_inscription_administrative().equals(inscription_adminisrative))
				.collect(Collectors.toList()).get(0);
		inscription_administrative_etudiant_filiere_repository.delete(inscription_administrative_etudiant_filiere);
				return new ModelAndView("redirect:/inscription/ListInscriptionAdministrative");
	}

//-----------------------------------------page affichant la liste des inscriptions administratives-------------------------------//

	@GetMapping("/inscription/ListInscriptionAdministrative")
	public ModelAndView listInscriptionAdministratives() throws UnsupportedEncodingException {
		ModelAndView model = new ModelAndView("ListInscriptionAdministrative");
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
		List<InscriptionAdministrativeEtudiantFiliere> inscription_administrative_associative = inscription_administrative_etudiant_filiere_repository.findAll();
		
		model.addObject("listAdministartive", "mm-active");
		model.addObject("annee", ele[0]);
		model.addObject("Inscription", inscription_administrative_associative);
		model.addObject("f", filieres);
		return model;
	}

//-------------------------------------------aller à la page pour uploder un fichier d'inscrip administratives------------------------//

	@GetMapping("/inscription/PageUploadInscriptionAdministrative")
	public ModelAndView PageUploadInscriptionAdministrative() {
		ModelAndView model = new ModelAndView("UploadInscriptionAdministrative");
		model.addObject("InscriptionAdministartive", "mm-active");

		return model;
	}

//-------------------------------------------aller à la page pour uploder un fichier d'inscrip administratives------------------------//
	/*
	@RequestMapping("/inscription/ProfilEtudiant/{id}")
	public ModelAndView ProfilEtudiant(@PathVariable("id") int id_ia) throws UnsupportedEncodingException {
		ModelAndView model = new ModelAndView("ProfilEtudiant");
		InscriptionAdministrative ia = inscriptionAdministrative.getOne(id_ia);

		if (ia.getPhoto() != null) {

			byte[] photo = ia.getPhoto();
			byte[] encodeBase64Photo = Base64.encodeBase64(photo);
			String base64Encoded = new String(encodeBase64Photo, "UTF-8");
			ia.setEncodedPhoto(base64Encoded);
		}
		if (ia.getDocument1() != null) {

			byte[] document1 = ia.getDocument1();
			byte[] encodeBase64Document1 = Base64.encodeBase64(document1);
			String base64Encoded = new String(encodeBase64Document1, "UTF-8");
			ia.setEncodedDocument1(base64Encoded);
		}

		if (ia.getAn() != null) {

			byte[] photo = ia.getAn();
			byte[] encodeBase64Photo = Base64.encodeBase64(photo);
			String base64Encoded = new String(encodeBase64Photo, "UTF-8");
			ia.setEncodedAn(base64Encoded);
		}

		if (ia.getBac() != null) {

			byte[] document1 = ia.getBac();
			byte[] encodeBase64Document1 = Base64.encodeBase64(document1);
			String base64Encoded = new String(encodeBase64Document1, "UTF-8");
			ia.setEncodedBac(base64Encoded);
		}

		if (ia.getRn() != null) {

			byte[] photo = ia.getRn();
			byte[] encodeBase64Photo = Base64.encodeBase64(photo);
			String base64Encoded = new String(encodeBase64Photo, "UTF-8");
			ia.setEncodedRv(base64Encoded);
		}

		if (ia.getCin() != null) {

			byte[] document1 = ia.getCin();
			byte[] encodeBase64Document1 = Base64.encodeBase64(document1);
			String base64Encoded = new String(encodeBase64Document1, "UTF-8");
			ia.setEncodedCin(base64Encoded);
		}

		if (ia.getDocument2() != null) {

			byte[] photo = ia.getDocument2();
			byte[] encodeBase64Photo = Base64.encodeBase64(photo);
			String base64Encoded = new String(encodeBase64Photo, "UTF-8");
			ia.setEncodedDocument2(base64Encoded);
		}

		Etudiant etudiant = ia.getEtudiant();
		List<InscriptionPedagogique> ip = inscriptionPedagogiqueRepository
				.getInscriptionsPedagogiqueByEtudiant(etudiant);
		model.addObject("etudiant", etudiant);
		model.addObject("ia", ia);
		model.addObject("ip", ip);
		model.addObject("ipm", inscriptionPedagogiqueModuleRepository.findAll());
		model.addObject("module", moduleRepository.findAll());

		return model;
	}

//-------------------------------------------aller à la page pour modifier inscrip administratives-----------------------------------------//

	@GetMapping("/admin/inscription/PageModifierInscriptionAdministrative")
	public ModelAndView PageModifierInscriptionAdministrative(@RequestParam("id") int id_ia) {
		List<InscriptionEnLigne> e = inscriptionEnLigne.getAllInscriptionsEnLigneAccepted();
		InscriptionAdministrative ia = inscriptionAdministrative.getOne(id_ia);
		ModelAndView model = new ModelAndView("ModifierInscriptionAdministrative");
		model.addObject("ListInscriptionAdministartive", "mm-active");
		model.addObject("ia", ia);
		model.addObject("Etudiant", e);

		return model;
	}

//--------------------------------------------------Upload du fichier et remplir la base de données----------------------------------------//
	/*
	@PostMapping("/inscription/UploadInscriptionAdministrative")
	public ModelAndView UploadInscriptionAdministartive(@RequestParam("file") MultipartFile file) throws IOException {
		ModelAndView model = new ModelAndView("RapportUpload");
		Path dir = Paths.get("src/main/resources/static/Excels/");
		Boolean dontSave = false;
		Path excelFilePath = excel2Db.write(file, dir);
		InscriptionAdministrative ia;
		// ---------------pour les lignes non valide---------//
		List<String> namesE = new ArrayList<String>();
		// ---------------pour les lignes valide---------//
		List<String> namesV = new ArrayList<String>();
		// ----------------------------------------------------------------------------------------//
		try {
			// ----------------------------declaration et initialisation des objets à
			// utiliser-------------------//
			long start = System.currentTimeMillis();

			FileInputStream inputStream = new FileInputStream(excelFilePath.toString());

			Workbook workbook = new XSSFWorkbook(inputStream);

			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = firstSheet.iterator();
			// -----------------------------------------------------------------------------------//

			rowIterator.next(); // skip the header row

			// boucle pour passer par toutes les lignes
			while (rowIterator.hasNext()) {
				dontSave = false;
				// -----incrementer l'iterator ligne-----//
				Row nextRow = rowIterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				// --------------------------//

				ia = new InscriptionAdministrative();

				// boucle pour passer par toutes les colonnes
				while (cellIterator.hasNext()) {

					// -----incrementer l'iterator colonne-----//
					Cell nextCell = cellIterator.next();
					int columnIndex = nextCell.getColumnIndex();
					// ------------------------------------//

					// ce switch pour indiquer dans quelle colonne on est.
					switch (columnIndex) {
					// colonne 1
					case 0:
						String annee_academique = nextCell.getStringCellValue();
						ia.setAnnee_academique(annee_academique);
						break;
					// colonne 2
					case 1:
						java.util.Date date_pre_inscription = nextCell.getDateCellValue();
						ia.setDate_pre_inscription(date_pre_inscription);
						break;
					// colonne 3
					case 2:
						java.util.Date date_valid_inscription = nextCell.getDateCellValue();
						ia.setDate_valid_inscription(date_valid_inscription);
						break;
					// colonne 4
					case 3:
						String fullNameEtudiant = nextCell.getStringCellValue();
						String[] nameEtudiant = fullNameEtudiant.split(" ");
						String first_name_fr = nameEtudiant[0].trim();
						String last_name_fr = nameEtudiant[1].trim();
						InscriptionEnLigne iel = inscriptionEnLigne.findByNameAccepted(first_name_fr, last_name_fr);

						// --------cas ou l'inscription en ligne n'existe pas-----//
						if (iel == null) {
							namesE.add(fullNameEtudiant
									+ " , cause : il n'y a aucune inscription en ligne acceptée correspondante.");
							dontSave = true;
							continue;
						}
						// ----------------------------------------------//

						Etudiant e = new Etudiant();
						e.setAcademy(iel.getAcademy());
						e.setBac_place(iel.getBac_place());
						e.setBac_type(iel.getBac_type());
						e.setBac_year(iel.getBac_year());
						e.setBirth_date(iel.getBirth_date());
						e.setBirth_place(iel.getBirth_place());
						e.setCity(iel.getCity());
						e.setCne(iel.getCne());
						e.setEtablissement(iel.getEtablissement());
						e.setFirst_name_ar(iel.getFirst_name_ar());
						e.setFirst_name_fr(iel.getFirst_name_fr());
						e.setGender(iel.getGender());
						e.setHigh_school(iel.getHigh_school());
						e.setLast_name_ar(iel.getLast_name_ar());
						e.setLast_name_fr(last_name_fr);
						e.setMassar_edu(iel.getMassar_edu());
						e.setMention(iel.getMention());
						e.setNationality(iel.getNationality());
						e.setProvince(iel.getProvince());
						e.setRegistration_date(iel.getRegistration_date());
						List<Integer> en = etudiantRepository.getIdEtudiantByName(iel.getFirst_name_fr(),
								iel.getLast_name_fr());
						if (!en.isEmpty()) {
							namesE.add(first_name_fr + " " + last_name_fr
									+ " , cause : L'etudiant est deja inscrit administrativement.");
							dontSave = true;
							continue;
						}
						etudiantRepository.save(e);

						ia.setEtudiant(e);
						inscriptionEnLigne.updateAcceptation(iel.getId(), 3);
						break;
					// colonne 5
					case 4:
						int filieres_id_filiere = (int) nextCell.getNumericCellValue();
						Filiere f = filiereRepository.getOne(filieres_id_filiere);
						ia.setFilieres(f);
						break;
					// colonne 6
					case 5:
						String operateur = nextCell.getStringCellValue();
						ia.setOperateur(operateur);
						if (dontSave == true)
							continue;

						// faire entrer l inscrip. dans la base de données
						inscriptionAdministrative.save(ia);
						namesV.add(ia.getEtudiant().getFirst_name_fr() + " " + ia.getEtudiant().getLast_name_fr());
						break;
					}
				}
			}
			workbook.close();

			long end = System.currentTimeMillis();
			System.out.printf("Import done in %d ms\n", (end - start));

			model.addObject("nonAccepter", namesE);
			model.addObject("Accepter", namesV);
			System.out.println(namesV);
			System.out.println(namesE);
			// -------------envoyer le size de la plus grande liste------//
			if (namesE.size() > namesV.size()) {
				model.addObject("size", namesE.size());
			} else {
				model.addObject("size", namesV.size());
			}

		} catch (IOException ex1) {
			System.out.println("Error reading file");
			ex1.printStackTrace();
		}
		// ------------------------------------------------------------------------------------//
		return model;
	}*/
}

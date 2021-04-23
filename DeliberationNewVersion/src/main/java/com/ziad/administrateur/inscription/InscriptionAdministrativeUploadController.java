package com.ziad.administrateur.inscription;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.ziad.repositories.AnnneAcademiqueRepository;
import com.ziad.repositories.EtudiantRepository;
import com.ziad.repositories.FiliereRepository;
import com.ziad.repositories.HistoriqueRepository;
import com.ziad.repositories.InscriptionAdministrativeRepository;
import com.ziad.repositories.InscriptionEnLigneRepository;
import com.ziad.repositories.InscriptionPedagogiqueRepository;
import com.ziad.repositories.ModuleRepository;

public class InscriptionAdministrativeUploadController {
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
	//-------------------------------------------aller à la page pour uploder un fichier d'inscrip administratives------------------------//

		/*@GetMapping("/inscription/PageUploadInscriptionAdministrative")
		public ModelAndView PageUploadInscriptionAdministrative() {
			ModelAndView model = new ModelAndView("UploadInscriptionAdministrative");
			model.addObject("InscriptionAdministartive", "mm-active");

			return model;
		}*/

	//-------------------------------------------aller à la page pour uploder un fichier d'inscrip administratives------------------------//
		/*@RequestMapping("/inscription/ProfilEtudiant/{id}")
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
	*/
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

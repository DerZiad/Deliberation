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
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import org.apache.commons.codec.binary.Base64;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
import com.ziad.repositories.EtapeRepository;
import com.ziad.repositories.EtudiantRepository;
import com.ziad.repositories.Excel2DbRepository;
import com.ziad.repositories.FiliereRepository;
import com.ziad.repositories.HistoriqueRepository;
import com.ziad.repositories.InscriptionAdministrativeRepository;
import com.ziad.repositories.InscriptionEnLigneRepository;
import com.ziad.repositories.InscriptionPedagogiqueRepository;
import com.ziad.repositories.ModuleRepository;

@Controller
@RequestMapping("/admin")
public class InscriptionAdministrativeController {
	@Autowired
	private CrudInscriptionAdministrative inscription_metier;
	
	private final static String ATTRIBUT_LIST_ETUDIANTS = "etudiants";
	private final static String ATTRIBUT_LIST_FILIERES = "filieres";
	private final static String ATTRIBUT_LIST_ANNEE_ACADEMIQUES = "annees_academiques";
	

	@SuppressWarnings("unchecked")
	@GetMapping("inscription/InscriptionAdministrative")
	public ModelAndView InscriptionAdministrative() throws DataNotFoundExceptions{
		ModelAndView model = new ModelAndView("InscriptionAdministrative");
		model.addObject("InscriptionAdministrative", "mm-active");// will be used in the nav-bar
		ArrayList<Object> besoins = inscription_metier.prepareInscriptionDatas();
		model.addObject(ATTRIBUT_LIST_ETUDIANTS, (List<Etudiant>)besoins.get(0));
		model.addObject(ATTRIBUT_LIST_FILIERES, (List<Filiere>)besoins.get(1));
		model.addObject(ATTRIBUT_LIST_ANNEE_ACADEMIQUES, (List<AnneeAcademique>)besoins.get(2));
		return model;
	}

//---------------------------------action :création d'une nouvelle inscription administrative-----------------------------//

	@PostMapping("/inscription/createANewInscriptionAdministrative")
	public ModelAndView createANewInscriptionAdministrative(@RequestParam("annee_academique") Long id_annee_academique,
			@RequestParam("id_inscription_en_ligne") Long id_inscription_en_ligne,
			@RequestParam("filiere") Long id_filiere,
			@RequestParam("photo") MultipartFile photo, @RequestParam("bac") MultipartFile bac,
			@RequestParam("rn") MultipartFile relevee_de_note, @RequestParam("an") MultipartFile acte_naissance,
			@RequestParam("cin") MultipartFile cin) throws IOException {
		inscription_metier.createInscriptionAdministrative(id_annee_academique, id_inscription_en_ligne, id_filiere, photo, bac, relevee_de_note, acte_naissance, cin);
		return new ModelAndView("redirect:/student/list");
	}

//-----------------------------------action : modifier inscription administrative---------------------------------------//
	
	@PostMapping("/inscription/ModifierInscriptionAdministrative/{id_filiere}/{id_etudiant}")
	public ModelAndView ModifierInscriptionAdministrative(
			@RequestParam("date_pre_inscription") Date date_pre_inscription,
			@RequestParam("date_valid_inscription") Date date_valid_inscription,
			@RequestParam("id_etudiant") Long id_etudiant, @RequestParam("filiere") Long id_filiere,
			@RequestParam("operateur") String operateur, @RequestParam("photo") MultipartFile photo,
			@RequestParam("bac") MultipartFile bac, @RequestParam("rn") MultipartFile relevee_note,
			@RequestParam("an") MultipartFile acte_de_naissance, @RequestParam("cin") MultipartFile cin,
			@RequestParam("annee_academique") Long id_annee_academique) throws IOException {
		inscription_metier.modifierInscriptionAdministrative(date_pre_inscription, date_valid_inscription, id_etudiant, id_filiere, operateur, photo, bac, relevee_note, acte_de_naissance, cin, id_annee_academique);
		return new ModelAndView("redirect:/inscription/ListInscriptionAdministrative");
	}
	
//---------------------------------------action : supprimer inscrip administrative----------------------------------------//

	@GetMapping("/inscription/SupprimerInscriptionAdministrative/{id_filiere}/{id_etudiant}")
	public ModelAndView SupprimerInscriptionAdministrative(@PathVariable("id_etudiant") Long id_etudiant,@PathVariable("id_filiere") Long id_filiere) throws EntityNotFoundException{
			inscription_metier.deleteInscriptionAdministrative(id_etudiant, id_filiere);
				return new ModelAndView("redirect:/inscription/ListInscriptionAdministrative");
	}

//-----------------------------------------page affichant la liste des inscriptions administratives-------------------------------//
	
	@GetMapping("/inscription/ListInscriptionAdministrative")
	public ModelAndView listInscriptionAdministratives() throws UnsupportedEncodingException,DataNotFoundExceptions {
		ModelAndView model = new ModelAndView("ListInscriptionAdministrative");
		ArrayList<Object> list = inscription_metier.listerInscriptionsAdministratives();
		model.addObject("listAdministartive", "mm-active");
		model.addObject("annee", (Integer)list.get(0));
		model.addObject("InscriptionAssociative", list.get(1));
		model.addObject("filieres", list.get(2));
		return model;
	}
	
	/*
	@GetMapping("/inscription/PageModifierInscriptionAdministrative/{id_inscription_administrative}")
	public ModelAndView PageModifierInscriptionAdministrative(@PathVariable Long id_inscription_administrative) {
		InscriptionAdministrative ia = inscriptionAdministrative.getOne(id_ia);
		ModelAndView model = new ModelAndView("ModifierInscriptionAdministrative");
		model.addObject("ListInscriptionAdministartive", "mm-active");
		model.addObject("ia", ia);
		model.addObject("Etudiant", e);

		return model;
	}*/
}

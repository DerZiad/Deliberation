package com.ziad.deliberation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.lowagie.text.DocumentException;
import com.ziad.enums.DeliberationType;
import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.AnneeAcademique;
import com.ziad.models.Deliberation;
import com.ziad.models.Etape;
import com.ziad.models.Filiere;
import com.ziad.models.Modulee;
import com.ziad.models.NoteEtape;
import com.ziad.models.NoteModule;
import com.ziad.models.Professeur;
import com.ziad.models.Semestre;
import com.ziad.models.User;
import com.ziad.repositories.AnnneAcademiqueRepository;
import com.ziad.repositories.DeliberationRepository;
import com.ziad.repositories.EtapeRepository;
import com.ziad.repositories.FiliereRepository;
import com.ziad.repositories.ModuleRepository;
import com.ziad.repositories.NotesModuleRepository;
import com.ziad.repositories.ProfesseurRepository;
import com.ziad.repositories.SemestreRepository;
import com.ziad.repositories.UserRepository;
import com.ziad.utilities.JSONConverter;
import com.ziad.utilities.PDFExport;

@Service
@Primary
public class DeliberationService implements DeliberationInterface {

	@Autowired
	private EtapeRepository etapeRepository;

	@Autowired
	private ModuleRepository moduleRepository;

	@Autowired
	private SemestreRepository semestreRepository;

	@Autowired
	private FiliereRepository filiereRepository;

	@Autowired
	private AnnneAcademiqueRepository anneeAcademiqueRepository;

	@Autowired
	private DeliberationRepository deliberationRepository;

	@Autowired
	private Algorithme algorithme;
	
	@Autowired
	private ProfesseurRepository professeurRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private NotesModuleRepository noteModuleRepository;

	@Autowired
	private JSONConverter converter;

	private static final String TYPE_DELIBERATION_MODULE = "parmodule";
	private static final String TYPE_DELIBERATION_SEMESTRE = "parsemestre";
	private static final String TYPE_DELIBERATION_ETAPE = "paretape";

	private static final String TYPE_DELIBERATION_ORDINAIRE = "ordinaire";
	private static final String TYPE_DELIBERATION_RATTRAPAGE = "rattrapage";
	
	
	private static final String ATTRIBUT_CLASSE_MERE = "mero";
	@Override
	public ArrayList<Object> getBesoinPageDeliberationParModule(HttpServletRequest req)
			throws DataNotFoundExceptions, EntityNotFoundException {
		List<Filiere> filieres = filiereRepository.findAll();
		List<AnneeAcademique> anneesAcademiques = anneeAcademiqueRepository.findAll();
		List<Semestre> semestres = semestreRepository.findAll();
		
		/**
		 * Verifier la connexion
		 * **/
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.getUserByUsername(auth.getName());
		List<Modulee> modules = null;
		if(user.isAdministrator()) {
			req.setAttribute(ATTRIBUT_CLASSE_MERE, "../layout.jsp");
		}else {
			req.setAttribute(ATTRIBUT_CLASSE_MERE, "../espace_professeur/layout-prof.jsp");
		}
		if(user.isAdministrator() || user.isResponsableFiliere()) {
			modules = moduleRepository.findAll();
		}else {
			if(user.isResponsableModule()) {
				Professeur prof = professeurRepository.getProfesseurByUser(user);
				modules = prof.getModules();
			}
		}
		ArrayList<Object> besoins = new ArrayList<Object>();
		besoins.add(filieres);
		besoins.add(anneesAcademiques);
		besoins.add(semestres);
		besoins.add(converter.convertModulee(modules));
		besoins.add(converter.convertSemestre(semestreRepository.findAll()));
		return besoins;
	}

	@Override
	public Deliberation deliberer(Long idAnneeAcademique, String type, Long id_element,
			String typeDeliberation, Integer consideration) throws DataNotFoundExceptions, EntityNotFoundException,
			DeliberationEtapeNotAllowed, DeliberationSemestreNotAllowed {
		AnneeAcademique annee = anneeAcademiqueRepository.getOne(idAnneeAcademique);
		if (typeDeliberation != null)
			if (typeDeliberation.equals(TYPE_DELIBERATION_ORDINAIRE)) {
				algorithme.enableDeliberationOrdinaire();
			} else if (typeDeliberation.equals(TYPE_DELIBERATION_RATTRAPAGE)) {
				if (consideration != null)
					algorithme.enableConsideration(true);
				algorithme.enableDeliberationRattrapage();
			}
		Deliberation delib = null;

		if (type.equals(TYPE_DELIBERATION_ETAPE)) {
			Etape etape = etapeRepository.getOne(id_element);
			delib = algorithme.delibererEtape(etape, annee);
		} else if (type.equals(TYPE_DELIBERATION_MODULE)) {
			Modulee module = moduleRepository.getOne(id_element);
			delib = algorithme.delibererModule(module, annee);
		} else if (type.equals(TYPE_DELIBERATION_SEMESTRE)) {
			Semestre semestre = semestreRepository.getOne(id_element);
			delib = algorithme.delibererSemestre(semestre, annee);
		}
		return delib;
	}

	@Override
	public Deliberation piocherDeliberation(Long idDelib) throws EntityNotFoundException {
		return deliberationRepository.getOne(idDelib);
	}

	@Override
	public void generateExcel(HttpServletResponse response, Long idDeliberation, Integer rattrapage)
			throws EntityNotFoundException, DocumentException, IOException {
		Deliberation deliberation = deliberationRepository.getOne(idDeliberation);
		List<NoteModule> notes = deliberation.getNotesModule();
		if (rattrapage != null) {
			notes = notes.stream().filter(n -> n.getEtat().equals(DeliberationType.RATTRAPAGE.name()))
					.collect(Collectors.toList());
		}
		PDFExport pdf = new PDFExport(response, "notesModule");
		pdf.generatePvModule(notes, deliberation.getModule());
		pdf.closeDocument();

	}
	
	@Override
	public void generateExcelEtape(HttpServletResponse response, Long idDeliberation)
			throws EntityNotFoundException, DocumentException, IOException {
		Deliberation deliberation = deliberationRepository.getOne(idDeliberation);
		List<NoteEtape> notes = deliberation.getNotesEtape();
		PDFExport pdf = new PDFExport(response, "notesEtape");
		pdf.generatePvEtape(notes, deliberation.getEtape());
		pdf.closeDocument();

	}

	@Override
	public List<Deliberation> listerDeliberation(HttpServletRequest req) throws DataNotFoundExceptions {
		/**
		 * Verifier la connexion
		 * **/
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.getUserByUsername(auth.getName());
		if(user.isAdministrator()) {
			req.setAttribute(ATTRIBUT_CLASSE_MERE, "../layout.jsp");
		}else {
			req.setAttribute(ATTRIBUT_CLASSE_MERE, "../espace_professeur/layout-prof.jsp");
		}
		
		List<Deliberation> listes = deliberationRepository.findAll();
		if (listes.size() == 0)
			throw new DataNotFoundExceptions();
		return listes;
	}

	@Override
	public ArrayList<Object> getBesoinPageDeliberationParSemestre(HttpServletRequest req)
			throws DataNotFoundExceptions, EntityNotFoundException {
		

		/**
		 * Verifier la connexion
		 * **/
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.getUserByUsername(auth.getName());
		List<Filiere> filieres = new ArrayList<Filiere>();
		List<Semestre> semestres = new ArrayList<Semestre>();
		if(user.isAdministrator()) {
			req.setAttribute(ATTRIBUT_CLASSE_MERE, "../layout.jsp");
			filieres = filiereRepository.findAll();
			semestres = semestreRepository.findAll();
		}else {
			req.setAttribute(ATTRIBUT_CLASSE_MERE, "../espace_professeur/layout-prof.jsp");
			Professeur prof = professeurRepository.getProfesseurByUser(user);
			filieres = prof.getFilieres();
			for (Filiere filiere : filieres) {
				for(Etape etape:filiere.getEtapes())
					semestres.addAll(etape.getSemestres());
			}
		}
		
		List<AnneeAcademique> anneesAcademiques = anneeAcademiqueRepository.findAll();
		ArrayList<Object> besoins = new ArrayList<Object>();
		besoins.add(filieres);
		besoins.add(anneesAcademiques);
		besoins.add(semestres);
		besoins.add(converter.convertSemestre(semestreRepository.findAll()));
		return besoins;
	}

	@Override
	public ArrayList<Object> getBesoinPageDeliberationParEtape(HttpServletRequest req)
			throws DataNotFoundExceptions, EntityNotFoundException {
		/**
		 * Verifier la connexion
		 * **/
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.getUserByUsername(auth.getName());
		List<Filiere> filieres = new ArrayList<Filiere>();
		List<Etape> etapes = new ArrayList<Etape>();
		if(user.isAdministrator()) {
			req.setAttribute(ATTRIBUT_CLASSE_MERE, "../layout.jsp");
			filieres = filiereRepository.findAll();
			etapes = etapeRepository.findAll();
		}else {
			req.setAttribute(ATTRIBUT_CLASSE_MERE, "../espace_professeur/layout-prof.jsp");
			Professeur prof = professeurRepository.getProfesseurByUser(user);
			filieres = prof.getFilieres();
			for (Filiere filiere : filieres) {
				etapes.addAll(filiere.getEtapes());
			}
		}
		
		List<AnneeAcademique> anneesAcademiques = anneeAcademiqueRepository.findAll();
		ArrayList<Object> besoins = new ArrayList<Object>();
		besoins.add(filieres);
		besoins.add(anneesAcademiques);
		besoins.add(etapes);
		besoins.add(converter.convertEtape(etapeRepository.findAll()));
		return besoins;
	}

	@Override
	public void generateUltimatePv(Long idDeliberation, HttpServletResponse response)
			throws EntityNotFoundException, DocumentException, IOException {
		Deliberation deliberation = deliberationRepository.getOne(idDeliberation);
		PDFExport pdf = new PDFExport(response, "PV");
		pdf.generateUltimatePv(deliberation.getSemestre(), noteModuleRepository, deliberation);
		pdf.closeDocument();
	}
	
	@Override
	public void saveExtendsLayout(HttpServletRequest req) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.getUserByUsername(auth.getName());
		if(user.isAdministrator()) {
			req.setAttribute(ATTRIBUT_CLASSE_MERE, "../layout.jsp");
		}else {
			req.setAttribute(ATTRIBUT_CLASSE_MERE, "../espace_professeur/layout-prof.jsp");
		}
	}
	
}

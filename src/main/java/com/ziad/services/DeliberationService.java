package com.ziad.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lowagie.text.DocumentException;
import com.ziad.enums.DeliberationType;
import com.ziad.enums.TypeNote;
import com.ziad.exceptions.CSVReaderOException;
import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.exceptions.DeliberationElementNotAllowed;
import com.ziad.exceptions.DeliberationEtapeNotAllowed;
import com.ziad.exceptions.DeliberationModuleNotAllowed;
import com.ziad.exceptions.DeliberationSemestreNotAllowed;
import com.ziad.exceptions.InvalidCredinals;
import com.ziad.models.AnneeAcademique;
import com.ziad.models.Deliberation;
import com.ziad.models.Element;
import com.ziad.models.Etape;
import com.ziad.models.Etudiant;
import com.ziad.models.Filiere;
import com.ziad.models.InscriptionPedagogique;
import com.ziad.models.Modulee;
import com.ziad.models.Note;
import com.ziad.models.NoteElement;
import com.ziad.models.NoteEtape;
import com.ziad.models.NoteModule;
import com.ziad.models.Professeur;
import com.ziad.models.Semestre;
import com.ziad.models.User;
import com.ziad.models.compositeid.ComposedInscriptionPedagogique;
import com.ziad.repositories.AnnneAcademiqueRepository;
import com.ziad.repositories.DeliberationRepository;
import com.ziad.repositories.ElementRepository;
import com.ziad.repositories.EtapeRepository;
import com.ziad.repositories.EtudiantRepository;
import com.ziad.repositories.FiliereRepository;
import com.ziad.repositories.InscriptionPedagogiqueRepository;
import com.ziad.repositories.ModuleRepository;
import com.ziad.repositories.NoteElementRepository;
import com.ziad.repositories.NotesModuleRepository;
import com.ziad.repositories.ProfesseurRepository;
import com.ziad.repositories.SemestreRepository;
import com.ziad.repositories.UserRepository;
import com.ziad.services.interfaces.DeliberationInterface;
import com.ziad.utilities.Algorithms;
import com.ziad.utilities.ExcelExport;
import com.ziad.utilities.ExcelReader;
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

	@Autowired
	private ElementRepository elementRepository;

	@Autowired
	private InscriptionPedagogiqueRepository inscriptionPedagogiqueRepository;

	@Autowired
	private ExcelReader reader;

	@Autowired
	private EtudiantRepository etudiantRepository;

	@Autowired
	private NoteElementRepository noteElementRepository;

	private ExcelExport generator_excel;

	@Autowired
	private Algorithms algorithmeRepository;

	private static final String ATTRIBUT_CLASSE_MERE = "mero";

	public void generateExcel(Long id_element, Long id_annee, HttpServletResponse response)
			throws EntityNotFoundException, IOException {
		Element element = elementRepository.getOne(id_element);
		AnneeAcademique annee = anneeAcademiqueRepository.getOne(id_annee);
		List<Etudiant> etudiants = inscriptionPedagogiqueRepository.getEtudiantsByElementAndAnneeAcademique(element,
				annee);

		generator_excel = new ExcelExport(etudiants);
		generator_excel.export(response, TypeNote.EXAM_ORDINAIRE);

	}

	private void readExcelOrdinaire(AnneeAcademique annee, Element element, MultipartFile file,
			Double coefficientControl, Double coefficientTp, Double coefficientExam)
			throws DataNotFoundExceptions, EntityNotFoundException, IOException, CSVReaderOException, InvalidCredinals {
		// Exception
		InvalidCredinals credinals = new InvalidCredinals();

		List<NoteElement> notesElement = new ArrayList<NoteElement>();

		Iterator<Row> rows = reader.readInscriptionAdministrative(file);
		rows.next();
		int cmp = 1;
		while (rows.hasNext()) {
			try {

				Row row = rows.next();
				String massar = row.getCell(0).getStringCellValue();
				String nom = row.getCell(1).getStringCellValue();
				String prenom = row.getCell(2).getStringCellValue();
				Double noteControl = null;
				try {
					noteControl = row.getCell(3).getNumericCellValue();
				} catch (Exception e) {
					noteControl = 0d;
				}

				Double noteTp = null;
				try {
					noteTp = row.getCell(4).getNumericCellValue();
				} catch (Exception e) {
					noteTp = 0d;
				}

				Double noteExam = null;
				boolean abscent = false;
				try {
					noteExam = row.getCell(5).getNumericCellValue();
				} catch (Exception e) {
					noteExam = 0d;
					abscent = true;
				}

				List<Etudiant> etudiant = etudiantRepository.listerEtudiantParMassarNomPrenom(massar, nom, prenom);
				if (etudiant.size() != 1) {
					credinals.addErreur("Erreur " + cmp,
							"l'etudiant " + massar + " " + nom + " " + prenom + " est introuvable");

				} else {
					NoteElement noteElement = noteElementRepository
							.getOne(new ComposedInscriptionPedagogique(etudiant.get(0), element, annee));
					Note noteControlObj = new Note(noteControl, coefficientControl, TypeNote.CONTROL, noteElement);
					Note noteTpObj = new Note(noteTp, coefficientTp, TypeNote.TP, noteElement);
					Note noteExamObj = new Note(noteExam, coefficientExam, TypeNote.EXAM_ORDINAIRE, noteElement);
					noteElement.addNote(noteExamObj);
					noteElement.addNote(noteControlObj);
					noteElement.addNote(noteTpObj);
					noteElement.setAbscent(abscent);
					notesElement.add(noteElement);
				}

				cmp++;
			} catch (Exception e) {
				credinals.addErreur("Erreur " + cmp, " il se trouve une erreur dans la ligne " + cmp);
			}

		}
		if (credinals.allow()) {
			noteElementRepository.saveAll(notesElement);
		} else {
			throw credinals;
		}

	}

	private void readExcelRattrapage(AnneeAcademique annee, Element element, MultipartFile file, Double coefficientExam)
			throws CSVReaderOException, IOException, InvalidCredinals {
		// Exception
		InvalidCredinals credinals = new InvalidCredinals();
		List<NoteElement> notesElement = new ArrayList<NoteElement>();

		Iterator<Row> rows = reader.readInscriptionAdministrative(file);
		rows.next();
		int cmp = 1;
		while (rows.hasNext()) {
			try {

				Row row = rows.next();
				String massar = row.getCell(0).getStringCellValue();
				String nom = row.getCell(1).getStringCellValue();
				String prenom = row.getCell(2).getStringCellValue();
				Double noteExam = null;
				boolean abscent = false;
				try {
					noteExam = row.getCell(3).getNumericCellValue();

				} catch (Exception e) {
					noteExam = 0d;
					abscent = true;
				}

				List<Etudiant> etudiant = etudiantRepository.listerEtudiantParMassarNomPrenom(massar, nom, prenom);
				if (etudiant.size() != 1) {
					credinals.addErreur("Erreur " + cmp,
							"l'etudiant " + massar + " " + nom + " " + prenom + " est introuvable");

				} else {
					NoteElement noteElement = noteElementRepository
							.getOne(new ComposedInscriptionPedagogique(etudiant.get(0), element, annee));
					Note noteExamObj = new Note(noteExam, coefficientExam, TypeNote.EXAM_RATTRAPAGE, noteElement);
					noteElement.addNote(noteExamObj);
					noteElement.setAbscent(abscent);
					notesElement.add(noteElement);
				}

				cmp++;
			} catch (Exception e) {
				credinals.addErreur("Erreur " + cmp, " il se trouve une erreur dans la ligne " + cmp);
			}

		}
		if (credinals.allow()) {
			noteElementRepository.saveAll(notesElement);
		} else {
			throw credinals;
		}
	}

	public Deliberation delibererElementOrdinaire(Long idAnneeAcademique, Long id_element, MultipartFile file,
			Double coefficientControl, Double coefficientTp, Double coefficientExam)
			throws EntityNotFoundException, InvalidCredinals, DataNotFoundExceptions, IOException, CSVReaderOException {
		AnneeAcademique annee = anneeAcademiqueRepository.getOne(idAnneeAcademique);
		Element element = elementRepository.getOne(id_element);
		readExcelOrdinaire(annee, element, file, coefficientControl, coefficientTp, coefficientExam);
		return algorithme.delibererElementOrdinaire(element, annee);
	}

	public Deliberation delibererElementRattrapage(Long idDeliberation, MultipartFile file,
			Double coefficientExamRattrapage, Integer consideration)
			throws InvalidCredinals, CSVReaderOException, IOException {
		Deliberation deliberation = deliberationRepository.getOne(idDeliberation);
		AnneeAcademique annee = deliberation.getAnneeAcademique();
		Element element = deliberation.getElement();

		readExcelRattrapage(deliberation.getAnneeAcademique(), element, file, coefficientExamRattrapage);

		return algorithme.delibererElementRattrapage(element, annee, consideration);
	}

	public Deliberation delibererModule(Long idModule, Long idAnne)
			throws DeliberationModuleNotAllowed, DeliberationElementNotAllowed {
		Modulee module = moduleRepository.getOne(idModule);
		AnneeAcademique annee = anneeAcademiqueRepository.getOne(idAnne);
		return algorithme.delibererModule(module, annee);
	}
	
	@Override
	public Deliberation delibererSemestre(Long idSemestre, Long idAnne) throws DeliberationSemestreNotAllowed {
		Semestre semestre = semestreRepository.getOne(idSemestre);
		AnneeAcademique annee = anneeAcademiqueRepository.getOne(idAnne);
		return algorithme.delibererSemestre(semestre, annee);
	}
	
	@Override
	public Deliberation delibererEtape(Long idEtape, Long idAnne) throws DeliberationEtapeNotAllowed {
		Etape etape = etapeRepository.getOne(idEtape);
		AnneeAcademique annee = anneeAcademiqueRepository.getOne(idAnne);
		return algorithme.delibererEtape(etape, annee);
	}

	@Override
	public ArrayList<Object> getBesoinPageDeliberationParElement(HttpServletRequest req)
			throws DataNotFoundExceptions, EntityNotFoundException {
		List<Filiere> filieres = filiereRepository.findAll();
		AnneeAcademique anneesAcademiques = algorithmeRepository.grabAnneeAcademiqueActuel();
		List<Semestre> semestres = semestreRepository.findAll();

		/**
		 * Verifier la connexion
		 **/
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.getUserByUsername(auth.getName());
		List<Modulee> modules = null;

		if (user.isAdministrator()) {
			req.setAttribute(ATTRIBUT_CLASSE_MERE, "../layout.jsp");
		} else {
			req.setAttribute(ATTRIBUT_CLASSE_MERE, "../espace_professeur/layout-prof.jsp");
		}

		if (user.isAdministrator() || user.isResponsableFiliere()) {
			modules = moduleRepository.findAll();
		} else {
			if (user.isResponsableModule()) {
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
		besoins.add(converter.convertElements(elementRepository.findAll()));
		return besoins;
	}

	@Override
	public ArrayList<Object> getBesoinPageDeliberationParModule(HttpServletRequest req)
			throws DataNotFoundExceptions, EntityNotFoundException {
		List<Filiere> filieres = filiereRepository.findAll();
		List<Semestre> semestres = semestreRepository.findAll();

		/**
		 * Verifier la connexion
		 **/
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.getUserByUsername(auth.getName());
		List<Modulee> modules = null;
		if (user.isAdministrator()) {
			req.setAttribute(ATTRIBUT_CLASSE_MERE, "../layout.jsp");
		} else {
			req.setAttribute(ATTRIBUT_CLASSE_MERE, "../espace_professeur/layout-prof.jsp");
		}
		if (user.isAdministrator() || user.isResponsableFiliere()) {
			modules = moduleRepository.findAll();
		} else {
			if (user.isResponsableModule()) {
				Professeur prof = professeurRepository.getProfesseurByUser(user);
				modules = prof.getModules();
			}
		}
		ArrayList<Object> besoins = new ArrayList<Object>();
		besoins.add(filieres);
		besoins.add(algorithmeRepository.grabAnneeAcademiqueActuel());
		besoins.add(semestres);
		besoins.add(converter.convertModulee(modules));
		besoins.add(converter.convertSemestre(semestreRepository.findAll()));
		return besoins;
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
		 **/
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.getUserByUsername(auth.getName());
		if (user.isAdministrator()) {
			req.setAttribute(ATTRIBUT_CLASSE_MERE, "../layout.jsp");
		} else {
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
		 **/
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.getUserByUsername(auth.getName());
		List<Filiere> filieres = new ArrayList<Filiere>();
		List<Semestre> semestres = new ArrayList<Semestre>();
		if (user.isAdministrator()) {
			req.setAttribute(ATTRIBUT_CLASSE_MERE, "../layout.jsp");
			filieres = filiereRepository.findAll();
			semestres = semestreRepository.findAll();
		} else {
			req.setAttribute(ATTRIBUT_CLASSE_MERE, "../espace_professeur/layout-prof.jsp");
			Professeur prof = professeurRepository.getProfesseurByUser(user);
			filieres = prof.getFilieres();
			for (Filiere filiere : filieres) {
				for (Etape etape : filiere.getEtapes())
					semestres.addAll(etape.getSemestres());
			}
		}

		ArrayList<Object> besoins = new ArrayList<Object>();
		besoins.add(filieres);
		besoins.add(algorithmeRepository.grabAnneeAcademiqueActuel());
		besoins.add(semestres);
		JSONConverter converter = new JSONConverter();
		List<Semestre> semestresNew = new ArrayList<Semestre>();
		
		List<Semestre> semestre = semestreRepository.findAll();
		semestre.stream().forEach((Semestre s) -> semestresNew.add(new Semestre(s)));
		besoins.add(converter.convertSemestre(semestresNew));
		return besoins;
	}

	@Override
	public ArrayList<Object> getBesoinPageDeliberationParEtape(HttpServletRequest req)
			throws DataNotFoundExceptions, EntityNotFoundException {
		/**
		 * Verifier la connexion
		 **/
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.getUserByUsername(auth.getName());
		List<Filiere> filieres = new ArrayList<Filiere>();
		List<Etape> etapes = new ArrayList<Etape>();
		if (user.isAdministrator()) {
			req.setAttribute(ATTRIBUT_CLASSE_MERE, "../layout.jsp");
			filieres = filiereRepository.findAll();
			etapes = etapeRepository.findAll();
		} else {
			req.setAttribute(ATTRIBUT_CLASSE_MERE, "../espace_professeur/layout-prof.jsp");
			Professeur prof = professeurRepository.getProfesseurByUser(user);
			filieres = prof.getFilieres();
			for (Filiere filiere : filieres) {
				etapes.addAll(filiere.getEtapes());
			}
		}

		AnneeAcademique anneeAcademique = algorithmeRepository.grabAnneeAcademiqueActuel();
		ArrayList<Object> besoins = new ArrayList<Object>();
		besoins.add(filieres);
		besoins.add(anneeAcademique);
		List<Etape> etapesNew = new ArrayList<Etape>();
		etapes.stream().forEach((Etape e) -> etapesNew.add(new Etape(e)));
		besoins.add(etapesNew);
		besoins.add(converter.convertEtape(etapesNew));
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
		if (user.isAdministrator()) {
			req.setAttribute(ATTRIBUT_CLASSE_MERE, "../layout.jsp");
		} else {
			req.setAttribute(ATTRIBUT_CLASSE_MERE, "../espace_professeur/layout-prof.jsp");
		}
	}

	@Override
	public String getEtudiants(Long idElement, Long idAnneeAcademique) {
		try {
			if (idElement == null || idAnneeAcademique == null)
				return "[]";

			AnneeAcademique annee = anneeAcademiqueRepository.getOne(idAnneeAcademique);
			Element element = elementRepository.getOne(idElement);

			List<InscriptionPedagogique> etudiants = inscriptionPedagogiqueRepository
					.getInscriptionPedagogiquesByElementAndAnneeAcademique(element, annee);
			return converter.convertInscriptionsPedagogiques(etudiants);
		} catch (EntityNotFoundException e) {
			return "[]";
		}
	}

	@Override
	public void generateExcelRattrapage(Long idDeliberation, HttpServletResponse response)
			throws EntityNotFoundException, IOException {
		Deliberation deliberation = deliberationRepository.getOne(idDeliberation);
		Element element = deliberation.getElement();
		AnneeAcademique annee = deliberation.getAnneeAcademique();
		List<NoteElement> etudiants = noteElementRepository.getNoteElementAnnee(element, annee);
		etudiants = etudiants.stream().filter(x -> !x.isValid() && !x.isAbscent()).collect(Collectors.toList());
		generator_excel = new ExcelExport(convertToStudents(etudiants));
		generator_excel.export(response, TypeNote.EXAM_RATTRAPAGE);

	}

	private List<Etudiant> convertToStudents(List<NoteElement> notes) {
		ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>();
		for (NoteElement note : notes) {
			etudiants.add(note.getEtudiant());
		}
		return etudiants;
	}

	@Override
	public Deliberation generatePvDeliberationElement(Long idDeliberation, HttpServletResponse response)
			throws EntityNotFoundException, DataNotFoundExceptions, MalformedURLException, IOException,
			DocumentException {
		Deliberation deliberation = deliberationRepository.getOne(idDeliberation);
		PDFExport pdf = new PDFExport(response, "PvElement");
		pdf.generatePvElement(deliberation.getNotesElement(), deliberation.getElement());
		pdf.closeDocument();
		return deliberation;
	}

}

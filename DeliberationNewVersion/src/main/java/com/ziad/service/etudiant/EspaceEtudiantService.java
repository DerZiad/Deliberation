package com.ziad.service.etudiant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.lowagie.text.DocumentException;
import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.Deliberation;
import com.ziad.models.Etudiant;
import com.ziad.models.InscriptionPedagogique;
import com.ziad.models.Modulee;
import com.ziad.models.NoteModule;
import com.ziad.models.NoteSemestre;
import com.ziad.models.Semestre;
import com.ziad.models.User;
import com.ziad.models.compositeid.ComposedNoteModule;
import com.ziad.models.compositeid.ComposedNoteSemestre;
import com.ziad.repositories.EtudiantRepository;
import com.ziad.repositories.InscriptionPedagogiqueRepository;
import com.ziad.repositories.NotesModuleRepository;
import com.ziad.repositories.NotesSemestreRepository;
import com.ziad.repositories.SemestreRepository;
import com.ziad.repositories.UserRepository;
import com.ziad.utilities.PDFExport;

@Service
@Primary
public class EspaceEtudiantService implements EspaceEtudiantInterface {

	@Autowired
	public UserRepository userRepository;
	@Autowired
	private EtudiantRepository etudiantRepository;
	@Autowired
	private InscriptionPedagogiqueRepository inscriptionPedagogiqueRepository;
	@Autowired
	private NotesSemestreRepository noteSemestreRepository;
	@Autowired
	private SemestreRepository semestreRepository;
	@Autowired
	private NotesModuleRepository notesModuleRepository;

	@Override
	public List<Semestre> listerSemestres() throws DataNotFoundExceptions {
		Etudiant etudiant = getSessionEtudiant();
		List<InscriptionPedagogique> inscriptionsPedagogiques = inscriptionPedagogiqueRepository
				.getInscriptionsPedagogiqueByEtudiant(etudiant);
		ArrayList<Semestre> semestres = new ArrayList<Semestre>();

		for (InscriptionPedagogique inscriptionPedagogique : inscriptionsPedagogiques) {
			if (!semestres.contains(inscriptionPedagogique.getElement().getModule().getSemestre())) {
				semestres.add(inscriptionPedagogique.getElement().getModule().getSemestre());
			}
		}

		return semestres;
	}

	@Override
	public List<Object> getNotes(Long idSemestre) throws EntityNotFoundException {
		Etudiant etudiant = getSessionEtudiant();
		Semestre semestre = semestreRepository.getOne(idSemestre);
		NoteSemestre note = noteSemestreRepository.getOne(new ComposedNoteSemestre(semestre, etudiant));

		ArrayList<NoteModule> notesModule = new ArrayList<NoteModule>();

		for (Modulee module : semestre.getModules()) {
			try {

				Optional<NoteModule> noteModule = notesModuleRepository
						.findById(new ComposedNoteModule(module, etudiant));
				NoteModule noteModuleOpt = noteModule.get();
				notesModule.add(noteModuleOpt);
			} catch (NoSuchElementException e) {
				System.out.println("Erreur");
			}
		}

		notesModule.forEach(System.out::println);

		ArrayList<Object> besoins = new ArrayList<Object>();
		besoins.add(note);
		besoins.add(notesModule);
		return besoins;
	}

	private Etudiant getSessionEtudiant() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.getUserByUsername(username);
		return etudiantRepository.getEtudiantByUser(user);
	}

	@Override
	public List<Semestre> getScolaritePage() throws DataNotFoundExceptions {
		Etudiant etudiant = getSessionEtudiant();
		List<Semestre> semestres = filterSemestre(inscriptionPedagogiqueRepository.getSemestresByEtudiant(etudiant));
		return semestres;
	}

	private List<Semestre> filterSemestre(List<Semestre> semestres) {
		List<Semestre> semestreFiltred = new ArrayList<Semestre>();
		for (Semestre sem : semestres) {
			if (!semestreFiltred.contains(sem)) {
				semestreFiltred.add(sem);
			}
		}
		return semestreFiltred;
	}

	@Override
	public void generateCertificatScolarite(Long idSemestre, HttpServletResponse response)
			throws EntityNotFoundException, DocumentException, IOException {
		Semestre semestre = semestreRepository.getOne(idSemestre);
		PDFExport pdf = new PDFExport(response, "CertificatScolariteSemestre");
		pdf.generateScolarCertificat(semestre, getSessionEtudiant());
		pdf.closeDocument();
	}

	@Override
	public void generateReleveNote(Long idSemestre, HttpServletResponse response)
			throws DocumentException, IOException {
		Semestre semestre = semestreRepository.getOne(idSemestre);
		PDFExport pdf = new PDFExport(response, "CertificatScolariteSemestre");
		pdf.generateEtudiantReleve(semestre, getSessionEtudiant(), notesModuleRepository);
		pdf.closeDocument();
	}

}

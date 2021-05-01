package com.ziad.professeurespace.notes;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ziad.enums.TypeNote;
import com.ziad.exceptions.CSVReaderOException;
import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.Element;
import com.ziad.models.Etudiant;
import com.ziad.models.InscriptionPedagogique;
import com.ziad.models.NoteElement;
import com.ziad.models.compositeid.ComposedInscriptionPedagogique;
import com.ziad.repositories.AnnneAcademiqueRepository;
import com.ziad.repositories.DocumentDePlusRepository;
import com.ziad.repositories.ElementRepository;
import com.ziad.repositories.EtablissementRepository;
import com.ziad.repositories.EtapeRepository;
import com.ziad.repositories.EtudiantRepository;
import com.ziad.repositories.FiliereRepository;
import com.ziad.repositories.InscriptionAdministrativeRepository;
import com.ziad.repositories.InscriptionEnLigneRepository;
import com.ziad.repositories.InscriptionPedagogiqueRepository;
import com.ziad.repositories.ModuleRepository;
import com.ziad.repositories.NoteElementRepository;
import com.ziad.repositories.ProfesseurRepository;
import com.ziad.repositories.SemestreRepository;
import com.ziad.repositories.UserRepository;
import com.ziad.utilities.ExcelReader;

@Service
@Primary
public class NoteService implements NoteInterface {
	@Autowired
	private AnnneAcademiqueRepository annee_academique;
	@Autowired
	private DocumentDePlusRepository documentDePlusRepository;
	@Autowired
	private ElementRepository elementRepository;
	@Autowired
	private EtablissementRepository etablissementRepository;
	@Autowired
	private EtapeRepository etapeRepository;
	@Autowired
	private EtudiantRepository etudiantRepository;
	@Autowired
	private FiliereRepository filiereRepository;
	@Autowired
	private InscriptionAdministrativeRepository inscriptionAdministrativeRepository;
	@Autowired
	private InscriptionEnLigneRepository inscriptionEnLigneRepository;
	@Autowired
	private InscriptionPedagogiqueRepository inscriptionPedagogiqueRepository;
	@Autowired
	private ModuleRepository moduleRepository;
	@Autowired
	private NoteElementRepository noteElementRepository;
	@Autowired
	private ProfesseurRepository professeurRepository;
	@Autowired
	private SemestreRepository semestreRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private ExcelReader reader;

	@Override
	public void readExcel(MultipartFile file, String type,Double coefficient)
			throws DataNotFoundExceptions, EntityNotFoundException, IOException, CSVReaderOException {

		Iterator<Row> rows = reader.readInscriptionAdministrative(file);
		Row second = rows.next();
		Long id_element = (long) second.getCell(2).getNumericCellValue();
		Element element = elementRepository.getOne(id_element);

		while (rows.hasNext()) {
			try {
				Row row = rows.next();
				String massar = row.getCell(0).getStringCellValue();
				String nom = row.getCell(1).getStringCellValue();
				String prenom = row.getCell(2).getStringCellValue();
				Double note = row.getCell(5).getNumericCellValue();
				List<Etudiant> etudiant = etudiantRepository.getStudentByNationality(massar, nom, prenom);
				if (etudiant.size() != 1)
					throw new DataNotFoundExceptions();
				TypeNote note_type = null;
				for (TypeNote type_object : TypeNote.values()) {
					if (type_object.name() == type)
						note_type = type_object;
				}
				InscriptionPedagogique inscription_pedagogique = inscriptionPedagogiqueRepository
						.getOne(new ComposedInscriptionPedagogique(etudiant.get(0), element));
				NoteElement note_element = new NoteElement(note, coefficient, note_type, inscription_pedagogique);
				inscription_pedagogique.addNote(note_element);
				inscriptionPedagogiqueRepository.save(inscription_pedagogique);
			} catch (Exception e) {

			}

		}

	}

}

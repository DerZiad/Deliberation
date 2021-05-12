package com.ziad.professeurespace.notes;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ziad.enums.TypeNote;
import com.ziad.exceptions.CSVReaderOException;
import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.models.Element;
import com.ziad.models.Etudiant;
import com.ziad.models.Note;
import com.ziad.models.NoteElement;
import com.ziad.models.compositeid.ComposedInscriptionPedagogique;
import com.ziad.repositories.ElementRepository;
import com.ziad.repositories.EtudiantRepository;
import com.ziad.repositories.NoteElementRepository;
import com.ziad.repositories.NoteRepository;
import com.ziad.utilities.ExcelReader;

@Service
@Primary
public class NoteService implements NoteInterface {
	@Autowired
	private ElementRepository elementRepository;
	@Autowired
	private EtudiantRepository etudiantRepository;
	@Autowired
	private NoteElementRepository noteElementRepository;
	@Autowired
	private ExcelReader reader;
	@Autowired
	private NoteRepository noteRepository;
	@Override
	public void readExcel(MultipartFile file, String type,Double coefficient)
			throws DataNotFoundExceptions, EntityNotFoundException, IOException, CSVReaderOException {

		Iterator<Row> rows = reader.readInscriptionAdministrative(file);
		Row second = rows.next();
		Long id_element = (long) second.getCell(2).getNumericCellValue();
		Element element = elementRepository.getOne(id_element);
		rows.next();
		while (rows.hasNext()) {
			try {
				Row row = rows.next();
				String massar = row.getCell(0).getStringCellValue();
				String nom = row.getCell(1).getStringCellValue();
				String prenom = row.getCell(2).getStringCellValue();
				Double note = row.getCell(3).getNumericCellValue();
				List<Etudiant> etudiant = etudiantRepository.listerEtudiantParMassarNomPrenom(massar, nom, prenom);
				System.out.println(etudiant.size());
				if (etudiant.size() != 1)
					throw new DataNotFoundExceptions();
				TypeNote note_type = null;
				for (TypeNote type_object : TypeNote.values()) {
					if (type_object.name() == type)
						note_type = type_object;
				}
				NoteElement noteElement = noteElementRepository.getOne(new ComposedInscriptionPedagogique(etudiant.get(0),element));
				Note noteS = new Note(note, coefficient, note_type, noteElement);
				noteElement.addNote(noteS);				
				noteElementRepository.save(noteElement);
				noteRepository.save(noteS);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}

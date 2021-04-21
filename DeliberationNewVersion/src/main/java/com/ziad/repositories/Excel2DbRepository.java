package com.ziad.repositories;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Iterator;

import javax.persistence.PersistenceContext;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Table.Cell;
import com.lowagie.text.Row;
import com.ziad.models.Etudiant;
import com.ziad.models.Filiere;
import com.ziad.models.InscriptionAdministrative;
import com.ziad.models.InscriptionEnLigne;

public class Excel2DbRepository {
	/*
	@PersistenceContext

	@Autowired
	private InscriptionEnLigneRepository ier;

	private EtudiantRepository etudiantRepository;
	private FiliereRepository filiereRepository;
	private InscriptionAdministrativeRepository inscriptionAdministrativeRepository;

	public Excel2DbRepository() {
		// TODO Auto-generated constructor stub
	}

	public Excel2DbRepository(EtudiantRepository etudiantRepository, InscriptionEnLigneRepository ielr,
			FiliereRepository filiereRepository,
			InscriptionAdministrativeRepository inscriptionAdministrativeRepository) {

		this.etudiantRepository = etudiantRepository;
		this.ier = ielr;
		this.filiereRepository = filiereRepository;
		this.inscriptionAdministrativeRepository = inscriptionAdministrativeRepository;
	}

	public void addIaFromExcel(String excelFilePath) {

		InscriptionAdministrative ia = new InscriptionAdministrative();

		try {
			long start = System.currentTimeMillis();

			FileInputStream inputStream = new FileInputStream(excelFilePath);

			Workbook workbook = new XSSFWorkbook(inputStream);

			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = firstSheet.iterator();

			rowIterator.next(); // skip the header row

			while (rowIterator.hasNext()) {
				Row nextRow = rowIterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();

				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();

					int columnIndex = nextCell.getColumnIndex();

					switch (columnIndex) {
					case 0:
						String annee_academique = nextCell.getStringCellValue();
						ia.getAnnee_academique().setAnnee_academique(Integer.parseInt(annee_academique));
						break;
					case 1:
						Date date_pre_inscription = nextCell.getDateCellValue();
						ia.setDate_pre_inscription(date_pre_inscription);
						break;
					case 2:
						Date date_valid_inscription = nextCell.getDateCellValue();
						ia.setDate_valid_inscription(date_valid_inscription);
						break;
					case 3:
						String fullNameEtudiant = nextCell.getStringCellValue();
						String[] nameEtudiant = fullNameEtudiant.split(" ");
						String first_name_fr = nameEtudiant[0];
						String last_name_fr = nameEtudiant[1];
						InscriptionEnLigne iel = ier.findByNameAccepted(first_name_fr, last_name_fr);
						Etudiant e = new Etudiant();
						etudiantRepository.copyIeEtudiant(iel.get);
						ia.setEtudiant(e);
						break;
					case 4:
						int filieres_id_filiere = (int) nextCell.getNumericCellValue();
						Filiere f = filiereRepository.getOne(filieres_id_filiere);
						ia.setFilieres(f);
						break;
					case 5:
						String operateur = nextCell.getStringCellValue();
						ia.setOperateur(operateur);
						break;
					}

				}

				inscriptionAdministrativeRepository.save(ia);

			}

			workbook.close();

			long end = System.currentTimeMillis();
			System.out.printf("Import done in %d ms\n", (end - start));

		} catch (IOException ex1) {
			System.out.println("Error reading file");
			ex1.printStackTrace();
		}

	}

	public Path write(MultipartFile file, Path dir) throws IOException {
		Path filepath = Paths.get(dir.toString(), file.getOriginalFilename());

		Files.write(filepath, file.getBytes());

		return filepath;

	}
	*/
}

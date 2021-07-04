package com.ziad.utilities;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ziad.enums.TypeNote;
import com.ziad.models.Etudiant;

public class ExcelExport {

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<Etudiant> etudiants;

	public ExcelExport(List<Etudiant> etudiants) {
		super();
		this.etudiants = etudiants;
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("Etudiants");
	}

	public void export(HttpServletResponse response, TypeNote note) throws IOException {
		writeHeaderRow(note);
		fillStudent();
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		outputStream.close();
	}

	private void writeHeaderRow(TypeNote note) {

		Row row = sheet.createRow(0);

		Cell cell = row.createCell(0);
		cell.setCellValue("Massar");

		Cell cell1 = row.createCell(1);
		cell1.setCellValue("Nom");

		Cell cell2 = row.createCell(2);
		cell2.setCellValue("Prenom");

		if (note.equals(TypeNote.EXAM_ORDINAIRE)) {
			Cell cell3 = row.createCell(3);
			cell3.setCellValue(TypeNote.CONTROL.name());

			Cell cell4 = row.createCell(4);
			cell4.setCellValue(TypeNote.TP.name());
			Cell cell5 = row.createCell(5);
			cell5.setCellValue(note.name());
		}else {
			Cell cell5 = row.createCell(3);
			cell5.setCellValue(note.name());
		}

		

	}

	private void fillStudent() {
		for (int i = 0; i < etudiants.size(); i++) {
			Row row = sheet.createRow(i + 1);

			Cell cell = row.createCell(0);
			cell.setCellValue(etudiants.get(i).getMassar_edu());

			Cell cell1 = row.createCell(1);
			cell1.setCellValue(etudiants.get(i).getFirst_name_fr());

			Cell cell2 = row.createCell(2);
			cell2.setCellValue(etudiants.get(i).getLast_name_fr());

			Cell cell3 = row.createCell(3);
			cell3.setCellValue("");

			Cell cell4 = row.createCell(4);
			cell4.setCellValue("");

			Cell cell5 = row.createCell(5);
			cell5.setCellValue("");
		}
	}

}

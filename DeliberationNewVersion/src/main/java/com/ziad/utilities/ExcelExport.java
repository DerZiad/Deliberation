package com.ziad.utilities;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ziad.models.Etudiant;

public class ExcelExport {

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<Etudiant> etudiants;
	private String nom_element;
	private Long id_element;
	
	public ExcelExport(List<Etudiant> etudiants,String nom_element,Long id_element) {
		super();
		this.etudiants = etudiants;
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("Etudiants");
		this.nom_element = nom_element;
		this.id_element = id_element;
	}

	public void export(HttpServletResponse response) throws IOException{
		writeHeaderRow();
		fillStudent();
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		outputStream.close();
	}

	private void writeHeaderRow() {
		
		Row element = sheet.createRow(0);
		
		Cell celle1 = element.createCell(0);
		celle1.setCellValue("Nom element");
		
		Cell celle2 = element.createCell(1);
		celle2.setCellValue(nom_element);
		
		Cell celle3 = element.createCell(2);
		celle3.setCellValue(id_element);
		
		
		Row row = sheet.createRow(1);
		
		
		Cell cell = row.createCell(0);
		cell.setCellValue("Massar");
		
		Cell cell1 = row.createCell(1);
		cell1.setCellValue("Nom");
		
		Cell cell2 = row.createCell(2);
		cell2.setCellValue("Prenom");
		
		Cell cell3 = row.createCell(3);
		cell3.setCellValue("Note ");
		
	}
	
	private void fillStudent() {
		for (int i = 0; i < etudiants.size(); i++) {
			Row row = sheet.createRow(i+2);
			
			Cell cell = row.createCell(0);
			cell.setCellValue(etudiants.get(i).getMassar_edu());
			
			Cell cell1 = row.createCell(1);
			cell1.setCellValue(etudiants.get(i).getFirst_name_fr());
			
			Cell cell2 = row.createCell(2);
			cell2.setCellValue(etudiants.get(i).getLast_name_fr());
			
			Cell cell3 = row.createCell(3);
			cell3.setCellValue("");
		}
	}
	
}

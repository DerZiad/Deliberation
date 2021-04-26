package com.ziad.services;

import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class ExcelToDbService {

	
	public Iterator<Row> readInscriptionAdministrative(MultipartFile file) throws CSVReaderOException,IOException{
		Workbook workbook = null;
		workbook = new XSSFWorkbook(file.getInputStream());
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rows = sheet.iterator();
		return rows;
	}
}

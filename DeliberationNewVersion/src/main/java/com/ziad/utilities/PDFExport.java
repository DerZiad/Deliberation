package com.ziad.utilities;

import java.awt.Color;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.ziad.models.Deliberation;
import com.ziad.models.Etudiant;
import com.ziad.models.Modulee;
import com.ziad.models.NoteModule;
import com.ziad.models.NoteSemestre;
import com.ziad.models.Professeur;
import com.ziad.models.Semestre;
import com.ziad.models.compositeid.ComposedNoteModule;
import com.ziad.repositories.ModuleRepository;
import com.ziad.repositories.NotesModuleRepository;
import com.ziad.repositories.NotesSemestreRepository;

public class PDFExport {

	private Document document;

	public PDFExport(HttpServletResponse response, String filename) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=" + filename + ".pdf";
		response.setHeader(headerKey, headerValue);

		/*
		 * Initializing document
		 */
		document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();

	}

	public void generatePvModule(List<NoteModule> listes, Modulee module) throws DocumentException, MalformedURLException, IOException {

		document.addTitle("Deliberation pour " + module.getLibelle_module());
		Image image = Image.getInstance("header.png");
		image.setBorderWidthBottom(100);
		image.setBorderWidthLeft(150);
		image.setBorderWidthRight(150);
		PdfPTable tableimage = new PdfPTable(1);
		
		PdfPCell cell = new PdfPCell(image);
		tableimage.addCell(cell);
		tableimage.setSpacingAfter(15);
		document.add(tableimage);
		
		
		
		Font textDeValeur = new Font(Font.COURIER, 18, Font.BOLD);
		textDeValeur.setColor(136, 14, 79);

		Font simpleText = new Font(Font.COURIER, 18, Font.BOLDITALIC);

		Paragraph paragModule = new Paragraph("Module");
		paragModule.setFont(textDeValeur);
		Paragraph paragModule1 = new Paragraph("     " + module.getLibelle_module());
		paragModule1.setFont(simpleText);

		Paragraph paragReponsable = new Paragraph("Reponsable");
		paragReponsable.setSpacingBefore(14);
		paragReponsable.setFont(textDeValeur);
		Paragraph paragReponsable1 = new Paragraph("     " + module.getResponsable_module().getNom_professeur() + " "
				+ module.getResponsable_module().getPrenom_professeur());
		paragReponsable1.setFont(simpleText);

		document.add(paragModule);
		document.add(paragModule1);
		document.add(paragReponsable);
		document.add(paragReponsable1);

		/**
		 * 
		 * Setting up table
		 * 
		 **/

		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100);
		table.setSpacingBefore(20);

		/**
		 * Making headers
		 */
		PdfPCell massarCell = new PdfPCell();
		Phrase massarPhrase = new Phrase("Massar");
		massarPhrase.setFont(simpleText);
		massarCell.setPhrase(massarPhrase);
		massarCell.setBackgroundColor(Color.YELLOW);
		massarCell.setPadding(5);

		table.addCell(massarCell);

		PdfPCell nomCell = new PdfPCell();
		Phrase nomPhrase = new Phrase("Nom");
		nomPhrase.setFont(simpleText);
		nomCell.setPhrase(nomPhrase);
		nomCell.setBackgroundColor(Color.YELLOW);
		nomCell.setPadding(5);

		table.addCell(nomCell);

		PdfPCell prenomCell = new PdfPCell();
		Phrase prenomPhrase = new Phrase("Prenom");
		prenomPhrase.setFont(simpleText);
		prenomCell.setPhrase(prenomPhrase);
		prenomCell.setBackgroundColor(Color.YELLOW);
		prenomCell.setPadding(5);

		table.addCell(prenomCell);

		PdfPCell noteCell = new PdfPCell();
		Phrase notePhrase = new Phrase("Note");
		notePhrase.setFont(simpleText);
		noteCell.setPhrase(notePhrase);
		noteCell.setBackgroundColor(Color.YELLOW);
		noteCell.setPadding(5);

		table.addCell(noteCell);

		PdfPCell etatCell = new PdfPCell();
		Phrase etatPhrase = new Phrase("Etat");
		etatPhrase.setFont(simpleText);
		etatCell.setPhrase(etatPhrase);
		etatCell.setBackgroundColor(Color.YELLOW);
		etatCell.setPadding(5);

		table.addCell(etatCell);

		/***
		 * Remplir tableau
		 */

		for (NoteModule note : listes) {
			PdfPCell massarCell1 = new PdfPCell();
			Phrase massarPhrase1 = new Phrase(note.getIdComposed().getEtudiant().getMassar_edu());
			massarPhrase1.setFont(simpleText);
			massarCell1.setPhrase(massarPhrase1);
			massarCell1.setBackgroundColor(Color.WHITE);
			massarCell1.setPadding(5);

			table.addCell(massarCell1);

			PdfPCell nomCell1 = new PdfPCell();
			Phrase nomPhrase1 = new Phrase(note.getIdComposed().getEtudiant().getFirst_name_fr());
			nomPhrase1.setFont(simpleText);
			nomCell1.setPhrase(nomPhrase1);
			nomCell1.setBackgroundColor(Color.WHITE);
			nomCell1.setPadding(5);

			table.addCell(nomCell1);

			PdfPCell prenomCell1 = new PdfPCell();
			Phrase prenomPhrase1 = new Phrase(note.getIdComposed().getEtudiant().getLast_name_fr());
			prenomPhrase1.setFont(simpleText);
			prenomCell1.setPhrase(prenomPhrase1);
			prenomCell1.setBackgroundColor(Color.WHITE);
			prenomCell1.setPadding(5);

			table.addCell(prenomCell1);

			Color c = null;
			if (note.isValid())
				c = Color.GREEN;
			else
				c = Color.RED;

			PdfPCell noteCell1 = new PdfPCell();
			Phrase notePhrase1 = new Phrase(note.getNote()+"");
			notePhrase1.setFont(simpleText);
			noteCell1.setPhrase(notePhrase1);
			noteCell1.setBackgroundColor(c);
			noteCell1.setPadding(5);

			table.addCell(noteCell1);

			PdfPCell etatCell1 = new PdfPCell();
			Phrase etatPhrase1 = new Phrase(note.getEtat());
			etatPhrase1.setFont(simpleText);
			etatCell1.setPhrase(etatPhrase1);
			etatCell1.setBackgroundColor(c);
			etatCell1.setPadding(5);

			table.addCell(etatCell1);
		}
		document.add(table);

	}
	
	public void generateUltimatePv(Semestre semestre,NotesModuleRepository noteModuleRepository,Deliberation deliberation) throws MalformedURLException, IOException, DocumentException {
		document.addTitle("Deliberation pour " + semestre.getLibelle_semestre());
		Image image = Image.getInstance("header.png");
		image.setBorderWidthBottom(100);
		image.setBorderWidthLeft(150);
		image.setBorderWidthRight(150);
		PdfPTable tableimage = new PdfPTable(1);
		
		PdfPCell cell = new PdfPCell(image);
		tableimage.addCell(cell);
		tableimage.setSpacingAfter(15);
		document.add(tableimage);
		
		
		
		Font textDeValeur = new Font(Font.COURIER, 18, Font.BOLD);
		textDeValeur.setColor(136, 14, 79);

		Font simpleText = new Font(Font.COURIER, 18, Font.BOLDITALIC);

		Paragraph paragModule = new Paragraph("Semestre");
		paragModule.setFont(textDeValeur);
		Paragraph paragModule1 = new Paragraph("     " + semestre.getLibelle_semestre());
		paragModule1.setFont(simpleText);

		Paragraph paragReponsable = new Paragraph("Reponsable de filiere");
		paragReponsable.setSpacingBefore(14);
		paragReponsable.setFont(textDeValeur);
		Professeur prof = semestre.getEtape().getFiliere().getResponsable_filiere();
		Paragraph paragReponsable1 = new Paragraph("     " + prof.getNom_professeur() + " "
				+ prof.getPrenom_professeur());
		paragReponsable1.setFont(simpleText);

		document.add(paragModule);
		document.add(paragModule1);
		document.add(paragReponsable);
		document.add(paragReponsable1);

		/**
		 * 
		 * Setting up table
		 * 
		 **/

		PdfPTable table = new PdfPTable(semestre.getModules().size() + 4);
		table.setWidthPercentage(100);
		table.setSpacingBefore(20);

		/**
		 * Making headers
		 */
		PdfPCell massarCell = new PdfPCell();
		Phrase massarPhrase = new Phrase("Massar");
		massarPhrase.setFont(simpleText);
		massarCell.setPhrase(massarPhrase);
		massarCell.setBackgroundColor(Color.YELLOW);
		massarCell.setPadding(5);

		table.addCell(massarCell);

		PdfPCell nomCell = new PdfPCell();
		Phrase nomPhrase = new Phrase("Nom");
		nomPhrase.setFont(simpleText);
		nomCell.setPhrase(nomPhrase);
		nomCell.setBackgroundColor(Color.YELLOW);
		nomCell.setPadding(5);

		table.addCell(nomCell);

		PdfPCell prenomCell = new PdfPCell();
		Phrase prenomPhrase = new Phrase("Prenom");
		prenomPhrase.setFont(simpleText);
		prenomCell.setPhrase(prenomPhrase);
		prenomCell.setBackgroundColor(Color.YELLOW);
		prenomCell.setPadding(5);

		table.addCell(prenomCell);

		PdfPCell noteCell = new PdfPCell();
		Phrase notePhrase = new Phrase("Note");
		notePhrase.setFont(simpleText);
		noteCell.setPhrase(notePhrase);
		noteCell.setBackgroundColor(Color.YELLOW);
		noteCell.setPadding(5);

		table.addCell(noteCell);

		PdfPCell etatCell = new PdfPCell();
		Phrase etatPhrase = new Phrase("Etat");
		etatPhrase.setFont(simpleText);
		etatCell.setPhrase(etatPhrase);
		etatCell.setBackgroundColor(Color.YELLOW);
		etatCell.setPadding(5);
		
		table.addCell(etatCell);
		
		for (NoteSemestre note : deliberation.getNotesSemestre()) {
			Etudiant etudiant = note.getIdCompose().getEtudiant();
			PdfPCell massarCell1 = new PdfPCell();
			Phrase massarPhrase1 = new Phrase(etudiant.getMassar_edu());
			massarPhrase1.setFont(simpleText);
			massarCell1.setPhrase(massarPhrase1);
			massarCell1.setBackgroundColor(Color.WHITE);
			massarCell1.setPadding(5);

			table.addCell(massarCell1);

			PdfPCell nomCell1 = new PdfPCell();
			Phrase nomPhrase1 = new Phrase(etudiant.getFirst_name_fr());
			nomPhrase1.setFont(simpleText);
			nomCell1.setPhrase(nomPhrase1);
			nomCell1.setBackgroundColor(Color.WHITE);
			nomCell1.setPadding(5);

			table.addCell(nomCell1);

			PdfPCell prenomCell1 = new PdfPCell();
			Phrase prenomPhrase1 = new Phrase(etudiant.getLast_name_fr());
			prenomPhrase1.setFont(simpleText);
			prenomCell1.setPhrase(prenomPhrase1);
			prenomCell1.setBackgroundColor(Color.WHITE);
			prenomCell1.setPadding(5);

			table.addCell(prenomCell1);
			
			for (Modulee module : semestre.getModules()) {
				NoteModule noteModule = noteModuleRepository.getOne(new ComposedNoteModule(module, etudiant));
				Color c = null;
				if (noteModule.isValid())
					c = Color.GREEN;
				else
					c = Color.RED;

				PdfPCell noteCell1 = new PdfPCell();
				Phrase notePhrase1 = new Phrase(noteModule.getNote()+"");
				notePhrase1.setFont(simpleText);
				noteCell1.setPhrase(notePhrase1);
				noteCell1.setBackgroundColor(c);
				noteCell1.setPadding(5);

				table.addCell(noteCell1);
			}

			Color c = null;
			if (note.isValid())
				c = Color.GREEN;
			else
				c = Color.RED;

			PdfPCell noteCell1 = new PdfPCell();
			Phrase notePhrase1 = new Phrase(note.getNote()+"");
			notePhrase1.setFont(simpleText);
			noteCell1.setPhrase(notePhrase1);
			noteCell1.setBackgroundColor(c);
			noteCell1.setPadding(5);

			table.addCell(noteCell1);
		}
		table.setSpacingAfter(15);
		
		for(Modulee module:semestre.getModules()) {
			Paragraph paragReponsableModule = new Paragraph("Reponsable de module - " + module.getLibelle_module());
			paragReponsableModule.setSpacingBefore(14);
			paragReponsableModule.setFont(textDeValeur);
			Professeur professeur = module.getResponsable_module();
			Paragraph paragReponsableModule1 = new Paragraph("     " + professeur.getNom_professeur() + " "
					+ professeur.getPrenom_professeur());
			paragReponsableModule1.setFont(simpleText);
		}

	}

	public void closeDocument() {
		document.close();
	}
}

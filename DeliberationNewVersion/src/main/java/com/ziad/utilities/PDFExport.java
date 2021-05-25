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
		
		
		
		
		Font textDeValeur = new Font(Font.COURIER, 18, Font.BOLD);
		textDeValeur.setColor(Color.RED);

		Font simpleText = new Font(Font.COURIER, 18, Font.BOLDITALIC);
		
		
		Paragraph paragTitre = new Paragraph();
		Phrase titre = new Phrase("Déliberation pour Bachelor / Université moulay ismail");
		titre.setFont(textDeValeur);		
		paragTitre.add(titre);
		document.add(paragTitre);

		Paragraph paragModule = new Paragraph();
		
		Phrase phrase1 = new Phrase("Module :");
		phrase1.setFont(textDeValeur);
		
		Phrase phrase2 = new Phrase(module.getLibelle_module());
		phrase2.setFont(simpleText);
		
		paragModule.add(phrase1);
		paragModule.add(phrase2);


		Paragraph paragReponsable = new Paragraph();
		paragReponsable.setSpacingBefore(14);
		Phrase phrase11 = new Phrase("Responsable de module :");
		phrase11.setFont(textDeValeur);
		
		Phrase phrase22 = new Phrase(module.getResponsable_module().getNom_professeur() + " "
				+ module.getResponsable_module().getPrenom_professeur());
		phrase22.setFont(simpleText);
		
		paragReponsable.add(phrase11);
		paragReponsable.add(phrase22);

		document.add(paragModule);
		document.add(paragReponsable);


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
		System.out.println(listes.size());
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
		Font textDeValeur = new Font(Font.COURIER, 18, Font.BOLD);
		textDeValeur.setColor(Color.RED);

		Font simpleText = new Font(Font.COURIER, 18, Font.BOLDITALIC);
		
		
		Paragraph paragTitre = new Paragraph();
		Phrase titre = new Phrase("Déliberation pour Bachelor / Université moulay ismail");
		titre.setFont(textDeValeur);		
		paragTitre.add(titre);
		document.add(paragTitre);
		
		Paragraph paragEntete = new Paragraph();
		
		Phrase phrase1 = new Phrase("Entête");
		phrase1.setFont(textDeValeur);
		
		Phrase phrase2 = new Phrase("Etablissement :" + semestre.getEtape().getFiliere().getEtablissement().getNom_etablissement() + " Filière :" + semestre.getEtape().getFiliere().getNom_filiere());
		phrase2.setFont(simpleText);
		
		paragEntete.add(phrase1);
		paragEntete.add(phrase2);
		
		
		Paragraph parag = new Paragraph("Semestre :" + semestre.getLibelle_semestre());
		parag.setSpacingBefore(15);
		document.add(parag);
		
		
		Paragraph paragFiliere = new Paragraph();
		
		Phrase phrase11 = new Phrase("Reponsable de filiere");
		phrase1.setFont(textDeValeur);
		
		Professeur prof = semestre.getEtape().getFiliere().getResponsable_filiere();
		Phrase phrase22 = new Phrase("     " + prof.getNom_professeur() + " "
				+ prof.getPrenom_professeur());
		phrase2.setFont(simpleText);
		
		paragFiliere.add(phrase11);
		paragFiliere.add(phrase22);
		paragFiliere.setSpacingBefore(15);
		document.add(paragFiliere);

		/**
		 * 
		 * Setting up table
		 * 
		 **/
		PdfPTable table = new PdfPTable(semestre.getModules().size() + 5);
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


		PdfPCell etatCell = new PdfPCell();
		Phrase etatPhrase = new Phrase("Etat");
		etatPhrase.setFont(simpleText);
		etatCell.setPhrase(etatPhrase);
		etatCell.setBackgroundColor(Color.YELLOW);
		etatCell.setPadding(5);
		
		table.addCell(etatCell);
		
		for (Modulee module : semestre.getModules()) {		
			PdfPCell moduleCell = new PdfPCell();
			Phrase modulePhrase = new Phrase(module.getLibelle_module());
			modulePhrase.setFont(simpleText);
			moduleCell.setPhrase(modulePhrase);
			moduleCell.setPadding(5);

			table.addCell(moduleCell);
		}
		
		PdfPCell semestreCell = new PdfPCell();
		Phrase semestrePhrase = new Phrase("Note semestre");
		semestrePhrase.setFont(simpleText);
		semestreCell.setPhrase(semestrePhrase);
		semestreCell.setPadding(5);

		table.addCell(semestreCell);
		
		
		
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
			
			PdfPCell etatCell1 = new PdfPCell();
			Phrase etatPhrase1 = new Phrase(etudiant.getLast_name_fr());
			etatPhrase1.setFont(simpleText);
			etatCell1.setPhrase(etatPhrase1);
			etatCell1.setBackgroundColor(Color.WHITE);
			etatCell1.setPadding(5);

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
		document.add(table);
		
		for(Modulee module:semestre.getModules()) {
			
			Professeur professeur = module.getResponsable_module();

			Paragraph paragReponsableModule = new Paragraph();
			
			Phrase ph = new Phrase("Reponsable de module - " + module.getLibelle_module());
			ph.setFont(textDeValeur);
			
			Phrase ph1 = new Phrase("     " + professeur.getNom_professeur() + " "
					+ professeur.getPrenom_professeur());
			
			paragReponsableModule.add(ph);
			paragReponsableModule.add(ph1);
			paragReponsableModule.setSpacingBefore(14);
			document.add(paragReponsableModule);
		}

	}

	public void closeDocument() {
		document.close();
	}
}

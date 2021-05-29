package com.ziad.utilities;

import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ziad.models.AnneeAcademique;
import com.ziad.models.Element;
import com.ziad.models.Etape;
import com.ziad.models.Filiere;
import com.ziad.models.InscriptionEnLigne;
import com.ziad.models.InscriptionPedagogique;
import com.ziad.models.Modulee;
import com.ziad.models.NoteEtape;
import com.ziad.models.NoteModule;
import com.ziad.models.NoteSemestre;
import com.ziad.models.Semestre;
import com.ziad.utilities.adaptater.AnneeAcademiqueAdaptater;
import com.ziad.utilities.adaptater.ElementAdaptater;
import com.ziad.utilities.adaptater.EtapeAdaptater;
import com.ziad.utilities.adaptater.FiliereAdaptater;
import com.ziad.utilities.adaptater.InscriptionEnLigneAdaptater;
import com.ziad.utilities.adaptater.InscriptionPedagogiqueAdaptater;
import com.ziad.utilities.adaptater.ModuleAdaptater;
import com.ziad.utilities.adaptater.NotesEtapeAdaptater;
import com.ziad.utilities.adaptater.NotesModuleAdaptater;
import com.ziad.utilities.adaptater.NotesSemestreAdaptater;
import com.ziad.utilities.adaptater.SemestreAdaptater;

@Service
public class JSONConverter{
	
	private GsonBuilder gsonBuilder;
	public JSONConverter() {
		gsonBuilder = new GsonBuilder();
	}
	
	public String convertModulee(List<Modulee> modules) {
	    Gson gson = gsonBuilder.registerTypeAdapter(Modulee.class, new ModuleAdaptater()).create();
	    return gson.toJson(modules);
	}
	
	public String convertSemestre(List<Semestre> semestres) {
		Gson gson = gsonBuilder.registerTypeAdapter(Semestre.class, new SemestreAdaptater()).create();
	    return gson.toJson(semestres);
	}
	
	public String convertElements(List<Element> elements) {
		Gson gson = gsonBuilder.registerTypeAdapter(Element.class, new ElementAdaptater()).create();
		return gson.toJson(elements);
	}
	
	public String convertAnneesAcademiques(List<AnneeAcademique> annees) {
		Gson gson = gsonBuilder.registerTypeAdapter(AnneeAcademique.class, new AnneeAcademiqueAdaptater()).create();
		return gson.toJson(annees);
	}
	
	public String convertInscriptionsPedagogiques(List<InscriptionPedagogique> inscription_pedagogiques) {
		Gson gson = gsonBuilder.registerTypeAdapter(InscriptionPedagogique.class,new InscriptionPedagogiqueAdaptater()).create();
		return gson.toJson(inscription_pedagogiques);
	}
	
	public String convertFiliere(Filiere filiere) {
		Gson gson = gsonBuilder.registerTypeAdapter(Filiere.class, new FiliereAdaptater()).create();
		return gson.toJson(filiere);
	}
	
	public String convertFilieres(List<Filiere> filieres) {
		Gson gson = gsonBuilder.registerTypeAdapter(Filiere.class, new FiliereAdaptater()).create();
		return gson.toJson(filieres);
	}
	
	public String convertEtape(Etape etape) {
		Gson gson = gsonBuilder.registerTypeAdapter(Etape.class, new EtapeAdaptater()).create();
		return gson.toJson(etape);
	}
	
	public String convertEtape(List<Etape> etapes) {
		 Gson gson = gsonBuilder.registerTypeAdapter(Etape.class, new EtapeAdaptater()).create();
		 return gson.toJson(etapes);
	}
	
	public String convertInscriptionsEnLignes(List<InscriptionEnLigne> inscriptions) {
		Gson gson = gsonBuilder.registerTypeAdapter(InscriptionEnLigne.class,new InscriptionEnLigneAdaptater()).create();
	    return gson.toJson(inscriptions);
	}
	
	public String convertInscriptionsEnLignes(InscriptionEnLigne inscription) {
		Gson gson = gsonBuilder.registerTypeAdapter(InscriptionEnLigne.class,new InscriptionEnLigneAdaptater()).create();
	    return gson.toJson(inscription);
	}
	
	public String convertNotesModule(NoteModule note) {
		Gson gson = gsonBuilder.registerTypeAdapter(NoteModule.class,new NotesModuleAdaptater()).create();
		return gson.toJson(note);
	}
	
	public String convertNotesModule(List<NoteModule> notes) {
		Gson gson = gsonBuilder.registerTypeAdapter(NoteModule.class,new NotesModuleAdaptater()).create();
		return gson.toJson(notes);
	}
	
	public String convertNotesEtape(NoteEtape note) {
		Gson gson = gsonBuilder.registerTypeAdapter(NoteEtape.class,new NotesEtapeAdaptater()).create();
		return gson.toJson(note);
	}
	
	public String convertNotesEtape(List<NoteEtape> notes) {
		Gson gson = gsonBuilder.registerTypeAdapter(NoteEtape.class,new NotesEtapeAdaptater()).create();
		return gson.toJson(notes);
	}
	
	public String convertNotesSemestre(NoteSemestre note) {
		Gson gson = gsonBuilder.registerTypeAdapter(NoteSemestre.class,new NotesSemestreAdaptater()).create();
		return gson.toJson(note);
	}
	
	public String convertNotesSemestre(List<NoteSemestre> notes) {
		Gson gson = gsonBuilder.registerTypeAdapter(NoteSemestre.class,new NotesSemestreAdaptater()).create();
		return gson.toJson(notes);
	}
}

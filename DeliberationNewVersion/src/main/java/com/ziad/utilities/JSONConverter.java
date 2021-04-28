package com.ziad.utilities;

import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ziad.models.AnneeAcademique;
import com.ziad.models.Element;
import com.ziad.models.InscriptionPedagogique;
import com.ziad.models.Modulee;
import com.ziad.models.Semestre;
import com.ziad.utilities.adaptater.AnneeAcademiqueAdaptater;
import com.ziad.utilities.adaptater.ElementAdaptater;
import com.ziad.utilities.adaptater.InscriptionPedagogiqueAdaptater;
import com.ziad.utilities.adaptater.ModuleAdaptater;
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
}

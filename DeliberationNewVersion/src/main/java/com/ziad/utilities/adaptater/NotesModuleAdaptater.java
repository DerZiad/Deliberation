package com.ziad.utilities.adaptater;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.ziad.models.NoteModule;

public class NotesModuleAdaptater implements JsonSerializer<NoteModule>{

	@Override
	public JsonElement serialize(NoteModule src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("note",src.getNote());
		jsonObject.addProperty("etat", src.getEtat());
		jsonObject.addProperty("isValid", src.isValid());
		jsonObject.addProperty("first_name_fr", src.getIdComposed().getEtudiant().getFirst_name_fr());
		jsonObject.addProperty("last_name_fr", src.getIdComposed().getEtudiant().getLast_name_fr());
		jsonObject.addProperty("massar_edu", src.getIdComposed().getEtudiant().getMassar_edu());
		jsonObject.addProperty("libelle_module", src.getIdComposed().getModule().getLibelle_module());
        return jsonObject;    
	}

}

package com.ziad.utilities.adaptater;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.ziad.models.NoteElement;

public class NoteElementAdaptater implements JsonSerializer<NoteElement>{

	@Override
	public JsonElement serialize(NoteElement src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("note",src.getNote());
		jsonObject.addProperty("etat", src.getEtat());
		jsonObject.addProperty("isValid", src.isValid());
		jsonObject.addProperty("isAbscent", src.isAbscent());
		jsonObject.addProperty("first_name_fr", src.getIdCompose().getEtudiant().getFirst_name_fr());
		jsonObject.addProperty("last_name_fr", src.getIdCompose().getEtudiant().getLast_name_fr());
		jsonObject.addProperty("massar_edu", src.getIdCompose().getEtudiant().getMassar_edu());
		jsonObject.addProperty("libelle", src.getIdCompose().getElement().getLibelle_element());
		return jsonObject;
	}

}

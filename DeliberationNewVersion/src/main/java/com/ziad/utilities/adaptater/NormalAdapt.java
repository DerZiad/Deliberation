package com.ziad.utilities.adaptater;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.ziad.models.NoteNorm;

public class NormalAdapt implements JsonSerializer<NoteNorm> {

	@Override
	public JsonElement serialize(NoteNorm src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject note = new JsonObject();
		note.addProperty("idElement", src.getElement().getId());
		note.addProperty("idStudent", src.getIdStudent());
		
		note.addProperty("libelle_element", src.getElement().getName());
		note.addProperty("type_element", src.getElement().getType());
		
		note.addProperty("first_name_fr", src.getEtudiant().getFirst_name_fr());
		note.addProperty("last_name_fr", src.getEtudiant().getLast_name_fr());
		
		note.addProperty("validation", src.getValidation());
		note.addProperty("etat", src.getEtat());
		note.addProperty("valid", src.isValid());
		note.addProperty("note", src.getNote());
		
		return note;
	}

}

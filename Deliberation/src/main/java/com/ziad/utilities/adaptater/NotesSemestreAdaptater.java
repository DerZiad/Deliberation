package com.ziad.utilities.adaptater;
import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.ziad.models.NoteSemestre;

public class NotesSemestreAdaptater implements JsonSerializer<NoteSemestre>{

	@Override
	public JsonElement serialize(NoteSemestre src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("note",src.getNote());
		jsonObject.addProperty("etat", src.getEtat());
		jsonObject.addProperty("isValid", src.isValid());
		jsonObject.addProperty("first_name_fr", src.getIdCompose().getEtudiant().getFirst_name_fr());
		jsonObject.addProperty("last_name_fr", src.getIdCompose().getEtudiant().getLast_name_fr());
		jsonObject.addProperty("massar_edu", src.getIdCompose().getEtudiant().getMassar_edu());
		jsonObject.addProperty("libelle_module", src.getIdCompose().getSemestre().getLibelle_semestre());
		return jsonObject;
	}

	

}

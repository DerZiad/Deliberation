package com.ziad.utilities.adaptater;

import com.ziad.models.Semestre;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class SemestreAdaptater implements JsonSerializer<Semestre>{

	@Override
	public JsonElement serialize(Semestre src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id_filiere", src.getEtape().getFiliere().getId_filiere());
        jsonObject.addProperty("id_semestre", src.getId_semestre());
        jsonObject.addProperty("libelle_semestre", src.getLibelle_semestre());
        jsonObject.addProperty("validation", src.getValidation());
        jsonObject.addProperty("ordre", src.getOrdre());
        return jsonObject;    
	}

}

package com.ziad.utilities.adaptater;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.ziad.models.Etablissement;

public class EtablissementAdaptater implements JsonSerializer<Etablissement>{

	@Override
	public JsonElement serialize(Etablissement src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject json = new JsonObject();
		json.addProperty("nom_etablissement", src.getNom_etablissement());
		json.addProperty("id_etablissement", src.getId_etablissement());
		return json;
	}

}

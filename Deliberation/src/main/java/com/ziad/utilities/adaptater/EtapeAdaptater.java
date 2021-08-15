package com.ziad.utilities.adaptater;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.ziad.models.Etape;

public class EtapeAdaptater implements JsonSerializer<Etape> {

	@Override
	public JsonElement serialize(Etape src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject json = new JsonObject();
		json.addProperty("id_etape", src.getId_etape());
		json.addProperty("id_filiere", src.getFiliere().getId_filiere());
		json.addProperty("libelle_etape", src.getLibelle_etape());
		json.addProperty("validation", src.getValidation());
		return json;
	}

}

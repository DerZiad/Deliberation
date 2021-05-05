package com.ziad.utilities.adaptater;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.ziad.models.Filiere;

public class FiliereAdaptater implements JsonSerializer<Filiere>{

	@Override
	public JsonElement serialize(Filiere src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject json = new JsonObject();
		json.addProperty("id_filiere", src.getId_filiere());
		json.addProperty("libelle_filiere", src.getNom_filiere());
		return json;
	}

}

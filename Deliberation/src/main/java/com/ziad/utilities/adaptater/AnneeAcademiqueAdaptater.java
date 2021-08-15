package com.ziad.utilities.adaptater;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.ziad.models.AnneeAcademique;

public class AnneeAcademiqueAdaptater implements JsonSerializer<AnneeAcademique>{

	@Override
	public JsonElement serialize(AnneeAcademique src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject json_object = new JsonObject();
		json_object.addProperty("annee_academique", src.getAnnee_academique());
		json_object.addProperty("id_annee_academique", src.getId_annee_academique());		
		return json_object;
	}

}

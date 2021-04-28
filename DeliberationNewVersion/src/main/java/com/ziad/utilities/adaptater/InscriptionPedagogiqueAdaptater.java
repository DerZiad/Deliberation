package com.ziad.utilities.adaptater;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.ziad.models.InscriptionPedagogique;

public class InscriptionPedagogiqueAdaptater implements JsonSerializer<InscriptionPedagogique>{

	@Override
	public JsonElement serialize(InscriptionPedagogique src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject json_object = new JsonObject();
		json_object.addProperty("id_etudiant",src.getId_inscription_pedagogique().getEtudiant().getId_etudiant());
		json_object.addProperty("id_element", src.getId_inscription_pedagogique().getElement().getId_element());
		json_object.addProperty("nom_etudiant", src.getId_inscription_pedagogique().getEtudiant().getFirst_name_fr());
		json_object.addProperty("prenom_etudiant", src.getId_inscription_pedagogique().getEtudiant().getFirst_name_fr());
		json_object.addProperty("libelle_element", src.getId_inscription_pedagogique().getElement().getLibelle_element());
		json_object.addProperty("valid", src.isValid());
		json_object.addProperty("type_inscription", src.getType_inscription().name());
		return json_object;
	}

}

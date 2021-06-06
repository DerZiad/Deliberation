package com.ziad.utilities.adaptater;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.ziad.models.InscriptionEnLigne;

public class InscriptionEnLigneAdaptater implements JsonSerializer<InscriptionEnLigne>{

	@SuppressWarnings("deprecation")
	@Override
	public JsonElement serialize(InscriptionEnLigne src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject json = new JsonObject();
		json.addProperty("id_inscription_en_ligne", src.getId_inscription_en_ligne());
		json.addProperty("email", src.getEmail());
		json.addProperty("massar_edu", src.getMassar_edu());
		json.addProperty("first_name_fr", src.getFirst_name_fr());
		json.addProperty("first_name_ar", src.getFirst_name_ar());
		json.addProperty("last_name_fr", src.getLast_name_fr());
		json.addProperty("last_name_ar", src.getLast_name_ar());
		json.addProperty("cne", src.getCne());
		json.addProperty("nationality", src.getNationality().getValueCountry());
		json.addProperty("gender", src.getGender().name());
		json.addProperty("birth_date", src.getBirth_date().toLocaleString());
		json.addProperty("birth_place", src.getBirth_place());
		json.addProperty("city", src.getCity());
		json.addProperty("province", src.getProvince());
		json.addProperty("bac_year", src.getBac_year());
		json.addProperty("bac_type", src.getBac_type());
		json.addProperty("mention", src.getMention());
		json.addProperty("high_school", src.getHigh_school());
		json.addProperty("bac_place", src.getBac_place());
		json.addProperty("academy", src.getAcademy());
		json.addProperty("registration_date", src.getRegistration_date().toLocaleString());
		json.addProperty("accepted", src.getAccepted());
		json.addProperty("acceptedParAdmin", src.getAcceptedParAdmin());
		return json;
	}

}

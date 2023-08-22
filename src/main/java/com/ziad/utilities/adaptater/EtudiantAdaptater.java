package com.ziad.utilities.adaptater;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.ziad.models.Etudiant;

public class EtudiantAdaptater extends TypeAdapter<Etudiant>{

	@Override
	public void write(JsonWriter out, Etudiant value) throws IOException {
		out.beginObject();
	    out.name("massar").value(value.getMassar_edu());
	    out.name("first_name_fr").value(value.getFirst_name_fr());
	    out.name("last_name_fr").value(value.getLast_name_fr());
	    out.endObject();
			
	}

	@Override
	public Etudiant read(JsonReader in) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}


}

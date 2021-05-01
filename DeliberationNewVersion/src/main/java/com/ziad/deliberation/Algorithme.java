package com.ziad.deliberation;

import java.util.Iterator;
import java.util.List;

import com.ziad.models.Element;
import com.ziad.models.InscriptionPedagogique;
import com.ziad.models.NoteElement;
import com.ziad.repositories.ElementRepository;
import com.ziad.repositories.ModuleRepository;
import com.ziad.repositories.NoteElementRepository;
import com.ziad.repositories.SemestreRepository;

public class Algorithme {

	private NoteElementRepository noteElementRepository;
	
	private ElementRepository elementRepository;
	
	private ModuleRepository moduleRepository;
	
	private SemestreRepository semestreRepository;

	public static void calculateElementNote(List<NoteElement> notes,InscriptionPedagogique inscriptionPedagogique) {
		for (NoteElement noteElement : notes) {
			
		}
	}
}

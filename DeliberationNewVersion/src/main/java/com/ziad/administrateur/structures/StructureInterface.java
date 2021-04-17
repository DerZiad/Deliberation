package com.ziad.administrateur.structures;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.ziad.models.Etape;
import com.ziad.models.Filiere;
import com.ziad.models.Modulee;
import com.ziad.models.Semestre;

public interface StructureInterface {
	public List<Etape> listerEtapes();
	public List<Filiere> listerFilieres();
	public List<Modulee> listerModules();
	public List<Semestre> listerSemestres();
	
	public void diplomerAnnee(String ids) throws EntityNotFoundException;
	
}

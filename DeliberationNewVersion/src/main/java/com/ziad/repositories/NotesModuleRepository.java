package com.ziad.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ziad.models.AnneeAcademique;
import com.ziad.models.Etape;
import com.ziad.models.Filiere;
import com.ziad.models.Modulee;
import com.ziad.models.NoteModule;
import com.ziad.models.Semestre;
import com.ziad.models.compositeid.ComposedNoteModule;
@Repository
public interface NotesModuleRepository extends JpaRepository<NoteModule, ComposedNoteModule> {
	
	@Query("select n from NoteModule n where n.idComposed.module=:module and n.etat='ORDINAIRE' and n.deliberation.anneeAcademique =:annee")
	public List<NoteModule> listerNotesModuleByAnneeOrdinaire(@Param("module")Modulee module,@Param("annee")AnneeAcademique annee);
	
	@Query("select n from NoteModule n where n.idComposed.module=:module and n.etat='ORDINAIRE' and n.deliberation.anneeAcademique =:annee")
	public List<NoteModule> listerNotesModuleByAnneeRattrapage(@Param("module")Modulee module,@Param("annee")AnneeAcademique annee);

	@Query("select n from NoteModule n where n.idComposed.module = :module and  n.anneeAcademique = :annee")
	List<NoteModule> getNoteModuleAnnee(@Param("module") Modulee module,@Param("annee") AnneeAcademique annee);
	
	}

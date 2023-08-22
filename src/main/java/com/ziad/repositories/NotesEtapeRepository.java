package com.ziad.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ziad.models.AnneeAcademique;
import com.ziad.models.Etape;
import com.ziad.models.NoteEtape;
import com.ziad.models.compositeid.ComposedNoteEtape;
@Repository
public interface NotesEtapeRepository extends JpaRepository<NoteEtape, ComposedNoteEtape> {
	@Query("select n from NoteEtape n where n.idCompose.etape =:etape and  n.anneeAcademique = :annee")
	List<NoteEtape> getNoteEtapeAnnee(@Param("etape") Etape etape,@Param("annee") AnneeAcademique annee);

}

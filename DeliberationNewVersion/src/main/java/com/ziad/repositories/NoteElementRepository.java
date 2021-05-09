package com.ziad.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ziad.models.AnneeAcademique;
import com.ziad.models.Element;
import com.ziad.models.Etudiant;
import com.ziad.models.NoteElement;
import com.ziad.models.compositeid.ComposedInscriptionPedagogique;
@Repository
public interface NoteElementRepository extends JpaRepository<NoteElement, ComposedInscriptionPedagogique> {

	@Query("select n from NoteElement n where n.idCompose.element = :element and  n.annee_academique = :annee")
	List<NoteElement> getNoteElementAnnee(@Param("element") Element element,@Param("annee") AnneeAcademique annee);
	
	/*@Query("select s from NoteElement s")
	List<NoteElement> getAllNoteElement();
	
	@Query("select s from NoteElement s where s.inscription_pedagogique = :inscription_pedagogique")
	List<NoteElement> getNoteElementEtudiant(@Param("inscription_pedagogique") InscriptionPedagogique i);
	
	@Query("select s from NoteElement s where s.inscription_pedagogique = :inscription_pedagogique and s.element = :element")
	List<NoteElement> getNoteElementEtudiantElement(@Param("inscription_pedagogique") InscriptionPedagogique i,@Param("element") Element e);
	@Query("select s from NoteElement s where s.element = :x")
	List<NoteElement> getNoteElementByElement(@Param("x") Element i);*/

}

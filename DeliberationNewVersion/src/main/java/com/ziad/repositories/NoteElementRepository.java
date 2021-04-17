package com.ziad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ziad.models.NoteElement;
@Repository
public interface NoteElementRepository extends JpaRepository<NoteElement, Integer> {
	/*@Query("select s from NoteElement s")
	List<NoteElement> getAllNoteElement();
	
	@Query("select s from NoteElement s where s.inscription_pedagogique = :inscription_pedagogique")
	List<NoteElement> getNoteElementEtudiant(@Param("inscription_pedagogique") InscriptionPedagogique i);
	
	@Query("select s from NoteElement s where s.inscription_pedagogique = :inscription_pedagogique and s.element = :element")
	List<NoteElement> getNoteElementEtudiantElement(@Param("inscription_pedagogique") InscriptionPedagogique i,@Param("element") Element e);
	@Query("select s from NoteElement s where s.element = :x")
	List<NoteElement> getNoteElementByElement(@Param("x") Element i);*/

}

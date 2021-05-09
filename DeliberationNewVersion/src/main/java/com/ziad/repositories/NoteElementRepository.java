package com.ziad.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ziad.models.AnneeAcademique;
import com.ziad.models.Element;
import com.ziad.models.NoteElement;
import com.ziad.models.compositeid.ComposedInscriptionPedagogique;
@Repository
public interface NoteElementRepository extends JpaRepository<NoteElement, ComposedInscriptionPedagogique> {

	@Query("select n from NoteElement n where n.idCompose.element = :element and  n.annee_academique = :annee")
	List<NoteElement> getNoteElementAnnee(@Param("element") Element element,@Param("annee") AnneeAcademique annee);
}

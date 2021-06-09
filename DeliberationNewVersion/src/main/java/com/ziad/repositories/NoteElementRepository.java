package com.ziad.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ziad.models.AnneeAcademique;
import com.ziad.models.Element;
import com.ziad.models.Etape;
import com.ziad.models.Filiere;
import com.ziad.models.Modulee;
import com.ziad.models.NoteElement;
import com.ziad.models.Semestre;
import com.ziad.models.compositeid.ComposedInscriptionPedagogique;
@Repository
public interface NoteElementRepository extends JpaRepository<NoteElement, ComposedInscriptionPedagogique> {

	@Query("select n from NoteElement n where n.idCompose.element = :element and  n.annee_academique = :annee")
	List<NoteElement> getNoteElementAnnee(@Param("element") Element element,@Param("annee") AnneeAcademique annee);
	
	@Query("select n from NoteElement n where n.idCompose.element.module.semestre.etape = :etape and  n.annee_academique = :annee")
	List<NoteElement> getNoteElementAnneeEtape(@Param("etape") Etape etape,@Param("annee") AnneeAcademique annee);
	
	@Query("select n from NoteElement n where n.idCompose.element.module.semestre =:semestre and  n.annee_academique = :annee")
	List<NoteElement> getNoteElementAnneeSemestre(@Param("semestre")Semestre semestre,@Param("annee") AnneeAcademique annee);
	
	@Query("select n from NoteElement n where n.idCompose.element.module = :module and  n.annee_academique = :annee")
	List<NoteElement> getNoteElementAnneeModule(@Param("module") Modulee module,@Param("annee") AnneeAcademique annee);
	
	@Query("select n from NoteElement n where n.idCompose.element.module.semestre.etape.filiere =:filiere and  n.annee_academique = :annee")
	List<NoteElement> getNoteElementAnneeFiliere(@Param("filiere")Filiere module,@Param("annee") AnneeAcademique annee);
	
}

package com.ziad.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ziad.models.AnneeAcademique;
import com.ziad.models.NoteSemestre;
import com.ziad.models.Semestre;
import com.ziad.models.compositeid.ComposedNoteSemestre;

@Repository
public interface NotesSemestreRepository extends JpaRepository<NoteSemestre, ComposedNoteSemestre>{

	@Query("select n from NoteSemestre n where n.idCompose.semestre =:semestre and  n.anneeAcademique = :annee")
	List<NoteSemestre> getNoteSemestreAnnee(@Param("semestre") Semestre semestre,@Param("annee") AnneeAcademique annee);

}

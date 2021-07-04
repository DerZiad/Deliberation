package com.ziad.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ziad.models.AnneeAcademique;
@Repository
public interface AnnneAcademiqueRepository extends JpaRepository<AnneeAcademique,Long>{

	@Query("select a from AnneeAcademique a where a.annee_academique = :annee")
	AnneeAcademique getAnneeAcademique(@Param("annee") int annee);	
}

package com.ziad.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ziad.models.AnneeAcademique;
import com.ziad.models.Deliberation;
import com.ziad.models.Element;
import com.ziad.models.Etape;
import com.ziad.models.Modulee;
import com.ziad.models.Semestre;

@Repository
public interface DeliberationRepository extends JpaRepository<Deliberation, Long> {
	@Query("select d from Deliberation d where d.module =:module and anneeAcademique =:annee")
	List<Deliberation> getDeliberationByModuleAnnneAcademique(@Param("module") Modulee module,
			@Param("annee") AnneeAcademique annee);

	@Query("select d from Deliberation d where d.semestre =:semestre and anneeAcademique =:annee")
	List<Deliberation> getDeliberationBySemestreAnnneAcademique(@Param("semestre") Semestre semestre,
			@Param("annee") AnneeAcademique annee);

	@Query("select d from Deliberation d where d.etape =:etape and anneeAcademique =:annee")
	List<Deliberation> getDeliberationByEtapeAnnneAcademique(@Param("etape") Etape Etape,
			@Param("annee") AnneeAcademique annee);
	
	@Query("select d from Deliberation d where d.element =:element and anneeAcademique =:annee")
	List<Deliberation> getDeliberationByElementAnnneAcademique(@Param("element") Element Element,
			@Param("annee") AnneeAcademique annee);

}

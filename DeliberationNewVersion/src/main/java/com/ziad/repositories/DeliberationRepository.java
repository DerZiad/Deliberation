package com.ziad.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ziad.models.AnneeAcademique;
import com.ziad.models.Deliberation;
import com.ziad.models.Etape;
import com.ziad.models.Modulee;
import com.ziad.models.Semestre;
@Repository
public interface DeliberationRepository extends JpaRepository<Deliberation, Long> {
	@Query("select d from Deliberation d where d.module =:module and anneeAcademique =:annee")
	List<Deliberation> getDeliberationByModuleOrdinaire(@Param("module") Modulee module,@Param("annee") AnneeAcademique annee);
	
	
	@Query("select d from Deliberation d where d.semestre =:semestre and typeDeliberation='ORDINAIRE' and anneeAcademique =:annee")
	List<Deliberation> getDeliberationBySemestreOrdinaire(@Param("semestre") Semestre semestre,@Param("annee") AnneeAcademique annee);
	
	
	@Query("select d from Deliberation d where d.semestre =:semestre and typeDeliberation='RATTRAPAGE' and anneeAcademique =:annee")
	List<Deliberation> getDeliberationBySemestreRattrapage(@Param("semestre") Semestre semestre,@Param("annee") AnneeAcademique annee);
	
	
	@Query("select d from Deliberation d where d.etape =:etape and typeDeliberation='ORDINAIRE' and anneeAcademique =:annee")
	List<Deliberation> getDeliberationByEtapeOrdinaire(@Param("etape") Etape Etape,@Param("annee") AnneeAcademique annee);
	
	
	@Query("select d from Deliberation d where d.etape =:etape and typeDeliberation='RATTRAPAGE' and anneeAcademique =:annee")
	List<Deliberation> getDeliberationByEtapeRattrapage(@Param("etape") Etape etape,@Param("annee") AnneeAcademique annee);
	
}

package com.ziad.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ziad.enums.TypeInscription;
import com.ziad.models.AnneeAcademique;
import com.ziad.models.Element;
import com.ziad.models.Etudiant;
import com.ziad.models.InscriptionPedagogique;
import com.ziad.models.Modulee;
import com.ziad.models.Semestre;
import com.ziad.models.compositeid.ComposedInscriptionPedagogique;

@Repository
public interface InscriptionPedagogiqueRepository
		extends JpaRepository<InscriptionPedagogique, ComposedInscriptionPedagogique> {

	@Query("select s from InscriptionPedagogique s where s.id_inscription_pedagogique.etudiant=:x")
	List<InscriptionPedagogique> getInscriptionsPedagogiqueByEtudiant(@Param("x") Etudiant etudiant);

	@Query("select s from InscriptionPedagogique s where s.id_inscription_pedagogique.element=:x")
	List<InscriptionPedagogique> getInscriptionsPedagogiqueByElement(@Param("x") Element element);

	@Query("select s.id_inscription_pedagogique.etudiant from InscriptionPedagogique s where s.id_inscription_pedagogique.element=:x and s.annee_academique =:a")
	List<Etudiant> getEtudiantsByElementAndAnneeAcademique(@Param("x") Element element,@Param("a")AnneeAcademique annee);
	
	@Query("select s from InscriptionPedagogique s where s.id_inscription_pedagogique.element=:x and s.annee_academique =:a")
	List<InscriptionPedagogique> getInscriptionPedagogiquesByElementAndAnneeAcademique(@Param("x") Element element,@Param("a")AnneeAcademique annee);

	@Query("select s from InscriptionPedagogique s where s.annee_academique =:annee and s.id_inscription_pedagogique.element.module =:module")
	List<InscriptionPedagogique> getInscriptionPedagogiqueParModule(@Param("module") Modulee module,@Param("annee")AnneeAcademique annee);
	
	@Query("select s from InscriptionPedagogique s where s.annee_academique =:annee and s.id_inscription_pedagogique.element.module.semestre =:semestre")
	List<InscriptionPedagogique> getInscriptionPedagogiqueParSemestre(@Param("semestre") Semestre semestre,@Param("annee")AnneeAcademique annee);
	
}

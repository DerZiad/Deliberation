package com.ziad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ziad.models.InscriptionAdministrative;
import com.ziad.models.compositeid.ComposedInscriptionAdministrative;
@Repository
public interface InscriptionAdministrativeRepository extends JpaRepository<InscriptionAdministrative, ComposedInscriptionAdministrative>{
	/*
	@Query("select s from InscriptionAdministrative s")
	List<InscriptionAdministrative> getAllInscriptionsAdministrative();
	
	@Query("select s from InscriptionAdministrative s where filieres=:x")
	List<InscriptionAdministrative> getInscriptionsAdministrativeByFiliere(@Param("x")Filiere f);
	
	
	@Query("select count(*) from InscriptionAdministrative s where s.filieres=:x")
	int getNumIaByFiliere(@Param("x")Filiere filiere);
	
	@Transactional
	@Modifying
	@Query("update InscriptionAdministrative s set s.annee_academique=:a, s.date_pre_inscription=:b, s.date_valid_inscription=:c, s.etudiant=:d, s.filieres=:e, s.operateur=:f where s.id_ia=:g")
	void updateInscriptionAdministrative(@Param("g")int id_ia,@Param("a")String annee_academique,@Param("b")Date date_pre_inscription,@Param("c")Date date_valid_inscription,@Param("d")Etudiant etudiant,@Param("e")Filiere filieres_id_filiere,@Param("f")String operateur);
	*/
}

package com.ziad.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ziad.models.Etudiant;
import com.ziad.models.Filiere;
import com.ziad.models.InscriptionAdministrative;
import com.ziad.models.Modulee;
import com.ziad.models.Semestre;
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
	
	@Query("select ia from InscriptionAdministrative ia where ia.composite_association_id.filiere =:filiere")
	public List<InscriptionAdministrative> listerInscriptionsAdministrativesByFiliere(@Param("filiere")Filiere filiere);
	
	@Query("select ia from InscriptionAdministrative ia,InscriptionPedagogique ip where ia.composite_association_id.etudiant = ip.id_inscription_pedagogique.etudiant and ip.id_inscription_pedagogique.element.module=:module")
	public List<InscriptionAdministrative> listerInscriptionsAdministrativesByModule(@Param("module")Modulee module);
	
	@Query("select ia from InscriptionAdministrative ia,InscriptionPedagogique ip where ia.composite_association_id.etudiant = ip.id_inscription_pedagogique.etudiant and ip.id_inscription_pedagogique.element.module.semestre=:semestre")
	public List<InscriptionAdministrative> listerInscriptionsAdministrativesBySemestre(@Param("semestre")Semestre semestre);
	
	@Query("delete from InscriptionAdministrative ia where ia.composite_association_id.filiere =:filiere and ia.composite_association_id.etudiant =:etudiant")
	public void deleteInscriptionAdministrative(@Param("filiere")Filiere filiere,@Param("etudiant")Etudiant etudiant);
}

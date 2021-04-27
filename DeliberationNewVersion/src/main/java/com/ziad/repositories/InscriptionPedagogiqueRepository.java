package com.ziad.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ziad.models.Etudiant;
import com.ziad.models.InscriptionPedagogique;
import com.ziad.models.Semestre;
@Repository
public interface InscriptionPedagogiqueRepository extends JpaRepository<InscriptionPedagogique, Long>{
	
	@Query("select s from InscriptionPedagogique s where s.id_inscription_pedagogique.etudiant=:x")
	List<InscriptionPedagogique> getInscriptionsPedagogiqueByEtudiant(@Param("x")Etudiant etudiant);
	
	/*
	@Transactional
	@Modifying
	@Query("update InscriptionPedagogique s set s.note_semestre=:n, s.validation=:v  where s.id_ip=:id")
	void updateInscriptionPedagogiqueModule(@Param("id") int id ,@Param("n") Double note ,@Param("v") Double validation);

	@Query("select s from InscriptionPedagogique s where etudiant=:x and annee_academique=:y and semestre=:z and date_pre_inscription=:a and date_valid_inscription=:b")
	InscriptionPedagogique getInscriptionsPedagogiqueByInscriptionPedagogique(@Param("x")Etudiant etudiant ,@Param("y")String annee_academique
			,@Param("z")Semestre semestre,@Param("a")Date date_pre_inscription,@Param("b")Date date_valid_inscription);*/
	
}

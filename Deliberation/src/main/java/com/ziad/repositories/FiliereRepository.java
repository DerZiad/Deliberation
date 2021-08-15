package com.ziad.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ziad.models.Etablissement;
import com.ziad.models.Filiere;
@Repository
public interface FiliereRepository extends JpaRepository<Filiere, Long>{
	/*
	@Query("select s from Filiere s")
	List<Filiere> getAllFiliere();
	*/
	@Query("select f from Filiere f where f.etablissement = :etablissement")
	List<Filiere> getFilieresByEtablissement(@Param("etablissement") Etablissement etablissement);
	
}

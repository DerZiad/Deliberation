package com.ziad.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ziad.models.Etape;
import com.ziad.models.Filiere;
@Repository
public interface EtapeRepository extends JpaRepository<Etape, Long>{
	/*
	@Query("select e from Etape e where e.libelle_etape = :libelle_etape")
	List<Etape> getEtapesByLibelle(@Param("libelle_etape")String libelle_etape);
	*/
	@Query("select s from Etape s where filiere=:x")
	List<Etape> getEtapeByFiliere(@Param("x")Filiere filiere);
	/*
	@Transactional
	@Modifying
	@Query("update Etape s set s.diplomante=:a where s.id_etape=:g")
	void activerDiplomante(@Param("g")int id_ia,@Param("a")int diplomante);*/
	
}

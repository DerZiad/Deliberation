package com.ziad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ziad.models.Professeur;
@Repository
public interface ProfesseurRepository extends JpaRepository<Professeur, Long>{
	/* 
	@Query(value="select s from Professeur s")
	    public List<Professeur> getAllProfesseur();
	 
	 @Query("select p from Professeur p where p.user = :user")
	 public Professeur getProfesseurByUser(@Param("user") User user);*/
}

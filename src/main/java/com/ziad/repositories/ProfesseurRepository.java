package com.ziad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ziad.models.Professeur;
import com.ziad.models.User;
@Repository
public interface ProfesseurRepository extends JpaRepository<Professeur, Long>{
	 @Query("select p from Professeur p where p.user = :user")
	 public Professeur getProfesseurByUser(@Param("user") User user);
}

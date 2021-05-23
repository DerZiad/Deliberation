package com.ziad.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ziad.models.Modulee;
import com.ziad.models.Semestre;
@Repository
public interface ModuleRepository extends JpaRepository<Modulee, Long>{
	/*
	@Query("select m from Module m where m.professeur = :professeur")
	List<Module> getModulesByProfesseur(@Param("professeur") Professeur professeur);
	*/
	@Query("select s from Modulee s where s.semestre=:x")
	List<Modulee> getModuleBySemestre(@Param("x")Semestre semestre);
	/*
	@Query("select s from Module s")
	List<Module> getAllModules();
	
	@Query("select m from Module m, Semestre s where m.semestre = s.id_semestre and s.filiere = :filiere")
	List<Module> getModulesByFiliere(@Param("filiere") Filiere filiere);
	*/
	
	
}

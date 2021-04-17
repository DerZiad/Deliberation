package com.ziad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ziad.models.Modulee;
@Repository
public interface ModuleRepository extends JpaRepository<Modulee, Long>{
	/*
	@Query("select m from Module m where m.professeur = :professeur")
	List<Module> getModulesByProfesseur(@Param("professeur") Professeur professeur);

	@Query("select s from Module s where semestre=:x")
	List<Module> getModuleBySemestre(@Param("x")Semestre semestre);
	
	@Query("select s from Module s")
	List<Module> getAllModules();
	
	@Query("select m from Module m, Semestre s where m.semestre = s.id_semestre and s.filiere = :filiere")
	List<Module> getModulesByFiliere(@Param("filiere") Filiere filiere);
	*/
}

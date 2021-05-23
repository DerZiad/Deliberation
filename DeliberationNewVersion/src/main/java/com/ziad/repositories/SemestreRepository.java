package com.ziad.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ziad.models.Etape;
import com.ziad.models.Filiere;
import com.ziad.models.Semestre;

@Repository
public interface SemestreRepository extends JpaRepository<Semestre, Long> {
	/*
	 * @Query("select s from Semestre s") List<Semestre> getAllSemestre();
	 */
	@Query("select s from Semestre s,Etape e,Filiere f where s.etape = e.id_etape and f.id_filiere=e.filiere and f =:x")
	List<Semestre> getSemestresByFiliere(@Param("x") Filiere filiere);

	/*
	 * @Query("select s from Semestre s where filiere=:x") List<Semestre>
	 * getSemestreByFiliere(@Param("x")Filiere filiere);
	 */
	@Query("select s from Semestre s where etape = :etape")
	List<Semestre> getSemestreByEtape(@Param("etape") Etape etape);
	/*
	 * @Query("select s from Semestre s where filiere=:x and libelle_semestre=:y")
	 * Semestre getSemestreByFiliereAndLibelle(@Param("x")Filiere
	 * filiere,@Param("y")String libelle_semestre);
	 */
}

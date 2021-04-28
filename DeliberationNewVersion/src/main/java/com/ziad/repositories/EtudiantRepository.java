package com.ziad.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ziad.models.Etudiant;
@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long>{
	@Query("select s from Etudiant s where s.massar_edu =:massar_edu and s.first_name_fr =:nom and s.last_name_fr =:prenom")
	List<Etudiant> getStudentByNationality(@Param("massar_edu") String massar_edu,@Param("nom")String nom,@Param("prenom")String prenom);
}

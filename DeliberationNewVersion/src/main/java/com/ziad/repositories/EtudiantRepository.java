package com.ziad.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ziad.models.Etudiant;
import com.ziad.models.Modulee;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
	@Query("select s from Etudiant s where s.massar_edu =:massar_edu and s.first_name_fr =:nom and s.last_name_fr =:prenom")
	List<Etudiant> listerEtudiantParMassarNomPrenom(@Param("massar_edu") String massar_edu, @Param("nom") String nom,
			@Param("prenom") String prenom);

	@Query("select e from Etudiant e,InscriptionPedagogique i where i.id_inscription_pedagogique.etudiant = e and i.id_inscription_pedagogique.element.module=:module ")
	List<Etudiant> listerEtudiantParModule(@Param("module") Modulee module);

}

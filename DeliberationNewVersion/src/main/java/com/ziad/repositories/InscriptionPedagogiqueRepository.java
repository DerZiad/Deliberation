package com.ziad.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ziad.models.Element;
import com.ziad.models.Etudiant;
import com.ziad.models.InscriptionPedagogique;
import com.ziad.models.compositeid.ComposedInscriptionPedagogique;

@Repository
public interface InscriptionPedagogiqueRepository
		extends JpaRepository<InscriptionPedagogique, ComposedInscriptionPedagogique> {

	@Query("select s from InscriptionPedagogique s where s.id_inscription_pedagogique.etudiant=:x")
	List<InscriptionPedagogique> getInscriptionsPedagogiqueByEtudiant(@Param("x") Etudiant etudiant);

	@Query("select s from InscriptionPedagogique s where s.id_inscription_pedagogique.element=:x")
	List<InscriptionPedagogique> getInscriptionsPedagogiqueByElement(@Param("x") Element element);

}

package com.ziad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ziad.models.InscriptionEnLigne;
@Repository
public interface InscriptionEnLigneRepository extends JpaRepository<InscriptionEnLigne, Long>{
	/*
	@Query("select s from InscriptionEnLigne s")
	List<InscriptionEnLigne> getAllInscriptionsEnLigne();
	
	@Query("select s from InscriptionEnLigne s where s.accepted=1")
	List<InscriptionEnLigne> getAllInscriptionsEnLigneAccepted();
	
	@Query("select s from InscriptionEnLigne s where s.accepted!=0 and s.first_name_fr=:x and s.last_name_fr=:y")
	InscriptionEnLigne findByNameAccepted(@Param("x")String first_name_fr,@Param("y")String last_name_fr);
	
	List<InscriptionEnLigne> findById(int ID);
	void deleteById(int ID);
	
	@Transactional
	@Modifying
	@Query("update InscriptionEnLigne s set s.accepted=:y where s.id=:x")
	void updateAcceptation(@Param("x")int id,@Param("y")int accepted);*/
	
}

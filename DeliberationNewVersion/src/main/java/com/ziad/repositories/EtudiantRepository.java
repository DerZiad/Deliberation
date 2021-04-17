package com.ziad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ziad.models.Etudiant;
@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long>{
	/*
	@Query("select s from Etudiant s")
	List<Etudiant> getAllStudents();
	
	@Query("select e from Etudiant e where e.cne = :cne")
	Etudiant getEtudiantByCne(@Param("cne")String cne);
	
	@Query("select s from Etudiant s where s.nationality = :nationality")
	List<Etudiant> getStudentByNationality(@Param("nationality") String nationality);
	
	@Query("select id from Etudiant s where first_name_fr=:a and last_name_fr=:b")
	List<Integer>getIdEtudiantByName(@Param("a")String first_name_fr,@Param("b")String last_name_fr);
	
	@Transactional
	@Modifying
	@Query(value="INSERT INTO Etudiant(id, academy, bac_place, bac_type, bac_year, date, birth_place,"
			+ " city, cne, establishment, first_name_ar, first_name_fr, gender, high_school, last_name_ar,"
			+ " last_name_fr, massar_edu, mention, nationality, province, registration_date)"
			+ " SELECT id, academy, bac_place, bac_type, bac_year, date, birth_place, city,"
			+ " cne, establishment, first_name_ar, first_name_fr, gender, high_school, last_name_ar,"
			+ " last_name_fr, massar_edu, mention, nationality, province, registration_date"
			+ " FROM deliberationbachelor.inscription_en_ligne WHERE id=:x" , nativeQuery=true)
    void copyIeEtudiant(@Param("x")int id);*/

}

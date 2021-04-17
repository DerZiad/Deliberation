package com.ziad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ziad.models.InscriptionAdministrativeEtudiantFiliere;
@Repository
public interface InscriptionAdministrativeEtudiantFiliereRepository extends JpaRepository<InscriptionAdministrativeEtudiantFiliere, Long>{

}

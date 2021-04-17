package com.ziad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ziad.models.Etablissement;
@Repository
public interface EtablissementRepository extends JpaRepository<Etablissement, Long>{
}

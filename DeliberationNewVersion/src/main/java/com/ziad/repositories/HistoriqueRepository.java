package com.ziad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ziad.models.Historique;
@Repository
public interface HistoriqueRepository extends JpaRepository<Historique, Long>{

}

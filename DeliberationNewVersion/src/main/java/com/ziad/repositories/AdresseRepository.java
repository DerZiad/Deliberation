package com.ziad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ziad.models.Adresse;

@Repository
public interface AdresseRepository extends JpaRepository<Adresse, Long>{

}

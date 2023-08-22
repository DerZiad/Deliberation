package com.ziad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ziad.models.DocumentDePlus;
@Repository
public interface DocumentDePlusRepository extends JpaRepository<DocumentDePlus, Long>{

}

package com.ziad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ziad.models.AnneeAcademique;
@Repository
public interface AnnneAcademiqueRepository extends JpaRepository<AnneeAcademique,Long>{

}

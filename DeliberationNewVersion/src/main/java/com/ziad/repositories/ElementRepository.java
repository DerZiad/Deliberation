package com.ziad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ziad.models.Element;
@Repository
public interface ElementRepository extends JpaRepository<Element, Long>{

}

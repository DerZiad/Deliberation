package com.ziad.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ziad.models.Element;
import com.ziad.models.Modulee;
@Repository
public interface ElementRepository extends JpaRepository<Element, Long>{
	
	@Query("select e from Element e where e.module =:module")
	List<Element> getElementsByModule(@Param("module") Modulee module);
}

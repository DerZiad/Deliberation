package com.ziad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ziad.models.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, String>{

}

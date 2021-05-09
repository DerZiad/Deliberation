package com.ziad.newmodels;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface DeliberationRepository extends JpaRepository<Deliberation, Long> {

}

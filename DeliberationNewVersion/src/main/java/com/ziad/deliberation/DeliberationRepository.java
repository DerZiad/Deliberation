package com.ziad.deliberation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface DeliberationRepository extends JpaRepository<DeliberationModel, ComposedKey>{

}

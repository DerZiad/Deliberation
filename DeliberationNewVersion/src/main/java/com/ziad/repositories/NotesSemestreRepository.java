package com.ziad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ziad.models.NoteSemestre;
import com.ziad.models.compositeid.ComposedNoteSemestre;

@Repository
public interface NotesSemestreRepository extends JpaRepository<NoteSemestre, ComposedNoteSemestre>{

}

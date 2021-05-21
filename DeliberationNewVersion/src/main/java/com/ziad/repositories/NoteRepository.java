package com.ziad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ziad.models.Note;
@Repository
public interface NoteRepository extends JpaRepository<Note, Long>{

}

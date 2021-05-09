package com.ziad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ziad.models.Note;

public interface NoteRepository extends JpaRepository<Note, Long>{

}

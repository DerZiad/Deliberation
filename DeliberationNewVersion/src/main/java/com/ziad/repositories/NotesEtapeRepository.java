package com.ziad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ziad.models.NoteEtape;
import com.ziad.models.compositeid.ComposedNoteEtape;
@Repository
public interface NotesEtapeRepository extends JpaRepository<NoteEtape, ComposedNoteEtape> {

}

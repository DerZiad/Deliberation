package com.ziad.newmodels;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface NotesEtapeRepository extends JpaRepository<NoteEtape, ComposedNoteEtape> {

}

package com.research.repository;

import com.research.domain.Notes;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Notes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NotesRepository extends JpaRepository<Notes, Long> {

}

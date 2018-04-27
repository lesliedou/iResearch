package com.research.repository;

import com.research.domain.OnsiteProcess;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the OnsiteProcess entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OnsiteProcessRepository extends JpaRepository<OnsiteProcess, Long> {

}

package com.research.repository;

import com.research.domain.Onsite;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Onsite entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OnsiteRepository extends JpaRepository<Onsite, Long> {

}

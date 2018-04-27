package com.research.repository;

import com.research.domain.Target;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Target entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TargetRepository extends JpaRepository<Target, Long> {

}

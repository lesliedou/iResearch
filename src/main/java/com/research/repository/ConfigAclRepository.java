package com.research.repository;

import com.research.domain.ConfigAcl;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the ConfigAcl entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConfigAclRepository extends JpaRepository<ConfigAcl, Long> {

}

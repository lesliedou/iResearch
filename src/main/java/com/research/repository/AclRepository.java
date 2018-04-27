package com.research.repository;

import com.research.domain.Acl;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Acl entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AclRepository extends JpaRepository<Acl, Long> {

}

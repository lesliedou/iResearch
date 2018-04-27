package com.research.repository;

import com.research.domain.UploadInfo;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the UploadInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UploadInfoRepository extends JpaRepository<UploadInfo, Long> {

}

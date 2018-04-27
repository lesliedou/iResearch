package com.research.repository;

import com.research.domain.SurveyQuestionIntf;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the SurveyQuestionIntf entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SurveyQuestionIntfRepository extends JpaRepository<SurveyQuestionIntf, Long> {

}

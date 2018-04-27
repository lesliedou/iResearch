package com.research.repository;

import com.research.domain.SurveyQuestionFlow;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the SurveyQuestionFlow entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SurveyQuestionFlowRepository extends JpaRepository<SurveyQuestionFlow, Long> {

}

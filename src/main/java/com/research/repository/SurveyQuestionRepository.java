package com.research.repository;

import com.research.domain.SurveyQuestion;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the SurveyQuestion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SurveyQuestionRepository extends JpaRepository<SurveyQuestion, Long> {

}

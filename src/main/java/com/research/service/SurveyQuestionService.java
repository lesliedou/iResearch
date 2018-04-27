package com.research.service;

import com.research.domain.SurveyQuestion;
import com.research.repository.SurveyQuestionRepository;
import com.research.service.dto.SurveyQuestionDTO;
import com.research.service.mapper.SurveyQuestionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing SurveyQuestion.
 */
@Service
@Transactional
public class SurveyQuestionService {

    private final Logger log = LoggerFactory.getLogger(SurveyQuestionService.class);

    private final SurveyQuestionRepository surveyQuestionRepository;

    private final SurveyQuestionMapper surveyQuestionMapper;

    public SurveyQuestionService(SurveyQuestionRepository surveyQuestionRepository, SurveyQuestionMapper surveyQuestionMapper) {
        this.surveyQuestionRepository = surveyQuestionRepository;
        this.surveyQuestionMapper = surveyQuestionMapper;
    }

    /**
     * Save a surveyQuestion.
     *
     * @param surveyQuestionDTO the entity to save
     * @return the persisted entity
     */
    public SurveyQuestionDTO save(SurveyQuestionDTO surveyQuestionDTO) {
        log.debug("Request to save SurveyQuestion : {}", surveyQuestionDTO);
        SurveyQuestion surveyQuestion = surveyQuestionMapper.toEntity(surveyQuestionDTO);
        surveyQuestion = surveyQuestionRepository.save(surveyQuestion);
        return surveyQuestionMapper.toDto(surveyQuestion);
    }

    /**
     * Get all the surveyQuestions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<SurveyQuestionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SurveyQuestions");
        return surveyQuestionRepository.findAll(pageable)
            .map(surveyQuestionMapper::toDto);
    }

    /**
     * Get one surveyQuestion by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public SurveyQuestionDTO findOne(Long id) {
        log.debug("Request to get SurveyQuestion : {}", id);
        SurveyQuestion surveyQuestion = surveyQuestionRepository.findOne(id);
        return surveyQuestionMapper.toDto(surveyQuestion);
    }

    /**
     * Delete the surveyQuestion by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete SurveyQuestion : {}", id);
        surveyQuestionRepository.delete(id);
    }
}

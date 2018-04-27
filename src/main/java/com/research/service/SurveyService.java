package com.research.service;

import com.research.domain.Survey;
import com.research.repository.SurveyRepository;
import com.research.service.dto.SurveyDTO;
import com.research.service.mapper.SurveyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Survey.
 */
@Service
@Transactional
public class SurveyService {

    private final Logger log = LoggerFactory.getLogger(SurveyService.class);

    private final SurveyRepository surveyRepository;

    private final SurveyMapper surveyMapper;

    public SurveyService(SurveyRepository surveyRepository, SurveyMapper surveyMapper) {
        this.surveyRepository = surveyRepository;
        this.surveyMapper = surveyMapper;
    }

    /**
     * Save a survey.
     *
     * @param surveyDTO the entity to save
     * @return the persisted entity
     */
    public SurveyDTO save(SurveyDTO surveyDTO) {
        log.debug("Request to save Survey : {}", surveyDTO);
        Survey survey = surveyMapper.toEntity(surveyDTO);
        survey = surveyRepository.save(survey);
        return surveyMapper.toDto(survey);
    }

    /**
     * Get all the surveys.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<SurveyDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Surveys");
        return surveyRepository.findAll(pageable)
            .map(surveyMapper::toDto);
    }

    /**
     * Get one survey by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public SurveyDTO findOne(Long id) {
        log.debug("Request to get Survey : {}", id);
        Survey survey = surveyRepository.findOne(id);
        return surveyMapper.toDto(survey);
    }

    /**
     * Delete the survey by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Survey : {}", id);
        surveyRepository.delete(id);
    }
}

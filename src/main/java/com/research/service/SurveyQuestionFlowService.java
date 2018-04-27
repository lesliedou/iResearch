package com.research.service;

import com.research.domain.SurveyQuestionFlow;
import com.research.repository.SurveyQuestionFlowRepository;
import com.research.service.dto.SurveyQuestionFlowDTO;
import com.research.service.mapper.SurveyQuestionFlowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing SurveyQuestionFlow.
 */
@Service
@Transactional
public class SurveyQuestionFlowService {

    private final Logger log = LoggerFactory.getLogger(SurveyQuestionFlowService.class);

    private final SurveyQuestionFlowRepository surveyQuestionFlowRepository;

    private final SurveyQuestionFlowMapper surveyQuestionFlowMapper;

    public SurveyQuestionFlowService(SurveyQuestionFlowRepository surveyQuestionFlowRepository, SurveyQuestionFlowMapper surveyQuestionFlowMapper) {
        this.surveyQuestionFlowRepository = surveyQuestionFlowRepository;
        this.surveyQuestionFlowMapper = surveyQuestionFlowMapper;
    }

    /**
     * Save a surveyQuestionFlow.
     *
     * @param surveyQuestionFlowDTO the entity to save
     * @return the persisted entity
     */
    public SurveyQuestionFlowDTO save(SurveyQuestionFlowDTO surveyQuestionFlowDTO) {
        log.debug("Request to save SurveyQuestionFlow : {}", surveyQuestionFlowDTO);
        SurveyQuestionFlow surveyQuestionFlow = surveyQuestionFlowMapper.toEntity(surveyQuestionFlowDTO);
        surveyQuestionFlow = surveyQuestionFlowRepository.save(surveyQuestionFlow);
        return surveyQuestionFlowMapper.toDto(surveyQuestionFlow);
    }

    /**
     * Get all the surveyQuestionFlows.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<SurveyQuestionFlowDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SurveyQuestionFlows");
        return surveyQuestionFlowRepository.findAll(pageable)
            .map(surveyQuestionFlowMapper::toDto);
    }

    /**
     * Get one surveyQuestionFlow by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public SurveyQuestionFlowDTO findOne(Long id) {
        log.debug("Request to get SurveyQuestionFlow : {}", id);
        SurveyQuestionFlow surveyQuestionFlow = surveyQuestionFlowRepository.findOne(id);
        return surveyQuestionFlowMapper.toDto(surveyQuestionFlow);
    }

    /**
     * Delete the surveyQuestionFlow by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete SurveyQuestionFlow : {}", id);
        surveyQuestionFlowRepository.delete(id);
    }
}

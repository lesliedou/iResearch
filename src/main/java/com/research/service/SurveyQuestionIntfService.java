package com.research.service;

import com.research.domain.SurveyQuestionIntf;
import com.research.repository.SurveyQuestionIntfRepository;
import com.research.service.dto.SurveyQuestionIntfDTO;
import com.research.service.mapper.SurveyQuestionIntfMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing SurveyQuestionIntf.
 */
@Service
@Transactional
public class SurveyQuestionIntfService {

    private final Logger log = LoggerFactory.getLogger(SurveyQuestionIntfService.class);

    private final SurveyQuestionIntfRepository surveyQuestionIntfRepository;

    private final SurveyQuestionIntfMapper surveyQuestionIntfMapper;

    public SurveyQuestionIntfService(SurveyQuestionIntfRepository surveyQuestionIntfRepository, SurveyQuestionIntfMapper surveyQuestionIntfMapper) {
        this.surveyQuestionIntfRepository = surveyQuestionIntfRepository;
        this.surveyQuestionIntfMapper = surveyQuestionIntfMapper;
    }

    /**
     * Save a surveyQuestionIntf.
     *
     * @param surveyQuestionIntfDTO the entity to save
     * @return the persisted entity
     */
    public SurveyQuestionIntfDTO save(SurveyQuestionIntfDTO surveyQuestionIntfDTO) {
        log.debug("Request to save SurveyQuestionIntf : {}", surveyQuestionIntfDTO);
        SurveyQuestionIntf surveyQuestionIntf = surveyQuestionIntfMapper.toEntity(surveyQuestionIntfDTO);
        surveyQuestionIntf = surveyQuestionIntfRepository.save(surveyQuestionIntf);
        return surveyQuestionIntfMapper.toDto(surveyQuestionIntf);
    }

    /**
     * Get all the surveyQuestionIntfs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<SurveyQuestionIntfDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SurveyQuestionIntfs");
        return surveyQuestionIntfRepository.findAll(pageable)
            .map(surveyQuestionIntfMapper::toDto);
    }

    /**
     * Get one surveyQuestionIntf by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public SurveyQuestionIntfDTO findOne(Long id) {
        log.debug("Request to get SurveyQuestionIntf : {}", id);
        SurveyQuestionIntf surveyQuestionIntf = surveyQuestionIntfRepository.findOne(id);
        return surveyQuestionIntfMapper.toDto(surveyQuestionIntf);
    }

    /**
     * Delete the surveyQuestionIntf by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete SurveyQuestionIntf : {}", id);
        surveyQuestionIntfRepository.delete(id);
    }
}

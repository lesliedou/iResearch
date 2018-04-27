package com.research.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.research.service.SurveyQuestionFlowService;
import com.research.web.rest.errors.BadRequestAlertException;
import com.research.web.rest.util.HeaderUtil;
import com.research.web.rest.util.PaginationUtil;
import com.research.service.dto.SurveyQuestionFlowDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing SurveyQuestionFlow.
 */
@RestController
@RequestMapping("/api")
public class SurveyQuestionFlowResource {

    private final Logger log = LoggerFactory.getLogger(SurveyQuestionFlowResource.class);

    private static final String ENTITY_NAME = "surveyQuestionFlow";

    private final SurveyQuestionFlowService surveyQuestionFlowService;

    public SurveyQuestionFlowResource(SurveyQuestionFlowService surveyQuestionFlowService) {
        this.surveyQuestionFlowService = surveyQuestionFlowService;
    }

    /**
     * POST  /survey-question-flows : Create a new surveyQuestionFlow.
     *
     * @param surveyQuestionFlowDTO the surveyQuestionFlowDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new surveyQuestionFlowDTO, or with status 400 (Bad Request) if the surveyQuestionFlow has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/survey-question-flows")
    @Timed
    public ResponseEntity<SurveyQuestionFlowDTO> createSurveyQuestionFlow(@RequestBody SurveyQuestionFlowDTO surveyQuestionFlowDTO) throws URISyntaxException {
        log.debug("REST request to save SurveyQuestionFlow : {}", surveyQuestionFlowDTO);
        if (surveyQuestionFlowDTO.getId() != null) {
            throw new BadRequestAlertException("A new surveyQuestionFlow cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SurveyQuestionFlowDTO result = surveyQuestionFlowService.save(surveyQuestionFlowDTO);
        return ResponseEntity.created(new URI("/api/survey-question-flows/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /survey-question-flows : Updates an existing surveyQuestionFlow.
     *
     * @param surveyQuestionFlowDTO the surveyQuestionFlowDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated surveyQuestionFlowDTO,
     * or with status 400 (Bad Request) if the surveyQuestionFlowDTO is not valid,
     * or with status 500 (Internal Server Error) if the surveyQuestionFlowDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/survey-question-flows")
    @Timed
    public ResponseEntity<SurveyQuestionFlowDTO> updateSurveyQuestionFlow(@RequestBody SurveyQuestionFlowDTO surveyQuestionFlowDTO) throws URISyntaxException {
        log.debug("REST request to update SurveyQuestionFlow : {}", surveyQuestionFlowDTO);
        if (surveyQuestionFlowDTO.getId() == null) {
            return createSurveyQuestionFlow(surveyQuestionFlowDTO);
        }
        SurveyQuestionFlowDTO result = surveyQuestionFlowService.save(surveyQuestionFlowDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, surveyQuestionFlowDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /survey-question-flows : get all the surveyQuestionFlows.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of surveyQuestionFlows in body
     */
    @GetMapping("/survey-question-flows")
    @Timed
    public ResponseEntity<List<SurveyQuestionFlowDTO>> getAllSurveyQuestionFlows(Pageable pageable) {
        log.debug("REST request to get a page of SurveyQuestionFlows");
        Page<SurveyQuestionFlowDTO> page = surveyQuestionFlowService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/survey-question-flows");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /survey-question-flows/:id : get the "id" surveyQuestionFlow.
     *
     * @param id the id of the surveyQuestionFlowDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the surveyQuestionFlowDTO, or with status 404 (Not Found)
     */
    @GetMapping("/survey-question-flows/{id}")
    @Timed
    public ResponseEntity<SurveyQuestionFlowDTO> getSurveyQuestionFlow(@PathVariable Long id) {
        log.debug("REST request to get SurveyQuestionFlow : {}", id);
        SurveyQuestionFlowDTO surveyQuestionFlowDTO = surveyQuestionFlowService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(surveyQuestionFlowDTO));
    }

    /**
     * DELETE  /survey-question-flows/:id : delete the "id" surveyQuestionFlow.
     *
     * @param id the id of the surveyQuestionFlowDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/survey-question-flows/{id}")
    @Timed
    public ResponseEntity<Void> deleteSurveyQuestionFlow(@PathVariable Long id) {
        log.debug("REST request to delete SurveyQuestionFlow : {}", id);
        surveyQuestionFlowService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

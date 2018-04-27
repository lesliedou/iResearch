package com.research.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.research.service.SurveyQuestionService;
import com.research.web.rest.errors.BadRequestAlertException;
import com.research.web.rest.util.HeaderUtil;
import com.research.web.rest.util.PaginationUtil;
import com.research.service.dto.SurveyQuestionDTO;
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
 * REST controller for managing SurveyQuestion.
 */
@RestController
@RequestMapping("/api")
public class SurveyQuestionResource {

    private final Logger log = LoggerFactory.getLogger(SurveyQuestionResource.class);

    private static final String ENTITY_NAME = "surveyQuestion";

    private final SurveyQuestionService surveyQuestionService;

    public SurveyQuestionResource(SurveyQuestionService surveyQuestionService) {
        this.surveyQuestionService = surveyQuestionService;
    }

    /**
     * POST  /survey-questions : Create a new surveyQuestion.
     *
     * @param surveyQuestionDTO the surveyQuestionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new surveyQuestionDTO, or with status 400 (Bad Request) if the surveyQuestion has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/survey-questions")
    @Timed
    public ResponseEntity<SurveyQuestionDTO> createSurveyQuestion(@RequestBody SurveyQuestionDTO surveyQuestionDTO) throws URISyntaxException {
        log.debug("REST request to save SurveyQuestion : {}", surveyQuestionDTO);
        if (surveyQuestionDTO.getId() != null) {
            throw new BadRequestAlertException("A new surveyQuestion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SurveyQuestionDTO result = surveyQuestionService.save(surveyQuestionDTO);
        return ResponseEntity.created(new URI("/api/survey-questions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /survey-questions : Updates an existing surveyQuestion.
     *
     * @param surveyQuestionDTO the surveyQuestionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated surveyQuestionDTO,
     * or with status 400 (Bad Request) if the surveyQuestionDTO is not valid,
     * or with status 500 (Internal Server Error) if the surveyQuestionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/survey-questions")
    @Timed
    public ResponseEntity<SurveyQuestionDTO> updateSurveyQuestion(@RequestBody SurveyQuestionDTO surveyQuestionDTO) throws URISyntaxException {
        log.debug("REST request to update SurveyQuestion : {}", surveyQuestionDTO);
        if (surveyQuestionDTO.getId() == null) {
            return createSurveyQuestion(surveyQuestionDTO);
        }
        SurveyQuestionDTO result = surveyQuestionService.save(surveyQuestionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, surveyQuestionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /survey-questions : get all the surveyQuestions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of surveyQuestions in body
     */
    @GetMapping("/survey-questions")
    @Timed
    public ResponseEntity<List<SurveyQuestionDTO>> getAllSurveyQuestions(Pageable pageable) {
        log.debug("REST request to get a page of SurveyQuestions");
        Page<SurveyQuestionDTO> page = surveyQuestionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/survey-questions");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /survey-questions/:id : get the "id" surveyQuestion.
     *
     * @param id the id of the surveyQuestionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the surveyQuestionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/survey-questions/{id}")
    @Timed
    public ResponseEntity<SurveyQuestionDTO> getSurveyQuestion(@PathVariable Long id) {
        log.debug("REST request to get SurveyQuestion : {}", id);
        SurveyQuestionDTO surveyQuestionDTO = surveyQuestionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(surveyQuestionDTO));
    }

    /**
     * DELETE  /survey-questions/:id : delete the "id" surveyQuestion.
     *
     * @param id the id of the surveyQuestionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/survey-questions/{id}")
    @Timed
    public ResponseEntity<Void> deleteSurveyQuestion(@PathVariable Long id) {
        log.debug("REST request to delete SurveyQuestion : {}", id);
        surveyQuestionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

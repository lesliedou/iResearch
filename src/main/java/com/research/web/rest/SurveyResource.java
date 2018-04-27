package com.research.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.research.service.SurveyService;
import com.research.web.rest.errors.BadRequestAlertException;
import com.research.web.rest.util.HeaderUtil;
import com.research.web.rest.util.PaginationUtil;
import com.research.service.dto.SurveyDTO;
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
 * REST controller for managing Survey.
 */
@RestController
@RequestMapping("/api")
public class SurveyResource {

    private final Logger log = LoggerFactory.getLogger(SurveyResource.class);

    private static final String ENTITY_NAME = "survey";

    private final SurveyService surveyService;

    public SurveyResource(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    /**
     * POST  /surveys : Create a new survey.
     *
     * @param surveyDTO the surveyDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new surveyDTO, or with status 400 (Bad Request) if the survey has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/surveys")
    @Timed
    public ResponseEntity<SurveyDTO> createSurvey(@RequestBody SurveyDTO surveyDTO) throws URISyntaxException {
        log.debug("REST request to save Survey : {}", surveyDTO);
        if (surveyDTO.getId() != null) {
            throw new BadRequestAlertException("A new survey cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SurveyDTO result = surveyService.save(surveyDTO);
        return ResponseEntity.created(new URI("/api/surveys/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /surveys : Updates an existing survey.
     *
     * @param surveyDTO the surveyDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated surveyDTO,
     * or with status 400 (Bad Request) if the surveyDTO is not valid,
     * or with status 500 (Internal Server Error) if the surveyDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/surveys")
    @Timed
    public ResponseEntity<SurveyDTO> updateSurvey(@RequestBody SurveyDTO surveyDTO) throws URISyntaxException {
        log.debug("REST request to update Survey : {}", surveyDTO);
        if (surveyDTO.getId() == null) {
            return createSurvey(surveyDTO);
        }
        SurveyDTO result = surveyService.save(surveyDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, surveyDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /surveys : get all the surveys.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of surveys in body
     */
    @GetMapping("/surveys")
    @Timed
    public ResponseEntity<List<SurveyDTO>> getAllSurveys(Pageable pageable) {
        log.debug("REST request to get a page of Surveys");
        Page<SurveyDTO> page = surveyService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/surveys");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /surveys/:id : get the "id" survey.
     *
     * @param id the id of the surveyDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the surveyDTO, or with status 404 (Not Found)
     */
    @GetMapping("/surveys/{id}")
    @Timed
    public ResponseEntity<SurveyDTO> getSurvey(@PathVariable Long id) {
        log.debug("REST request to get Survey : {}", id);
        SurveyDTO surveyDTO = surveyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(surveyDTO));
    }

    /**
     * DELETE  /surveys/:id : delete the "id" survey.
     *
     * @param id the id of the surveyDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/surveys/{id}")
    @Timed
    public ResponseEntity<Void> deleteSurvey(@PathVariable Long id) {
        log.debug("REST request to delete Survey : {}", id);
        surveyService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

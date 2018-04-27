package com.research.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.research.service.SurveyQuestionIntfService;
import com.research.web.rest.errors.BadRequestAlertException;
import com.research.web.rest.util.HeaderUtil;
import com.research.web.rest.util.PaginationUtil;
import com.research.service.dto.SurveyQuestionIntfDTO;
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
 * REST controller for managing SurveyQuestionIntf.
 */
@RestController
@RequestMapping("/api")
public class SurveyQuestionIntfResource {

    private final Logger log = LoggerFactory.getLogger(SurveyQuestionIntfResource.class);

    private static final String ENTITY_NAME = "surveyQuestionIntf";

    private final SurveyQuestionIntfService surveyQuestionIntfService;

    public SurveyQuestionIntfResource(SurveyQuestionIntfService surveyQuestionIntfService) {
        this.surveyQuestionIntfService = surveyQuestionIntfService;
    }

    /**
     * POST  /survey-question-intfs : Create a new surveyQuestionIntf.
     *
     * @param surveyQuestionIntfDTO the surveyQuestionIntfDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new surveyQuestionIntfDTO, or with status 400 (Bad Request) if the surveyQuestionIntf has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/survey-question-intfs")
    @Timed
    public ResponseEntity<SurveyQuestionIntfDTO> createSurveyQuestionIntf(@RequestBody SurveyQuestionIntfDTO surveyQuestionIntfDTO) throws URISyntaxException {
        log.debug("REST request to save SurveyQuestionIntf : {}", surveyQuestionIntfDTO);
        if (surveyQuestionIntfDTO.getId() != null) {
            throw new BadRequestAlertException("A new surveyQuestionIntf cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SurveyQuestionIntfDTO result = surveyQuestionIntfService.save(surveyQuestionIntfDTO);
        return ResponseEntity.created(new URI("/api/survey-question-intfs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /survey-question-intfs : Updates an existing surveyQuestionIntf.
     *
     * @param surveyQuestionIntfDTO the surveyQuestionIntfDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated surveyQuestionIntfDTO,
     * or with status 400 (Bad Request) if the surveyQuestionIntfDTO is not valid,
     * or with status 500 (Internal Server Error) if the surveyQuestionIntfDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/survey-question-intfs")
    @Timed
    public ResponseEntity<SurveyQuestionIntfDTO> updateSurveyQuestionIntf(@RequestBody SurveyQuestionIntfDTO surveyQuestionIntfDTO) throws URISyntaxException {
        log.debug("REST request to update SurveyQuestionIntf : {}", surveyQuestionIntfDTO);
        if (surveyQuestionIntfDTO.getId() == null) {
            return createSurveyQuestionIntf(surveyQuestionIntfDTO);
        }
        SurveyQuestionIntfDTO result = surveyQuestionIntfService.save(surveyQuestionIntfDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, surveyQuestionIntfDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /survey-question-intfs : get all the surveyQuestionIntfs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of surveyQuestionIntfs in body
     */
    @GetMapping("/survey-question-intfs")
    @Timed
    public ResponseEntity<List<SurveyQuestionIntfDTO>> getAllSurveyQuestionIntfs(Pageable pageable) {
        log.debug("REST request to get a page of SurveyQuestionIntfs");
        Page<SurveyQuestionIntfDTO> page = surveyQuestionIntfService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/survey-question-intfs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /survey-question-intfs/:id : get the "id" surveyQuestionIntf.
     *
     * @param id the id of the surveyQuestionIntfDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the surveyQuestionIntfDTO, or with status 404 (Not Found)
     */
    @GetMapping("/survey-question-intfs/{id}")
    @Timed
    public ResponseEntity<SurveyQuestionIntfDTO> getSurveyQuestionIntf(@PathVariable Long id) {
        log.debug("REST request to get SurveyQuestionIntf : {}", id);
        SurveyQuestionIntfDTO surveyQuestionIntfDTO = surveyQuestionIntfService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(surveyQuestionIntfDTO));
    }

    /**
     * DELETE  /survey-question-intfs/:id : delete the "id" surveyQuestionIntf.
     *
     * @param id the id of the surveyQuestionIntfDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/survey-question-intfs/{id}")
    @Timed
    public ResponseEntity<Void> deleteSurveyQuestionIntf(@PathVariable Long id) {
        log.debug("REST request to delete SurveyQuestionIntf : {}", id);
        surveyQuestionIntfService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

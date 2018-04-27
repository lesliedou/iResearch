package com.research.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.research.service.TargetService;
import com.research.web.rest.errors.BadRequestAlertException;
import com.research.web.rest.util.HeaderUtil;
import com.research.web.rest.util.PaginationUtil;
import com.research.service.dto.TargetDTO;
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
 * REST controller for managing Target.
 */
@RestController
@RequestMapping("/api")
public class TargetResource {

    private final Logger log = LoggerFactory.getLogger(TargetResource.class);

    private static final String ENTITY_NAME = "target";

    private final TargetService targetService;

    public TargetResource(TargetService targetService) {
        this.targetService = targetService;
    }

    /**
     * POST  /targets : Create a new target.
     *
     * @param targetDTO the targetDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new targetDTO, or with status 400 (Bad Request) if the target has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/targets")
    @Timed
    public ResponseEntity<TargetDTO> createTarget(@RequestBody TargetDTO targetDTO) throws URISyntaxException {
        log.debug("REST request to save Target : {}", targetDTO);
        if (targetDTO.getId() != null) {
            throw new BadRequestAlertException("A new target cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TargetDTO result = targetService.save(targetDTO);
        return ResponseEntity.created(new URI("/api/targets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /targets : Updates an existing target.
     *
     * @param targetDTO the targetDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated targetDTO,
     * or with status 400 (Bad Request) if the targetDTO is not valid,
     * or with status 500 (Internal Server Error) if the targetDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/targets")
    @Timed
    public ResponseEntity<TargetDTO> updateTarget(@RequestBody TargetDTO targetDTO) throws URISyntaxException {
        log.debug("REST request to update Target : {}", targetDTO);
        if (targetDTO.getId() == null) {
            return createTarget(targetDTO);
        }
        TargetDTO result = targetService.save(targetDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, targetDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /targets : get all the targets.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of targets in body
     */
    @GetMapping("/targets")
    @Timed
    public ResponseEntity<List<TargetDTO>> getAllTargets(Pageable pageable) {
        log.debug("REST request to get a page of Targets");
        Page<TargetDTO> page = targetService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/targets");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /targets/:id : get the "id" target.
     *
     * @param id the id of the targetDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the targetDTO, or with status 404 (Not Found)
     */
    @GetMapping("/targets/{id}")
    @Timed
    public ResponseEntity<TargetDTO> getTarget(@PathVariable Long id) {
        log.debug("REST request to get Target : {}", id);
        TargetDTO targetDTO = targetService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(targetDTO));
    }

    /**
     * DELETE  /targets/:id : delete the "id" target.
     *
     * @param id the id of the targetDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/targets/{id}")
    @Timed
    public ResponseEntity<Void> deleteTarget(@PathVariable Long id) {
        log.debug("REST request to delete Target : {}", id);
        targetService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

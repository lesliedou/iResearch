package com.research.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.research.service.OnsiteProcessService;
import com.research.web.rest.errors.BadRequestAlertException;
import com.research.web.rest.util.HeaderUtil;
import com.research.web.rest.util.PaginationUtil;
import com.research.service.dto.OnsiteProcessDTO;
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
 * REST controller for managing OnsiteProcess.
 */
@RestController
@RequestMapping("/api")
public class OnsiteProcessResource {

    private final Logger log = LoggerFactory.getLogger(OnsiteProcessResource.class);

    private static final String ENTITY_NAME = "onsiteProcess";

    private final OnsiteProcessService onsiteProcessService;

    public OnsiteProcessResource(OnsiteProcessService onsiteProcessService) {
        this.onsiteProcessService = onsiteProcessService;
    }

    /**
     * POST  /onsite-processes : Create a new onsiteProcess.
     *
     * @param onsiteProcessDTO the onsiteProcessDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new onsiteProcessDTO, or with status 400 (Bad Request) if the onsiteProcess has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/onsite-processes")
    @Timed
    public ResponseEntity<OnsiteProcessDTO> createOnsiteProcess(@RequestBody OnsiteProcessDTO onsiteProcessDTO) throws URISyntaxException {
        log.debug("REST request to save OnsiteProcess : {}", onsiteProcessDTO);
        if (onsiteProcessDTO.getId() != null) {
            throw new BadRequestAlertException("A new onsiteProcess cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OnsiteProcessDTO result = onsiteProcessService.save(onsiteProcessDTO);
        return ResponseEntity.created(new URI("/api/onsite-processes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /onsite-processes : Updates an existing onsiteProcess.
     *
     * @param onsiteProcessDTO the onsiteProcessDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated onsiteProcessDTO,
     * or with status 400 (Bad Request) if the onsiteProcessDTO is not valid,
     * or with status 500 (Internal Server Error) if the onsiteProcessDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/onsite-processes")
    @Timed
    public ResponseEntity<OnsiteProcessDTO> updateOnsiteProcess(@RequestBody OnsiteProcessDTO onsiteProcessDTO) throws URISyntaxException {
        log.debug("REST request to update OnsiteProcess : {}", onsiteProcessDTO);
        if (onsiteProcessDTO.getId() == null) {
            return createOnsiteProcess(onsiteProcessDTO);
        }
        OnsiteProcessDTO result = onsiteProcessService.save(onsiteProcessDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, onsiteProcessDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /onsite-processes : get all the onsiteProcesses.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of onsiteProcesses in body
     */
    @GetMapping("/onsite-processes")
    @Timed
    public ResponseEntity<List<OnsiteProcessDTO>> getAllOnsiteProcesses(Pageable pageable) {
        log.debug("REST request to get a page of OnsiteProcesses");
        Page<OnsiteProcessDTO> page = onsiteProcessService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/onsite-processes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /onsite-processes/:id : get the "id" onsiteProcess.
     *
     * @param id the id of the onsiteProcessDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the onsiteProcessDTO, or with status 404 (Not Found)
     */
    @GetMapping("/onsite-processes/{id}")
    @Timed
    public ResponseEntity<OnsiteProcessDTO> getOnsiteProcess(@PathVariable Long id) {
        log.debug("REST request to get OnsiteProcess : {}", id);
        OnsiteProcessDTO onsiteProcessDTO = onsiteProcessService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(onsiteProcessDTO));
    }

    /**
     * DELETE  /onsite-processes/:id : delete the "id" onsiteProcess.
     *
     * @param id the id of the onsiteProcessDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/onsite-processes/{id}")
    @Timed
    public ResponseEntity<Void> deleteOnsiteProcess(@PathVariable Long id) {
        log.debug("REST request to delete OnsiteProcess : {}", id);
        onsiteProcessService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

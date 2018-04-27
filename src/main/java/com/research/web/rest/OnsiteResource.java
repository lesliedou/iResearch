package com.research.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.research.service.OnsiteService;
import com.research.web.rest.errors.BadRequestAlertException;
import com.research.web.rest.util.HeaderUtil;
import com.research.web.rest.util.PaginationUtil;
import com.research.service.dto.OnsiteDTO;
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
 * REST controller for managing Onsite.
 */
@RestController
@RequestMapping("/api")
public class OnsiteResource {

    private final Logger log = LoggerFactory.getLogger(OnsiteResource.class);

    private static final String ENTITY_NAME = "onsite";

    private final OnsiteService onsiteService;

    public OnsiteResource(OnsiteService onsiteService) {
        this.onsiteService = onsiteService;
    }

    /**
     * POST  /onsites : Create a new onsite.
     *
     * @param onsiteDTO the onsiteDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new onsiteDTO, or with status 400 (Bad Request) if the onsite has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/onsites")
    @Timed
    public ResponseEntity<OnsiteDTO> createOnsite(@RequestBody OnsiteDTO onsiteDTO) throws URISyntaxException {
        log.debug("REST request to save Onsite : {}", onsiteDTO);
        if (onsiteDTO.getId() != null) {
            throw new BadRequestAlertException("A new onsite cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OnsiteDTO result = onsiteService.save(onsiteDTO);
        return ResponseEntity.created(new URI("/api/onsites/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /onsites : Updates an existing onsite.
     *
     * @param onsiteDTO the onsiteDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated onsiteDTO,
     * or with status 400 (Bad Request) if the onsiteDTO is not valid,
     * or with status 500 (Internal Server Error) if the onsiteDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/onsites")
    @Timed
    public ResponseEntity<OnsiteDTO> updateOnsite(@RequestBody OnsiteDTO onsiteDTO) throws URISyntaxException {
        log.debug("REST request to update Onsite : {}", onsiteDTO);
        if (onsiteDTO.getId() == null) {
            return createOnsite(onsiteDTO);
        }
        OnsiteDTO result = onsiteService.save(onsiteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, onsiteDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /onsites : get all the onsites.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of onsites in body
     */
    @GetMapping("/onsites")
    @Timed
    public ResponseEntity<List<OnsiteDTO>> getAllOnsites(Pageable pageable) {
        log.debug("REST request to get a page of Onsites");
        Page<OnsiteDTO> page = onsiteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/onsites");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /onsites/:id : get the "id" onsite.
     *
     * @param id the id of the onsiteDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the onsiteDTO, or with status 404 (Not Found)
     */
    @GetMapping("/onsites/{id}")
    @Timed
    public ResponseEntity<OnsiteDTO> getOnsite(@PathVariable Long id) {
        log.debug("REST request to get Onsite : {}", id);
        OnsiteDTO onsiteDTO = onsiteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(onsiteDTO));
    }

    /**
     * DELETE  /onsites/:id : delete the "id" onsite.
     *
     * @param id the id of the onsiteDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/onsites/{id}")
    @Timed
    public ResponseEntity<Void> deleteOnsite(@PathVariable Long id) {
        log.debug("REST request to delete Onsite : {}", id);
        onsiteService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

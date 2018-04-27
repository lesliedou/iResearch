package com.research.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.research.service.ConfigAclService;
import com.research.web.rest.errors.BadRequestAlertException;
import com.research.web.rest.util.HeaderUtil;
import com.research.web.rest.util.PaginationUtil;
import com.research.service.dto.ConfigAclDTO;
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
 * REST controller for managing ConfigAcl.
 */
@RestController
@RequestMapping("/api")
public class ConfigAclResource {

    private final Logger log = LoggerFactory.getLogger(ConfigAclResource.class);

    private static final String ENTITY_NAME = "configAcl";

    private final ConfigAclService configAclService;

    public ConfigAclResource(ConfigAclService configAclService) {
        this.configAclService = configAclService;
    }

    /**
     * POST  /config-acls : Create a new configAcl.
     *
     * @param configAclDTO the configAclDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new configAclDTO, or with status 400 (Bad Request) if the configAcl has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/config-acls")
    @Timed
    public ResponseEntity<ConfigAclDTO> createConfigAcl(@RequestBody ConfigAclDTO configAclDTO) throws URISyntaxException {
        log.debug("REST request to save ConfigAcl : {}", configAclDTO);
        if (configAclDTO.getId() != null) {
            throw new BadRequestAlertException("A new configAcl cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ConfigAclDTO result = configAclService.save(configAclDTO);
        return ResponseEntity.created(new URI("/api/config-acls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /config-acls : Updates an existing configAcl.
     *
     * @param configAclDTO the configAclDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated configAclDTO,
     * or with status 400 (Bad Request) if the configAclDTO is not valid,
     * or with status 500 (Internal Server Error) if the configAclDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/config-acls")
    @Timed
    public ResponseEntity<ConfigAclDTO> updateConfigAcl(@RequestBody ConfigAclDTO configAclDTO) throws URISyntaxException {
        log.debug("REST request to update ConfigAcl : {}", configAclDTO);
        if (configAclDTO.getId() == null) {
            return createConfigAcl(configAclDTO);
        }
        ConfigAclDTO result = configAclService.save(configAclDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, configAclDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /config-acls : get all the configAcls.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of configAcls in body
     */
    @GetMapping("/config-acls")
    @Timed
    public ResponseEntity<List<ConfigAclDTO>> getAllConfigAcls(Pageable pageable) {
        log.debug("REST request to get a page of ConfigAcls");
        Page<ConfigAclDTO> page = configAclService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/config-acls");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /config-acls/:id : get the "id" configAcl.
     *
     * @param id the id of the configAclDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the configAclDTO, or with status 404 (Not Found)
     */
    @GetMapping("/config-acls/{id}")
    @Timed
    public ResponseEntity<ConfigAclDTO> getConfigAcl(@PathVariable Long id) {
        log.debug("REST request to get ConfigAcl : {}", id);
        ConfigAclDTO configAclDTO = configAclService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(configAclDTO));
    }

    /**
     * DELETE  /config-acls/:id : delete the "id" configAcl.
     *
     * @param id the id of the configAclDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/config-acls/{id}")
    @Timed
    public ResponseEntity<Void> deleteConfigAcl(@PathVariable Long id) {
        log.debug("REST request to delete ConfigAcl : {}", id);
        configAclService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

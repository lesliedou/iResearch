package com.research.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.research.service.AclService;
import com.research.web.rest.errors.BadRequestAlertException;
import com.research.web.rest.util.HeaderUtil;
import com.research.web.rest.util.PaginationUtil;
import com.research.service.dto.AclDTO;
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
 * REST controller for managing Acl.
 */
@RestController
@RequestMapping("/api")
public class AclResource {

    private final Logger log = LoggerFactory.getLogger(AclResource.class);

    private static final String ENTITY_NAME = "acl";

    private final AclService aclService;

    public AclResource(AclService aclService) {
        this.aclService = aclService;
    }

    /**
     * POST  /acls : Create a new acl.
     *
     * @param aclDTO the aclDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new aclDTO, or with status 400 (Bad Request) if the acl has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/acls")
    @Timed
    public ResponseEntity<AclDTO> createAcl(@RequestBody AclDTO aclDTO) throws URISyntaxException {
        log.debug("REST request to save Acl : {}", aclDTO);
        if (aclDTO.getId() != null) {
            throw new BadRequestAlertException("A new acl cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AclDTO result = aclService.save(aclDTO);
        return ResponseEntity.created(new URI("/api/acls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /acls : Updates an existing acl.
     *
     * @param aclDTO the aclDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated aclDTO,
     * or with status 400 (Bad Request) if the aclDTO is not valid,
     * or with status 500 (Internal Server Error) if the aclDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/acls")
    @Timed
    public ResponseEntity<AclDTO> updateAcl(@RequestBody AclDTO aclDTO) throws URISyntaxException {
        log.debug("REST request to update Acl : {}", aclDTO);
        if (aclDTO.getId() == null) {
            return createAcl(aclDTO);
        }
        AclDTO result = aclService.save(aclDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, aclDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /acls : get all the acls.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of acls in body
     */
    @GetMapping("/acls")
    @Timed
    public ResponseEntity<List<AclDTO>> getAllAcls(Pageable pageable) {
        log.debug("REST request to get a page of Acls");
        Page<AclDTO> page = aclService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/acls");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /acls/:id : get the "id" acl.
     *
     * @param id the id of the aclDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the aclDTO, or with status 404 (Not Found)
     */
    @GetMapping("/acls/{id}")
    @Timed
    public ResponseEntity<AclDTO> getAcl(@PathVariable Long id) {
        log.debug("REST request to get Acl : {}", id);
        AclDTO aclDTO = aclService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(aclDTO));
    }

    /**
     * DELETE  /acls/:id : delete the "id" acl.
     *
     * @param id the id of the aclDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/acls/{id}")
    @Timed
    public ResponseEntity<Void> deleteAcl(@PathVariable Long id) {
        log.debug("REST request to delete Acl : {}", id);
        aclService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

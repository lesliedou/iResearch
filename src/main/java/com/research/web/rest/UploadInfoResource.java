package com.research.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.research.service.UploadInfoService;
import com.research.web.rest.errors.BadRequestAlertException;
import com.research.web.rest.util.HeaderUtil;
import com.research.web.rest.util.PaginationUtil;
import com.research.service.dto.UploadInfoDTO;
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
 * REST controller for managing UploadInfo.
 */
@RestController
@RequestMapping("/api")
public class UploadInfoResource {

    private final Logger log = LoggerFactory.getLogger(UploadInfoResource.class);

    private static final String ENTITY_NAME = "uploadInfo";

    private final UploadInfoService uploadInfoService;

    public UploadInfoResource(UploadInfoService uploadInfoService) {
        this.uploadInfoService = uploadInfoService;
    }

    /**
     * POST  /upload-infos : Create a new uploadInfo.
     *
     * @param uploadInfoDTO the uploadInfoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new uploadInfoDTO, or with status 400 (Bad Request) if the uploadInfo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/upload-infos")
    @Timed
    public ResponseEntity<UploadInfoDTO> createUploadInfo(@RequestBody UploadInfoDTO uploadInfoDTO) throws URISyntaxException {
        log.debug("REST request to save UploadInfo : {}", uploadInfoDTO);
        if (uploadInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new uploadInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UploadInfoDTO result = uploadInfoService.save(uploadInfoDTO);
        return ResponseEntity.created(new URI("/api/upload-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /upload-infos : Updates an existing uploadInfo.
     *
     * @param uploadInfoDTO the uploadInfoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated uploadInfoDTO,
     * or with status 400 (Bad Request) if the uploadInfoDTO is not valid,
     * or with status 500 (Internal Server Error) if the uploadInfoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/upload-infos")
    @Timed
    public ResponseEntity<UploadInfoDTO> updateUploadInfo(@RequestBody UploadInfoDTO uploadInfoDTO) throws URISyntaxException {
        log.debug("REST request to update UploadInfo : {}", uploadInfoDTO);
        if (uploadInfoDTO.getId() == null) {
            return createUploadInfo(uploadInfoDTO);
        }
        UploadInfoDTO result = uploadInfoService.save(uploadInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, uploadInfoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /upload-infos : get all the uploadInfos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of uploadInfos in body
     */
    @GetMapping("/upload-infos")
    @Timed
    public ResponseEntity<List<UploadInfoDTO>> getAllUploadInfos(Pageable pageable) {
        log.debug("REST request to get a page of UploadInfos");
        Page<UploadInfoDTO> page = uploadInfoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/upload-infos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /upload-infos/:id : get the "id" uploadInfo.
     *
     * @param id the id of the uploadInfoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the uploadInfoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/upload-infos/{id}")
    @Timed
    public ResponseEntity<UploadInfoDTO> getUploadInfo(@PathVariable Long id) {
        log.debug("REST request to get UploadInfo : {}", id);
        UploadInfoDTO uploadInfoDTO = uploadInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(uploadInfoDTO));
    }

    /**
     * DELETE  /upload-infos/:id : delete the "id" uploadInfo.
     *
     * @param id the id of the uploadInfoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/upload-infos/{id}")
    @Timed
    public ResponseEntity<Void> deleteUploadInfo(@PathVariable Long id) {
        log.debug("REST request to delete UploadInfo : {}", id);
        uploadInfoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

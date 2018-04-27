package com.research.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.research.service.FilesService;
import com.research.web.rest.errors.BadRequestAlertException;
import com.research.web.rest.util.HeaderUtil;
import com.research.web.rest.util.PaginationUtil;
import com.research.service.dto.FilesDTO;
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
 * REST controller for managing Files.
 */
@RestController
@RequestMapping("/api")
public class FilesResource {

    private final Logger log = LoggerFactory.getLogger(FilesResource.class);

    private static final String ENTITY_NAME = "files";

    private final FilesService filesService;

    public FilesResource(FilesService filesService) {
        this.filesService = filesService;
    }

    /**
     * POST  /files : Create a new files.
     *
     * @param filesDTO the filesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new filesDTO, or with status 400 (Bad Request) if the files has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/files")
    @Timed
    public ResponseEntity<FilesDTO> createFiles(@RequestBody FilesDTO filesDTO) throws URISyntaxException {
        log.debug("REST request to save Files : {}", filesDTO);
        if (filesDTO.getId() != null) {
            throw new BadRequestAlertException("A new files cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FilesDTO result = filesService.save(filesDTO);
        return ResponseEntity.created(new URI("/api/files/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /files : Updates an existing files.
     *
     * @param filesDTO the filesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated filesDTO,
     * or with status 400 (Bad Request) if the filesDTO is not valid,
     * or with status 500 (Internal Server Error) if the filesDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/files")
    @Timed
    public ResponseEntity<FilesDTO> updateFiles(@RequestBody FilesDTO filesDTO) throws URISyntaxException {
        log.debug("REST request to update Files : {}", filesDTO);
        if (filesDTO.getId() == null) {
            return createFiles(filesDTO);
        }
        FilesDTO result = filesService.save(filesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, filesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /files : get all the files.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of files in body
     */
    @GetMapping("/files")
    @Timed
    public ResponseEntity<List<FilesDTO>> getAllFiles(Pageable pageable) {
        log.debug("REST request to get a page of Files");
        Page<FilesDTO> page = filesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/files");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /files/:id : get the "id" files.
     *
     * @param id the id of the filesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the filesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/files/{id}")
    @Timed
    public ResponseEntity<FilesDTO> getFiles(@PathVariable Long id) {
        log.debug("REST request to get Files : {}", id);
        FilesDTO filesDTO = filesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(filesDTO));
    }

    /**
     * DELETE  /files/:id : delete the "id" files.
     *
     * @param id the id of the filesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/files/{id}")
    @Timed
    public ResponseEntity<Void> deleteFiles(@PathVariable Long id) {
        log.debug("REST request to delete Files : {}", id);
        filesService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

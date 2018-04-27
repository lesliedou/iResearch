package com.research.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.research.service.NotesService;
import com.research.web.rest.errors.BadRequestAlertException;
import com.research.web.rest.util.HeaderUtil;
import com.research.web.rest.util.PaginationUtil;
import com.research.service.dto.NotesDTO;
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
 * REST controller for managing Notes.
 */
@RestController
@RequestMapping("/api")
public class NotesResource {

    private final Logger log = LoggerFactory.getLogger(NotesResource.class);

    private static final String ENTITY_NAME = "notes";

    private final NotesService notesService;

    public NotesResource(NotesService notesService) {
        this.notesService = notesService;
    }

    /**
     * POST  /notes : Create a new notes.
     *
     * @param notesDTO the notesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new notesDTO, or with status 400 (Bad Request) if the notes has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/notes")
    @Timed
    public ResponseEntity<NotesDTO> createNotes(@RequestBody NotesDTO notesDTO) throws URISyntaxException {
        log.debug("REST request to save Notes : {}", notesDTO);
        if (notesDTO.getId() != null) {
            throw new BadRequestAlertException("A new notes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NotesDTO result = notesService.save(notesDTO);
        return ResponseEntity.created(new URI("/api/notes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /notes : Updates an existing notes.
     *
     * @param notesDTO the notesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated notesDTO,
     * or with status 400 (Bad Request) if the notesDTO is not valid,
     * or with status 500 (Internal Server Error) if the notesDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/notes")
    @Timed
    public ResponseEntity<NotesDTO> updateNotes(@RequestBody NotesDTO notesDTO) throws URISyntaxException {
        log.debug("REST request to update Notes : {}", notesDTO);
        if (notesDTO.getId() == null) {
            return createNotes(notesDTO);
        }
        NotesDTO result = notesService.save(notesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, notesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /notes : get all the notes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of notes in body
     */
    @GetMapping("/notes")
    @Timed
    public ResponseEntity<List<NotesDTO>> getAllNotes(Pageable pageable) {
        log.debug("REST request to get a page of Notes");
        Page<NotesDTO> page = notesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/notes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /notes/:id : get the "id" notes.
     *
     * @param id the id of the notesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the notesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/notes/{id}")
    @Timed
    public ResponseEntity<NotesDTO> getNotes(@PathVariable Long id) {
        log.debug("REST request to get Notes : {}", id);
        NotesDTO notesDTO = notesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(notesDTO));
    }

    /**
     * DELETE  /notes/:id : delete the "id" notes.
     *
     * @param id the id of the notesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/notes/{id}")
    @Timed
    public ResponseEntity<Void> deleteNotes(@PathVariable Long id) {
        log.debug("REST request to delete Notes : {}", id);
        notesService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

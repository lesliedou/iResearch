package com.research.service;

import com.research.domain.Notes;
import com.research.repository.NotesRepository;
import com.research.service.dto.NotesDTO;
import com.research.service.mapper.NotesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Notes.
 */
@Service
@Transactional
public class NotesService {

    private final Logger log = LoggerFactory.getLogger(NotesService.class);

    private final NotesRepository notesRepository;

    private final NotesMapper notesMapper;

    public NotesService(NotesRepository notesRepository, NotesMapper notesMapper) {
        this.notesRepository = notesRepository;
        this.notesMapper = notesMapper;
    }

    /**
     * Save a notes.
     *
     * @param notesDTO the entity to save
     * @return the persisted entity
     */
    public NotesDTO save(NotesDTO notesDTO) {
        log.debug("Request to save Notes : {}", notesDTO);
        Notes notes = notesMapper.toEntity(notesDTO);
        notes = notesRepository.save(notes);
        return notesMapper.toDto(notes);
    }

    /**
     * Get all the notes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<NotesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Notes");
        return notesRepository.findAll(pageable)
            .map(notesMapper::toDto);
    }

    /**
     * Get one notes by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public NotesDTO findOne(Long id) {
        log.debug("Request to get Notes : {}", id);
        Notes notes = notesRepository.findOne(id);
        return notesMapper.toDto(notes);
    }

    /**
     * Delete the notes by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Notes : {}", id);
        notesRepository.delete(id);
    }
}

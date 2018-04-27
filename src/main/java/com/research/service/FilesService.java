package com.research.service;

import com.research.domain.Files;
import com.research.repository.FilesRepository;
import com.research.service.dto.FilesDTO;
import com.research.service.mapper.FilesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Files.
 */
@Service
@Transactional
public class FilesService {

    private final Logger log = LoggerFactory.getLogger(FilesService.class);

    private final FilesRepository filesRepository;

    private final FilesMapper filesMapper;

    public FilesService(FilesRepository filesRepository, FilesMapper filesMapper) {
        this.filesRepository = filesRepository;
        this.filesMapper = filesMapper;
    }

    /**
     * Save a files.
     *
     * @param filesDTO the entity to save
     * @return the persisted entity
     */
    public FilesDTO save(FilesDTO filesDTO) {
        log.debug("Request to save Files : {}", filesDTO);
        Files files = filesMapper.toEntity(filesDTO);
        files = filesRepository.save(files);
        return filesMapper.toDto(files);
    }

    /**
     * Get all the files.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<FilesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Files");
        return filesRepository.findAll(pageable)
            .map(filesMapper::toDto);
    }

    /**
     * Get one files by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public FilesDTO findOne(Long id) {
        log.debug("Request to get Files : {}", id);
        Files files = filesRepository.findOne(id);
        return filesMapper.toDto(files);
    }

    /**
     * Delete the files by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Files : {}", id);
        filesRepository.delete(id);
    }
}

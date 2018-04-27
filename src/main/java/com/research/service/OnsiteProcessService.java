package com.research.service;

import com.research.domain.OnsiteProcess;
import com.research.repository.OnsiteProcessRepository;
import com.research.service.dto.OnsiteProcessDTO;
import com.research.service.mapper.OnsiteProcessMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing OnsiteProcess.
 */
@Service
@Transactional
public class OnsiteProcessService {

    private final Logger log = LoggerFactory.getLogger(OnsiteProcessService.class);

    private final OnsiteProcessRepository onsiteProcessRepository;

    private final OnsiteProcessMapper onsiteProcessMapper;

    public OnsiteProcessService(OnsiteProcessRepository onsiteProcessRepository, OnsiteProcessMapper onsiteProcessMapper) {
        this.onsiteProcessRepository = onsiteProcessRepository;
        this.onsiteProcessMapper = onsiteProcessMapper;
    }

    /**
     * Save a onsiteProcess.
     *
     * @param onsiteProcessDTO the entity to save
     * @return the persisted entity
     */
    public OnsiteProcessDTO save(OnsiteProcessDTO onsiteProcessDTO) {
        log.debug("Request to save OnsiteProcess : {}", onsiteProcessDTO);
        OnsiteProcess onsiteProcess = onsiteProcessMapper.toEntity(onsiteProcessDTO);
        onsiteProcess = onsiteProcessRepository.save(onsiteProcess);
        return onsiteProcessMapper.toDto(onsiteProcess);
    }

    /**
     * Get all the onsiteProcesses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<OnsiteProcessDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OnsiteProcesses");
        return onsiteProcessRepository.findAll(pageable)
            .map(onsiteProcessMapper::toDto);
    }

    /**
     * Get one onsiteProcess by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public OnsiteProcessDTO findOne(Long id) {
        log.debug("Request to get OnsiteProcess : {}", id);
        OnsiteProcess onsiteProcess = onsiteProcessRepository.findOne(id);
        return onsiteProcessMapper.toDto(onsiteProcess);
    }

    /**
     * Delete the onsiteProcess by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete OnsiteProcess : {}", id);
        onsiteProcessRepository.delete(id);
    }
}

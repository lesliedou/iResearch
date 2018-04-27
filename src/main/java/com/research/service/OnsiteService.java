package com.research.service;

import com.research.domain.Onsite;
import com.research.repository.OnsiteRepository;
import com.research.service.dto.OnsiteDTO;
import com.research.service.mapper.OnsiteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Onsite.
 */
@Service
@Transactional
public class OnsiteService {

    private final Logger log = LoggerFactory.getLogger(OnsiteService.class);

    private final OnsiteRepository onsiteRepository;

    private final OnsiteMapper onsiteMapper;

    public OnsiteService(OnsiteRepository onsiteRepository, OnsiteMapper onsiteMapper) {
        this.onsiteRepository = onsiteRepository;
        this.onsiteMapper = onsiteMapper;
    }

    /**
     * Save a onsite.
     *
     * @param onsiteDTO the entity to save
     * @return the persisted entity
     */
    public OnsiteDTO save(OnsiteDTO onsiteDTO) {
        log.debug("Request to save Onsite : {}", onsiteDTO);
        Onsite onsite = onsiteMapper.toEntity(onsiteDTO);
        onsite = onsiteRepository.save(onsite);
        return onsiteMapper.toDto(onsite);
    }

    /**
     * Get all the onsites.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<OnsiteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Onsites");
        return onsiteRepository.findAll(pageable)
            .map(onsiteMapper::toDto);
    }

    /**
     * Get one onsite by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public OnsiteDTO findOne(Long id) {
        log.debug("Request to get Onsite : {}", id);
        Onsite onsite = onsiteRepository.findOne(id);
        return onsiteMapper.toDto(onsite);
    }

    /**
     * Delete the onsite by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Onsite : {}", id);
        onsiteRepository.delete(id);
    }
}

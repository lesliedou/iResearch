package com.research.service;

import com.research.domain.ConfigAcl;
import com.research.repository.ConfigAclRepository;
import com.research.service.dto.ConfigAclDTO;
import com.research.service.mapper.ConfigAclMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing ConfigAcl.
 */
@Service
@Transactional
public class ConfigAclService {

    private final Logger log = LoggerFactory.getLogger(ConfigAclService.class);

    private final ConfigAclRepository configAclRepository;

    private final ConfigAclMapper configAclMapper;

    public ConfigAclService(ConfigAclRepository configAclRepository, ConfigAclMapper configAclMapper) {
        this.configAclRepository = configAclRepository;
        this.configAclMapper = configAclMapper;
    }

    /**
     * Save a configAcl.
     *
     * @param configAclDTO the entity to save
     * @return the persisted entity
     */
    public ConfigAclDTO save(ConfigAclDTO configAclDTO) {
        log.debug("Request to save ConfigAcl : {}", configAclDTO);
        ConfigAcl configAcl = configAclMapper.toEntity(configAclDTO);
        configAcl = configAclRepository.save(configAcl);
        return configAclMapper.toDto(configAcl);
    }

    /**
     * Get all the configAcls.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<ConfigAclDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ConfigAcls");
        return configAclRepository.findAll(pageable)
            .map(configAclMapper::toDto);
    }

    /**
     * Get one configAcl by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public ConfigAclDTO findOne(Long id) {
        log.debug("Request to get ConfigAcl : {}", id);
        ConfigAcl configAcl = configAclRepository.findOne(id);
        return configAclMapper.toDto(configAcl);
    }

    /**
     * Delete the configAcl by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ConfigAcl : {}", id);
        configAclRepository.delete(id);
    }
}

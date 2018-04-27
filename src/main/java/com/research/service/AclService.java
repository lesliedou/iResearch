package com.research.service;

import com.research.domain.Acl;
import com.research.repository.AclRepository;
import com.research.service.dto.AclDTO;
import com.research.service.mapper.AclMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Acl.
 */
@Service
@Transactional
public class AclService {

    private final Logger log = LoggerFactory.getLogger(AclService.class);

    private final AclRepository aclRepository;

    private final AclMapper aclMapper;

    public AclService(AclRepository aclRepository, AclMapper aclMapper) {
        this.aclRepository = aclRepository;
        this.aclMapper = aclMapper;
    }

    /**
     * Save a acl.
     *
     * @param aclDTO the entity to save
     * @return the persisted entity
     */
    public AclDTO save(AclDTO aclDTO) {
        log.debug("Request to save Acl : {}", aclDTO);
        Acl acl = aclMapper.toEntity(aclDTO);
        acl = aclRepository.save(acl);
        return aclMapper.toDto(acl);
    }

    /**
     * Get all the acls.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<AclDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Acls");
        return aclRepository.findAll(pageable)
            .map(aclMapper::toDto);
    }

    /**
     * Get one acl by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public AclDTO findOne(Long id) {
        log.debug("Request to get Acl : {}", id);
        Acl acl = aclRepository.findOne(id);
        return aclMapper.toDto(acl);
    }

    /**
     * Delete the acl by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Acl : {}", id);
        aclRepository.delete(id);
    }
}

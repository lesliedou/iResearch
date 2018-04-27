package com.research.service;

import com.research.domain.Target;
import com.research.repository.TargetRepository;
import com.research.service.dto.TargetDTO;
import com.research.service.mapper.TargetMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Target.
 */
@Service
@Transactional
public class TargetService {

    private final Logger log = LoggerFactory.getLogger(TargetService.class);

    private final TargetRepository targetRepository;

    private final TargetMapper targetMapper;

    public TargetService(TargetRepository targetRepository, TargetMapper targetMapper) {
        this.targetRepository = targetRepository;
        this.targetMapper = targetMapper;
    }

    /**
     * Save a target.
     *
     * @param targetDTO the entity to save
     * @return the persisted entity
     */
    public TargetDTO save(TargetDTO targetDTO) {
        log.debug("Request to save Target : {}", targetDTO);
        Target target = targetMapper.toEntity(targetDTO);
        target = targetRepository.save(target);
        return targetMapper.toDto(target);
    }

    /**
     * Get all the targets.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<TargetDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Targets");
        return targetRepository.findAll(pageable)
            .map(targetMapper::toDto);
    }

    /**
     * Get one target by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public TargetDTO findOne(Long id) {
        log.debug("Request to get Target : {}", id);
        Target target = targetRepository.findOne(id);
        return targetMapper.toDto(target);
    }

    /**
     * Delete the target by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Target : {}", id);
        targetRepository.delete(id);
    }
}

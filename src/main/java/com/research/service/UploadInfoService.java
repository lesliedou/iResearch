package com.research.service;

import com.research.domain.UploadInfo;
import com.research.repository.UploadInfoRepository;
import com.research.service.dto.UploadInfoDTO;
import com.research.service.mapper.UploadInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing UploadInfo.
 */
@Service
@Transactional
public class UploadInfoService {

    private final Logger log = LoggerFactory.getLogger(UploadInfoService.class);

    private final UploadInfoRepository uploadInfoRepository;

    private final UploadInfoMapper uploadInfoMapper;

    public UploadInfoService(UploadInfoRepository uploadInfoRepository, UploadInfoMapper uploadInfoMapper) {
        this.uploadInfoRepository = uploadInfoRepository;
        this.uploadInfoMapper = uploadInfoMapper;
    }

    /**
     * Save a uploadInfo.
     *
     * @param uploadInfoDTO the entity to save
     * @return the persisted entity
     */
    public UploadInfoDTO save(UploadInfoDTO uploadInfoDTO) {
        log.debug("Request to save UploadInfo : {}", uploadInfoDTO);
        UploadInfo uploadInfo = uploadInfoMapper.toEntity(uploadInfoDTO);
        uploadInfo = uploadInfoRepository.save(uploadInfo);
        return uploadInfoMapper.toDto(uploadInfo);
    }

    /**
     * Get all the uploadInfos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<UploadInfoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UploadInfos");
        return uploadInfoRepository.findAll(pageable)
            .map(uploadInfoMapper::toDto);
    }

    /**
     * Get one uploadInfo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public UploadInfoDTO findOne(Long id) {
        log.debug("Request to get UploadInfo : {}", id);
        UploadInfo uploadInfo = uploadInfoRepository.findOne(id);
        return uploadInfoMapper.toDto(uploadInfo);
    }

    /**
     * Delete the uploadInfo by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete UploadInfo : {}", id);
        uploadInfoRepository.delete(id);
    }
}

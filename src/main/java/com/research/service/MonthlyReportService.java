package com.research.service;

import com.research.domain.MonthlyReport;
import com.research.repository.MonthlyReportRepository;
import com.research.service.dto.MonthlyReportDTO;
import com.research.service.mapper.MonthlyReportMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing MonthlyReport.
 */
@Service
@Transactional
public class MonthlyReportService {

    private final Logger log = LoggerFactory.getLogger(MonthlyReportService.class);

    private final MonthlyReportRepository monthlyReportRepository;

    private final MonthlyReportMapper monthlyReportMapper;

    public MonthlyReportService(MonthlyReportRepository monthlyReportRepository, MonthlyReportMapper monthlyReportMapper) {
        this.monthlyReportRepository = monthlyReportRepository;
        this.monthlyReportMapper = monthlyReportMapper;
    }

    /**
     * Save a monthlyReport.
     *
     * @param monthlyReportDTO the entity to save
     * @return the persisted entity
     */
    public MonthlyReportDTO save(MonthlyReportDTO monthlyReportDTO) {
        log.debug("Request to save MonthlyReport : {}", monthlyReportDTO);
        MonthlyReport monthlyReport = monthlyReportMapper.toEntity(monthlyReportDTO);
        monthlyReport = monthlyReportRepository.save(monthlyReport);
        return monthlyReportMapper.toDto(monthlyReport);
    }

    /**
     * Get all the monthlyReports.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<MonthlyReportDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MonthlyReports");
        return monthlyReportRepository.findAll(pageable)
            .map(monthlyReportMapper::toDto);
    }

    /**
     * Get one monthlyReport by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public MonthlyReportDTO findOne(Long id) {
        log.debug("Request to get MonthlyReport : {}", id);
        MonthlyReport monthlyReport = monthlyReportRepository.findOne(id);
        return monthlyReportMapper.toDto(monthlyReport);
    }

    /**
     * Delete the monthlyReport by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete MonthlyReport : {}", id);
        monthlyReportRepository.delete(id);
    }
}

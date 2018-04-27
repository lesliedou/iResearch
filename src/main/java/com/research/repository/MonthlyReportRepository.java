package com.research.repository;

import com.research.domain.MonthlyReport;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the MonthlyReport entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MonthlyReportRepository extends JpaRepository<MonthlyReport, Long> {

}

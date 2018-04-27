package com.research.service.mapper;

import com.research.domain.*;
import com.research.service.dto.MonthlyReportDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MonthlyReport and its DTO MonthlyReportDTO.
 */
@Mapper(componentModel = "spring", uses = {DepartmentMapper.class})
public interface MonthlyReportMapper extends EntityMapper<MonthlyReportDTO, MonthlyReport> {

    @Mapping(source = "department.id", target = "departmentId")
    MonthlyReportDTO toDto(MonthlyReport monthlyReport);

    @Mapping(source = "departmentId", target = "department")
    MonthlyReport toEntity(MonthlyReportDTO monthlyReportDTO);

    default MonthlyReport fromId(Long id) {
        if (id == null) {
            return null;
        }
        MonthlyReport monthlyReport = new MonthlyReport();
        monthlyReport.setId(id);
        return monthlyReport;
    }
}

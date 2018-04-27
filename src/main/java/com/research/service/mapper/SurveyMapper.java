package com.research.service.mapper;

import com.research.domain.*;
import com.research.service.dto.SurveyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Survey and its DTO SurveyDTO.
 */
@Mapper(componentModel = "spring", uses = {DepartmentMapper.class})
public interface SurveyMapper extends EntityMapper<SurveyDTO, Survey> {

    @Mapping(source = "department.id", target = "departmentId")
    SurveyDTO toDto(Survey survey);

    @Mapping(source = "departmentId", target = "department")
    Survey toEntity(SurveyDTO surveyDTO);

    default Survey fromId(Long id) {
        if (id == null) {
            return null;
        }
        Survey survey = new Survey();
        survey.setId(id);
        return survey;
    }
}

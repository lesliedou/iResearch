package com.research.service.mapper;

import com.research.domain.*;
import com.research.service.dto.SurveyQuestionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SurveyQuestion and its DTO SurveyQuestionDTO.
 */
@Mapper(componentModel = "spring", uses = {OnsiteMapper.class, DepartmentMapper.class})
public interface SurveyQuestionMapper extends EntityMapper<SurveyQuestionDTO, SurveyQuestion> {

    @Mapping(source = "survey.id", target = "surveyId")
    @Mapping(source = "onsite.id", target = "onsiteId")
    @Mapping(source = "department.id", target = "departmentId")
    SurveyQuestionDTO toDto(SurveyQuestion surveyQuestion);

    @Mapping(source = "surveyId", target = "survey")
    @Mapping(source = "onsiteId", target = "onsite")
    @Mapping(source = "departmentId", target = "department")
    SurveyQuestion toEntity(SurveyQuestionDTO surveyQuestionDTO);

    default SurveyQuestion fromId(Long id) {
        if (id == null) {
            return null;
        }
        SurveyQuestion surveyQuestion = new SurveyQuestion();
        surveyQuestion.setId(id);
        return surveyQuestion;
    }
}

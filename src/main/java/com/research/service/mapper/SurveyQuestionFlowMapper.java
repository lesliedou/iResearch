package com.research.service.mapper;

import com.research.domain.*;
import com.research.service.dto.SurveyQuestionFlowDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SurveyQuestionFlow and its DTO SurveyQuestionFlowDTO.
 */
@Mapper(componentModel = "spring", uses = {SurveyQuestionMapper.class})
public interface SurveyQuestionFlowMapper extends EntityMapper<SurveyQuestionFlowDTO, SurveyQuestionFlow> {

    @Mapping(source = "surveyQuestion.id", target = "surveyQuestionId")
    SurveyQuestionFlowDTO toDto(SurveyQuestionFlow surveyQuestionFlow);

    @Mapping(source = "surveyQuestionId", target = "surveyQuestion")
    SurveyQuestionFlow toEntity(SurveyQuestionFlowDTO surveyQuestionFlowDTO);

    default SurveyQuestionFlow fromId(Long id) {
        if (id == null) {
            return null;
        }
        SurveyQuestionFlow surveyQuestionFlow = new SurveyQuestionFlow();
        surveyQuestionFlow.setId(id);
        return surveyQuestionFlow;
    }
}

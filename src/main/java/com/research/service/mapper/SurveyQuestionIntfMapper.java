package com.research.service.mapper;

import com.research.domain.*;
import com.research.service.dto.SurveyQuestionIntfDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SurveyQuestionIntf and its DTO SurveyQuestionIntfDTO.
 */
@Mapper(componentModel = "spring", uses = {SurveyQuestionMapper.class})
public interface SurveyQuestionIntfMapper extends EntityMapper<SurveyQuestionIntfDTO, SurveyQuestionIntf> {

    @Mapping(source = "surveyQuestion.id", target = "surveyQuestionId")
    SurveyQuestionIntfDTO toDto(SurveyQuestionIntf surveyQuestionIntf);

    @Mapping(source = "surveyQuestionId", target = "surveyQuestion")
    SurveyQuestionIntf toEntity(SurveyQuestionIntfDTO surveyQuestionIntfDTO);

    default SurveyQuestionIntf fromId(Long id) {
        if (id == null) {
            return null;
        }
        SurveyQuestionIntf surveyQuestionIntf = new SurveyQuestionIntf();
        surveyQuestionIntf.setId(id);
        return surveyQuestionIntf;
    }
}

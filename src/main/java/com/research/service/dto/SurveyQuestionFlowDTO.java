package com.research.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the SurveyQuestionFlow entity.
 */
public class SurveyQuestionFlowDTO implements Serializable {

    private Long id;

    private String confirm;

    private String remarks;

    private Long surveyQuestionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getSurveyQuestionId() {
        return surveyQuestionId;
    }

    public void setSurveyQuestionId(Long surveyQuestionId) {
        this.surveyQuestionId = surveyQuestionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SurveyQuestionFlowDTO surveyQuestionFlowDTO = (SurveyQuestionFlowDTO) o;
        if(surveyQuestionFlowDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), surveyQuestionFlowDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SurveyQuestionFlowDTO{" +
            "id=" + getId() +
            ", confirm='" + getConfirm() + "'" +
            ", remarks='" + getRemarks() + "'" +
            "}";
    }
}

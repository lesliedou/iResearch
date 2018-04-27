package com.research.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Survey entity.
 */
public class SurveyDTO implements Serializable {

    private Long id;

    private String surveyType;

    private String targetTypeReadme;

    private String targetType;

    private String targetContactInfo;

    private String targetContactPerson;

    private String targetName;

    private String targetId;

    private String deptContactInfo;

    private String deptContactPerson;

    private String surveyDate;

    private Long departmentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurveyType() {
        return surveyType;
    }

    public void setSurveyType(String surveyType) {
        this.surveyType = surveyType;
    }

    public String getTargetTypeReadme() {
        return targetTypeReadme;
    }

    public void setTargetTypeReadme(String targetTypeReadme) {
        this.targetTypeReadme = targetTypeReadme;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getTargetContactInfo() {
        return targetContactInfo;
    }

    public void setTargetContactInfo(String targetContactInfo) {
        this.targetContactInfo = targetContactInfo;
    }

    public String getTargetContactPerson() {
        return targetContactPerson;
    }

    public void setTargetContactPerson(String targetContactPerson) {
        this.targetContactPerson = targetContactPerson;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getDeptContactInfo() {
        return deptContactInfo;
    }

    public void setDeptContactInfo(String deptContactInfo) {
        this.deptContactInfo = deptContactInfo;
    }

    public String getDeptContactPerson() {
        return deptContactPerson;
    }

    public void setDeptContactPerson(String deptContactPerson) {
        this.deptContactPerson = deptContactPerson;
    }

    public String getSurveyDate() {
        return surveyDate;
    }

    public void setSurveyDate(String surveyDate) {
        this.surveyDate = surveyDate;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SurveyDTO surveyDTO = (SurveyDTO) o;
        if(surveyDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), surveyDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SurveyDTO{" +
            "id=" + getId() +
            ", surveyType='" + getSurveyType() + "'" +
            ", targetTypeReadme='" + getTargetTypeReadme() + "'" +
            ", targetType='" + getTargetType() + "'" +
            ", targetContactInfo='" + getTargetContactInfo() + "'" +
            ", targetContactPerson='" + getTargetContactPerson() + "'" +
            ", targetName='" + getTargetName() + "'" +
            ", targetId='" + getTargetId() + "'" +
            ", deptContactInfo='" + getDeptContactInfo() + "'" +
            ", deptContactPerson='" + getDeptContactPerson() + "'" +
            ", surveyDate='" + getSurveyDate() + "'" +
            "}";
    }
}

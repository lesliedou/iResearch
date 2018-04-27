package com.research.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Survey.
 */
@Entity
@Table(name = "ddy_survey")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Survey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "survey_id")
    private Long id;

    @Column(name = "survey_type")
    private String surveyType;

    @Column(name = "target_type_readme")
    private String targetTypeReadme;

    @Column(name = "target_type")
    private String targetType;

    @Column(name = "target_contact_info")
    private String targetContactInfo;

    @Column(name = "target_contact_person")
    private String targetContactPerson;

    @Column(name = "target_name")
    private String targetName;

    @Column(name = "target_id")
    private String targetId;

    @Column(name = "dept_contact_info")
    private String deptContactInfo;

    @Column(name = "dept_contact_person")
    private String deptContactPerson;

    @Column(name = "survey_date")
    private String surveyDate;

    @OneToOne
    @JoinColumn(unique = true)
    private Department department;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurveyType() {
        return surveyType;
    }

    public Survey surveyType(String surveyType) {
        this.surveyType = surveyType;
        return this;
    }

    public void setSurveyType(String surveyType) {
        this.surveyType = surveyType;
    }

    public String getTargetTypeReadme() {
        return targetTypeReadme;
    }

    public Survey targetTypeReadme(String targetTypeReadme) {
        this.targetTypeReadme = targetTypeReadme;
        return this;
    }

    public void setTargetTypeReadme(String targetTypeReadme) {
        this.targetTypeReadme = targetTypeReadme;
    }

    public String getTargetType() {
        return targetType;
    }

    public Survey targetType(String targetType) {
        this.targetType = targetType;
        return this;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getTargetContactInfo() {
        return targetContactInfo;
    }

    public Survey targetContactInfo(String targetContactInfo) {
        this.targetContactInfo = targetContactInfo;
        return this;
    }

    public void setTargetContactInfo(String targetContactInfo) {
        this.targetContactInfo = targetContactInfo;
    }

    public String getTargetContactPerson() {
        return targetContactPerson;
    }

    public Survey targetContactPerson(String targetContactPerson) {
        this.targetContactPerson = targetContactPerson;
        return this;
    }

    public void setTargetContactPerson(String targetContactPerson) {
        this.targetContactPerson = targetContactPerson;
    }

    public String getTargetName() {
        return targetName;
    }

    public Survey targetName(String targetName) {
        this.targetName = targetName;
        return this;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getTargetId() {
        return targetId;
    }

    public Survey targetId(String targetId) {
        this.targetId = targetId;
        return this;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getDeptContactInfo() {
        return deptContactInfo;
    }

    public Survey deptContactInfo(String deptContactInfo) {
        this.deptContactInfo = deptContactInfo;
        return this;
    }

    public void setDeptContactInfo(String deptContactInfo) {
        this.deptContactInfo = deptContactInfo;
    }

    public String getDeptContactPerson() {
        return deptContactPerson;
    }

    public Survey deptContactPerson(String deptContactPerson) {
        this.deptContactPerson = deptContactPerson;
        return this;
    }

    public void setDeptContactPerson(String deptContactPerson) {
        this.deptContactPerson = deptContactPerson;
    }

    public String getSurveyDate() {
        return surveyDate;
    }

    public Survey surveyDate(String surveyDate) {
        this.surveyDate = surveyDate;
        return this;
    }

    public void setSurveyDate(String surveyDate) {
        this.surveyDate = surveyDate;
    }

    public Department getDepartment() {
        return department;
    }

    public Survey department(Department department) {
        this.department = department;
        return this;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Survey survey = (Survey) o;
        if (survey.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), survey.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Survey{" +
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

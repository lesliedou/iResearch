package com.research.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A SurveyQuestionFlow.
 */
@Entity
@Table(name = "ddy_survey_question_flow")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SurveyQuestionFlow implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flow_id")
    private Long id;

    @Column(name = "confirm")
    private String confirm;

    @Column(name = "remarks")
    private String remarks;

    @OneToOne
    @JoinColumn(unique = true)
    private SurveyQuestion surveyQuestion;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConfirm() {
        return confirm;
    }

    public SurveyQuestionFlow confirm(String confirm) {
        this.confirm = confirm;
        return this;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getRemarks() {
        return remarks;
    }

    public SurveyQuestionFlow remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public SurveyQuestion getSurveyQuestion() {
        return surveyQuestion;
    }

    public SurveyQuestionFlow surveyQuestion(SurveyQuestion surveyQuestion) {
        this.surveyQuestion = surveyQuestion;
        return this;
    }

    public void setSurveyQuestion(SurveyQuestion surveyQuestion) {
        this.surveyQuestion = surveyQuestion;
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
        SurveyQuestionFlow surveyQuestionFlow = (SurveyQuestionFlow) o;
        if (surveyQuestionFlow.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), surveyQuestionFlow.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SurveyQuestionFlow{" +
            "id=" + getId() +
            ", confirm='" + getConfirm() + "'" +
            ", remarks='" + getRemarks() + "'" +
            "}";
    }
}

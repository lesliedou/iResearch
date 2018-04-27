package com.research.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A SurveyQuestionIntf.
 */
@Entity
@Table(name = "ddy_survey_question_intf")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SurveyQuestionIntf implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_id")
    private String taskID;

    @Column(name = "status_type")
    private String statusType;

    @Column(name = "return_time")
    private String returnTime;

    @Column(name = "return_note")
    private String returnNote;

    @Column(name = "create_time")
    private String createTime;

    @Column(name = "all_end_time")
    private String allEndTime;

    @Column(name = "create_note")
    private String createNote;

    @Column(name = "end_time")
    private String endTime;

    @Column(name = "last_solving_time")
    private String lastSolvingTime;

    @Column(name = "solving_note")
    private String solvingNote;

    @Column(name = "end_note")
    private String endNote;

    @Column(name = "banli_result")
    private String banliResult;

    @Column(name = "execute_dept_name")
    private String executeDeptName;

    @Column(name = "assistant_dept_name")
    private String assistantDeptName;

    @Column(name = "feed_back_note")
    private String feedBackNote;

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

    public String getTaskID() {
        return taskID;
    }

    public SurveyQuestionIntf taskID(String taskID) {
        this.taskID = taskID;
        return this;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getStatusType() {
        return statusType;
    }

    public SurveyQuestionIntf statusType(String statusType) {
        this.statusType = statusType;
        return this;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public SurveyQuestionIntf returnTime(String returnTime) {
        this.returnTime = returnTime;
        return this;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public String getReturnNote() {
        return returnNote;
    }

    public SurveyQuestionIntf returnNote(String returnNote) {
        this.returnNote = returnNote;
        return this;
    }

    public void setReturnNote(String returnNote) {
        this.returnNote = returnNote;
    }

    public String getCreateTime() {
        return createTime;
    }

    public SurveyQuestionIntf createTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getAllEndTime() {
        return allEndTime;
    }

    public SurveyQuestionIntf allEndTime(String allEndTime) {
        this.allEndTime = allEndTime;
        return this;
    }

    public void setAllEndTime(String allEndTime) {
        this.allEndTime = allEndTime;
    }

    public String getCreateNote() {
        return createNote;
    }

    public SurveyQuestionIntf createNote(String createNote) {
        this.createNote = createNote;
        return this;
    }

    public void setCreateNote(String createNote) {
        this.createNote = createNote;
    }

    public String getEndTime() {
        return endTime;
    }

    public SurveyQuestionIntf endTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLastSolvingTime() {
        return lastSolvingTime;
    }

    public SurveyQuestionIntf lastSolvingTime(String lastSolvingTime) {
        this.lastSolvingTime = lastSolvingTime;
        return this;
    }

    public void setLastSolvingTime(String lastSolvingTime) {
        this.lastSolvingTime = lastSolvingTime;
    }

    public String getSolvingNote() {
        return solvingNote;
    }

    public SurveyQuestionIntf solvingNote(String solvingNote) {
        this.solvingNote = solvingNote;
        return this;
    }

    public void setSolvingNote(String solvingNote) {
        this.solvingNote = solvingNote;
    }

    public String getEndNote() {
        return endNote;
    }

    public SurveyQuestionIntf endNote(String endNote) {
        this.endNote = endNote;
        return this;
    }

    public void setEndNote(String endNote) {
        this.endNote = endNote;
    }

    public String getBanliResult() {
        return banliResult;
    }

    public SurveyQuestionIntf banliResult(String banliResult) {
        this.banliResult = banliResult;
        return this;
    }

    public void setBanliResult(String banliResult) {
        this.banliResult = banliResult;
    }

    public String getExecuteDeptName() {
        return executeDeptName;
    }

    public SurveyQuestionIntf executeDeptName(String executeDeptName) {
        this.executeDeptName = executeDeptName;
        return this;
    }

    public void setExecuteDeptName(String executeDeptName) {
        this.executeDeptName = executeDeptName;
    }

    public String getAssistantDeptName() {
        return assistantDeptName;
    }

    public SurveyQuestionIntf assistantDeptName(String assistantDeptName) {
        this.assistantDeptName = assistantDeptName;
        return this;
    }

    public void setAssistantDeptName(String assistantDeptName) {
        this.assistantDeptName = assistantDeptName;
    }

    public String getFeedBackNote() {
        return feedBackNote;
    }

    public SurveyQuestionIntf feedBackNote(String feedBackNote) {
        this.feedBackNote = feedBackNote;
        return this;
    }

    public void setFeedBackNote(String feedBackNote) {
        this.feedBackNote = feedBackNote;
    }

    public SurveyQuestion getSurveyQuestion() {
        return surveyQuestion;
    }

    public SurveyQuestionIntf surveyQuestion(SurveyQuestion surveyQuestion) {
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
        SurveyQuestionIntf surveyQuestionIntf = (SurveyQuestionIntf) o;
        if (surveyQuestionIntf.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), surveyQuestionIntf.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SurveyQuestionIntf{" +
            "id=" + getId() +
            ", taskID='" + getTaskID() + "'" +
            ", statusType='" + getStatusType() + "'" +
            ", returnTime='" + getReturnTime() + "'" +
            ", returnNote='" + getReturnNote() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", allEndTime='" + getAllEndTime() + "'" +
            ", createNote='" + getCreateNote() + "'" +
            ", endTime='" + getEndTime() + "'" +
            ", lastSolvingTime='" + getLastSolvingTime() + "'" +
            ", solvingNote='" + getSolvingNote() + "'" +
            ", endNote='" + getEndNote() + "'" +
            ", banliResult='" + getBanliResult() + "'" +
            ", executeDeptName='" + getExecuteDeptName() + "'" +
            ", assistantDeptName='" + getAssistantDeptName() + "'" +
            ", feedBackNote='" + getFeedBackNote() + "'" +
            "}";
    }
}

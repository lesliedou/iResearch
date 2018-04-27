package com.research.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the SurveyQuestionIntf entity.
 */
public class SurveyQuestionIntfDTO implements Serializable {

    private Long id;

    private String taskID;

    private String statusType;

    private String returnTime;

    private String returnNote;

    private String createTime;

    private String allEndTime;

    private String createNote;

    private String endTime;

    private String lastSolvingTime;

    private String solvingNote;

    private String endNote;

    private String banliResult;

    private String executeDeptName;

    private String assistantDeptName;

    private String feedBackNote;

    private Long surveyQuestionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public String getReturnNote() {
        return returnNote;
    }

    public void setReturnNote(String returnNote) {
        this.returnNote = returnNote;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getAllEndTime() {
        return allEndTime;
    }

    public void setAllEndTime(String allEndTime) {
        this.allEndTime = allEndTime;
    }

    public String getCreateNote() {
        return createNote;
    }

    public void setCreateNote(String createNote) {
        this.createNote = createNote;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLastSolvingTime() {
        return lastSolvingTime;
    }

    public void setLastSolvingTime(String lastSolvingTime) {
        this.lastSolvingTime = lastSolvingTime;
    }

    public String getSolvingNote() {
        return solvingNote;
    }

    public void setSolvingNote(String solvingNote) {
        this.solvingNote = solvingNote;
    }

    public String getEndNote() {
        return endNote;
    }

    public void setEndNote(String endNote) {
        this.endNote = endNote;
    }

    public String getBanliResult() {
        return banliResult;
    }

    public void setBanliResult(String banliResult) {
        this.banliResult = banliResult;
    }

    public String getExecuteDeptName() {
        return executeDeptName;
    }

    public void setExecuteDeptName(String executeDeptName) {
        this.executeDeptName = executeDeptName;
    }

    public String getAssistantDeptName() {
        return assistantDeptName;
    }

    public void setAssistantDeptName(String assistantDeptName) {
        this.assistantDeptName = assistantDeptName;
    }

    public String getFeedBackNote() {
        return feedBackNote;
    }

    public void setFeedBackNote(String feedBackNote) {
        this.feedBackNote = feedBackNote;
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

        SurveyQuestionIntfDTO surveyQuestionIntfDTO = (SurveyQuestionIntfDTO) o;
        if(surveyQuestionIntfDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), surveyQuestionIntfDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SurveyQuestionIntfDTO{" +
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

package com.research.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the SurveyQuestion entity.
 */
public class SurveyQuestionDTO implements Serializable {

    private Long id;

    private String targetId;

    private String targetName;

    private String surveyCode;

    private String subSeq;

    private String report;

    private String reportTime;

    private String questionSummary;

    private String questionType;

    private String questionInfo;

    private String deptPlan;

    private String deptPlanType;

    private String deptPlanExtra;

    private String questionStatus;

    private String masterDeptId;

    private String auxDeptId;

    private String officePlan;

    private String officePlanJson;

    private String officeSubmitUuid;

    private String officeSubmitTime;

    private String leaderConfirm;

    private String leaderConfirmTime;

    private String leaderRemarks;

    private String leaderSubmitUuid;

    private String leaderSubmitTime;

    private String rstProcedure;

    private String rstOutcome;

    private String rstDate;

    private String rstTarget;

    private String flowStatus;

    private String inputDeptId;

    private String inputUser;

    private String inputPhone;

    private String inputTime;

    private Long surveyId;

    private Long onsiteId;

    private Long departmentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getSurveyCode() {
        return surveyCode;
    }

    public void setSurveyCode(String surveyCode) {
        this.surveyCode = surveyCode;
    }

    public String getSubSeq() {
        return subSeq;
    }

    public void setSubSeq(String subSeq) {
        this.subSeq = subSeq;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public String getQuestionSummary() {
        return questionSummary;
    }

    public void setQuestionSummary(String questionSummary) {
        this.questionSummary = questionSummary;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getQuestionInfo() {
        return questionInfo;
    }

    public void setQuestionInfo(String questionInfo) {
        this.questionInfo = questionInfo;
    }

    public String getDeptPlan() {
        return deptPlan;
    }

    public void setDeptPlan(String deptPlan) {
        this.deptPlan = deptPlan;
    }

    public String getDeptPlanType() {
        return deptPlanType;
    }

    public void setDeptPlanType(String deptPlanType) {
        this.deptPlanType = deptPlanType;
    }

    public String getDeptPlanExtra() {
        return deptPlanExtra;
    }

    public void setDeptPlanExtra(String deptPlanExtra) {
        this.deptPlanExtra = deptPlanExtra;
    }

    public String getQuestionStatus() {
        return questionStatus;
    }

    public void setQuestionStatus(String questionStatus) {
        this.questionStatus = questionStatus;
    }

    public String getMasterDeptId() {
        return masterDeptId;
    }

    public void setMasterDeptId(String masterDeptId) {
        this.masterDeptId = masterDeptId;
    }

    public String getAuxDeptId() {
        return auxDeptId;
    }

    public void setAuxDeptId(String auxDeptId) {
        this.auxDeptId = auxDeptId;
    }

    public String getOfficePlan() {
        return officePlan;
    }

    public void setOfficePlan(String officePlan) {
        this.officePlan = officePlan;
    }

    public String getOfficePlanJson() {
        return officePlanJson;
    }

    public void setOfficePlanJson(String officePlanJson) {
        this.officePlanJson = officePlanJson;
    }

    public String getOfficeSubmitUuid() {
        return officeSubmitUuid;
    }

    public void setOfficeSubmitUuid(String officeSubmitUuid) {
        this.officeSubmitUuid = officeSubmitUuid;
    }

    public String getOfficeSubmitTime() {
        return officeSubmitTime;
    }

    public void setOfficeSubmitTime(String officeSubmitTime) {
        this.officeSubmitTime = officeSubmitTime;
    }

    public String getLeaderConfirm() {
        return leaderConfirm;
    }

    public void setLeaderConfirm(String leaderConfirm) {
        this.leaderConfirm = leaderConfirm;
    }

    public String getLeaderConfirmTime() {
        return leaderConfirmTime;
    }

    public void setLeaderConfirmTime(String leaderConfirmTime) {
        this.leaderConfirmTime = leaderConfirmTime;
    }

    public String getLeaderRemarks() {
        return leaderRemarks;
    }

    public void setLeaderRemarks(String leaderRemarks) {
        this.leaderRemarks = leaderRemarks;
    }

    public String getLeaderSubmitUuid() {
        return leaderSubmitUuid;
    }

    public void setLeaderSubmitUuid(String leaderSubmitUuid) {
        this.leaderSubmitUuid = leaderSubmitUuid;
    }

    public String getLeaderSubmitTime() {
        return leaderSubmitTime;
    }

    public void setLeaderSubmitTime(String leaderSubmitTime) {
        this.leaderSubmitTime = leaderSubmitTime;
    }

    public String getRstProcedure() {
        return rstProcedure;
    }

    public void setRstProcedure(String rstProcedure) {
        this.rstProcedure = rstProcedure;
    }

    public String getRstOutcome() {
        return rstOutcome;
    }

    public void setRstOutcome(String rstOutcome) {
        this.rstOutcome = rstOutcome;
    }

    public String getRstDate() {
        return rstDate;
    }

    public void setRstDate(String rstDate) {
        this.rstDate = rstDate;
    }

    public String getRstTarget() {
        return rstTarget;
    }

    public void setRstTarget(String rstTarget) {
        this.rstTarget = rstTarget;
    }

    public String getFlowStatus() {
        return flowStatus;
    }

    public void setFlowStatus(String flowStatus) {
        this.flowStatus = flowStatus;
    }

    public String getInputDeptId() {
        return inputDeptId;
    }

    public void setInputDeptId(String inputDeptId) {
        this.inputDeptId = inputDeptId;
    }

    public String getInputUser() {
        return inputUser;
    }

    public void setInputUser(String inputUser) {
        this.inputUser = inputUser;
    }

    public String getInputPhone() {
        return inputPhone;
    }

    public void setInputPhone(String inputPhone) {
        this.inputPhone = inputPhone;
    }

    public String getInputTime() {
        return inputTime;
    }

    public void setInputTime(String inputTime) {
        this.inputTime = inputTime;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyQuestionId) {
        this.surveyId = surveyQuestionId;
    }

    public Long getOnsiteId() {
        return onsiteId;
    }

    public void setOnsiteId(Long onsiteId) {
        this.onsiteId = onsiteId;
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

        SurveyQuestionDTO surveyQuestionDTO = (SurveyQuestionDTO) o;
        if(surveyQuestionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), surveyQuestionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SurveyQuestionDTO{" +
            "id=" + getId() +
            ", targetId='" + getTargetId() + "'" +
            ", targetName='" + getTargetName() + "'" +
            ", surveyCode='" + getSurveyCode() + "'" +
            ", subSeq='" + getSubSeq() + "'" +
            ", report='" + getReport() + "'" +
            ", reportTime='" + getReportTime() + "'" +
            ", questionSummary='" + getQuestionSummary() + "'" +
            ", questionType='" + getQuestionType() + "'" +
            ", questionInfo='" + getQuestionInfo() + "'" +
            ", deptPlan='" + getDeptPlan() + "'" +
            ", deptPlanType='" + getDeptPlanType() + "'" +
            ", deptPlanExtra='" + getDeptPlanExtra() + "'" +
            ", questionStatus='" + getQuestionStatus() + "'" +
            ", masterDeptId='" + getMasterDeptId() + "'" +
            ", auxDeptId='" + getAuxDeptId() + "'" +
            ", officePlan='" + getOfficePlan() + "'" +
            ", officePlanJson='" + getOfficePlanJson() + "'" +
            ", officeSubmitUuid='" + getOfficeSubmitUuid() + "'" +
            ", officeSubmitTime='" + getOfficeSubmitTime() + "'" +
            ", leaderConfirm='" + getLeaderConfirm() + "'" +
            ", leaderConfirmTime='" + getLeaderConfirmTime() + "'" +
            ", leaderRemarks='" + getLeaderRemarks() + "'" +
            ", leaderSubmitUuid='" + getLeaderSubmitUuid() + "'" +
            ", leaderSubmitTime='" + getLeaderSubmitTime() + "'" +
            ", rstProcedure='" + getRstProcedure() + "'" +
            ", rstOutcome='" + getRstOutcome() + "'" +
            ", rstDate='" + getRstDate() + "'" +
            ", rstTarget='" + getRstTarget() + "'" +
            ", flowStatus='" + getFlowStatus() + "'" +
            ", inputDeptId='" + getInputDeptId() + "'" +
            ", inputUser='" + getInputUser() + "'" +
            ", inputPhone='" + getInputPhone() + "'" +
            ", inputTime='" + getInputTime() + "'" +
            "}";
    }
}

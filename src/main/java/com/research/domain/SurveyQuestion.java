package com.research.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A SurveyQuestion.
 */
@Entity
@Table(name = "ddy_survey_question")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SurveyQuestion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    @Column(name = "onsite_id")
    private String onsiteId;

    @Column(name = "survey_id")
    private String surveyId;

    @Column(name = "target_id")
    private String targetId;

    @Column(name = "target_name")
    private String targetName;

    @Column(name = "survey_code")
    private String surveyCode;

    @Column(name = "sub_seq")
    private String subSeq;

    @Column(name = "report")
    private String report;

    @Column(name = "report_time")
    private String reportTime;

    @Column(name = "question_summary")
    private String questionSummary;

    @Column(name = "question_type")
    private String questionType;

    @Column(name = "question_info")
    private String questionInfo;

    @Column(name = "dept_plan")
    private String deptPlan;

    @Column(name = "dept_plan_type")
    private String deptPlanType;

    @Column(name = "dept_plan_extra")
    private String deptPlanExtra;

    @Column(name = "question_status")
    private String questionStatus;

    @Column(name = "master_dept_id")
    private String masterDeptId;

    @Column(name = "aux_dept_id")
    private String auxDeptId;

    @Column(name = "office_plan")
    private String officePlan;

    @Column(name = "office_plan_json")
    private String officePlanJson;

    @Column(name = "office_submit_uuid")
    private String officeSubmitUuid;

    @Column(name = "office_submit_time")
    private String officeSubmitTime;

    @Column(name = "leader_confirm")
    private String leaderConfirm;

    @Column(name = "leader_confirm_time")
    private String leaderConfirmTime;

    @Column(name = "leader_remarks")
    private String leaderRemarks;

    @Column(name = "leader_submit_uuid")
    private String leaderSubmitUuid;

    @Column(name = "leader_submit_time")
    private String leaderSubmitTime;

    @Column(name = "rst_procedure")
    private String rstProcedure;

    @Column(name = "rst_outcome")
    private String rstOutcome;

    @Column(name = "rst_date")
    private String rstDate;

    @Column(name = "rst_target")
    private String rstTarget;

    @Column(name = "flow_status")
    private String flowStatus;

    @Column(name = "input_dept_id")
    private String inputDeptId;

    @Column(name = "input_user")
    private String inputUser;

    @Column(name = "input_phone")
    private String inputPhone;

    @Column(name = "input_time")
    private String inputTime;

    @OneToOne
    @JoinColumn(unique = true)
    private SurveyQuestion survey;

    @OneToOne
    @JoinColumn(unique = true)
    private Onsite onsite;

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

    public String getOnsiteId() {
        return onsiteId;
    }

    public SurveyQuestion onsiteId(String onsiteId) {
        this.onsiteId = onsiteId;
        return this;
    }

    public void setOnsiteId(String onsiteId) {
        this.onsiteId = onsiteId;
    }

    public String getSurveyId() {
        return surveyId;
    }

    public SurveyQuestion surveyId(String surveyId) {
        this.surveyId = surveyId;
        return this;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    public String getTargetId() {
        return targetId;
    }

    public SurveyQuestion targetId(String targetId) {
        this.targetId = targetId;
        return this;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getTargetName() {
        return targetName;
    }

    public SurveyQuestion targetName(String targetName) {
        this.targetName = targetName;
        return this;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getSurveyCode() {
        return surveyCode;
    }

    public SurveyQuestion surveyCode(String surveyCode) {
        this.surveyCode = surveyCode;
        return this;
    }

    public void setSurveyCode(String surveyCode) {
        this.surveyCode = surveyCode;
    }

    public String getSubSeq() {
        return subSeq;
    }

    public SurveyQuestion subSeq(String subSeq) {
        this.subSeq = subSeq;
        return this;
    }

    public void setSubSeq(String subSeq) {
        this.subSeq = subSeq;
    }

    public String getReport() {
        return report;
    }

    public SurveyQuestion report(String report) {
        this.report = report;
        return this;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getReportTime() {
        return reportTime;
    }

    public SurveyQuestion reportTime(String reportTime) {
        this.reportTime = reportTime;
        return this;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public String getQuestionSummary() {
        return questionSummary;
    }

    public SurveyQuestion questionSummary(String questionSummary) {
        this.questionSummary = questionSummary;
        return this;
    }

    public void setQuestionSummary(String questionSummary) {
        this.questionSummary = questionSummary;
    }

    public String getQuestionType() {
        return questionType;
    }

    public SurveyQuestion questionType(String questionType) {
        this.questionType = questionType;
        return this;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getQuestionInfo() {
        return questionInfo;
    }

    public SurveyQuestion questionInfo(String questionInfo) {
        this.questionInfo = questionInfo;
        return this;
    }

    public void setQuestionInfo(String questionInfo) {
        this.questionInfo = questionInfo;
    }

    public String getDeptPlan() {
        return deptPlan;
    }

    public SurveyQuestion deptPlan(String deptPlan) {
        this.deptPlan = deptPlan;
        return this;
    }

    public void setDeptPlan(String deptPlan) {
        this.deptPlan = deptPlan;
    }

    public String getDeptPlanType() {
        return deptPlanType;
    }

    public SurveyQuestion deptPlanType(String deptPlanType) {
        this.deptPlanType = deptPlanType;
        return this;
    }

    public void setDeptPlanType(String deptPlanType) {
        this.deptPlanType = deptPlanType;
    }

    public String getDeptPlanExtra() {
        return deptPlanExtra;
    }

    public SurveyQuestion deptPlanExtra(String deptPlanExtra) {
        this.deptPlanExtra = deptPlanExtra;
        return this;
    }

    public void setDeptPlanExtra(String deptPlanExtra) {
        this.deptPlanExtra = deptPlanExtra;
    }

    public String getQuestionStatus() {
        return questionStatus;
    }

    public SurveyQuestion questionStatus(String questionStatus) {
        this.questionStatus = questionStatus;
        return this;
    }

    public void setQuestionStatus(String questionStatus) {
        this.questionStatus = questionStatus;
    }

    public String getMasterDeptId() {
        return masterDeptId;
    }

    public SurveyQuestion masterDeptId(String masterDeptId) {
        this.masterDeptId = masterDeptId;
        return this;
    }

    public void setMasterDeptId(String masterDeptId) {
        this.masterDeptId = masterDeptId;
    }

    public String getAuxDeptId() {
        return auxDeptId;
    }

    public SurveyQuestion auxDeptId(String auxDeptId) {
        this.auxDeptId = auxDeptId;
        return this;
    }

    public void setAuxDeptId(String auxDeptId) {
        this.auxDeptId = auxDeptId;
    }

    public String getOfficePlan() {
        return officePlan;
    }

    public SurveyQuestion officePlan(String officePlan) {
        this.officePlan = officePlan;
        return this;
    }

    public void setOfficePlan(String officePlan) {
        this.officePlan = officePlan;
    }

    public String getOfficePlanJson() {
        return officePlanJson;
    }

    public SurveyQuestion officePlanJson(String officePlanJson) {
        this.officePlanJson = officePlanJson;
        return this;
    }

    public void setOfficePlanJson(String officePlanJson) {
        this.officePlanJson = officePlanJson;
    }

    public String getOfficeSubmitUuid() {
        return officeSubmitUuid;
    }

    public SurveyQuestion officeSubmitUuid(String officeSubmitUuid) {
        this.officeSubmitUuid = officeSubmitUuid;
        return this;
    }

    public void setOfficeSubmitUuid(String officeSubmitUuid) {
        this.officeSubmitUuid = officeSubmitUuid;
    }

    public String getOfficeSubmitTime() {
        return officeSubmitTime;
    }

    public SurveyQuestion officeSubmitTime(String officeSubmitTime) {
        this.officeSubmitTime = officeSubmitTime;
        return this;
    }

    public void setOfficeSubmitTime(String officeSubmitTime) {
        this.officeSubmitTime = officeSubmitTime;
    }

    public String getLeaderConfirm() {
        return leaderConfirm;
    }

    public SurveyQuestion leaderConfirm(String leaderConfirm) {
        this.leaderConfirm = leaderConfirm;
        return this;
    }

    public void setLeaderConfirm(String leaderConfirm) {
        this.leaderConfirm = leaderConfirm;
    }

    public String getLeaderConfirmTime() {
        return leaderConfirmTime;
    }

    public SurveyQuestion leaderConfirmTime(String leaderConfirmTime) {
        this.leaderConfirmTime = leaderConfirmTime;
        return this;
    }

    public void setLeaderConfirmTime(String leaderConfirmTime) {
        this.leaderConfirmTime = leaderConfirmTime;
    }

    public String getLeaderRemarks() {
        return leaderRemarks;
    }

    public SurveyQuestion leaderRemarks(String leaderRemarks) {
        this.leaderRemarks = leaderRemarks;
        return this;
    }

    public void setLeaderRemarks(String leaderRemarks) {
        this.leaderRemarks = leaderRemarks;
    }

    public String getLeaderSubmitUuid() {
        return leaderSubmitUuid;
    }

    public SurveyQuestion leaderSubmitUuid(String leaderSubmitUuid) {
        this.leaderSubmitUuid = leaderSubmitUuid;
        return this;
    }

    public void setLeaderSubmitUuid(String leaderSubmitUuid) {
        this.leaderSubmitUuid = leaderSubmitUuid;
    }

    public String getLeaderSubmitTime() {
        return leaderSubmitTime;
    }

    public SurveyQuestion leaderSubmitTime(String leaderSubmitTime) {
        this.leaderSubmitTime = leaderSubmitTime;
        return this;
    }

    public void setLeaderSubmitTime(String leaderSubmitTime) {
        this.leaderSubmitTime = leaderSubmitTime;
    }

    public String getRstProcedure() {
        return rstProcedure;
    }

    public SurveyQuestion rstProcedure(String rstProcedure) {
        this.rstProcedure = rstProcedure;
        return this;
    }

    public void setRstProcedure(String rstProcedure) {
        this.rstProcedure = rstProcedure;
    }

    public String getRstOutcome() {
        return rstOutcome;
    }

    public SurveyQuestion rstOutcome(String rstOutcome) {
        this.rstOutcome = rstOutcome;
        return this;
    }

    public void setRstOutcome(String rstOutcome) {
        this.rstOutcome = rstOutcome;
    }

    public String getRstDate() {
        return rstDate;
    }

    public SurveyQuestion rstDate(String rstDate) {
        this.rstDate = rstDate;
        return this;
    }

    public void setRstDate(String rstDate) {
        this.rstDate = rstDate;
    }

    public String getRstTarget() {
        return rstTarget;
    }

    public SurveyQuestion rstTarget(String rstTarget) {
        this.rstTarget = rstTarget;
        return this;
    }

    public void setRstTarget(String rstTarget) {
        this.rstTarget = rstTarget;
    }

    public String getFlowStatus() {
        return flowStatus;
    }

    public SurveyQuestion flowStatus(String flowStatus) {
        this.flowStatus = flowStatus;
        return this;
    }

    public void setFlowStatus(String flowStatus) {
        this.flowStatus = flowStatus;
    }

    public String getInputDeptId() {
        return inputDeptId;
    }

    public SurveyQuestion inputDeptId(String inputDeptId) {
        this.inputDeptId = inputDeptId;
        return this;
    }

    public void setInputDeptId(String inputDeptId) {
        this.inputDeptId = inputDeptId;
    }

    public String getInputUser() {
        return inputUser;
    }

    public SurveyQuestion inputUser(String inputUser) {
        this.inputUser = inputUser;
        return this;
    }

    public void setInputUser(String inputUser) {
        this.inputUser = inputUser;
    }

    public String getInputPhone() {
        return inputPhone;
    }

    public SurveyQuestion inputPhone(String inputPhone) {
        this.inputPhone = inputPhone;
        return this;
    }

    public void setInputPhone(String inputPhone) {
        this.inputPhone = inputPhone;
    }

    public String getInputTime() {
        return inputTime;
    }

    public SurveyQuestion inputTime(String inputTime) {
        this.inputTime = inputTime;
        return this;
    }

    public void setInputTime(String inputTime) {
        this.inputTime = inputTime;
    }

    public SurveyQuestion getSurvey() {
        return survey;
    }

    public SurveyQuestion survey(SurveyQuestion surveyQuestion) {
        this.survey = surveyQuestion;
        return this;
    }

    public void setSurvey(SurveyQuestion surveyQuestion) {
        this.survey = surveyQuestion;
    }

    public Onsite getOnsite() {
        return onsite;
    }

    public SurveyQuestion onsite(Onsite onsite) {
        this.onsite = onsite;
        return this;
    }

    public void setOnsite(Onsite onsite) {
        this.onsite = onsite;
    }

    public Department getDepartment() {
        return department;
    }

    public SurveyQuestion department(Department department) {
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
        SurveyQuestion surveyQuestion = (SurveyQuestion) o;
        if (surveyQuestion.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), surveyQuestion.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SurveyQuestion{" +
            "id=" + getId() +
            ", onsiteId='" + getOnsiteId() + "'" +
            ", surveyId='" + getSurveyId() + "'" +
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

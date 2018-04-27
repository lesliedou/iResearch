package com.research.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the MonthlyReport entity.
 */
public class MonthlyReportDTO implements Serializable {

    private Long id;

    private String monthName;

    private String report;

    private String reportTime;

    private String reportDate;

    private String count;

    private String surveyDeptCount;

    private String surveyCount;

    private String surveyTargetCountJson;

    private String surveyTargetCountTotal;

    private String surveyTypeCountJson;

    private String surveyTypeCountTotal;

    private String questionCcountJson;

    private String questionCountTotal;

    private String questionClosedCountJson;

    private String questionClosedCountTota;

    private String proposalCountJson;

    private String proposalCountTotal;

    private Long departmentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
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

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getSurveyDeptCount() {
        return surveyDeptCount;
    }

    public void setSurveyDeptCount(String surveyDeptCount) {
        this.surveyDeptCount = surveyDeptCount;
    }

    public String getSurveyCount() {
        return surveyCount;
    }

    public void setSurveyCount(String surveyCount) {
        this.surveyCount = surveyCount;
    }

    public String getSurveyTargetCountJson() {
        return surveyTargetCountJson;
    }

    public void setSurveyTargetCountJson(String surveyTargetCountJson) {
        this.surveyTargetCountJson = surveyTargetCountJson;
    }

    public String getSurveyTargetCountTotal() {
        return surveyTargetCountTotal;
    }

    public void setSurveyTargetCountTotal(String surveyTargetCountTotal) {
        this.surveyTargetCountTotal = surveyTargetCountTotal;
    }

    public String getSurveyTypeCountJson() {
        return surveyTypeCountJson;
    }

    public void setSurveyTypeCountJson(String surveyTypeCountJson) {
        this.surveyTypeCountJson = surveyTypeCountJson;
    }

    public String getSurveyTypeCountTotal() {
        return surveyTypeCountTotal;
    }

    public void setSurveyTypeCountTotal(String surveyTypeCountTotal) {
        this.surveyTypeCountTotal = surveyTypeCountTotal;
    }

    public String getQuestionCcountJson() {
        return questionCcountJson;
    }

    public void setQuestionCcountJson(String questionCcountJson) {
        this.questionCcountJson = questionCcountJson;
    }

    public String getQuestionCountTotal() {
        return questionCountTotal;
    }

    public void setQuestionCountTotal(String questionCountTotal) {
        this.questionCountTotal = questionCountTotal;
    }

    public String getQuestionClosedCountJson() {
        return questionClosedCountJson;
    }

    public void setQuestionClosedCountJson(String questionClosedCountJson) {
        this.questionClosedCountJson = questionClosedCountJson;
    }

    public String getQuestionClosedCountTota() {
        return questionClosedCountTota;
    }

    public void setQuestionClosedCountTota(String questionClosedCountTota) {
        this.questionClosedCountTota = questionClosedCountTota;
    }

    public String getProposalCountJson() {
        return proposalCountJson;
    }

    public void setProposalCountJson(String proposalCountJson) {
        this.proposalCountJson = proposalCountJson;
    }

    public String getProposalCountTotal() {
        return proposalCountTotal;
    }

    public void setProposalCountTotal(String proposalCountTotal) {
        this.proposalCountTotal = proposalCountTotal;
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

        MonthlyReportDTO monthlyReportDTO = (MonthlyReportDTO) o;
        if(monthlyReportDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), monthlyReportDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MonthlyReportDTO{" +
            "id=" + getId() +
            ", monthName='" + getMonthName() + "'" +
            ", report='" + getReport() + "'" +
            ", reportTime='" + getReportTime() + "'" +
            ", reportDate='" + getReportDate() + "'" +
            ", count='" + getCount() + "'" +
            ", surveyDeptCount='" + getSurveyDeptCount() + "'" +
            ", surveyCount='" + getSurveyCount() + "'" +
            ", surveyTargetCountJson='" + getSurveyTargetCountJson() + "'" +
            ", surveyTargetCountTotal='" + getSurveyTargetCountTotal() + "'" +
            ", surveyTypeCountJson='" + getSurveyTypeCountJson() + "'" +
            ", surveyTypeCountTotal='" + getSurveyTypeCountTotal() + "'" +
            ", questionCcountJson='" + getQuestionCcountJson() + "'" +
            ", questionCountTotal='" + getQuestionCountTotal() + "'" +
            ", questionClosedCountJson='" + getQuestionClosedCountJson() + "'" +
            ", questionClosedCountTota='" + getQuestionClosedCountTota() + "'" +
            ", proposalCountJson='" + getProposalCountJson() + "'" +
            ", proposalCountTotal='" + getProposalCountTotal() + "'" +
            "}";
    }
}

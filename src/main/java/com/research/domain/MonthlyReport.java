package com.research.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A MonthlyReport.
 */
@Entity
@Table(name = "ddy_monthly_report")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MonthlyReport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long id;

    @Column(name = "month_name")
    private String monthName;

    @Column(name = "report")
    private String report;

    @Column(name = "report_time")
    private String reportTime;

    @Column(name = "report_date")
    private String reportDate;

    @Column(name = "count")
    private String count;

    @Column(name = "survey_dept_count")
    private String surveyDeptCount;

    @Column(name = "survey_count")
    private String surveyCount;

    @Column(name = "survey_target_count_json")
    private String surveyTargetCountJson;

    @Column(name = "survey_target_count_total")
    private String surveyTargetCountTotal;

    @Column(name = "survey_type_count_json")
    private String surveyTypeCountJson;

    @Column(name = "survey_type_count_total")
    private String surveyTypeCountTotal;

    @Column(name = "question_ccount_json")
    private String questionCcountJson;

    @Column(name = "question_count_total")
    private String questionCountTotal;

    @Column(name = "question_closed_count_json")
    private String questionClosedCountJson;

    @Column(name = "question_closed_count_tota")
    private String questionClosedCountTota;

    @Column(name = "proposal_count_json")
    private String proposalCountJson;

    @Column(name = "proposal_count_total")
    private String proposalCountTotal;

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

    public String getMonthName() {
        return monthName;
    }

    public MonthlyReport monthName(String monthName) {
        this.monthName = monthName;
        return this;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public String getReport() {
        return report;
    }

    public MonthlyReport report(String report) {
        this.report = report;
        return this;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getReportTime() {
        return reportTime;
    }

    public MonthlyReport reportTime(String reportTime) {
        this.reportTime = reportTime;
        return this;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public String getReportDate() {
        return reportDate;
    }

    public MonthlyReport reportDate(String reportDate) {
        this.reportDate = reportDate;
        return this;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getCount() {
        return count;
    }

    public MonthlyReport count(String count) {
        this.count = count;
        return this;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getSurveyDeptCount() {
        return surveyDeptCount;
    }

    public MonthlyReport surveyDeptCount(String surveyDeptCount) {
        this.surveyDeptCount = surveyDeptCount;
        return this;
    }

    public void setSurveyDeptCount(String surveyDeptCount) {
        this.surveyDeptCount = surveyDeptCount;
    }

    public String getSurveyCount() {
        return surveyCount;
    }

    public MonthlyReport surveyCount(String surveyCount) {
        this.surveyCount = surveyCount;
        return this;
    }

    public void setSurveyCount(String surveyCount) {
        this.surveyCount = surveyCount;
    }

    public String getSurveyTargetCountJson() {
        return surveyTargetCountJson;
    }

    public MonthlyReport surveyTargetCountJson(String surveyTargetCountJson) {
        this.surveyTargetCountJson = surveyTargetCountJson;
        return this;
    }

    public void setSurveyTargetCountJson(String surveyTargetCountJson) {
        this.surveyTargetCountJson = surveyTargetCountJson;
    }

    public String getSurveyTargetCountTotal() {
        return surveyTargetCountTotal;
    }

    public MonthlyReport surveyTargetCountTotal(String surveyTargetCountTotal) {
        this.surveyTargetCountTotal = surveyTargetCountTotal;
        return this;
    }

    public void setSurveyTargetCountTotal(String surveyTargetCountTotal) {
        this.surveyTargetCountTotal = surveyTargetCountTotal;
    }

    public String getSurveyTypeCountJson() {
        return surveyTypeCountJson;
    }

    public MonthlyReport surveyTypeCountJson(String surveyTypeCountJson) {
        this.surveyTypeCountJson = surveyTypeCountJson;
        return this;
    }

    public void setSurveyTypeCountJson(String surveyTypeCountJson) {
        this.surveyTypeCountJson = surveyTypeCountJson;
    }

    public String getSurveyTypeCountTotal() {
        return surveyTypeCountTotal;
    }

    public MonthlyReport surveyTypeCountTotal(String surveyTypeCountTotal) {
        this.surveyTypeCountTotal = surveyTypeCountTotal;
        return this;
    }

    public void setSurveyTypeCountTotal(String surveyTypeCountTotal) {
        this.surveyTypeCountTotal = surveyTypeCountTotal;
    }

    public String getQuestionCcountJson() {
        return questionCcountJson;
    }

    public MonthlyReport questionCcountJson(String questionCcountJson) {
        this.questionCcountJson = questionCcountJson;
        return this;
    }

    public void setQuestionCcountJson(String questionCcountJson) {
        this.questionCcountJson = questionCcountJson;
    }

    public String getQuestionCountTotal() {
        return questionCountTotal;
    }

    public MonthlyReport questionCountTotal(String questionCountTotal) {
        this.questionCountTotal = questionCountTotal;
        return this;
    }

    public void setQuestionCountTotal(String questionCountTotal) {
        this.questionCountTotal = questionCountTotal;
    }

    public String getQuestionClosedCountJson() {
        return questionClosedCountJson;
    }

    public MonthlyReport questionClosedCountJson(String questionClosedCountJson) {
        this.questionClosedCountJson = questionClosedCountJson;
        return this;
    }

    public void setQuestionClosedCountJson(String questionClosedCountJson) {
        this.questionClosedCountJson = questionClosedCountJson;
    }

    public String getQuestionClosedCountTota() {
        return questionClosedCountTota;
    }

    public MonthlyReport questionClosedCountTota(String questionClosedCountTota) {
        this.questionClosedCountTota = questionClosedCountTota;
        return this;
    }

    public void setQuestionClosedCountTota(String questionClosedCountTota) {
        this.questionClosedCountTota = questionClosedCountTota;
    }

    public String getProposalCountJson() {
        return proposalCountJson;
    }

    public MonthlyReport proposalCountJson(String proposalCountJson) {
        this.proposalCountJson = proposalCountJson;
        return this;
    }

    public void setProposalCountJson(String proposalCountJson) {
        this.proposalCountJson = proposalCountJson;
    }

    public String getProposalCountTotal() {
        return proposalCountTotal;
    }

    public MonthlyReport proposalCountTotal(String proposalCountTotal) {
        this.proposalCountTotal = proposalCountTotal;
        return this;
    }

    public void setProposalCountTotal(String proposalCountTotal) {
        this.proposalCountTotal = proposalCountTotal;
    }

    public Department getDepartment() {
        return department;
    }

    public MonthlyReport department(Department department) {
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
        MonthlyReport monthlyReport = (MonthlyReport) o;
        if (monthlyReport.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), monthlyReport.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MonthlyReport{" +
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

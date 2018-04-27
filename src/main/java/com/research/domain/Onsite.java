package com.research.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Onsite.
 */
@Entity
@Table(name = "ddy_onsite")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Onsite implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "onsite_id")
    private Long id;

    @Column(name = "report")
    private String report;

    @Column(name = "onsite_time")
    private String onsiteTime;

    @Column(name = "accompany")
    private String accompany;

    @Column(name = "onsite_addr")
    private String onsiteAddr;

    @Column(name = "onsite_info")
    private String onsiteInfo;

    @Column(name = "target_id")
    private String targetId;

    @Column(name = "target_name")
    private String targetName;

    @Column(name = "survey_type")
    private String surveyType;

    @Column(name = "info_type")
    private String infoType;

    @Column(name = "advice_dept_id")
    private String adviceDeptId;

    @Column(name = "advice_read_time")
    private String adviceReadTime;

    @Column(name = "advice_read_uuid")
    private String adviceReadUuid;

    @Column(name = "advice_status")
    private String adviceStatus;

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

    public String getReport() {
        return report;
    }

    public Onsite report(String report) {
        this.report = report;
        return this;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getOnsiteTime() {
        return onsiteTime;
    }

    public Onsite onsiteTime(String onsiteTime) {
        this.onsiteTime = onsiteTime;
        return this;
    }

    public void setOnsiteTime(String onsiteTime) {
        this.onsiteTime = onsiteTime;
    }

    public String getAccompany() {
        return accompany;
    }

    public Onsite accompany(String accompany) {
        this.accompany = accompany;
        return this;
    }

    public void setAccompany(String accompany) {
        this.accompany = accompany;
    }

    public String getOnsiteAddr() {
        return onsiteAddr;
    }

    public Onsite onsiteAddr(String onsiteAddr) {
        this.onsiteAddr = onsiteAddr;
        return this;
    }

    public void setOnsiteAddr(String onsiteAddr) {
        this.onsiteAddr = onsiteAddr;
    }

    public String getOnsiteInfo() {
        return onsiteInfo;
    }

    public Onsite onsiteInfo(String onsiteInfo) {
        this.onsiteInfo = onsiteInfo;
        return this;
    }

    public void setOnsiteInfo(String onsiteInfo) {
        this.onsiteInfo = onsiteInfo;
    }

    public String getTargetId() {
        return targetId;
    }

    public Onsite targetId(String targetId) {
        this.targetId = targetId;
        return this;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getTargetName() {
        return targetName;
    }

    public Onsite targetName(String targetName) {
        this.targetName = targetName;
        return this;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getSurveyType() {
        return surveyType;
    }

    public Onsite surveyType(String surveyType) {
        this.surveyType = surveyType;
        return this;
    }

    public void setSurveyType(String surveyType) {
        this.surveyType = surveyType;
    }

    public String getInfoType() {
        return infoType;
    }

    public Onsite infoType(String infoType) {
        this.infoType = infoType;
        return this;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public String getAdviceDeptId() {
        return adviceDeptId;
    }

    public Onsite adviceDeptId(String adviceDeptId) {
        this.adviceDeptId = adviceDeptId;
        return this;
    }

    public void setAdviceDeptId(String adviceDeptId) {
        this.adviceDeptId = adviceDeptId;
    }

    public String getAdviceReadTime() {
        return adviceReadTime;
    }

    public Onsite adviceReadTime(String adviceReadTime) {
        this.adviceReadTime = adviceReadTime;
        return this;
    }

    public void setAdviceReadTime(String adviceReadTime) {
        this.adviceReadTime = adviceReadTime;
    }

    public String getAdviceReadUuid() {
        return adviceReadUuid;
    }

    public Onsite adviceReadUuid(String adviceReadUuid) {
        this.adviceReadUuid = adviceReadUuid;
        return this;
    }

    public void setAdviceReadUuid(String adviceReadUuid) {
        this.adviceReadUuid = adviceReadUuid;
    }

    public String getAdviceStatus() {
        return adviceStatus;
    }

    public Onsite adviceStatus(String adviceStatus) {
        this.adviceStatus = adviceStatus;
        return this;
    }

    public void setAdviceStatus(String adviceStatus) {
        this.adviceStatus = adviceStatus;
    }

    public Department getDepartment() {
        return department;
    }

    public Onsite department(Department department) {
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
        Onsite onsite = (Onsite) o;
        if (onsite.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), onsite.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Onsite{" +
            "id=" + getId() +
            ", report='" + getReport() + "'" +
            ", onsiteTime='" + getOnsiteTime() + "'" +
            ", accompany='" + getAccompany() + "'" +
            ", onsiteAddr='" + getOnsiteAddr() + "'" +
            ", onsiteInfo='" + getOnsiteInfo() + "'" +
            ", targetId='" + getTargetId() + "'" +
            ", targetName='" + getTargetName() + "'" +
            ", surveyType='" + getSurveyType() + "'" +
            ", infoType='" + getInfoType() + "'" +
            ", adviceDeptId='" + getAdviceDeptId() + "'" +
            ", adviceReadTime='" + getAdviceReadTime() + "'" +
            ", adviceReadUuid='" + getAdviceReadUuid() + "'" +
            ", adviceStatus='" + getAdviceStatus() + "'" +
            "}";
    }
}

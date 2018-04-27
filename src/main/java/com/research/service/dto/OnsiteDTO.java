package com.research.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Onsite entity.
 */
public class OnsiteDTO implements Serializable {

    private Long id;

    private String report;

    private String onsiteTime;

    private String accompany;

    private String onsiteAddr;

    private String onsiteInfo;

    private String targetId;

    private String targetName;

    private String surveyType;

    private String infoType;

    private String adviceDeptId;

    private String adviceReadTime;

    private String adviceReadUuid;

    private String adviceStatus;

    private Long departmentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getOnsiteTime() {
        return onsiteTime;
    }

    public void setOnsiteTime(String onsiteTime) {
        this.onsiteTime = onsiteTime;
    }

    public String getAccompany() {
        return accompany;
    }

    public void setAccompany(String accompany) {
        this.accompany = accompany;
    }

    public String getOnsiteAddr() {
        return onsiteAddr;
    }

    public void setOnsiteAddr(String onsiteAddr) {
        this.onsiteAddr = onsiteAddr;
    }

    public String getOnsiteInfo() {
        return onsiteInfo;
    }

    public void setOnsiteInfo(String onsiteInfo) {
        this.onsiteInfo = onsiteInfo;
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

    public String getSurveyType() {
        return surveyType;
    }

    public void setSurveyType(String surveyType) {
        this.surveyType = surveyType;
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public String getAdviceDeptId() {
        return adviceDeptId;
    }

    public void setAdviceDeptId(String adviceDeptId) {
        this.adviceDeptId = adviceDeptId;
    }

    public String getAdviceReadTime() {
        return adviceReadTime;
    }

    public void setAdviceReadTime(String adviceReadTime) {
        this.adviceReadTime = adviceReadTime;
    }

    public String getAdviceReadUuid() {
        return adviceReadUuid;
    }

    public void setAdviceReadUuid(String adviceReadUuid) {
        this.adviceReadUuid = adviceReadUuid;
    }

    public String getAdviceStatus() {
        return adviceStatus;
    }

    public void setAdviceStatus(String adviceStatus) {
        this.adviceStatus = adviceStatus;
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

        OnsiteDTO onsiteDTO = (OnsiteDTO) o;
        if(onsiteDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), onsiteDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OnsiteDTO{" +
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

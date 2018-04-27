package com.research.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Files entity.
 */
public class FilesDTO implements Serializable {

    private Long id;

    private String funcName;

    private String linkId;

    private String subId;

    private String subId2;

    private String authStr;

    private String subType;

    private String fname;

    private String rawName;

    private String fsize;

    private String ftype;

    private String remarks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public String getSubId2() {
        return subId2;
    }

    public void setSubId2(String subId2) {
        this.subId2 = subId2;
    }

    public String getAuthStr() {
        return authStr;
    }

    public void setAuthStr(String authStr) {
        this.authStr = authStr;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getRawName() {
        return rawName;
    }

    public void setRawName(String rawName) {
        this.rawName = rawName;
    }

    public String getFsize() {
        return fsize;
    }

    public void setFsize(String fsize) {
        this.fsize = fsize;
    }

    public String getFtype() {
        return ftype;
    }

    public void setFtype(String ftype) {
        this.ftype = ftype;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FilesDTO filesDTO = (FilesDTO) o;
        if(filesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), filesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FilesDTO{" +
            "id=" + getId() +
            ", funcName='" + getFuncName() + "'" +
            ", linkId='" + getLinkId() + "'" +
            ", subId='" + getSubId() + "'" +
            ", subId2='" + getSubId2() + "'" +
            ", authStr='" + getAuthStr() + "'" +
            ", subType='" + getSubType() + "'" +
            ", fname='" + getFname() + "'" +
            ", rawName='" + getRawName() + "'" +
            ", fsize='" + getFsize() + "'" +
            ", ftype='" + getFtype() + "'" +
            ", remarks='" + getRemarks() + "'" +
            "}";
    }
}

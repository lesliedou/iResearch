package com.research.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Files.
 */
@Entity
@Table(name = "glo_files")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Files implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "func_name")
    private String funcName;

    @Column(name = "link_id")
    private String linkId;

    @Column(name = "sub_id")
    private String subId;

    @Column(name = "sub_id_2")
    private String subId2;

    @Column(name = "auth_str")
    private String authStr;

    @Column(name = "sub_type")
    private String subType;

    @Column(name = "fname")
    private String fname;

    @Column(name = "raw_name")
    private String rawName;

    @Column(name = "fsize")
    private String fsize;

    @Column(name = "ftype")
    private String ftype;

    @Column(name = "remarks")
    private String remarks;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFuncName() {
        return funcName;
    }

    public Files funcName(String funcName) {
        this.funcName = funcName;
        return this;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public String getLinkId() {
        return linkId;
    }

    public Files linkId(String linkId) {
        this.linkId = linkId;
        return this;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public String getSubId() {
        return subId;
    }

    public Files subId(String subId) {
        this.subId = subId;
        return this;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public String getSubId2() {
        return subId2;
    }

    public Files subId2(String subId2) {
        this.subId2 = subId2;
        return this;
    }

    public void setSubId2(String subId2) {
        this.subId2 = subId2;
    }

    public String getAuthStr() {
        return authStr;
    }

    public Files authStr(String authStr) {
        this.authStr = authStr;
        return this;
    }

    public void setAuthStr(String authStr) {
        this.authStr = authStr;
    }

    public String getSubType() {
        return subType;
    }

    public Files subType(String subType) {
        this.subType = subType;
        return this;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getFname() {
        return fname;
    }

    public Files fname(String fname) {
        this.fname = fname;
        return this;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getRawName() {
        return rawName;
    }

    public Files rawName(String rawName) {
        this.rawName = rawName;
        return this;
    }

    public void setRawName(String rawName) {
        this.rawName = rawName;
    }

    public String getFsize() {
        return fsize;
    }

    public Files fsize(String fsize) {
        this.fsize = fsize;
        return this;
    }

    public void setFsize(String fsize) {
        this.fsize = fsize;
    }

    public String getFtype() {
        return ftype;
    }

    public Files ftype(String ftype) {
        this.ftype = ftype;
        return this;
    }

    public void setFtype(String ftype) {
        this.ftype = ftype;
    }

    public String getRemarks() {
        return remarks;
    }

    public Files remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
        Files files = (Files) o;
        if (files.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), files.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Files{" +
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

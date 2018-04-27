package com.research.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A UploadInfo.
 */
@Entity
@Table(name = "ddy_info_upload")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UploadInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "info_id")
    private Long id;

    @Column(name = "info_type")
    private String infoType;

    @Column(name = "subj")
    private String subj;

    @Column(name = "content")
    private String content;

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

    public String getInfoType() {
        return infoType;
    }

    public UploadInfo infoType(String infoType) {
        this.infoType = infoType;
        return this;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public String getSubj() {
        return subj;
    }

    public UploadInfo subj(String subj) {
        this.subj = subj;
        return this;
    }

    public void setSubj(String subj) {
        this.subj = subj;
    }

    public String getContent() {
        return content;
    }

    public UploadInfo content(String content) {
        this.content = content;
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Department getDepartment() {
        return department;
    }

    public UploadInfo department(Department department) {
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
        UploadInfo uploadInfo = (UploadInfo) o;
        if (uploadInfo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), uploadInfo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UploadInfo{" +
            "id=" + getId() +
            ", infoType='" + getInfoType() + "'" +
            ", subj='" + getSubj() + "'" +
            ", content='" + getContent() + "'" +
            "}";
    }
}

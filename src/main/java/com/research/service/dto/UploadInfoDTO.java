package com.research.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the UploadInfo entity.
 */
public class UploadInfoDTO implements Serializable {

    private Long id;

    private String infoType;

    private String subj;

    private String content;

    private Long departmentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public String getSubj() {
        return subj;
    }

    public void setSubj(String subj) {
        this.subj = subj;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

        UploadInfoDTO uploadInfoDTO = (UploadInfoDTO) o;
        if(uploadInfoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), uploadInfoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UploadInfoDTO{" +
            "id=" + getId() +
            ", infoType='" + getInfoType() + "'" +
            ", subj='" + getSubj() + "'" +
            ", content='" + getContent() + "'" +
            "}";
    }
}

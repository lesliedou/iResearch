package com.research.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Target entity.
 */
public class TargetDTO implements Serializable {

    private Long id;

    private String targetName;

    private String targetType;

    private String targetTypeReadme;

    private String contactPerson;

    private String contactPhone;

    private String contactEmail;

    private String contactAddr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getTargetTypeReadme() {
        return targetTypeReadme;
    }

    public void setTargetTypeReadme(String targetTypeReadme) {
        this.targetTypeReadme = targetTypeReadme;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactAddr() {
        return contactAddr;
    }

    public void setContactAddr(String contactAddr) {
        this.contactAddr = contactAddr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TargetDTO targetDTO = (TargetDTO) o;
        if(targetDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), targetDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TargetDTO{" +
            "id=" + getId() +
            ", targetName='" + getTargetName() + "'" +
            ", targetType='" + getTargetType() + "'" +
            ", targetTypeReadme='" + getTargetTypeReadme() + "'" +
            ", contactPerson='" + getContactPerson() + "'" +
            ", contactPhone='" + getContactPhone() + "'" +
            ", contactEmail='" + getContactEmail() + "'" +
            ", contactAddr='" + getContactAddr() + "'" +
            "}";
    }
}

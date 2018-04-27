package com.research.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Target.
 */
@Entity
@Table(name = "ddy_target")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Target implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "target_id")
    private Long id;

    @Column(name = "target_name")
    private String targetName;

    @Column(name = "target_type")
    private String targetType;

    @Column(name = "target_type_readme")
    private String targetTypeReadme;

    @Column(name = "contact_person")
    private String contactPerson;

    @Column(name = "contact_phone")
    private String contactPhone;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "contact_addr")
    private String contactAddr;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTargetName() {
        return targetName;
    }

    public Target targetName(String targetName) {
        this.targetName = targetName;
        return this;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getTargetType() {
        return targetType;
    }

    public Target targetType(String targetType) {
        this.targetType = targetType;
        return this;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getTargetTypeReadme() {
        return targetTypeReadme;
    }

    public Target targetTypeReadme(String targetTypeReadme) {
        this.targetTypeReadme = targetTypeReadme;
        return this;
    }

    public void setTargetTypeReadme(String targetTypeReadme) {
        this.targetTypeReadme = targetTypeReadme;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public Target contactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
        return this;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public Target contactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
        return this;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public Target contactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
        return this;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactAddr() {
        return contactAddr;
    }

    public Target contactAddr(String contactAddr) {
        this.contactAddr = contactAddr;
        return this;
    }

    public void setContactAddr(String contactAddr) {
        this.contactAddr = contactAddr;
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
        Target target = (Target) o;
        if (target.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), target.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Target{" +
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

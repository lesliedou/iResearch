package com.research.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Department.
 */
@Entity
@Table(name = "ddy_dept")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_id")
    private Long id;

    @Column(name = "dept_name")
    private String deptName;

    @Column(name = "dept_code")
    private String deptCode;

    @Column(name = "dept_type")
    private String deptType;

    @Column(name = "contact_person")
    private String contactPerson;

    @Column(name = "contact_info")
    private String contactInfo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public Department deptName(String deptName) {
        this.deptName = deptName;
        return this;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public Department deptCode(String deptCode) {
        this.deptCode = deptCode;
        return this;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptType() {
        return deptType;
    }

    public Department deptType(String deptType) {
        this.deptType = deptType;
        return this;
    }

    public void setDeptType(String deptType) {
        this.deptType = deptType;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public Department contactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
        return this;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public Department contactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
        return this;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
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
        Department department = (Department) o;
        if (department.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), department.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Department{" +
            "id=" + getId() +
            ", deptName='" + getDeptName() + "'" +
            ", deptCode='" + getDeptCode() + "'" +
            ", deptType='" + getDeptType() + "'" +
            ", contactPerson='" + getContactPerson() + "'" +
            ", contactInfo='" + getContactInfo() + "'" +
            "}";
    }
}

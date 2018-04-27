package com.research.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Acl.
 */
@Entity
@Table(name = "ddy_acl")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Acl implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acl_id")
    private Long id;

    @Column(name = "mod_name")
    private String modName;

    @Column(name = "func_name")
    private String funcName;

    @Lob
    @Column(name = "acl_list")
    private String aclList;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModName() {
        return modName;
    }

    public Acl modName(String modName) {
        this.modName = modName;
        return this;
    }

    public void setModName(String modName) {
        this.modName = modName;
    }

    public String getFuncName() {
        return funcName;
    }

    public Acl funcName(String funcName) {
        this.funcName = funcName;
        return this;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public String getAclList() {
        return aclList;
    }

    public Acl aclList(String aclList) {
        this.aclList = aclList;
        return this;
    }

    public void setAclList(String aclList) {
        this.aclList = aclList;
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
        Acl acl = (Acl) o;
        if (acl.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), acl.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Acl{" +
            "id=" + getId() +
            ", modName='" + getModName() + "'" +
            ", funcName='" + getFuncName() + "'" +
            ", aclList='" + getAclList() + "'" +
            "}";
    }
}

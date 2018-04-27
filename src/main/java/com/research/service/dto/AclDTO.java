package com.research.service.dto;


import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the Acl entity.
 */
public class AclDTO implements Serializable {

    private Long id;

    private String modName;

    private String funcName;

    @Lob
    private String aclList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModName() {
        return modName;
    }

    public void setModName(String modName) {
        this.modName = modName;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public String getAclList() {
        return aclList;
    }

    public void setAclList(String aclList) {
        this.aclList = aclList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AclDTO aclDTO = (AclDTO) o;
        if(aclDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), aclDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AclDTO{" +
            "id=" + getId() +
            ", modName='" + getModName() + "'" +
            ", funcName='" + getFuncName() + "'" +
            ", aclList='" + getAclList() + "'" +
            "}";
    }
}

package com.research.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the OnsiteProcess entity.
 */
public class OnsiteProcessDTO implements Serializable {

    private Long id;

    private String confirm;

    private String remarks;

    private Long onsiteId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getOnsiteId() {
        return onsiteId;
    }

    public void setOnsiteId(Long onsiteId) {
        this.onsiteId = onsiteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OnsiteProcessDTO onsiteProcessDTO = (OnsiteProcessDTO) o;
        if(onsiteProcessDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), onsiteProcessDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OnsiteProcessDTO{" +
            "id=" + getId() +
            ", confirm='" + getConfirm() + "'" +
            ", remarks='" + getRemarks() + "'" +
            "}";
    }
}

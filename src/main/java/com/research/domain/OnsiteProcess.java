package com.research.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A OnsiteProcess.
 */
@Entity
@Table(name = "ddy_onsite_process")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OnsiteProcess implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flow_id")
    private Long id;

    @Column(name = "confirm")
    private String confirm;

    @Column(name = "remarks")
    private String remarks;

    @OneToOne
    @JoinColumn(unique = true)
    private Onsite onsite;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConfirm() {
        return confirm;
    }

    public OnsiteProcess confirm(String confirm) {
        this.confirm = confirm;
        return this;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getRemarks() {
        return remarks;
    }

    public OnsiteProcess remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Onsite getOnsite() {
        return onsite;
    }

    public OnsiteProcess onsite(Onsite onsite) {
        this.onsite = onsite;
        return this;
    }

    public void setOnsite(Onsite onsite) {
        this.onsite = onsite;
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
        OnsiteProcess onsiteProcess = (OnsiteProcess) o;
        if (onsiteProcess.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), onsiteProcess.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OnsiteProcess{" +
            "id=" + getId() +
            ", confirm='" + getConfirm() + "'" +
            ", remarks='" + getRemarks() + "'" +
            "}";
    }
}

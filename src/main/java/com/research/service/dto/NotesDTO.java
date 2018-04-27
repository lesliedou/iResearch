package com.research.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Notes entity.
 */
public class NotesDTO implements Serializable {

    private Long id;

    private String subject;

    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NotesDTO notesDTO = (NotesDTO) o;
        if(notesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), notesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NotesDTO{" +
            "id=" + getId() +
            ", subject='" + getSubject() + "'" +
            ", content='" + getContent() + "'" +
            "}";
    }
}

package com.research.service.mapper;

import com.research.domain.*;
import com.research.service.dto.FilesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Files and its DTO FilesDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FilesMapper extends EntityMapper<FilesDTO, Files> {



    default Files fromId(Long id) {
        if (id == null) {
            return null;
        }
        Files files = new Files();
        files.setId(id);
        return files;
    }
}

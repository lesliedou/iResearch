package com.research.service.mapper;

import com.research.domain.*;
import com.research.service.dto.OnsiteProcessDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity OnsiteProcess and its DTO OnsiteProcessDTO.
 */
@Mapper(componentModel = "spring", uses = {OnsiteMapper.class})
public interface OnsiteProcessMapper extends EntityMapper<OnsiteProcessDTO, OnsiteProcess> {

    @Mapping(source = "onsite.id", target = "onsiteId")
    OnsiteProcessDTO toDto(OnsiteProcess onsiteProcess);

    @Mapping(source = "onsiteId", target = "onsite")
    OnsiteProcess toEntity(OnsiteProcessDTO onsiteProcessDTO);

    default OnsiteProcess fromId(Long id) {
        if (id == null) {
            return null;
        }
        OnsiteProcess onsiteProcess = new OnsiteProcess();
        onsiteProcess.setId(id);
        return onsiteProcess;
    }
}

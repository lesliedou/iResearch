package com.research.service.mapper;

import com.research.domain.*;
import com.research.service.dto.OnsiteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Onsite and its DTO OnsiteDTO.
 */
@Mapper(componentModel = "spring", uses = {DepartmentMapper.class})
public interface OnsiteMapper extends EntityMapper<OnsiteDTO, Onsite> {

    @Mapping(source = "department.id", target = "departmentId")
    OnsiteDTO toDto(Onsite onsite);

    @Mapping(source = "departmentId", target = "department")
    Onsite toEntity(OnsiteDTO onsiteDTO);

    default Onsite fromId(Long id) {
        if (id == null) {
            return null;
        }
        Onsite onsite = new Onsite();
        onsite.setId(id);
        return onsite;
    }
}

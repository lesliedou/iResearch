package com.research.service.mapper;

import com.research.domain.*;
import com.research.service.dto.TargetDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Target and its DTO TargetDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TargetMapper extends EntityMapper<TargetDTO, Target> {



    default Target fromId(Long id) {
        if (id == null) {
            return null;
        }
        Target target = new Target();
        target.setId(id);
        return target;
    }
}

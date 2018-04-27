package com.research.service.mapper;

import com.research.domain.*;
import com.research.service.dto.ConfigAclDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ConfigAcl and its DTO ConfigAclDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ConfigAclMapper extends EntityMapper<ConfigAclDTO, ConfigAcl> {



    default ConfigAcl fromId(Long id) {
        if (id == null) {
            return null;
        }
        ConfigAcl configAcl = new ConfigAcl();
        configAcl.setId(id);
        return configAcl;
    }
}

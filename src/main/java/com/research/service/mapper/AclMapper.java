package com.research.service.mapper;

import com.research.domain.*;
import com.research.service.dto.AclDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Acl and its DTO AclDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AclMapper extends EntityMapper<AclDTO, Acl> {



    default Acl fromId(Long id) {
        if (id == null) {
            return null;
        }
        Acl acl = new Acl();
        acl.setId(id);
        return acl;
    }
}

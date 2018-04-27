package com.research.service.mapper;

import com.research.domain.*;
import com.research.service.dto.UploadInfoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity UploadInfo and its DTO UploadInfoDTO.
 */
@Mapper(componentModel = "spring", uses = {DepartmentMapper.class})
public interface UploadInfoMapper extends EntityMapper<UploadInfoDTO, UploadInfo> {

    @Mapping(source = "department.id", target = "departmentId")
    UploadInfoDTO toDto(UploadInfo uploadInfo);

    @Mapping(source = "departmentId", target = "department")
    UploadInfo toEntity(UploadInfoDTO uploadInfoDTO);

    default UploadInfo fromId(Long id) {
        if (id == null) {
            return null;
        }
        UploadInfo uploadInfo = new UploadInfo();
        uploadInfo.setId(id);
        return uploadInfo;
    }
}

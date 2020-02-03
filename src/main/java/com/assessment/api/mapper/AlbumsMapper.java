package com.assessment.api.mapper;

import org.mapstruct.Mapper;

import com.assessment.api.dto.AlbumsDTO;
import com.assessment.api.entity.Albums;

@Mapper(componentModel = "spring", implementationPackage = "com.assessment.api.mapper.impl")
public interface AlbumsMapper extends EntityMapper<AlbumsDTO, Albums> {

}

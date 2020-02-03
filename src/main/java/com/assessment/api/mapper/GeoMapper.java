package com.assessment.api.mapper;

import org.mapstruct.Mapper;

import com.assessment.api.dto.GeoDTO;
import com.assessment.api.entity.Geo;

@Mapper(componentModel = "spring", implementationPackage = "com.assessment.api.mapper.impl")
public interface GeoMapper extends EntityMapper<GeoDTO, Geo> {

}

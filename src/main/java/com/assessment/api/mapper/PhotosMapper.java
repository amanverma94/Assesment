package com.assessment.api.mapper;

import org.mapstruct.Mapper;

import com.assessment.api.dto.PhotosDTO;
import com.assessment.api.entity.Photos;

@Mapper(componentModel = "spring", implementationPackage = "com.assessment.api.mapper.impl")
public interface PhotosMapper extends EntityMapper<PhotosDTO, Photos> {

}

package com.assessment.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.assessment.api.dto.PhotosDTO;
import com.assessment.api.entity.Photos;

@Mapper(componentModel = "spring", implementationPackage = "com.assessment.api.mapper.impl")
public interface PhotosMapper extends EntityMapper<PhotosDTO, Photos> {
	
	@Mapping(target = "album", source = "photos.albumId.id")
	public PhotosDTO toDto(Photos photos);
}

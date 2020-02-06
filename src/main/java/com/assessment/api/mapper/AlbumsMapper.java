package com.assessment.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.assessment.api.dto.AlbumsDTO;
import com.assessment.api.entity.Albums;

@Mapper(componentModel = "spring", implementationPackage = "com.assessment.api.mapper.impl")
public interface AlbumsMapper{
	
	@Mapping(target = "userId", source = "user.id")
	public AlbumsDTO toDto(Albums albums);

}

package com.assessment.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.assessment.api.dto.PostsDTO;
import com.assessment.api.entity.Posts;

@Mapper(componentModel = "spring", implementationPackage = "com.assessment.api.mapper.impl")
public interface PostsMapper {

	@Mapping(target = "userId", source = "user.id")
	public PostsDTO toDto(Posts post);

}

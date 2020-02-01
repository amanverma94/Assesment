package com.assessment.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.assessment.api.dto.PostsDTO;
import com.assessment.api.entity.Posts;

@Mapper(componentModel = "spring", implementationPackage = "com.assessment.api.mapper.impl")
public interface PostsMapper extends EntityMapper<PostsDTO, Posts> {
	
	@Override
	@Mapping(target = "title", source = "postTitle")
	@Mapping(target = "body", source = "postBody")
	PostsDTO toDto(Posts posts);

}

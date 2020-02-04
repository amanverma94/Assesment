package com.assessment.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.assessment.api.dto.CommentsDTO;
import com.assessment.api.entity.Comments;

@Mapper(componentModel = "spring", implementationPackage = "com.assessment.api.mapper.impl")
public interface CommentsMapper {

	@Mapping(target = "postId", source = "comment.postId.id")
	public CommentsDTO toDto(Comments comment);

}

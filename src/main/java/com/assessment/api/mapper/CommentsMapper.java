package com.assessment.api.mapper;

import org.mapstruct.Mapper;

import com.assessment.api.dto.CommentsDTO;
import com.assessment.api.entity.Comments;

@Mapper(componentModel = "spring", implementationPackage = "com.assessment.api.mapper.impl")
public interface CommentsMapper extends EntityMapper<CommentsDTO, Comments> {

}

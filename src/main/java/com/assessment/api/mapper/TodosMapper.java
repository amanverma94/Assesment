package com.assessment.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.assessment.api.dto.TodosDTO;
import com.assessment.api.entity.Todos;

@Mapper(componentModel = "spring", implementationPackage = "com.assessment.api.mapper.impl")
public interface TodosMapper {
	
	@Mapping(target = "userId", source = "user.id")
	public TodosDTO toDto(Todos todos);
	
}

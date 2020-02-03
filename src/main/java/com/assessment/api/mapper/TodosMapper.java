package com.assessment.api.mapper;

import org.mapstruct.Mapper;

import com.assessment.api.dto.TodosDTO;
import com.assessment.api.entity.Todos;

@Mapper(componentModel = "spring", implementationPackage = "com.assessment.api.mapper.impl")
public interface TodosMapper extends EntityMapper<TodosDTO, Todos> {

}

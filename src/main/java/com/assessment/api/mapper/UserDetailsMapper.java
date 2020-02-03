package com.assessment.api.mapper;

import org.mapstruct.Mapper;

import com.assessment.api.dto.UserDetailsDTO;
import com.assessment.api.entity.UserDetails;

@Mapper(componentModel = "spring", implementationPackage = "com.assessment.api.mapper.impl")
public interface UserDetailsMapper extends EntityMapper<UserDetailsDTO, UserDetails> {
	
}

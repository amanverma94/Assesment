package com.assessment.api.mapper;

import org.mapstruct.Mapper;

import com.assessment.api.dto.AddressDTO;
import com.assessment.api.entity.Address;

@Mapper(componentModel = "spring", implementationPackage = "com.assessment.api.mapper.impl")
public interface AddressMapper extends EntityMapper<AddressDTO, Address> {

}

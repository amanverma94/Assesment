package com.assessment.api.mapper;

import org.mapstruct.Mapper;

import com.assessment.api.dto.CompanyDTO;
import com.assessment.api.entity.Company;

@Mapper(componentModel = "spring", implementationPackage = "com.assessment.api.mapper.impl")
public interface CompanyMapper extends EntityMapper<CompanyDTO, Company> {

}

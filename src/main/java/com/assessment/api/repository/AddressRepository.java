package com.assessment.api.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assessment.api.entity.Address;
import com.assessment.api.entity.UserDetails;

@Repository
@Transactional
public interface AddressRepository extends JpaRepository<Address, Integer> {


}

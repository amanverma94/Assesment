package com.assessment.api.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assessment.api.entity.Geo;

@Repository
@Transactional
public interface GeoRepository extends JpaRepository<Geo, Integer> {


}

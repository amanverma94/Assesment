package com.assessment.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.api.entity.Photos;

@Repository
@Transactional
public interface PhotosRepository extends JpaRepository<Photos, Integer> {


}

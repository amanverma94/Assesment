package com.assessment.api.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assessment.api.entity.Albums;

@Repository
@Transactional
public interface AlbumsRepository extends JpaRepository<Albums, Integer> {


}

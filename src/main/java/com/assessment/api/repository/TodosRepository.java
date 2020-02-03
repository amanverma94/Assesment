package com.assessment.api.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assessment.api.entity.Todos;

@Repository
@Transactional
public interface TodosRepository extends JpaRepository<Todos, Integer> {


}

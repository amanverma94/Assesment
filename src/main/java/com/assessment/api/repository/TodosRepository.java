package com.assessment.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.api.entity.Todos;

@Repository
@Transactional
public interface TodosRepository extends JpaRepository<Todos, Integer> {

}

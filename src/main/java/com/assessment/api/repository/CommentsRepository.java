package com.assessment.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.api.entity.Comments;

@Repository
@Transactional
public interface CommentsRepository extends JpaRepository<Comments, Integer> {

}

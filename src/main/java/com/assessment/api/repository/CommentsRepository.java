package com.assessment.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.api.entity.Comments;
import com.assessment.api.entity.Posts;

@Repository
@Transactional
public interface CommentsRepository extends JpaRepository<Comments, Integer> {

	Optional<List<Comments>> findByPost(Posts post);

	@Query(nativeQuery = true, value = "Select * from comments p where p.email like %:email%")
	Optional<List<Comments>> findByEmail(@Param("email") String email);

	@Query(nativeQuery = true, value = "Select * from comments p where p.name like %:name%")
	Optional<List<Comments>> findByName(@Param("name") String name);

	@Query(nativeQuery = true, value = "Select * from comments p where p.body like %:body%")
	Optional<List<Comments>> findByBody(@Param("body") String body);

}

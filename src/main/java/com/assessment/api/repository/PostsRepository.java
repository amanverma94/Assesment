package com.assessment.api.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assessment.api.entity.Posts;
import com.assessment.api.entity.UserDetails;

@Repository
@Transactional
public interface PostsRepository extends JpaRepository<Posts, Integer> {

	Optional<List<Posts>> findByUserId(UserDetails userId);

	Optional<Posts> findById(Integer id);

	@Query(nativeQuery = true, value = "Select * from posts p where p.post_title like %:title%")
	Optional<List<Posts>> findPostByTitleContent(@Param("title") String title);

	@Query(nativeQuery = true, value = "Select * from posts p where p.post_body like %:body%")
	Optional<List<Posts>> findPostByBodyContent(@Param("body") String body);
}

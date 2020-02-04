package com.assessment.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.api.entity.Albums;
import com.assessment.api.entity.UserDetails;

@Repository
@Transactional
public interface AlbumsRepository extends JpaRepository<Albums, Integer> {

	Optional<List<Albums>> findByUserId(UserDetails userId);
	
	@Query(nativeQuery = true, value = "Select * from albums p where p.title like %:title%")
	Optional<List<Albums>> findPostByTitle(@Param("title") String title);
}

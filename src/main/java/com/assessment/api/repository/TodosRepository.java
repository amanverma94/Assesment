package com.assessment.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.api.entity.Todos;
import com.assessment.api.entity.UserDetails;

@Repository
@Transactional
public interface TodosRepository extends JpaRepository<Todos, Integer> {
	
	Optional<List<Todos>> findByUser(UserDetails userId);
	
	Optional<List<Todos>> findByCompleted(Boolean completed);
	
	@Query(nativeQuery = true, value = "Select * from todos p where p.title like %:title%")
	Optional<List<Todos>> findByTitle(@Param("title") String title);
}

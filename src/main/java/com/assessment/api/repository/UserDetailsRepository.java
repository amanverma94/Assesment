package com.assessment.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.api.entity.UserDetails;

@Repository
@Transactional
public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {
	
	public Optional<UserDetails> findByName(String name);
	
	public Optional<UserDetails> findByUsername(String username);
	
	public Optional<UserDetails> findByEmail(String email);
	
	public Optional<UserDetails> findByPhone(String phone);
	
	public Optional<UserDetails> findByWebsite(String website);


}

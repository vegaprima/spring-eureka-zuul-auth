package com.personal.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.personal.auth.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
	
	@Query(value = "SELECT * FROM USER WHERE PHONE_NUMBER = ?1 LIMIT 1", nativeQuery = true)
	User findByPhoneNumber(String phoneNumber);
}

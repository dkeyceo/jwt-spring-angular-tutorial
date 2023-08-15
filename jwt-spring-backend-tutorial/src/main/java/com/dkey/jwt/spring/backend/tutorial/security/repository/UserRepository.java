package com.dkey.jwt.spring.backend.tutorial.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dkey.jwt.spring.backend.tutorial.security.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByUsername(String username);
	Optional<User> findByUsernameOrEmail(String username, String email);
	Optional<User> findByTokenPassword(String tokenPassword);
	boolean existsByUsername(String username);
	boolean existsByEmail(String email);

}

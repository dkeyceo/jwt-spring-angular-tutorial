package com.dkey.jwt.spring.backend.tutorial.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dkey.jwt.spring.backend.tutorial.security.entity.User;
import com.dkey.jwt.spring.backend.tutorial.security.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public Optional<User> getByUsername(String username){
        return userRepository.findByUsername(username);
    }

	public Optional<User> getByUsernameOrEmail(String usernameOrEmail){
        return userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
    }
	
	public Optional<User> getByTokenPassword(String tokenPassword){
        return userRepository.findByTokenPassword(tokenPassword);
    }
	
    public boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public void save(User user){
        userRepository.save(user);
    }
}

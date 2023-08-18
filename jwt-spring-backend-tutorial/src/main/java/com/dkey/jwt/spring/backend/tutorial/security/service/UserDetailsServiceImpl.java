package com.dkey.jwt.spring.backend.tutorial.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dkey.jwt.spring.backend.tutorial.security.entity.User;
import com.dkey.jwt.spring.backend.tutorial.security.entity.UserPrincipal;
import com.dkey.jwt.spring.backend.tutorial.security.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) 
    		throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).get();
        return UserPrincipal.build(user);
    }
}

package com.dkey.jwt.spring.backend.tutorial.security.service;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dkey.jwt.spring.backend.tutorial.dto.Message;
import com.dkey.jwt.spring.backend.tutorial.exceptions.CustomException;
import com.dkey.jwt.spring.backend.tutorial.security.dto.JwtDto;
import com.dkey.jwt.spring.backend.tutorial.security.dto.LoginUserDto;
import com.dkey.jwt.spring.backend.tutorial.security.dto.NewUserDto;
import com.dkey.jwt.spring.backend.tutorial.security.entity.Role;
import com.dkey.jwt.spring.backend.tutorial.security.entity.User;
import com.dkey.jwt.spring.backend.tutorial.security.enums.RoleName;
import com.dkey.jwt.spring.backend.tutorial.security.jwt.JwtProvider;
import com.dkey.jwt.spring.backend.tutorial.security.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    PasswordEncoder passwordEncoder;
	
	@Autowired
    RoleService roleService;
	
	@Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;
	
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
    
    public JwtDto login(LoginUserDto loginUserDto) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUserDto.getUsername(), loginUserDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        return new JwtDto(jwt);
    }
    
    public JwtDto refresh (JwtDto jwtDto) throws ParseException {
    	String token = jwtProvider.refreshToken(jwtDto);
        return new JwtDto(token);
    }
    
    public Message save(NewUserDto newUserDto){
        if(userRepository.existsByUsername(newUserDto.getUsername()))
        	throw new CustomException(HttpStatus.BAD_REQUEST, "username already exists");
        if(userRepository.existsByEmail(newUserDto.getEmail()))
        	throw new CustomException(HttpStatus.BAD_REQUEST, "email already exists");
        User user = new User(newUserDto.getName(), newUserDto.getUsername(), newUserDto.getEmail(), passwordEncoder.encode(newUserDto.getPassword()));
    	Set<Role> roles = new HashSet<>();
    	roles.add(roleService.getByRoleName(RoleName.ROLE_USER).get());
    	if(newUserDto.getRoles().contains("admin"))
    		roles.add(roleService.getByRoleName(RoleName.ROLE_ADMIN).get());
    	user.setRoles(roles);
        userRepository.save(user);
    	return new Message(user.getUsername()+ " was created");
    }
}

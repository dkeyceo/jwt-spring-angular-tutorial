package com.dkey.jwt.spring.backend.tutorial.security.controller;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dkey.jwt.spring.backend.tutorial.accountrecovery.dto.EmailValuesDto;
import com.dkey.jwt.spring.backend.tutorial.dto.Message;
import com.dkey.jwt.spring.backend.tutorial.security.dto.JwtDto;
import com.dkey.jwt.spring.backend.tutorial.security.dto.LoginUserDto;
import com.dkey.jwt.spring.backend.tutorial.security.dto.NewUserDto;
import com.dkey.jwt.spring.backend.tutorial.security.entity.Role;
import com.dkey.jwt.spring.backend.tutorial.security.entity.User;
import com.dkey.jwt.spring.backend.tutorial.security.enums.RoleName;
import com.dkey.jwt.spring.backend.tutorial.security.jwt.JwtProvider;
import com.dkey.jwt.spring.backend.tutorial.security.service.RoleService;
import com.dkey.jwt.spring.backend.tutorial.security.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
	
	@Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/create")
    public ResponseEntity<Message> newUser(@Valid @RequestBody NewUserDto newUserDto){
        return ResponseEntity.ok(userService.save(newUserDto));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUserDto loginUserDto){
        return ResponseEntity.ok(userService.login(loginUserDto));
    }
        
    @PostMapping("/refresh")
    public ResponseEntity<JwtDto> refresh(@RequestBody JwtDto jwtDto) throws ParseException{
    	return ResponseEntity.ok(userService.refresh(jwtDto));
    }
}

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
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/create")
    public ResponseEntity<?> newUser(@Valid @RequestBody NewUserDto newUserDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Message("there are errors!"), HttpStatus.BAD_REQUEST);
        if(userService.existsByUsername(newUserDto.getUsername()))
            return new ResponseEntity(new Message("username exists"), HttpStatus.BAD_REQUEST);
        if(userService.existsByEmail(newUserDto.getEmail()))
            return new ResponseEntity(new Message("email exists"), HttpStatus.BAD_REQUEST);
        User user =
                new User(newUserDto.getName(), newUserDto.getUsername(), newUserDto.getEmail(),
                        passwordEncoder.encode(newUserDto.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getByRoleName(RoleName.ROLE_USER).get());
        if(newUserDto.getRoles().contains("admin"))
            roles.add(roleService.getByRoleName(RoleName.ROLE_ADMIN).get());
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity(new Message("user was created"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUserDto loginUserDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Message("wrong fields"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUserDto.getUsername(), loginUserDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        JwtDto jwtDto = new JwtDto(jwt);
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
        
    @PostMapping("/refresh")
    public ResponseEntity<JwtDto> refresh(@RequestBody JwtDto jwtDto) throws ParseException{
        String token = jwtProvider.refreshToken(jwtDto);
        JwtDto jwt = new JwtDto(token);
    	return new ResponseEntity(jwt, HttpStatus.OK);
    }
}

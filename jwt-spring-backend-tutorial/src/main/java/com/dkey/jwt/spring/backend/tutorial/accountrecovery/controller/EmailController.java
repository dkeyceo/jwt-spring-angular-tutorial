package com.dkey.jwt.spring.backend.tutorial.accountrecovery.controller;

import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dkey.jwt.spring.backend.tutorial.accountrecovery.dto.ChangePasswordDto;
import com.dkey.jwt.spring.backend.tutorial.accountrecovery.dto.EmailValuesDto;
import com.dkey.jwt.spring.backend.tutorial.accountrecovery.service.EmailService;
import com.dkey.jwt.spring.backend.tutorial.dto.Message;
import com.dkey.jwt.spring.backend.tutorial.security.entity.User;
import com.dkey.jwt.spring.backend.tutorial.security.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/email-password")
@CrossOrigin
public class EmailController {
	
    @Autowired
    EmailService emailService;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${spring.mail.username}")
    private String mailFrom;

    private static final String subject = "Change password";

    @PostMapping("/send-email")
    public ResponseEntity<?> sendEmailTemplate(@RequestBody EmailValuesDto dto) {

    	Optional<User> userOpt = userService.getByUsernameOrEmail(dto.getMailTo());
        if(!userOpt.isPresent())
            return new ResponseEntity(new Message("No user exists!!!"), HttpStatus.NOT_FOUND);
        
        User user = userOpt.get();
        dto.setMailFrom(mailFrom);
        dto.setMailTo(user.getEmail());
        dto.setSubject(subject);
        dto.setUsername(dto.getUsername());
        UUID uuid = UUID.randomUUID();
        String tokenPassword = uuid.toString();
        dto.setTokenPassword(tokenPassword);
        user.setTokenPassword(tokenPassword);
        userService.save(user);
        emailService.sendEmail(dto);
        return new ResponseEntity(new Message("The email was sent"), HttpStatus.OK);
    }
    
    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordDto dto, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Message("Wrong req body"), HttpStatus.BAD_REQUEST);
        if(!dto.getPassword().equals(dto.getConfirmPassword()))
            return new ResponseEntity(new Message("Passwords not the same"), HttpStatus.BAD_REQUEST);
        Optional<User> userOpt = userService.getByTokenPassword(dto.getTokenPassword());
        if(!userOpt.isPresent())
            return new ResponseEntity(new Message("User with this token password not exists"), HttpStatus.NOT_FOUND);
        User user = userOpt.get();
        String newPassword = passwordEncoder.encode(dto.getPassword());
        user.setPassword(newPassword);
        user.setTokenPassword(null);
        userService.save(user);
        return new ResponseEntity(new Message("Password was changed"), HttpStatus.OK);
    }
    
}

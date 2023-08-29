package com.dkey.jwt.spring.backend.tutorial.accountrecovery.controller;

import java.util.Optional;
import java.util.UUID;

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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/email-password")
@CrossOrigin
public class EmailController {
	
    @Autowired
    EmailService emailService;

    @PostMapping("/send-email")
    public ResponseEntity<Message> sendEmailTemplate(@RequestBody EmailValuesDto dto) {
        return ResponseEntity.ok(emailService.sendEmailTemplate(dto));
    }
    
    @PostMapping("/change-password")
    public ResponseEntity<Message> changePassword(@Valid @RequestBody ChangePasswordDto dto) {
        return ResponseEntity.ok(emailService.changePassword(dto));
    }
    
}

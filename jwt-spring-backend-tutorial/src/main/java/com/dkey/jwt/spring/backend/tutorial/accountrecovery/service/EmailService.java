package com.dkey.jwt.spring.backend.tutorial.accountrecovery.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.dkey.jwt.spring.backend.tutorial.accountrecovery.dto.ChangePasswordDto;
import com.dkey.jwt.spring.backend.tutorial.accountrecovery.dto.EmailValuesDto;
import com.dkey.jwt.spring.backend.tutorial.dto.Message;
import com.dkey.jwt.spring.backend.tutorial.exceptions.CustomException;
import com.dkey.jwt.spring.backend.tutorial.security.entity.User;
import com.dkey.jwt.spring.backend.tutorial.security.repository.UserRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	
	@Autowired
    JavaMailSender javaMailSender;

    @Autowired
    TemplateEngine templateEngine;
    
    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${spring.mail.username}")
    private String mailFrom;
    
    @Autowired
    private UserRepository userRepository;
    
    @Value("${mail.urlFront}")
    private String urlFront;
    
    private static final String subject = "Recover password";
    
    public Message sendEmailTemplate(EmailValuesDto dto) {
    	Optional<User> userOpt = userRepository.findByUsernameOrEmail(dto.getMailTo(), dto.getMailTo());
    	if(!userOpt.isPresent())
    		throw new CustomException(HttpStatus.NOT_FOUND, "user not exists");
    	User user = userOpt.get();
    	dto.setMailFrom(mailFrom);
    	dto.setMailTo(user.getEmail());
    	dto.setSubject(subject);
    	dto.setUsername(user.getUsername());
    	UUID uuid = UUID.randomUUID();
    	String tokenPassword = uuid.toString();
    	dto.setTokenPassword(tokenPassword);
    	user.setTokenPassword(tokenPassword);
    	userRepository.save(user);
    	sendEmail(dto);
    	return new Message("Message was send");
    	
    }
    public void sendEmail(EmailValuesDto dto) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            Context context = new Context();
            Map<String, Object> model = new HashMap<>();
            model.put("username", dto.getUsername());
            model.put("url", urlFront + dto.getTokenPassword());
            context.setVariables(model);
            String htmlText = templateEngine.process("email-template", context);
            helper.setFrom(dto.getMailFrom());
            helper.setTo(dto.getMailTo());
            helper.setSubject(dto.getSubject());
            helper.setText(htmlText, true);

            javaMailSender.send(message);
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }
    
    public Message changePassword(ChangePasswordDto dto) {
    	if(!dto.getPassword().equals(dto.getConfirmPassword()))
    		throw new CustomException(HttpStatus.BAD_REQUEST, "Passwords not matches");
    	Optional<User> userOpt = userRepository.findByTokenPassword(dto.getTokenPassword());
    	if(!userOpt.isPresent()) {
    		throw new CustomException(HttpStatus.NOT_FOUND, "User not exists");
    	}
    	User user = userOpt.get();
    	String newPassword = passwordEncoder.encode(dto.getPassword());
    	user.setPassword(newPassword);
    	user.setTokenPassword(null);
    	userRepository.save(user);
    	return new Message("Password actualized");
    }
}

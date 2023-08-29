package com.dkey.jwt.spring.backend.tutorial.security.dto;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewUserDto {
	@NotBlank(message="name is required")
    private String name;
	@NotBlank(message="username is required")
    private String username;
    @Email(message="email is invalid")
    @NotBlank(message="email is required")
    private String email;
    @NotBlank(message="password is required")
    private String password;
    private Set<String> roles = new HashSet<>();
}

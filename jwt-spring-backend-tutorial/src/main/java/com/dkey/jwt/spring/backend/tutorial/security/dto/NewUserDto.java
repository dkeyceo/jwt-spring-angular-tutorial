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
	@NotBlank
    private String name;
    @NotBlank
    private String username;
    @Email
    private String email;
    @NotBlank
    private String password;
    private Set<String> roles = new HashSet<>();
}

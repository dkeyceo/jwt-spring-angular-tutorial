package com.dkey.jwt.spring.backend.tutorial.security.dto;



import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserDto {
	@NotBlank(message = "Username or email is required")
    private String username;
    @NotBlank(message = "Password is required")
    private String password;
}

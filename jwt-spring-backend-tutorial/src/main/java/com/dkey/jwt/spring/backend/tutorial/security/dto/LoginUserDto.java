package com.dkey.jwt.spring.backend.tutorial.security.dto;



import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserDto {
	@NotBlank
    private String username;
    @NotBlank
    private String password;
}

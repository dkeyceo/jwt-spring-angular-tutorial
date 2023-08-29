package com.dkey.jwt.spring.backend.tutorial.accountrecovery.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordDto {
	
	@NotBlank(message="Password is required")
	private String password;
	@NotBlank(message="repeat password please")
	private String confirmPassword;
	@NotBlank(message="token is required")
	private String tokenPassword;
}

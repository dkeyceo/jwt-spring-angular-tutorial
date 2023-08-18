package com.dkey.jwt.spring.backend.tutorial.accountrecovery.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordDto {
	
	@NotBlank
	private String password;
	@NotBlank
	private String confirmPassword;
	@NotBlank
	private String tokenPassword;
}

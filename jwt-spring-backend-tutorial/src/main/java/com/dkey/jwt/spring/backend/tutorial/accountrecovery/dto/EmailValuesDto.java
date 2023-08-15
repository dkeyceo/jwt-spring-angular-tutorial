package com.dkey.jwt.spring.backend.tutorial.accountrecovery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailValuesDto {
	
	private String mailFrom;
	private String mailTo;
	private String subject;
	private String username;
	private String tokenPassword;
		
}

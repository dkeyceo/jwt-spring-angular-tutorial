package com.dkey.jwt.spring.backend.tutorial.exceptions;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomException extends RuntimeException {
	
	private HttpStatus status;
	
	public CustomException(HttpStatus status, String message) {
		super(message);
		this.status = status;	
	}	
}

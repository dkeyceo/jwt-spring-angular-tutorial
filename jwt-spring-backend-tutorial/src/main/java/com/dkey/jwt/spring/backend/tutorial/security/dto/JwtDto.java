package com.dkey.jwt.spring.backend.tutorial.security.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtDto {
	 private String token;
	    private String bearer = "Bearer";
	    private String username;
	    private Collection<? extends GrantedAuthority> authorities;
		
	    public JwtDto(String token, String username, Collection<? extends GrantedAuthority> authorities) {
			this.token = token;
			this.username = username;
			this.authorities = authorities;
		}
	    
	    
}

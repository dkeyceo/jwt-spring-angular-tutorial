package com.dkey.jwt.spring.backend.tutorial.accountrecovery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class EmailValuesDto {
	
	private String mailFrom;
	private String mailTo;
	private String subject;
	private String username;
	private String tokenPassword;
	
	
	public EmailValuesDto(String mailFrom, String mailTo, String subject, String username, String tokenPassword) {
		super();
		this.mailFrom = mailFrom;
		this.mailTo = mailTo;
		this.subject = subject;
		this.username = username;
		this.tokenPassword = tokenPassword;
	}
	public String getMailFrom() {
		return mailFrom;
	}
	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}
	public String getMailTo() {
		return mailTo;
	}
	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTokenPassword() {
		return tokenPassword;
	}
	public void setTokenPassword(String tokenPassword) {
		this.tokenPassword = tokenPassword;
	}
	
	
	
	
}

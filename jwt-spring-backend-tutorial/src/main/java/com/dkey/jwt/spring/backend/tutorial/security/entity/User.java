package com.dkey.jwt.spring.backend.tutorial.security.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private String name;
	@NotNull
	@Column(unique = true)
	private String username;
	@NotNull
	private String email;
	@NotNull
	private String password;
	private String tokenPassword;
	
	@NotNull
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="user_role", joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="role_id"))
	private Set<Role> roles = new HashSet<>();
	
	public User(@NotNull String name, @NotNull String username, @NotNull String email, @NotNull String password) {
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
	}
}

package com.dkey.jwt.spring.backend.tutorial.security.entity;



import com.dkey.jwt.spring.backend.tutorial.security.enums.RoleName;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	@Enumerated
	private RoleName roleName;
	
	public Role(@NotNull RoleName roleName) {
		this.roleName = roleName;
	}
}

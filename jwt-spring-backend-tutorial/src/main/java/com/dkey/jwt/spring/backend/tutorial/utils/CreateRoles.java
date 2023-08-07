package com.dkey.jwt.spring.backend.tutorial.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.dkey.jwt.spring.backend.tutorial.security.entity.Role;
import com.dkey.jwt.spring.backend.tutorial.security.enums.RoleName;
import com.dkey.jwt.spring.backend.tutorial.security.service.RoleService;

@Component
public class CreateRoles implements CommandLineRunner {

	@Autowired
	RoleService roleService;
	
	@Override
	public void run(String... args) throws Exception {
		/*
		Role roleAdmin = new Role(RoleName.ROLE_ADMIN);
        Role roleUser = new Role(RoleName.ROLE_USER);
        roleService.save(roleAdmin);
        roleService.save(roleUser);
        */
	}

}

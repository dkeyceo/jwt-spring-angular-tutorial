package com.dkey.jwt.spring.backend.tutorial.security.service;

import java.lang.StackWalker.Option;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dkey.jwt.spring.backend.tutorial.security.entity.Role;
import com.dkey.jwt.spring.backend.tutorial.security.enums.RoleName;
import com.dkey.jwt.spring.backend.tutorial.security.repository.RoleRepository;

@Service
@Transactional
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	public Optional<Role> getByRoleName(RoleName roleName){
		return roleRepository.findByRoleName(roleName);
	}
}

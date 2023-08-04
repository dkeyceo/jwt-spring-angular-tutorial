package com.dkey.jwt.spring.backend.tutorial.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dkey.jwt.spring.backend.tutorial.security.entity.Role;
import com.dkey.jwt.spring.backend.tutorial.security.enums.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	Optional<Role> findByRoleName(RoleName roleName);
}

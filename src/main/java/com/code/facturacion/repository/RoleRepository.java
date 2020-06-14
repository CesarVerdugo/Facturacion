package com.code.facturacion.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code.facturacion.entity.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
 
	Optional<Role> findByname(String name);
}

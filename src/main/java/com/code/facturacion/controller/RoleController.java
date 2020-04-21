package com.code.facturacion.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.facturacion.entity.Role;
import com.code.facturacion.repository.RoleRepository;

@RestController
@RequestMapping("/roles")
public class RoleController {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@GetMapping
	public Page<Role> getRoles(Pageable pageable){
		return roleRepository.findAll(pageable);
	}
	
	
	@PostMapping
	public Role createRole(@Valid @RequestBody Role role){
		return roleRepository.save(role);
	}
}

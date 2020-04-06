package com.code.facturacion.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.facturacion.entity.Role;
import com.code.facturacion.repository.RoleRepository;

@RestController
public class RoleController {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@GetMapping("/roles")
	public Page<Role> getRoles(Pageable pageable){
		return roleRepository.findAll(pageable);
	}

}

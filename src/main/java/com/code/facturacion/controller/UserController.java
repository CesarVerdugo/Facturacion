package com.code.facturacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.facturacion.entity.User;
import com.code.facturacion.repository.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
//	public UserController(UserRepository userRepository) {
//		this.userRepository= userRepository;
//	}

	@GetMapping("/users")
	public List<User> getUsers(){
		return userRepository.findAll();
	}
	
}

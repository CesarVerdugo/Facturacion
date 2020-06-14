package com.code.facturacion.controller;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.code.facturacion.entity.Role;
import com.code.facturacion.entity.User;
import com.code.facturacion.exception.DocumentNumberAlreadyUsedException;
import com.code.facturacion.exception.EmailAlreadyUsedException;
import com.code.facturacion.repository.RoleRepository;
import com.code.facturacion.repository.UserRepository;
import com.code.facturacion.security.TokenProvider;

@RestController
@RequestMapping("account")
public class AccountController {

	@Autowired
	private TokenProvider  tokenProvider;
	
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private AuthenticationManagerBuilder authenticationManagerBuilder;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestParam("email") String email, @RequestParam("password") String password){
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email,password);
		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = tokenProvider.generateToken(authentication);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", "Bearer " + jwt);
		
		HashMap<String, String> jwtHashMap = new HashMap<String, String>();
		jwtHashMap.put("Token", jwt);
		
		return new ResponseEntity<Object>(jwtHashMap, httpHeaders, HttpStatus.OK);
	}
	
 //	hacer registro y recuperar contraseña
	
	@PostMapping("/register")
	public ResponseEntity<Object> register(@Valid @RequestBody User user){
//		userRepository.findOneByEmailIgnoreCase(user.getEmail())
//			.ifPresent(foundUser->new EmailAlreadyUsedException(user.getEmail()));
//		userRepository.findOneByDocumentNumber(user.getDocumentNumber())
//			.ifPresent(foundUser-> new DocumentNumberAlreadyUsedException(user.getDocumentNumber()));
		Optional<User> optionalUser = userRepository.findOneByEmailIgnoreCase(user.getEmail()); 
		if (optionalUser.isPresent()) {
			
			throw new EmailAlreadyUsedException();
			
		}
		
		optionalUser = userRepository.findOneByDocumentNumber(user.getDocumentNumber());
		if (optionalUser.isPresent()) {
			
			throw new DocumentNumberAlreadyUsedException();
			
		}
		
		Set<Role> roles = new HashSet<>(); 
		roles.add(roleRepository.findByname("USER").get());
		user.setRoles(roles);
		
		user.setActive(true);
		user.setEliminated(false);
//		String encryptedPassword = passwordEncoder.encode(user.getPassword());
//		user.setPassword(encryptedPassword);
		
		 User userSaved = userRepository.save(user);
		
		return ResponseEntity.created(null).body(userSaved);
	}
	
}

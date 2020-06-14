package com.code.facturacion.security;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.code.facturacion.entity.Role;
import com.code.facturacion.entity.User;
import com.code.facturacion.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = userRepository.findOneByEmailIgnoreCase(email)
				.orElseThrow(()->new UsernameNotFoundException(email));
		
		return new org.springframework.security.core.userdetails.User(
				user.getEmail(),
				user.getPassword(),
				this.getAuthorities(user)
		);
	}
	
	private List<? extends GrantedAuthority> getAuthorities(User usuario) {
		Set<Role> roles = usuario.getRoles();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));
		return authorities;
	}
}

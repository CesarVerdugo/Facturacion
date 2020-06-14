package com.code.facturacion.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.code.facturacion.security.JwtAuthotizationFilter;
import com.code.facturacion.security.TokenProvider;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private TokenProvider tokenProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors()
		.and()
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/account/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.addFilter(new JwtAuthotizationFilter(authenticationManager(), tokenProvider))
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	
}

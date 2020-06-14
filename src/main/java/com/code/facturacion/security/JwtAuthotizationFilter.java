package com.code.facturacion.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

public class JwtAuthotizationFilter extends BasicAuthenticationFilter{

	
	private TokenProvider tokenProvider;
	
	public JwtAuthotizationFilter(AuthenticationManager authenticationManager,TokenProvider tokenProvider) {
		super(authenticationManager);
		this.tokenProvider = tokenProvider;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String token = request.getHeader("Authorization");
		if(StringUtils.hasText(token) && token.startsWith("Bearer ")) {
			token = token.substring(7);
			System.out.println("TOKN " +token);
			if(StringUtils.hasText(token) && tokenProvider.validateToken(token)) {
				
				Authentication authentication = tokenProvider.getAuthentication(token);
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
			}
			
		}
		chain.doFilter(request, response);
	}
}

package com.code.facturacion.security;


import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.code.facturacion.configuration.ApplicationProperties;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenProvider {

	private final Logger log = LoggerFactory.getLogger(TokenProvider.class);
	private static final String AUTHORITIES_KEY = "auth"; 
	
	@Autowired
	private ApplicationProperties properties;
	private SecretKey key;
	private Date expirateDate;
	
	public TokenProvider() {
//		SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//		String secretString = Encoders.BASE64.encode(key.getEncoded());
//		System.out.println("clave " + secretString);
	}
	
	@PostConstruct
	public void init() {
		/* crear una arreglo de bits para posteriormente convertirla en una clave encriptada */
		byte[] keyBytes = properties.getJWT().getBase64Secret().getBytes(StandardCharsets.UTF_8);
		// toma el arreglo de bits y encripta 
		this.key = Keys.hmacShaKeyFor(keyBytes);
		// obtiene la fecha de expiracion 
		this.expirateDate = new Date(System.currentTimeMillis() + properties.getJWT().getExpirationInSeconds() * 1000);
	}
	
	public String generateToken(Authentication authentication) {	
		
		// toma el arrego de roles que tiene un usuario y los concatena en una cadena de texto separandolos por una coma
		String authorities = authentication.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));
		// construye un objeto JWTS con el nombre del usuario, la cadena de roles separados por comas, la clave secreta encriptada y la fecha de expiracion 
		return Jwts.builder()
				.setSubject(authentication.getName())
				.claim(AUTHORITIES_KEY, authorities)
				.signWith(key, SignatureAlgorithm.HS256)
				.setExpiration(expirateDate)
				.compact();
	}
	
	public UsernamePasswordAuthenticationToken getAuthentication(String token) {
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();

		Collection<? extends GrantedAuthority> authorities =
				Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
						.map(SimpleGrantedAuthority::new)
						.collect(Collectors.toList());

		return new UsernamePasswordAuthenticationToken(claims.getSubject(), token, authorities);
	}
	
	public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.info("Invalid JWT token.");
            log.trace("Invalid JWT token trace.", e);
        }
        return false;
    }
	
}

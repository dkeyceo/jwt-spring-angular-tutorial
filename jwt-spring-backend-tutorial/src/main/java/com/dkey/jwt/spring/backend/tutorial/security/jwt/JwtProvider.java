package com.dkey.jwt.spring.backend.tutorial.security.jwt;

import java.security.Key;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.dkey.jwt.spring.backend.tutorial.security.dto.JwtDto;
import com.dkey.jwt.spring.backend.tutorial.security.entity.UserPrincipal;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtProvider {

	@Value("${jwt.secret}")
	private String secret;
	@Value("${jwt.expiration}")
	private int expiration;

	public String generateToken(Authentication authentication) {
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		List<String> roles = userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());
		return Jwts.builder().setSubject(userPrincipal.getUsername()).claim("roles", roles).setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + expiration))
				.signWith(getSecret(secret)).compact();
	}

	public String getUsernameFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(getSecret(secret)).build().parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(getSecret(secret)).build().parseClaimsJws(token);
			return true;
		} catch (MalformedJwtException e) {
			log.error("token has wrong format");
		} catch (UnsupportedJwtException e) {
			log.error("token no supported");
		} catch (ExpiredJwtException e) {
			log.error("token expired");
		} catch (IllegalArgumentException e) {
			log.error("token is empty");
		} catch (SignatureException e) {
			log.error("an error during sign");
		}
		return false;
	}

	public String refreshToken(JwtDto jwtDto) throws ParseException {
		try {
			Jwts.parserBuilder().setSigningKey(getSecret(secret)).build().parseClaimsJws(jwtDto.getToken());
		} catch (ExpiredJwtException e) {
			JWT jwt = JWTParser.parse(jwtDto.getToken());
			JWTClaimsSet claims = jwt.getJWTClaimsSet();
			String nombreUsuario = claims.getSubject();
			List<String> roles = (List<String>) claims.getClaim("roles");

			return Jwts.builder().setSubject(nombreUsuario).claim("roles", roles).setIssuedAt(new Date())
					.setExpiration(new Date(new Date().getTime() + expiration))
					.signWith(getSecret(secret)).compact();
		}
		return null;
	}
	
	private Key getSecret(String secret){
        byte[] secretBytes = Decoders.BASE64URL.decode(secret);
        return Keys.hmacShaKeyFor(secretBytes);
    }

}

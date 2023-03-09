package com.smartpay.security.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.smartpay.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtils {

	@Value("${jwt.key}")
	private String jwtKey;

	public String generateAccessToken(User user) {
		SecretKey key = Keys.hmacShaKeyFor(jwtKey.getBytes(StandardCharsets.UTF_8));
		
		return Jwts.builder().setSubject(String.format("%s,%s", user.getUserIdentificationNo(), user.getUsername()))
				.setIssuer("SmartPay").setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 90000)).signWith(key)
				.compact();

	}
}

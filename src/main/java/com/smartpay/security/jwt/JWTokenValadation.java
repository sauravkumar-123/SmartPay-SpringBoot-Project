//package com.smartpay.security.jwt;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.MalformedJwtException;
//import io.jsonwebtoken.UnsupportedJwtException;
//import java.security.SignatureException;
//import lombok.extern.slf4j.Slf4j;
//
//@Component
//@Slf4j
//public class JWTokenValadation {
//
//	@Value("${jwt.key}")
//	private String jwtKey;
//
//	private static final Logger LOGGER = LoggerFactory.getLogger(JWTokenValadation.class);
//
//	public boolean validateAccessToken(String token) {
//		log.info("Enter into JWT Token Valadation");
//		try {
//			Jwts.parser().setSigningKey(jwtKey).parseClaimsJws(token);
//			return true;
//		} catch (ExpiredJwtException ex) {
//			LOGGER.error("JWT expired", ex.getMessage());
//		} catch (IllegalArgumentException ex) {
//			LOGGER.error("Token is null, empty or only whitespace", ex.getMessage());
//		} catch (MalformedJwtException ex) {
//			LOGGER.error("JWT is invalid", ex);
//		} catch (UnsupportedJwtException ex) {
//			LOGGER.error("JWT is not supported", ex);
//		} catch (SignatureException ex) {
//			LOGGER.error("Signature validation failed");
//		}
//
//		return false;
//	}
//
//	public String getSubject(String token) {
//		return parseClaims(token).getSubject();
//	}
//
//	private Claims parseClaims(String token) {
//		return Jwts.parser().setSigningKey(jwtKey).parseClaimsJws(token).getBody();
//	}
//}

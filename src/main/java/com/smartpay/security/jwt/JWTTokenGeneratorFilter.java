package com.smartpay.security.jwt;
//package com.Smartpay.Security;
//
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.util.Collection;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.Set;
//
//import javax.crypto.SecretKey;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.Smartpay.Constants.Constant;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//
//@Component
//public class JWTTokenGeneratorFilter extends OncePerRequestFilter {
//
////	@Value("${jwt.key}")
////	private String jwtKey;
////
////	@Value("${jwt.header}")
////	private String jwtheader;
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//		if (null != authentication) {
//			SecretKey key = Keys.hmacShaKeyFor(Constant.JWTKEY.getBytes(StandardCharsets.UTF_8));
//
//			String jwt = Jwts.builder().setIssuer("SmartPay").setSubject("JWT Token")
//					.claim("username", authentication.getName())
//					.claim("authorities", populateAuthorities(authentication.getAuthorities())).setIssuedAt(new Date())
//					.setExpiration(new Date((new Date()).getTime() + 80000)).signWith(key).compact();
//			response.setHeader(Constant.JWTHEADER, jwt);
//		}
//		filterChain.doFilter(request, response);
//	}
//
//	@Override
//	protected boolean shouldNotFilter(HttpServletRequest request) {
//		return !request.getServletPath().equals("/v1/auth/**");
//	}
//
//	private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
//		Set<String> authoritiesSet = new HashSet<>();
//		for (GrantedAuthority authority : collection) {
//			authoritiesSet.add(authority.getAuthority());
//		}
//		return String.join(",", authoritiesSet);
//	}
//
//}

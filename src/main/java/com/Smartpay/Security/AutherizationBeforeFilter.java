//package com.Smartpay.Security;
//
//import static org.springframework.http.HttpHeaders.AUTHORIZATION;
//
//import java.io.IOException;
//import java.nio.charset.Charset;
//import java.nio.charset.StandardCharsets;
//import java.util.Base64;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.GenericFilterBean;
//
//@Component
//public class AutherizationBeforeFilter extends GenericFilterBean {
//
//	private static final Logger logger = LoggerFactory.getLogger(AutherizationBeforeFilter.class);
//
//	public static final String AUTHENTICATION_SCHEME_BASIC = "Basic";
//	private Charset credentialsCharset = StandardCharsets.UTF_8;
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		HttpServletRequest req = (HttpServletRequest) request;
//		HttpServletResponse res = (HttpServletResponse) response;
//		String header = req.getHeader(AUTHORIZATION);
//		if (header != null) {
//			header = header.trim();
//			if (StringUtils.startsWithIgnoreCase(header, AUTHENTICATION_SCHEME_BASIC)) {
//				byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
//				byte[] decoded;
//				try {
//					decoded = Base64.getDecoder().decode(base64Token);
//					String token = new String(decoded, getCredentialsCharset(req));
//					int delim = token.indexOf(":");
//					if (delim == -1) {
//						throw new BadCredentialsException("Invalid basic authentication token");
//					} else {
//						String[] tokenArr = token.split(":");
//						String username = tokenArr[0];
//						String password = tokenArr[1];
//						if (org.apache.commons.lang.StringUtils.isEmpty(username)
//								|| org.apache.commons.lang.StringUtils.isEmpty(password)) {
//							res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//							return;
//						} else {
//							SecurityContextHolder.getContext()
//									.setAuthentication(new UsernamePasswordAuthenticationToken(username, password));
//						}
//					}
//				} catch (IllegalArgumentException e) {
//					throw new BadCredentialsException("Failed to decode basic authentication token");
//				}
//			}
//		}
//		chain.doFilter(request, response);
//
//	}
//
//	protected Charset getCredentialsCharset(HttpServletRequest request) {
//		return getCredentialsCharset();
//	}
//
//	public Charset getCredentialsCharset() {
//		return this.credentialsCharset;
//	}
//
//}

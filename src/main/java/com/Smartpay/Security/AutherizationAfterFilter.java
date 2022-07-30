package com.Smartpay.Security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AutherizationAfterFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(AutherizationAfterFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (null != authentication) {
			logger.info(authentication.getName() + " is succesfully Authenciated");
		}

		chain.doFilter(request, response);
	}

}

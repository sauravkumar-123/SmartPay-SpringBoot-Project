package com.Smartpay.Security;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.BadRequestException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;

import com.Smartpay.DAO.UserRepository;
import com.Smartpay.Model.User;
import com.Smartpay.Response.TwoFactorResponse;
import com.Smartpay.Utility.Utility;

public class AutherizationAtFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(AutherizationAtFilter.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		Principal principal = req.getUserPrincipal();

		if (null != principal) {
			String username = principal.getName();
			User user = userRepository.findUserByUsername(username);
			if (null != user) {
				TwoFactorResponse twoFactorResponse = Utility.sendLoginOTP(user.getMobileno());
				res.setStatus(HttpServletResponse.SC_OK, twoFactorResponse.getStatus());
			}
		} else {
			logger.info("User Not Authenciated");
			throw new BadCredentialsException("User Not Authenciated");
		}
		chain.doFilter(request, response);
	}

}

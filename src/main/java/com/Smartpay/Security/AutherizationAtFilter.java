//package com.Smartpay.Security;
//
//import java.io.IOException;
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
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.GenericFilterBean;
//
//import com.Smartpay.DAO.UserRepository;
//import com.Smartpay.Model.User;
//import com.Smartpay.Response.TwoFactorResponse;
//import com.Smartpay.Utility.Utility;
//
//@Component
//public class AutherizationAtFilter extends GenericFilterBean {
//
//	private static final Logger logger = LoggerFactory.getLogger(AutherizationAtFilter.class);
//
//	@Autowired
//	private UserRepository userRepository;
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//
//		logger.info("User Authenciation in progress..");
//		HttpServletRequest req = (HttpServletRequest) request;
//		HttpServletResponse res = (HttpServletResponse) response;
//
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//		if (null != authentication) {
//			logger.info("User Authencation in progress..");
////			String username = authentication.getName();
////			User user = userRepository.findUserByUsername(username);
////			if (null != user) {
////				TwoFactorResponse twoFactorResponse = Utility.sendLoginOTP(user.getMobileno());
////				res.setStatus(HttpServletResponse.SC_OK, twoFactorResponse.getStatus());
////			}
//		} else {
//			logger.info("User Not Authenciated");
//			throw new BadCredentialsException("User Not Authenciated");
//		}
//		chain.doFilter(request, response);
//	}
//
//}

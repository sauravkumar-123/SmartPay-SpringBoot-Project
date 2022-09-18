package com.Smartpay.Security.Filter;
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
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.GenericFilterBean;
//
//@Component
//public class AutherizationAfterFilter extends GenericFilterBean {
//
//	private static final Logger logger = LoggerFactory.getLogger(AutherizationAfterFilter.class);
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//
//		HttpServletRequest req = (HttpServletRequest) request;
//		HttpServletResponse res = (HttpServletResponse) response;
//
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//		if (null != authentication) {
//			logger.info(authentication.getName() + "is Successfully Authenciated..");
////			res.setStatus(HttpServletResponse.SC_OK);
//		}
//
//		chain.doFilter(request, response);
//	}
//
//}

//package com.smartpay.security.jwt;
//
//import java.io.IOException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.util.ObjectUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.smartpay.model.User;
//import com.smartpay.security.UserSecurity;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@Component
//public class JWTTokenFilter extends OncePerRequestFilter {
//
//    @Autowired
//    //@Qualifier("jwtValid")
//    private JWTokenValadation jwTokenValadation;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//
//        if (!hasAuthorizationBearer(request)) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        String token = getAccessToken(request);
//
//        if (!jwTokenValadation.validateAccessToken(token)) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        setAuthenticationContext(token, request);
//        filterChain.doFilter(request, response);
//    }
//
//    private boolean hasAuthorizationBearer(HttpServletRequest request) {
//        String header = request.getHeader("Authorization");
//        if (ObjectUtils.isEmpty(header) || !header.startsWith("Bearer")) {
//            return false;
//        }
//
//        return true;
//    }
//
//    private String getAccessToken(HttpServletRequest request) {
//        String header = request.getHeader("Authorization");
//        String token = header.split(" ")[1].trim();
//        return token;
//    }
//
//    private void setAuthenticationContext(String token, HttpServletRequest request) {
//        UserDetails userDetails = getUserDetails(token);
//
//        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
//                null);
//
//        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//    }
//
//    private UserDetails getUserDetails(String token) {
//        User user = new User();
//        String[] jwtSubject = jwTokenValadation.getSubject(token).split(",");
//
//        // userDetails.setId(Integer.parseInt(jwtSubject[0]));
////		user.setUserIdentificationNo(Long.parseLong(jwtSubject[0]));
//        user.setUsername(jwtSubject[1]);
//        UserSecurity userDetails = new UserSecurity(user);
//        return userDetails;
//    }
//
//}

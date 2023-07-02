package com.smartpay.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.smartpay.dao.UserRepository;
import com.smartpay.model.User;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SmartPayAuthencationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("Enter into SmartPayAuthencationProvider::authenticate()");
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = userRepository.findUserByUsername(userName);
        if (null != user) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(user.getRole()));
                return new UsernamePasswordAuthenticationToken(userName, password, authorities);
            } else {
                log.info("Authencation Failed!!!!..");
                throw new BadCredentialsException("Invalid Password!!");
            }
        } else {
            log.info("User Details Not Avaliable With Username:: ", userName);
            throw new BadCredentialsException("User Not Registred With Username: " + userName);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}

package com.Smartpay.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Smartpay.DAO.UserRepository;
import com.Smartpay.Model.User;

@Service
public class LoadUserDetails implements UserDetailsService {

	@Autowired
	private UserRepository UserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = UserRepository.findUserByUsername(username);
		if (null == user) {
			throw new UsernameNotFoundException("User Not Found With Username: " + username);
		}
		return new UserSecurity(user);
	}

}

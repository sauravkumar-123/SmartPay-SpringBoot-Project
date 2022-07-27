package com.Smartpay.Service;

import org.springframework.stereotype.Service;

import com.Smartpay.Model.User;

@Service
public interface UserService {
	public User registerUser(User user);
}

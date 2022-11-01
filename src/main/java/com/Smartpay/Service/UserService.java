package com.Smartpay.Service;

import org.springframework.stereotype.Service;

import com.Smartpay.Model.User;
import com.Smartpay.Response.RegistrationResponse;

@Service
public interface UserService {
	public RegistrationResponse registerUser(User user);
}

package com.Smartpay.Service;

import org.springframework.stereotype.Service;

import com.Smartpay.Model.User;
import com.Smartpay.Response.RegistrationResponse;

@Service
public interface AdminService {
	public RegistrationResponse registerAdmin(User user);
}

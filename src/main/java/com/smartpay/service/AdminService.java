package com.smartpay.service;

import org.springframework.stereotype.Service;

import com.smartpay.model.User;

@Service
public interface AdminService {
	public User registerAdmin(User user);
}

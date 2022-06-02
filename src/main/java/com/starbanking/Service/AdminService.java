package com.starbanking.Service;

import org.springframework.stereotype.Service;

import com.starbanking.Model.User;

@Service
public interface AdminService {
	public User registerAdmin(User user);
}

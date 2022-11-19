package com.Smartpay.DAO;

import org.springframework.stereotype.Repository;

import com.Smartpay.Model.MainWallet;
import com.Smartpay.Model.User;

@Repository
public interface UserRepository {

	public boolean saveUserDetails(User user, MainWallet mainWallet);

	public User getAdminDetails();

	public User getUserDetails(String emailId, String mobno);

	public User findUserByUsername(String username);

	public boolean updateUserDetails(User user);
}

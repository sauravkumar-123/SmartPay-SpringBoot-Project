package com.starbanking.DAO;

import org.springframework.stereotype.Repository;

import com.starbanking.Model.MainWallet;
import com.starbanking.Model.User;

@Repository
public interface UserRepository {

	public boolean saveUserDetails(User user, MainWallet mainWallet);

	public User getAdminDetails();

	public User getUserDetails(String emailId, String mobno, char isActive);

	public User findUserByUsername(String username);
}

package com.smartpay.dao;

import com.smartpay.enums.EnumsStatus;
import org.springframework.stereotype.Repository;

import com.smartpay.model.User;

@Repository
public interface UserRepository {

    public User saveUserDetails(User user);

    public User getAdminDetails();

    public User getUserDetailsByEmailOrMobno(String emailId, String mobno);

    public User findUserByUsername(String username);

    public void updateBankingServiceStatus(String userIdNo, EnumsStatus.YesNO status);

    public boolean updateUserLoginPassword(String userIdNo, String password);

}

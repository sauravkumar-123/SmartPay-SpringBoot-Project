package com.smartpay.serviceimpl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.smartpay.dao.RoleRepository;
import com.smartpay.dao.UserRepository;
import com.smartpay.enums.EnumsStatus.IsActive;
import com.smartpay.enums.EnumsStatus.UserRole;
import com.smartpay.enums.EnumsStatus.YesNO;
import com.smartpay.exception.GlobalException;
import com.smartpay.model.MainWallet;
import com.smartpay.model.Role;
import com.smartpay.model.User;
import com.smartpay.service.UserService;
import com.smartpay.utility.StringUtil;
import com.smartpay.utility.Utility;
import org.springframework.http.HttpStatus;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User registerUser(User user) {
        logger.info("Enter Into User Registration Service");
        User userData = userRepository.getUserDetailsByEmailOrMobno(user.getEmailId(), user.getMobileNo());
        if (null == userData) {
            Role merchantRole = roleRepository.findRoleByName(UserRole.MERCHANT.getRoleName());
            List<Role> roleList = Arrays.asList(merchantRole);
            User userRegistration = new User();
            userRegistration.setFirstName(user.getFirstName());
            userRegistration.setMiddleName(user.getMiddleName());
            userRegistration.setLastName(user.getLastName());
            userRegistration.setEmailId(user.getEmailId());
            userRegistration.setMobileNo(user.getMobileNo());
            userRegistration.setDateOfBirth(user.getDateOfBirth());
            userRegistration.setBankingServiceStatus(YesNO.NO);
            userRegistration.setIsActive(IsActive.ACTIVE);
            userRegistration.setRole(UserRole.MERCHANT.getRoleName());
            userRegistration.setRoles(roleList.stream().collect(Collectors.toSet()));
            userRegistration
                    .setPassword(passwordEncoder.encode(StringUtil.generateDefaultPassword(user.getFirstName())));
            userRegistration.setCustomerId((Utility.generateRandomfiveDigitNo()));
            userRegistration.setUsername("IR" + StringUtil.getLastSixDigitOfMobileNo(user.getMobileNo()));

            User parentDetails = userRepository.getAdminDetails();
            if (null != parentDetails) {
                userRegistration.setParentUsername(parentDetails.getUsername());
            }

            MainWallet mainWallet = new MainWallet();
            mainWallet.setUserName(userRegistration.getUsername());
            mainWallet.setCurrentBalance(BigDecimal.ZERO);
            mainWallet.setCommissionCredit(BigDecimal.ZERO);
            mainWallet.setCharges(BigDecimal.ZERO);
            mainWallet.setTds(BigDecimal.ZERO);
            mainWallet.setCreditAmount(BigDecimal.ZERO);
            mainWallet.setDebitAmount(BigDecimal.ZERO);
            mainWallet.setIsActive(IsActive.ACTIVE);
            mainWallet.setCreditType(null);
            mainWallet.setDebitType(null);
            mainWallet.setUser(userRegistration);

            userRegistration.setMainWallet(mainWallet);
            User savedUser = userRepository.saveUserDetails(userRegistration);
            return savedUser;
        } else {
            logger.error("User Registration Details Already Present....");
            throw new GlobalException("User Details Already Avaliable", HttpStatus.EXPECTATION_FAILED);
        }
    }
}

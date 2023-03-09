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
import com.smartpay.service.AdminService;
import com.smartpay.utility.StringUtil;
import com.smartpay.utility.Utility;

@Service
public class AdminServiceImpl implements AdminService {

	private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public User registerAdmin(User user) {
		logger.info("Enter into Admin Registration Service");
		User userData = userRepository.getUserDetailsByEmailOrMobno(user.getEmailId(), user.getMobileNo());
		if (null == userData) {
			Role adminRole = roleRepository.findRoleByName(UserRole.ADMIN.getRoleName());
			List<Role> roleList = Arrays.asList(adminRole);
			User userRegistration = new User();
			userRegistration.setFirstName(user.getFirstName());
			userRegistration.setMiddleName(user.getMiddleName());
			userRegistration.setLastName(user.getLastName());
			userRegistration.setEmailId(user.getEmailId());
			userRegistration.setMobileNo(user.getMobileNo());
			userRegistration.setDateOfBirth(user.getDateOfBirth());
			userRegistration.setBankingServiceStatus(YesNO.NO);
			userRegistration.setIsActive(IsActive.ACTIVE);
			userRegistration.setRole(UserRole.ADMIN.getRoleName());
			userRegistration.setRoles(roleList.stream().collect(Collectors.toSet()));
			userRegistration
					.setPassword(passwordEncoder.encode(StringUtil.generateDefaultPassword(user.getFirstName())));
			userRegistration.setCustomerId(Utility.generateRandomfiveDigitNo());
			userRegistration.setUsername("AD" + StringUtil.getLastSixDigitOfMobileNo(user.getMobileNo()));
			userRegistration.setParentUsername("Parent Details Not Avaliable!!");

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
			logger.error("Admin Registration Details Already Present....");
			throw new GlobalException("Admin Details Already Avaliable");
		}
	}
}

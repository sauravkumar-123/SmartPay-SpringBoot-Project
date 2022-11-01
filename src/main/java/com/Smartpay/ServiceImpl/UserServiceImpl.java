package com.Smartpay.ServiceImpl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Smartpay.DAO.RoleRepository;
import com.Smartpay.DAO.UserRepository;
import com.Smartpay.Enum.EnumsStatus.IsActive;
import com.Smartpay.Enum.EnumsStatus.UserRole;
import com.Smartpay.Enum.EnumsStatus.YesNO;
import com.Smartpay.Exception.GlobalException;
import com.Smartpay.Model.MainWallet;
import com.Smartpay.Model.Role;
import com.Smartpay.Model.User;
import com.Smartpay.Response.RegistrationResponse;
import com.Smartpay.Response.TwoFactorResponse;
import com.Smartpay.Service.UserService;
import com.Smartpay.Utility.StringUtil;
import com.Smartpay.Utility.Utility;

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
	public RegistrationResponse registerUser(User user) {
		logger.info("Enter Into User Registration Service");
		User userData = userRepository.getUserDetails(user.getEmailId(), user.getMobileNo());
		if (null == userData) {
			Role merchantRole = roleRepository.findRoleByName(UserRole.MERCHANT.getRoleName());
			List<Role> roleList = Arrays.asList(merchantRole);
			User userRegistration = new User();
			userRegistration.setApplicantName(user.getApplicantName());
			userRegistration.setEmailId(user.getEmailId());
			userRegistration.setMobileNo(user.getMobileNo());
			userRegistration.setDateOfBirth(user.getDateOfBirth());
			userRegistration.setBankingServiceStatus(YesNO.NO);
			userRegistration.setIsActive(IsActive.ACTIVE);
			userRegistration.setRole(UserRole.MERCHANT.getRoleName());
			userRegistration.setRoles(roleList.stream().collect(Collectors.toSet()));
			userRegistration
					.setPassword(passwordEncoder.encode(StringUtil.generateDefaultPassword(user.getApplicantName())));
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
			boolean saveStatus = userRepository.saveUserDetails(userRegistration, mainWallet);
			if (saveStatus) {
				TwoFactorResponse twofactorResponse = Utility.sendLoginDetailsToUserMobno(
						userRegistration.getApplicantName(), userRegistration.getMobileNo(),
						userRegistration.getUsername());
				logger.info("User and Mainwallet Details{}" + userRegistration + " {} " + mainWallet);
				logger.info("Send LoginDetails To UserMobno " + twofactorResponse);
				RegistrationResponse response = new RegistrationResponse();
				response.setApplicantName(userRegistration.getApplicantName());
				response.setBankingServiceStatus(userRegistration.getBankingServiceStatus());
				response.setCustomerId(userRegistration.getCustomerId());
				response.setDateOfBirth(userRegistration.getDateOfBirth());
				response.setIsActive(userRegistration.getIsActive());
				response.setParentUsername(userRegistration.getParentUsername());
				response.setRole(userRegistration.getRole());
				response.setUsername(userRegistration.getUsername());
				return response;
			} else {
				return null;
			}
		} else {
			logger.debug("User Registration Details Already Present....");
			throw new GlobalException("User Details Already Avaliable");
		}
	}
}

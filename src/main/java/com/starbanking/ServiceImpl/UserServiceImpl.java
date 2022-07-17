package com.starbanking.ServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.starbanking.DAO.RoleRepository;
import com.starbanking.DAO.UserRepository;
import com.starbanking.Enum.EnumsStatus.UserRole;
import com.starbanking.Enum.EnumsStatus.YesNO;
import com.starbanking.Exception.GlobalException;
import com.starbanking.Model.MainWallet;
import com.starbanking.Model.Role;
import com.starbanking.Model.User;
import com.starbanking.Service.UserService;
import com.starbanking.Utility.StringUtil;
import com.starbanking.Utility.Utility;

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
		User userData = userRepository.getUserDetails(user.getEmailid(), user.getMobileno(), 'Y');
		if (null == userData) {
			Role merchantRole = roleRepository.findRoleByName(UserRole.MERCHANT.getRoleName());
			List<Role> roleList = Arrays.asList(merchantRole);
			User userRegistration = new User();
			userRegistration.setApplicantName(user.getApplicantName());
			userRegistration.setEmailid(user.getEmailid());
			userRegistration.setMobileno(user.getMobileno());
			userRegistration.setDateOfBirth(user.getDateOfBirth());
			userRegistration.setBankingServiceStatus(YesNO.NO);
			userRegistration.setIsActive('Y');
			userRegistration.setRole(UserRole.MERCHANT.getRoleName());
			userRegistration.setRoles(roleList.stream().collect(Collectors.toSet()));
			userRegistration
					.setPassword(passwordEncoder.encode(StringUtil.generateDefaultPassword(user.getApplicantName())));
			userRegistration.setUserIdentificationNo(Utility.generateRandomfiveDigitNo());
			userRegistration.setUsername("IR" + StringUtil.getLastSixDigitOfMobileNo(user.getMobileno()));

			User parentDetails = userRepository.getAdminDetails();
			if (null != parentDetails) {
				userRegistration.setParentUsername(parentDetails.getUsername());
			}

			MainWallet mainWallet = new MainWallet();
			mainWallet.setUserName(userRegistration.getUsername());
			mainWallet.setCurrentBalance(0.00);
			mainWallet.setCommissionCredit(0.00);
			mainWallet.setCharges(0.00);
			mainWallet.setTds(0.00);
			mainWallet.setCreditAmount(0.00);
			mainWallet.setDebitAmount(0.00);
			mainWallet.setIsActive('Y');
			mainWallet.setCreditType(null);
			mainWallet.setDebitType(null);
			boolean Savestatus = userRepository.saveUserDetails(userRegistration, mainWallet);
			if (Savestatus) {
				logger.info("User and Mainwallet Details{}" + userRegistration + " {} " + mainWallet);
				return userRegistration;
			} else {
				return null;
			}
		} else {
			throw new GlobalException("User Details Already Avaliable");
		}
	}
}

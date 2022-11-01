package com.Smartpay.MockTest;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.Smartpay.Base.BaseTest;
import com.Smartpay.DAO.RoleRepository;
import com.Smartpay.DAO.UserRepository;
import com.Smartpay.Enum.EnumsStatus.IsActive;
import com.Smartpay.Enum.EnumsStatus.UserRole;
import com.Smartpay.Enum.EnumsStatus.YesNO;
import com.Smartpay.Model.MainWallet;
import com.Smartpay.Model.Role;
import com.Smartpay.Model.User;
import com.Smartpay.Response.RegistrationResponse;
import com.Smartpay.ServiceImpl.UserServiceImpl;
import com.Smartpay.Utility.StringUtil;
import com.Smartpay.Utility.Utility;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServicesMockTest extends BaseTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImpl userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

	@DisplayName("Test UserRegistration Service")
	@Test
	public void testUserRegistration() {
		logger.info("testRegisterUser");
		given(userRepository.getAdminDetails()).willReturn(new User());
		User userRegistration = new User();
		Role merchantRole = roleRepository.findRoleByName(UserRole.MERCHANT.getRoleName());
		List<Role> roleList = Arrays.asList(merchantRole);
		userRegistration.setApplicantName("Nichal Kumar Singh");
		userRegistration.setEmailId("nichal.kumar@gmail.com");
		userRegistration.setMobileNo("6521004563");
		userRegistration.setDateOfBirth(Utility.convertStringToDate("1975-03-16"));
		userRegistration.setBankingServiceStatus(YesNO.NO);
		userRegistration.setIsActive(IsActive.ACTIVE);
		userRegistration.setRole(UserRole.MERCHANT.getRoleName());
		userRegistration.setRoles(roleList.stream().collect(Collectors.toSet()));
		userRegistration.setPassword(
				passwordEncoder.encode(StringUtil.generateDefaultPassword(userRegistration.getApplicantName())));
		userRegistration.setCustomerId((Utility.generateRandomfiveDigitNo()));
		userRegistration.setUsername("IR" + StringUtil.getLastSixDigitOfMobileNo(userRegistration.getMobileNo()));

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
		when(userRepository.getUserDetails("nichal.kumar@gmail.com", "6521004563")).thenReturn(userRegistration);
		// when(userRepository.saveUserDetails(userRegistration,
		// mainWallet)).thenReturn(Boolean.TRUE);

		verify(userRepository, times(1)).getAdminDetails();
		verify(userRepository, times(1)).getUserDetails("nichal.kumar@gmail.com", "6521004563");
		RegistrationResponse result = userService.registerUser(userRegistration);
		Assertions.assertNotNull(result);
	}
}

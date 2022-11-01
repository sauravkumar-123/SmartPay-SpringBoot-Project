package com.Smartpay.DAOImpl;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
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
import com.Smartpay.Utility.StringUtil;
import com.Smartpay.Utility.Utility;

//@RunWith(MockitoJUnitRunner.class)  --> Junit4
@ExtendWith(MockitoExtension.class) // --> Junit5
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryImplMockTest extends BaseTest {

	@Mock
	private UserRepository userRepos;

	@InjectMocks
	private UserRepositoryImpl userRepositoryImpl;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

	@BeforeAll
	public void init() {
		logger.info("UserRepository Methods MockTest Start....");
		//userRepos = mock(UserRepository.class);
	}

	@DisplayName("Test SaveUserDetails")
	@Test
	public void testSaveUserDetails() {
		logger.info("testSaveUserDetails");
		User userRegistration = new User();
		Role merchantRole = roleRepository.findRoleByName(UserRole.MERCHANT.getRoleName());
		List<Role> roleList = Arrays.asList(merchantRole);
		userRegistration.setApplicantName("Pavan Kumar Bansal");
		userRegistration.setEmailId("pavanbansal.095@gmail.com");
		userRegistration.setMobileNo("9852141020");
		userRegistration.setDateOfBirth(Utility.convertStringToDate("11-05-1995"));
		userRegistration.setBankingServiceStatus(YesNO.NO);
		userRegistration.setIsActive(IsActive.ACTIVE);
		userRegistration.setRole(UserRole.MERCHANT.getRoleName());
		userRegistration.setRoles(roleList.stream().collect(Collectors.toSet()));
		userRegistration.setPassword(
				passwordEncoder.encode(StringUtil.generateDefaultPassword(userRegistration.getApplicantName())));
		userRegistration.setCustomerId((Utility.generateRandomfiveDigitNo()));
		userRegistration.setUsername("IR" + StringUtil.getLastSixDigitOfMobileNo(userRegistration.getMobileNo()));

		User parentDetails = userRepos.getAdminDetails();
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
		when(userRepos.saveUserDetails(userRegistration, mainWallet)).thenReturn(Boolean.TRUE);
		verify(userRepos, times(1)).saveUserDetails(userRegistration, mainWallet);
		Assertions.assertTrue(userRepos.saveUserDetails(userRegistration, mainWallet));
	}

	@DisplayName("Test GetAdminDetails")
	@Test
	public void testGetAdminDetails() {
		logger.info("testGetAdminDetails");
		given(userRepos.getAdminDetails()).willReturn(new User());
		// verify(userRepos, times(1)).getAdminDetails();
		Assertions.assertNotNull(userRepos.getAdminDetails());
	}

	@DisplayName("Test GetUserDetails")
	@Test
	public void testGetUserDetails() {
		logger.info("testFindUserByUsername");
		when(userRepos.getUserDetails("surya.kanti088@hotmail.com", "7752104510")).thenReturn(new User());
		verify(userRepos, times(1)).getUserDetails("surya.kanti088@hotmail.com", "7752104510");
		Assertions.assertNotNull(userRepos.getUserDetails("surya.kanti088@hotmail.com", "7752104510"));
	}

	@DisplayName("Test FindUserByUsername")
	@Test
	public void testFindUserByUsername() {
		logger.info("testFindUserByUsername");
		given(userRepos.findUserByUsername("IR104510")).willReturn(new User());
		verify(userRepos, times(1)).findUserByUsername("IR104510");
		Assertions.assertNotNull(userRepos.findUserByUsername("IR104510"));
	}
}

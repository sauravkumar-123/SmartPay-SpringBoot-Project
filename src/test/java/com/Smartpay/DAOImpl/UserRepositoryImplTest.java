package com.Smartpay.DAOImpl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserRepositoryImplTest extends BaseTest {

	private UserRepository userRepos;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

	@BeforeAll
	public void init() {
		logger.info("UserRepository Methods Test Start....");
		userRepos = new UserRepositoryImpl(super.entityManager);
	}

	@DisplayName("Test SaveUserDetails")
	@Test
	public void testSaveUserDetails() {
		logger.info("testSaveUserDetails");
		User userRegistration = new User();
		Role merchantRole = roleRepository.findRoleByName(UserRole.MERCHANT.getRoleName());
		List<Role> roleList = Arrays.asList(merchantRole);
		userRegistration.setApplicantName("Sridhar Reddy");
		userRegistration.setEmailId("sridhar.reddy@outlook.com");
		userRegistration.setMobileNo("8500692510");
		userRegistration.setDateOfBirth(Utility.convertStringToDate("1993-10-10"));
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
		boolean result = userRepos.saveUserDetails(userRegistration, mainWallet);
		Assertions.assertTrue(result);
	}

	@DisplayName("Test GetAdminDetails")
	@Test
	public void testGetAdminDetails() {
		logger.info("testGetAdminDetails");
		User result = userRepos.getAdminDetails();
		Assertions.assertNotNull(result);
	}

	@DisplayName("Test GetUserDetails")
	@Test
	public void testGetUserDetails() {
		logger.info("testGetUserDetails");
		User result = userRepos.getUserDetails("kumar.raju045@outlook.com", "8821506500");
		Assertions.assertNotNull(result);
	}

	@DisplayName("Test FindUserByUsername")
	@Test
	public void testFindUserByUsername() {
		logger.info("testFindUserByUsername");
		User result = userRepos.findUserByUsername("IR002565");
		Assertions.assertNotNull(result);
	}

	@AfterAll
	public void finish() {
		logger.info("UserRepository Methods Test Finished....");
		userRepos = null;
	}

}

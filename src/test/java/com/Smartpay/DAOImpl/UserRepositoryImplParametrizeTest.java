package com.Smartpay.DAOImpl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
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
public class UserRepositoryImplParametrizeTest extends BaseTest {

	private UserRepository userRepos;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

	@BeforeAll
	public void init() {
		logger.info("UserRepository Methods ParametrizeTest Start....");
		userRepos = new UserRepositoryImpl(super.entityManager);
	}

	@DisplayName("Test SaveUserDetails")
	@MethodSource("saveUserDetailsArguments")
	@ParameterizedTest
	public void testSaveUserDetails(User userRegistration, MainWallet mainWallet) {
		logger.info("testSaveUserDetails");
		boolean result = userRepos.saveUserDetails(userRegistration, mainWallet);
		Assertions.assertTrue(result);
	}

	private Stream<? extends Arguments> saveUserDetailsArguments() {

		Role merchantRole = roleRepository.findRoleByName(UserRole.MERCHANT.getRoleName());
		List<Role> roleList = Arrays.asList(merchantRole);

		User userRegistration1 = new User();
		userRegistration1.setApplicantName("Monika Singh");
		userRegistration1.setEmailId("singh.monika012@gmail.com");
		userRegistration1.setMobileNo("8541002145");
		userRegistration1.setDateOfBirth(Utility.convertStringToDate("1988-05-02"));
		userRegistration1.setBankingServiceStatus(YesNO.NO);
		userRegistration1.setIsActive(IsActive.ACTIVE);
		userRegistration1.setRole(UserRole.MERCHANT.getRoleName());
		userRegistration1.setRoles(roleList.stream().collect(Collectors.toSet()));
		userRegistration1.setPassword(
				passwordEncoder.encode(StringUtil.generateDefaultPassword(userRegistration1.getApplicantName())));
		userRegistration1.setCustomerId((Utility.generateRandomfiveDigitNo()));
		userRegistration1.setUsername("IR" + StringUtil.getLastSixDigitOfMobileNo(userRegistration1.getMobileNo()));

		User parentDetails = userRepos.getAdminDetails();
		if (null != parentDetails) {
			userRegistration1.setParentUsername(parentDetails.getUsername());
		}

		MainWallet mainWallet1 = new MainWallet();
		mainWallet1.setUserName(userRegistration1.getUsername());
		mainWallet1.setCurrentBalance(BigDecimal.ZERO);
		mainWallet1.setCommissionCredit(BigDecimal.ZERO);
		mainWallet1.setCharges(BigDecimal.ZERO);
		mainWallet1.setTds(BigDecimal.ZERO);
		mainWallet1.setCreditAmount(BigDecimal.ZERO);
		mainWallet1.setDebitAmount(BigDecimal.ZERO);
		mainWallet1.setIsActive(IsActive.ACTIVE);
		mainWallet1.setCreditType(null);
		mainWallet1.setDebitType(null);

		User userRegistration2 = new User();
		userRegistration2.setApplicantName("Surya Kanti");
		userRegistration2.setEmailId("surya.kanti088@hotmail.com");
		userRegistration2.setMobileNo("7752104510");
		userRegistration2.setDateOfBirth(Utility.convertStringToDate("1988-05-06"));
		userRegistration2.setBankingServiceStatus(YesNO.NO);
		userRegistration2.setIsActive(IsActive.ACTIVE);
		userRegistration2.setRole(UserRole.MERCHANT.getRoleName());
		userRegistration2.setRoles(roleList.stream().collect(Collectors.toSet()));
		userRegistration2.setPassword(
				passwordEncoder.encode(StringUtil.generateDefaultPassword(userRegistration2.getApplicantName())));
		userRegistration2.setCustomerId((Utility.generateRandomfiveDigitNo()));
		userRegistration2.setUsername("IR" + StringUtil.getLastSixDigitOfMobileNo(userRegistration2.getMobileNo()));

		if (null != parentDetails) {
			userRegistration2.setParentUsername(parentDetails.getUsername());
		}

		MainWallet mainWallet2 = new MainWallet();
		mainWallet2.setUserName(userRegistration2.getUsername());
		mainWallet2.setCurrentBalance(BigDecimal.ZERO);
		mainWallet2.setCommissionCredit(BigDecimal.ZERO);
		mainWallet2.setCharges(BigDecimal.ZERO);
		mainWallet2.setTds(BigDecimal.ZERO);
		mainWallet2.setCreditAmount(BigDecimal.ZERO);
		mainWallet2.setDebitAmount(BigDecimal.ZERO);
		mainWallet2.setIsActive(IsActive.ACTIVE);
		mainWallet2.setCreditType(null);
		mainWallet2.setDebitType(null);

		User userRegistration3 = new User();
		userRegistration3.setApplicantName("Suman Kumar");
		userRegistration3.setEmailId("suman.kumar098@gmail.com");
		userRegistration3.setMobileNo("7852001426");
		userRegistration3.setDateOfBirth(Utility.convertStringToDate("1998-12-16"));
		userRegistration3.setBankingServiceStatus(YesNO.NO);
		userRegistration3.setIsActive(IsActive.ACTIVE);
		userRegistration3.setRole(UserRole.MERCHANT.getRoleName());
		userRegistration3.setRoles(roleList.stream().collect(Collectors.toSet()));
		userRegistration3.setPassword(
				passwordEncoder.encode(StringUtil.generateDefaultPassword(userRegistration3.getApplicantName())));
		userRegistration3.setCustomerId((Utility.generateRandomfiveDigitNo()));
		userRegistration3.setUsername("IR" + StringUtil.getLastSixDigitOfMobileNo(userRegistration3.getMobileNo()));

		if (null != parentDetails) {
			userRegistration3.setParentUsername(parentDetails.getUsername());
		}

		MainWallet mainWallet3 = new MainWallet();
		mainWallet3.setUserName(userRegistration3.getUsername());
		mainWallet3.setCurrentBalance(BigDecimal.ZERO);
		mainWallet3.setCommissionCredit(BigDecimal.ZERO);
		mainWallet3.setCharges(BigDecimal.ZERO);
		mainWallet3.setTds(BigDecimal.ZERO);
		mainWallet3.setCreditAmount(BigDecimal.ZERO);
		mainWallet3.setDebitAmount(BigDecimal.ZERO);
		mainWallet3.setIsActive(IsActive.ACTIVE);
		mainWallet3.setCreditType(null);
		mainWallet3.setDebitType(null);

		return Stream.of(Arguments.of(userRegistration1, mainWallet1), Arguments.of(userRegistration2, mainWallet2),
				Arguments.of(userRegistration3, mainWallet3));
	}

	@DisplayName("Test GetAdminDetails")
	@Test
	public void testGetAdminDetails() {
		logger.info("testGetAdminDetails");
		User result = userRepos.getAdminDetails();
		Assertions.assertNotNull(result);
	}

	@DisplayName("Test GetUserDetails")
	@MethodSource("getUserDetailsArguments")
	@ParameterizedTest
	public void testGetUserDetails(String emailId, String mobNo) {
		logger.info("testGetUserDetails");
		User result = userRepos.getUserDetails(emailId, mobNo);
		Assertions.assertNotNull(result);
	}

	private static Stream<? extends Arguments> getUserDetailsArguments() {
		return Stream.of(Arguments.of("ram.shanker@gmail.com", "7755001540"),
				Arguments.of("suman.kumar098@gmail.com", "6520158945"),
				Arguments.of("surya.kanti@hotmail.com", "7752104510"));
	}

	@DisplayName("Test FindUserByUsername")
	@ValueSource(strings = { "IR001540", "IR692510", "IR004586" })
	@ParameterizedTest
	public void testFindUserByUsername(String username) {
		logger.info("testFindUserByUsername");
		User result = userRepos.findUserByUsername(username);
		Assertions.assertNotNull(result);
	}

	@DisplayName("Test UpdateUserDetails")
	@MethodSource("updateUserDetailsArguments")
	@ParameterizedTest
	public void testUpdateUserDetails(User user) {
		logger.info("testUpdateUserDetails");
		boolean result = userRepos.updateUserDetails(user);
		Assertions.assertTrue(result);
	}

	private Stream<? extends Arguments> updateUserDetailsArguments() {

		Role merchantRole = roleRepository.findRoleByName(UserRole.MERCHANT.getRoleName());
		List<Role> roleList = Arrays.asList(merchantRole);

		User userRegistration1 = new User();
		userRegistration1.setApplicantName("Raghubindar Das");
		userRegistration1.setEmailId("raghubindar.das045@gmail.com");
		userRegistration1.setMobileNo("7541002658");
		userRegistration1.setDateOfBirth(Utility.convertStringToDate("1996-03-07"));
		userRegistration1.setBankingServiceStatus(YesNO.NO);
		userRegistration1.setIsActive(IsActive.ACTIVE);
		userRegistration1.setRole(UserRole.MERCHANT.getRoleName());
		userRegistration1.setRoles(roleList.stream().collect(Collectors.toSet()));
		userRegistration1.setPassword(
				passwordEncoder.encode(StringUtil.generateDefaultPassword(userRegistration1.getApplicantName())));
		userRegistration1.setCustomerId((Utility.generateRandomfiveDigitNo()));
		userRegistration1.setUsername("IR" + StringUtil.getLastSixDigitOfMobileNo(userRegistration1.getMobileNo()));

		User parentDetails = userRepos.getAdminDetails();
		if (null != parentDetails) {
			userRegistration1.setParentUsername(parentDetails.getUsername());
		}

		return Stream.of(Arguments.of(userRegistration1));
	}

	@AfterAll
	public void finish() {
		logger.info("UserRepository Methods ParametrizeTest Finished....");
		userRepos = null;
	}

}

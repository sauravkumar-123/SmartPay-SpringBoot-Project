package com.starbanking.ServiceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.starbanking.DAO.RoleRepository;
import com.starbanking.Enum.EnumsStatus.UserRole;
import com.starbanking.Enum.EnumsStatus.YesNO;
import com.starbanking.Model.Role;
import com.starbanking.Model.User;
import com.starbanking.Service.UserService;
import com.starbanking.Utility.StringUtil;
import com.starbanking.Utility.Utility;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceMockTest {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceMockTest.class);

	@Mock
	private UserService userService;

//	@Autowired
//	private PasswordEncoder passwordEncoder;
//
//	@Autowired
//	private RoleRepository roleRepository;

	@BeforeAll
	public void initalSetUp() {
		logger.info("UserService SetUp Started");
	}

	@DisplayName("Test registerUserDetails method")
	@Test
	public void testRegisterUser() {
		logger.info("RegisterUser");
		User userRegistration1 = new User();
//		Role merchantRole = roleRepository.findRoleByName(UserRole.MERCHANT.getRoleName());
//		List<Role> roleList = Arrays.asList(merchantRole);
		userRegistration1.setApplicantName("Nirmal Kumar Aalnkar");
		userRegistration1.setEmailid("nirmal.kumar045@gmail.com");
		userRegistration1.setMobileno("6520102360");
		userRegistration1.setDateOfBirth(Utility.convertStringToDate("11-06-1974"));
		userRegistration1.setBankingServiceStatus(YesNO.NO);
		userRegistration1.setIsActive('Y');
		userRegistration1.setRole(UserRole.MERCHANT.getRoleName());
//		userRegistration1.setRoles(roleList.stream().collect(Collectors.toSet()));
//		userRegistration1.setPassword(
//				passwordEncoder.encode(StringUtil.generateDefaultPassword(userRegistration1.getApplicantName())));
		userRegistration1.setUserIdentificationNo(Utility.generateRandomfiveDigitNo());
		userRegistration1.setUsername("IR" + StringUtil.getLastSixDigitOfMobileNo(userRegistration1.getMobileno()));
		userRegistration1.setParentUsername("AD098742");

		given(userService.registerUser(userRegistration1)).willReturn(userRegistration1);
		//assertThat(userService.registerUser(userRegistration1), is(userRegistration1));
		Assertions.assertNotNull(userService.registerUser(userRegistration1));

	}
	
	@AfterAll
	public void endSetUp() {
		logger.info("UserService MockTest SetUp Ended");
	}
}

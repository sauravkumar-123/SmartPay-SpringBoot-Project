package com.starbanking.ServiceTest;

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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.starbanking.DAO.RoleRepository;
import com.starbanking.DAO.UserRepository;
import com.starbanking.Enum.EnumsStatus.UserRole;
import com.starbanking.Enum.EnumsStatus.YesNO;
import com.starbanking.Model.Role;
import com.starbanking.Model.User;
import com.starbanking.Service.UserService;
import com.starbanking.ServiceImpl.UserServiceImpl;
import com.starbanking.Utility.StringUtil;
import com.starbanking.Utility.Utility;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceMockTest {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceMockTest.class);

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImpl userService;

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
		User user = new User();
//		Role merchantRole = roleRepository.findRoleByName(UserRole.MERCHANT.getRoleName());
//		List<Role> roleList = Arrays.asList(merchantRole);
		user.setApplicantName("Shri Krishna");
		user.setEmailid("shrikrishna085@gmail.com");
		user.setMobileno("8596201540");
		user.setDateOfBirth(Utility.convertStringToDate("11-06-1974"));
		user.setBankingServiceStatus(YesNO.NO);
		user.setIsActive('Y');
		user.setRole(UserRole.MERCHANT.getRoleName());
//		user.setRoles(roleList.stream().collect(Collectors.toSet()));
//		user.setPassword(passwordEncoder.encode(StringUtil.generateDefaultPassword(user.getApplicantName())));
		user.setUserIdentificationNo(Utility.generateRandomfiveDigitNo());
		user.setUsername("IR" + StringUtil.getLastSixDigitOfMobileNo(user.getMobileno()));
		user.setParentUsername("AD098742");

		given(userRepository.getUserDetails(user.getEmailid(), user.getMobileno(), 'Y')).willReturn(null);
		// assertThat(userService.registerUser(userRegistration1),
		// is(userRegistration1));
		Assertions.assertNotNull(userService.registerUser(user));

	}

	@AfterAll
	public void endSetUp() {
		logger.info("UserService MockTest SetUp Ended");
	}
}

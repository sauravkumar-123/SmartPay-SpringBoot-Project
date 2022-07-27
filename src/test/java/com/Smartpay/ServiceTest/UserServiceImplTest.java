package com.Smartpay.ServiceTest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.Smartpay.Model.User;
import com.Smartpay.Service.UserService;
import com.Smartpay.Utility.Utility;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceImplTest {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImplTest.class);

	@Autowired
	private UserService userService;

	@BeforeAll
	public void initalSetUp() {
		logger.info("UserService SetUp Started");
	}

	@DisplayName("Test UserRegistration and their mainWalet Service")
	@Test
	public void testRegisterUser() {
		logger.info("saveUserRegistration");
		User user = new User();
		user.setApplicantName("Manjeet Kumar Roy");
		user.setMobileno("6520159840");
		user.setEmailid("manjeet.roy452@gmail.com");
		user.setDateOfBirth(Utility.convertStringToDate("11-05-1993"));
		User result = userService.registerUser(user);
		Assertions.assertNotNull(result);
	}

	@AfterAll
	public void endSetUp() {
		logger.info("SetUp Ended");
	}
}

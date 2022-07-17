package com.starbanking.ServiceTest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.starbanking.Model.User;
import com.starbanking.Service.UserService;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceParametrizeTest {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceParametrizeTest.class);
	
	@Autowired
	private UserService userService;

	@BeforeAll
	public void initalSetUp() {
		logger.info("UserService SetUp Started");
	}

	@DisplayName("Test UserRegistration and their mainWalet Service")
	@ParameterizedTest
	@MethodSource("com.starbanking.ServiceTest.UserServiceArgumentSource#registerUserServiceTestArguments")
	public void testRegisterUser(User user) {
		logger.info("saveUserRegistration");
		User result=userService.registerUser(user);
		Assertions.assertNotNull(result);
	}

	@AfterAll
	public void endSetUp() {
		logger.info("SetUp Ended");
	}
}

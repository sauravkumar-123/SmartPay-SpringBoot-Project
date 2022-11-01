package com.Smartpay.ServiceImpl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.Smartpay.Base.BaseTest;
import com.Smartpay.Model.User;
import com.Smartpay.Response.RegistrationResponse;
import com.Smartpay.Service.UserService;
import com.Smartpay.Utility.Utility;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceImplTest extends BaseTest {

	@Autowired
	private UserService userService;

	@BeforeAll
	public void init() {
		logger.info("UserService Methods Test Start.......");
	}

	@DisplayName("Test RegisterUser")
	@Test
	public void testRegisterUser() {
		logger.info("testRegisterUser");
		User user = new User();
		user.setApplicantName("Rajeev Kumar Singh");
		user.setEmailId("rajeev.kumar085@gmail.com");
		user.setMobileNo("7841002565");
		user.setDateOfBirth(Utility.convertStringToDate("1985-02-11"));
		RegistrationResponse result = userService.registerUser(user);
		Assertions.assertNotNull(result);
	}

	@AfterAll
	public void finish() {
		logger.info("UserService Methods Test Finished....");
		userService = null;
	}

}

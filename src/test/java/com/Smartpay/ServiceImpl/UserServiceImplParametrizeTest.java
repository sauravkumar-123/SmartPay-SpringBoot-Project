package com.Smartpay.ServiceImpl;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.Smartpay.Base.BaseTest;
import com.Smartpay.Exception.GlobalException;
import com.Smartpay.Model.User;
import com.Smartpay.Response.RegistrationResponse;
import com.Smartpay.Service.UserService;
import com.Smartpay.Utility.Utility;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceImplParametrizeTest extends BaseTest {

	@Autowired
	private UserService userService;

	@BeforeAll
	public void init() {
		logger.info("UserService Methods ParametrizeTest Start.......");
	}

	@DisplayName("Test RegisterUser")
	@MethodSource("registerUserDetailsArguments")
	@ParameterizedTest
	public void testRegisterUser(User user) {
		logger.info("testRegisterUser");
		RegistrationResponse result = userService.registerUser(user);
		Assertions.assertNotNull(result);
		Assertions.assertThrows(GlobalException.class, () -> {
			userService.registerUser(user);
		}, "GlobalException Excepted");

	}

	private Stream<? extends Arguments> registerUserDetailsArguments() {
		User user1 = new User();
		user1.setApplicantName("Rajeev Kumar Singh");
		user1.setEmailId("rajeev.kumar085@gmail.com");
		user1.setMobileNo("7841002565");
		user1.setDateOfBirth(Utility.convertStringToDate("1985-02-11"));

		User user2 = new User();
		user2.setApplicantName("Simran Kumari");
		user2.setEmailId("kumari.simran096@gmail.com");
		user2.setMobileNo("8510256520");
		user2.setDateOfBirth(Utility.convertStringToDate("1992-12-21"));

		return Stream.of(Arguments.of(user1), Arguments.of(user2));
	}

	@AfterAll
	public void finish() {
		logger.info("UserService Methods ParametrizeTest Finished....");
	}

}

package com.Smartpay.ServiceImpl;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.Smartpay.Base.BaseTest;
import com.Smartpay.Exception.GlobalException;
import com.Smartpay.Model.User;
import com.Smartpay.Service.AdminService;
import com.Smartpay.Utility.Utility;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AdminServiceImplParametrizeTest extends BaseTest {

	@Autowired
	private AdminService adminService;

	@BeforeAll
	public void init() {
		logger.info("AdminService Methods ParametrizeTest Start.......");
	}

	@DisplayName("Test RegisterAdmin")
	@MethodSource("registerAdminDetailsArguments")
	@ParameterizedTest
	public void testRegisterAdmin() {
		logger.info("testRegisterAdmin");
		User user = new User();
		user.setApplicantName("Saurav Kumar");
		user.setEmailId("krsaurav.097@gmail.com");
		user.setMobileNo("9691098742");
		user.setDateOfBirth(Utility.convertStringToDate("1997-05-10"));
		// RegistrationResponse result = adminService.registerAdmin(user);
		Assertions.assertThrows(GlobalException.class, () -> {
			adminService.registerAdmin(user);
		}, "GlobalException is Expected");
	}

	private Stream<? extends Arguments> registerAdminDetailsArguments() {
		User user1 = new User();
		user1.setApplicantName("Saurav Kumar");
		user1.setEmailId("krsaurav.097@gmail.com");
		user1.setMobileNo("9691098742");
		user1.setDateOfBirth(Utility.convertStringToDate("1997-05-10"));

		User user2 = new User();
		user2.setApplicantName("Saurav Kumar");
		user2.setEmailId("krsaurav.097@gmail.com");
		user2.setMobileNo("9691098742");
		user2.setDateOfBirth(Utility.convertStringToDate("1997-05-10"));

		return Stream.of(Arguments.of(user1), Arguments.of(user2));
	}

	@AfterAll
	public void finish() {
		logger.info("AdminService Methods ParametrizeTest Finished....");
	}
}

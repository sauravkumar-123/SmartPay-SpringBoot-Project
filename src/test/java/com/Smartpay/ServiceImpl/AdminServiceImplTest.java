package com.Smartpay.ServiceImpl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.Smartpay.Base.BaseTest;
import com.Smartpay.Exception.GlobalException;
import com.Smartpay.Model.User;
import com.Smartpay.Service.AdminService;
import com.Smartpay.Utility.Utility;

public class AdminServiceImplTest extends BaseTest {

	@Autowired
	private AdminService adminService;

	@BeforeAll
	public void init() {
		logger.info("AdminService Methods Test Start.......");
	}

	@DisplayName("Test RegisterAdmin")
	@Test
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

	@AfterAll
	public void finish() {
		logger.info("AdminService Methods Test Finished....");
		adminService = null;
	}

}

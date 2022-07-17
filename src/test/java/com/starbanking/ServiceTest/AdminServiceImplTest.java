package com.starbanking.ServiceTest;

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

import com.starbanking.Model.User;
import com.starbanking.Service.AdminService;
import com.starbanking.Utility.Utility;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AdminServiceImplTest {

	private static final Logger logger = LoggerFactory.getLogger(AdminServiceImplTest.class);

	@Autowired
	private AdminService adminService;

	@BeforeAll
	public void initalSetUp() {
		logger.info("UserService SetUp Started");
	}

	@DisplayName("Test AdminRegistration and their mainWalet Service")
	@Test
	public void testRegisterAdmin() {
		logger.info("saveAdminRegistration");
		User admin = new User();
		admin.setApplicantName("Saurav Kumar");
		admin.setMobileno("8740261025");
		admin.setEmailid("krsaurav.097@gmail.com");
		admin.setDateOfBirth(Utility.convertStringToDate("10-05-1997"));
		User result = adminService.registerAdmin(admin);
		Assertions.assertNotNull(result);
	}

	@AfterAll
	public void endSetUp() {
		logger.info("SetUp Ended");
	}
}

package com.Smartpay.Utility;

import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

import com.Smartpay.Base.BaseTest;
import com.Smartpay.Response.TwoFactorResponse;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UtilityTest extends BaseTest {

	@BeforeAll
	public void init() {
		logger.info("Utility Methods Test Start....");
	}

	@DisplayName("Test GenerateRandomfiveDigitNo")
	@Test
	public void testGenerateRandomfiveDigitNo() {
		logger.info("testGenerateRandomfiveDigitNo");
		long result = Utility.generateRandomfiveDigitNo();
		logger.info("Generated CustomerId {} " + result);
		Assertions.assertNotNull(result);
	}

	@DisplayName("Test ConvertStringToDate")
	@Test
	public void testConvertStringToDate() {
		logger.info("testConvertStringToDate");
		Date result = Utility.convertStringToDate("10-05-1997");
		logger.info("Converted Date{}: " + result);
		Assertions.assertNotNull(result);
	}

	@DisplayName("Test SendLoginDetailsToUserMobno")
	@Test
	public void testSendLoginDetailsToUserMobno() {
		logger.info("testSendLoginDetailsToUserMobno");
		TwoFactorResponse twoFactorResponse = Utility.sendLoginDetailsToUserMobno("Saurav Kumar", "9691098742",
				"IR098742");
		Assertions.assertTrue(twoFactorResponse.getStatus().equalsIgnoreCase("Success"));
	}

	@DisplayName("Test SendLoginOTP")
	@Test
	public void testSendLoginOTP() {
		logger.info("testSendLoginOTP");
		TwoFactorResponse twoFactorResponse = Utility.sendLoginOTP("9691098742");
		Assertions.assertTrue(twoFactorResponse.getStatus().equalsIgnoreCase("Success"));
	}

	@DisplayName("Test VerifyLoginOTP")
	@Test
	public void testVerifyLoginOTP() {
		logger.info("testVerifyLoginOTP");
		TwoFactorResponse twoFactorResponse = Utility.verifyLoginOTP("8d2c49d3-28ef-4fd0-8886-0dd9ecabd302", "782363");
		Assertions.assertTrue(twoFactorResponse.getStatus().equalsIgnoreCase("Success"));
	}

	@AfterAll
	public void finish() {
		logger.info("Utility Methods Test Finished....");
	}
}

package com.Smartpay.UtilMethodTest;

import java.util.Date;
import java.util.stream.Stream;

import org.apache.http.HttpEntity;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.provider.Arguments;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.Smartpay.Utility.Utility;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UtilTest {

	private static final Logger logger = LoggerFactory.getLogger(StringUtilTest.class);

	@BeforeAll
	public void init() {
		logger.info("Utility Methods Test SetUp Started");
	}

	@DisplayName("Test generateRandomfiveDigitNo")
	@Test
	public void generateRandomfiveDigitNoTest() {
		logger.info("generateRandomfiveDigitNo");
		long result = Utility.generateRandomfiveDigitNo();
		logger.info("Generated CustomerId {} " + result);
		Assertions.assertNotNull(result);
	}

	@DisplayName("Test convertStringToDate")
	@Test
	public void convertStringToDateTest() {
		logger.info("convertStringToDate");
		Date result = Utility.convertStringToDate("10-05-1997");
		logger.info("Converted Date {}" + result);
		Assertions.assertNotNull(result);
	}

	@DisplayName("Test sendLoginDetailsToUserMobno")
	@Test
	public void sendLoginDetailsToUserMobnoTest() {
		logger.info("sendLoginDetailsToUserMobno");
		String status = Utility.sendLoginDetailsToUserMobno("Saurav Kumar", "9691098742", "IR098742");
		logger.info("Response Status " + status);
		Assertions.assertEquals("Success",status);
	}

	@AfterAll
	public void endUp() {
		logger.info("Utility Methods Test SetUp Ended");
	}
}

package com.Smartpay.Utility;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

import com.Smartpay.Base.BaseTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StringUtilTest extends BaseTest {

	@BeforeAll
	public void init() {
		logger.info("StringUtil Methods Test Start....");
	}

	@DisplayName("Test GetLastSixDigitOfMobileNo")
	@Test
	public void testGetLastSixDigitOfMobileNo() {
		logger.info("testGetLastSixDigitOfMobileNo");
		String result = StringUtil.getLastSixDigitOfMobileNo("9691098742");
		Assertions.assertEquals("098742", result);
	}

	@DisplayName("Test GenerateDefaultPassword")
	@Test
	public void testGenerateDefaultPassword() {
		logger.info("testGenerateDefaultPassword");
		String result = StringUtil.generateDefaultPassword("Ravi Roy");
		Assertions.assertTrue(result.equalsIgnoreCase("Ravi12345"));
	}

	@DisplayName("Test GetLast4digit")
	@Test
	public void testGetLast4digit() {
		logger.info("testGetLast4digit");
		String result = StringUtil.getLast4digit("FXZPK7876K");
		Assertions.assertTrue(result.equalsIgnoreCase("876K"));
	}

	@AfterAll
	public void finish() {
		logger.info("StringUtil Methods Test Finished....");
	}

}

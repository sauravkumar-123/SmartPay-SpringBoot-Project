package com.Smartpay.UtilMethodTest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.Smartpay.Utility.StringUtil;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StringUtilTest {

	private static final Logger logger = LoggerFactory.getLogger(StringUtilTest.class);

	StringUtil util = null;

	@BeforeAll
	public void init() {
		logger.info("StringUtil Method SetUp Started");
		// util = new StringUtil();
	}

	@Test
	public void getLastSixDigitOfMobileNoTest() {
		logger.info("getLastSixDigitOfMobileNo");
		String result = StringUtil.getLastSixDigitOfMobileNo("9691098742");
		Assertions.assertEquals("098742", result);
	}

	@Test
	public void generateDefaultPasswordTest() {
		logger.info("generateDefaultPassword");
		String result = StringUtil.generateDefaultPassword("Saurav kumar");
		Assertions.assertEquals("Saurav12345", result);
	}

	@Test
	public void testGetLast4digit() {
		logger.info("getLast4digit");
		String result = StringUtil.getLast4digit("FXZPK7876K");
		Assertions.assertEquals("876K", result);
	}

	@AfterAll
	public void finish() {
		logger.info("StringUtil Method SetUp Ended");
		// util = null;
	}
}

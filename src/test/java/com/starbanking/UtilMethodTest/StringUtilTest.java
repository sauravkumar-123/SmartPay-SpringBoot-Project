package com.starbanking.UtilMethodTest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.starbanking.Utility.StringUtil;

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
		String result = StringUtil.getLastSixDigitOfMobileNo("9691098742");
		Assertions.assertEquals("098742", result);
	}

	@Test
	public void generateDefaultPasswordTest() {
		String result = StringUtil.generateDefaultPassword("Saurav kumar");
		Assertions.assertEquals("Saurav12345", result);
	}

	@AfterAll
	public void finish() {
		logger.info("StringUtil Method SetUp Ended");
		// util = null;
	}
}

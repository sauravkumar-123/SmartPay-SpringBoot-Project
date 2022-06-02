package com.starbanking.UtilMethodTest;

import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.starbanking.Utility.Utility;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UtilTest {

	private static final Logger logger = LoggerFactory.getLogger(StringUtilTest.class);

	@BeforeAll
	public void init() {
		logger.info("Utility Methods Test SetUp Started");
	}

	@Test
	public void generateRandomfiveDigitNoTest() {
		long result = Utility.generateRandomfiveDigitNo();
		logger.info("Generated CustomerId {} " + result);
		Assertions.assertNotNull(result);
	}

	@Test
	public void convertStringToDateTest() {
		Date result = Utility.convertStringToDate("10-05-1997");
		logger.info("Converted Date {}" + result);
		Assertions.assertNotNull(result);
	}

	@AfterAll
	public void endUp() {
		logger.info("Utility Methods Test SetUp Ended");
	}
}

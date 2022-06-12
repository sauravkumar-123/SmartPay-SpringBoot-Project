package com.starbanking.UtilMethodTest;

import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.starbanking.Utility.Utility;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UtilParametrizeTest {

	private static final Logger logger = LoggerFactory.getLogger(UtilParametrizeTest.class);

	@BeforeAll
	public void init() {
		logger.info("Utility Methods Test SetUp Started");
	}

	@DisplayName("Test convertStringToDate")
	@ParameterizedTest
	@ValueSource(strings = { "11-03-1998", "05-11-1987", "10-12-2000" })
	public void convertStringToDateTest(String inputDate) {
		Date result = Utility.convertStringToDate(inputDate);
		logger.info("Converted Date {}" + result);
		Assertions.assertNotNull(result);
	}

	@AfterAll
	public void endUp() {
		logger.info("Utility Methods Test SetUp Ended");
	}
}

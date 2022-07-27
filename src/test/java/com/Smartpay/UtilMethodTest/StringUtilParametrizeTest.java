package com.Smartpay.UtilMethodTest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.Smartpay.Utility.StringUtil;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StringUtilParametrizeTest {

	private static final Logger logger = LoggerFactory.getLogger(StringUtilParametrizeTest.class);

	@BeforeAll
	public void init() {
		logger.info("StringUtil Parametrize Test Started");
	}

	@DisplayName("Test getLastSixDigitOfMobileNo")
	@ParameterizedTest
	@ValueSource(strings = { "9691098742", "8541023210", "8541002695" })
	public void getLastSixDigitOfMobileNoTest(String mobNo) {
		logger.info("getLastSixDigitOfMobileNo");
		String result = StringUtil.getLastSixDigitOfMobileNo(mobNo);
		Assertions.assertNotNull(result);
	}

	@DisplayName("Test generateDefaultPassword")
	@ParameterizedTest
	@ValueSource(strings = { "Saurav Kumar", "Varsha Rajpoot", "Vikram Singh" })
	public void generateDefaultPasswordTest(String applicantName) {
		logger.info("generateDefaultPassword");
		String result = StringUtil.generateDefaultPassword(applicantName);
		Assertions.assertNotNull(result);
	}

	@AfterAll
	public void finish() {
		logger.info("StringUtil Parametrize Test Ended");
	}
}

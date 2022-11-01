package com.Smartpay.Utility;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import com.Smartpay.Base.BaseTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StringUtilParametrizeTest extends BaseTest {

	@BeforeAll
	public void init() {
		logger.info("StringUtil Methods ParametrizeTest Start....");
	}

	@DisplayName("Test GetLastSixDigitOfMobileNo")
	@ValueSource(strings = { "9691098742", "7979969287", "8525410025" })
	@ParameterizedTest
	public void testGetLastSixDigitOfMobileNo(String mobNo) {
		logger.info("testGetLastSixDigitOfMobileNo");
		String result = StringUtil.getLastSixDigitOfMobileNo(mobNo);
		Assertions.assertNotNull(result);
	}

	@DisplayName("Test GenerateDefaultPassword")
	@ValueSource(strings = { "Saurav Kumar", "Shivam Roy", "Kiran Kumari" })
	@ParameterizedTest
	public void testGenerateDefaultPassword(String applicantName) {
		logger.info("testGenerateDefaultPassword");
		String result = StringUtil.generateDefaultPassword(applicantName);
		Assertions.assertNotNull(result);
	}

	@DisplayName("Test GetLast4digit")
	@ValueSource(strings = { "212695665642", "FXZPK7876K", "8500692140" })
	@ParameterizedTest
	public void testGetLast4digit(String inputData) {
		logger.info("testGetLast4digit");
		String result = StringUtil.getLast4digit(inputData);
		Assertions.assertNotNull(result);
	}

	@AfterAll
	public void finish() {
		logger.info("StringUtil Methods ParametrizeTest Finished....");
	}

}

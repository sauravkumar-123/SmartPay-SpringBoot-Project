package com.Smartpay.Utility;

import java.util.Date;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import com.Smartpay.Base.BaseTest;
import com.Smartpay.Response.TwoFactorResponse;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UtilityParametrizeTest extends BaseTest {

	@BeforeAll
	public void init() {
		logger.info("Utility Methods ParametrizeTest Start....");
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
	@ValueSource(strings = { "1998-03-11", "05-11-1987", "10-12-2000", "03-25-1988" })
	@ParameterizedTest
	public void testConvertStringToDate(String date) {
		logger.info("testConvertStringToDate");
		Date result = Utility.convertStringToDate(date);
		logger.info("Converted Date{}: " + result);
		Assertions.assertNotNull(result);
	}

	@DisplayName("Test SendLoginDetailsToUserMobno")
	@MethodSource("sendLoginDetailsToUserMobnoArguments")
	@ParameterizedTest
	public void testSendLoginDetailsToUserMobno(String applicantName, String mobNo, String username) {
		logger.info("testSendLoginDetailsToUserMobno");
		TwoFactorResponse twoFactorResponse = Utility.sendLoginDetailsToUserMobno(applicantName, mobNo, username);
		Assertions.assertTrue(twoFactorResponse.getStatus().equalsIgnoreCase("Success"));
	}

	private static Stream<? extends Arguments> sendLoginDetailsToUserMobnoArguments() {
		return Stream.of(Arguments.of("Saurav Kumar", "9835525906", "IR125620"),
				Arguments.of("Mahesh Kumar", "8569521065", "IR521065"),
				Arguments.of("Priya Kumari", "7999052140", "IR052140"));
	}

	@DisplayName("Test SendLoginOTP")
	@MethodSource("sendLoginOTPArguments")
	@ParameterizedTest
	public void testSendLoginOTP(String mobNo) {
		logger.info("testSendLoginOTP");
		TwoFactorResponse twoFactorResponse = Utility.sendLoginOTP(mobNo);
		Assertions.assertTrue(twoFactorResponse.getStatus().equalsIgnoreCase("Success"));
	}

	private static Stream<? extends Arguments> sendLoginOTPArguments() {
		return Stream.of(Arguments.of("9691098742"), Arguments.of("7999052140"), Arguments.of("9835525906"));
	}

	@DisplayName("Test VerifyLoginOTP")
	@MethodSource("verifyLoginOTPArguments")
	@ParameterizedTest
	public void testVerifyLoginOTP(String sessionId, String inputOtp) {
		logger.info("testVerifyLoginOTP");
		TwoFactorResponse twoFactorResponse = Utility.verifyLoginOTP(sessionId, inputOtp);
		Assertions.assertTrue(twoFactorResponse.getStatus().equalsIgnoreCase("Success"));
	}

	private static Stream<? extends Arguments> verifyLoginOTPArguments() {
		return Stream.of(Arguments.of("8d2c49d3-28ef-4fd0-8886-0dd9ecabd302", "782363"),
				Arguments.of("acc15033-cfc1-4399-9c49-6cc24bf01840", "479137"),
				Arguments.of("acc15033-cfc1-4399-9c49-6cc24bf01840", "479137"));
	}

	@AfterAll
	public void finish() {
		logger.info("Utility Methods ParametrizeTest Finished....");
	}
}

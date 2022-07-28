package com.Smartpay.UtilMethodTest;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

public class UtilMethodArgumentSource {

	static Stream<? extends Arguments> sendLoginDetailsTestArguments() {
		return Stream.of(Arguments.of("Komal Jain", "8952125620", "IR125620"),
				Arguments.of("Mahesh Kumar", "8569521065", "IR521065"),
				Arguments.of("Priya Kumari", "7999052140", "IR052140"));
	}

	static Stream<? extends Arguments> sendLoginOTPTestArguments() {
		return Stream.of(Arguments.of("9691098742"), Arguments.of("7999052140"), Arguments.of("9835525906"));
	}

	static Stream<? extends Arguments> verifyLoginOTPTestArguments() {
		return Stream.of(Arguments.of("897bd200-660f-4a12-b709-471b46334ea7", "884538"),
				Arguments.of("897bd200-660f-4a12-b709-471b46334ea7", "884538"),
				Arguments.of("897bd200-660f-4a12-b709-471b46334ea7", "884535"));
	}
}

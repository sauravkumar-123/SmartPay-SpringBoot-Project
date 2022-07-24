package com.starbanking.UtilMethodTest;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

public class UtilMethodArgumentSource {

	static Stream<? extends Arguments> sendLoginDetailsTestArguments() {
		return Stream.of(Arguments.of("Komal Jain", "8952125620", "IR125620"),
				Arguments.of("Mahesh Kumar", "8569521065", "IR521065"),
				Arguments.of("Priya Kumari", "7999052140", "IR052140"));
	}
}

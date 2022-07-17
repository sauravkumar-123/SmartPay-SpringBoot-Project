package com.starbanking.ServiceTest;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import com.starbanking.Model.User;
import com.starbanking.Utility.Utility;

public class UserServiceArgumentSource {

	static Stream<? extends Arguments> registerUserServiceTestArguments() {
		User user1 = new User();
		user1.setApplicantName("Suman Kumar");
		user1.setMobileno("6520154580");
		user1.setEmailid("suman.kumar452@gmail.com");
		user1.setDateOfBirth(Utility.convertStringToDate("10-05-1998"));

		User user2 = new User();
		user2.setApplicantName("Rishav Jain");
		user2.setMobileno("8952103560");
		user2.setEmailid("jain.rishav@outlook.com");
		user2.setDateOfBirth(Utility.convertStringToDate("02-12-1990"));

		return Stream.of(Arguments.of(user1), Arguments.of(user2));
	}
}

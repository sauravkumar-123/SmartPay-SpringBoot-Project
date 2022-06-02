package com.starbanking.DAOTest;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.starbanking.DAO.UserRepository;
import com.starbanking.Enum.EnumsStatus.UserRole;
import com.starbanking.Enum.EnumsStatus.YesNO;
import com.starbanking.Model.MainWallet;
import com.starbanking.Model.User;
import com.starbanking.Utility.StringUtil;
import com.starbanking.Utility.Utility;

public class UserRepositoryImplTestArgumentSource {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	static Stream<? extends Arguments> saveUserDetailsTestArguments() {
		User userRegistration1 = new User();
		userRegistration1.setApplicantName("Rinku Singh");
		userRegistration1.setEmailid("rinku.singh453x@gmail.com");
		userRegistration1.setMobileno("8541023210");
		userRegistration1.setDateOfBirth(Utility.convertStringToDate("20-10-1998"));
		userRegistration1.setBankingServiceStatus(YesNO.NO);
		userRegistration1.setIsActive('Y');
		userRegistration1.setRole(UserRole.MERCHANT.getRoleName());
		userRegistration1.setPassword(StringUtil.generateDefaultPassword(userRegistration1.getApplicantName()));
		userRegistration1.setUserIdentificationNo(Utility.generateRandomfiveDigitNo());
		userRegistration1.setUsername("IR" + StringUtil.getLastSixDigitOfMobileNo(userRegistration1.getMobileno()));
		userRegistration1.setParentUsername("AD098742");
		
		MainWallet mainWallet1 = new MainWallet();
		mainWallet1.setUserName(userRegistration1.getUsername());
		mainWallet1.setCurrentBalance(0.00);
		mainWallet1.setCommissionCredit(0.00);
		mainWallet1.setCharges(0.00);
		mainWallet1.setTds(0.00);
		mainWallet1.setCreditAmount(0.00);
		mainWallet1.setDebitAmount(0.00);
		mainWallet1.setCreditType(null);
		mainWallet1.setDebitType(null);

		return Stream.of(
				Arguments.of(userRegistration1, mainWallet1)
				);
	}
}

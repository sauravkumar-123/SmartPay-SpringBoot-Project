package com.Smartpay.DAOTest;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.Smartpay.DAO.UserRepository;
import com.Smartpay.Enum.EnumsStatus.UserRole;
import com.Smartpay.Enum.EnumsStatus.YesNO;
import com.Smartpay.Model.MainWallet;
import com.Smartpay.Model.User;
import com.Smartpay.Utility.StringUtil;
import com.Smartpay.Utility.Utility;

public class UserRepositoryImplTestArgumentSource {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	static Stream<? extends Arguments> saveUserDetailsTestArguments() {
		User userRegistration1 = new User();
		userRegistration1.setApplicantName("Prashant Rai");
		userRegistration1.setEmailid("prashant.rai85@gmail.com");
		userRegistration1.setMobileno("9825412000");
		userRegistration1.setDateOfBirth(Utility.convertStringToDate("01-02-1985"));
		userRegistration1.setBankingServiceStatus(YesNO.NO);
		userRegistration1.setIsActive('Y');
		userRegistration1.setRole(UserRole.MERCHANT.getRoleName());
		userRegistration1.setPassword(StringUtil.generateDefaultPassword(userRegistration1.getApplicantName()));
		userRegistration1.setUserIdentificationNo(Utility.generateRandomfiveDigitNo());
		userRegistration1.setUsername("IR" + StringUtil.getLastSixDigitOfMobileNo(userRegistration1.getMobileno()));
		userRegistration1.setParentUsername("AD098742");
//		User parentDetails = userRepository.getAdminDetails();
//		if (null != parentDetails) {
//			userRegistration1.setParentUsername(parentDetails.getUsername());
//		}

		MainWallet mainWallet1 = new MainWallet();
		mainWallet1.setUserName(userRegistration1.getUsername());
		mainWallet1.setCurrentBalance(0.00);
		mainWallet1.setCommissionCredit(0.00);
		mainWallet1.setCharges(0.00);
		mainWallet1.setTds(0.00);
		mainWallet1.setCreditAmount(0.00);
		mainWallet1.setDebitAmount(0.00);
		mainWallet1.setIsActive('Y');
		mainWallet1.setCreditType(null);
		mainWallet1.setDebitType(null);

		User userRegistration2 = new User();
		userRegistration2.setApplicantName("Sanjay Kumar Singh");
		userRegistration2.setEmailid("singh.sanjay075@hotmail.com");
		userRegistration2.setMobileno("6512003620");
		userRegistration2.setDateOfBirth(Utility.convertStringToDate("03-03-1975"));
		userRegistration2.setBankingServiceStatus(YesNO.NO);
		userRegistration2.setIsActive('N');
		userRegistration2.setRole(UserRole.MERCHANT.getRoleName());
		userRegistration2.setPassword(StringUtil.generateDefaultPassword(userRegistration2.getApplicantName()));
		userRegistration2.setUserIdentificationNo(Utility.generateRandomfiveDigitNo());
		userRegistration2.setUsername("IR" + StringUtil.getLastSixDigitOfMobileNo(userRegistration2.getMobileno()));
		userRegistration2.setParentUsername("AD098742");

//		User parentDetails = userRepository.getAdminDetails();
//		if (null != parentDetails) {
//			userRegistration2.setParentUsername(parentDetails.getUsername());
//		}

		MainWallet mainWallet2 = new MainWallet();
		mainWallet2.setUserName(userRegistration2.getUsername());
		mainWallet2.setCurrentBalance(0.00);
		mainWallet2.setCommissionCredit(0.00);
		mainWallet2.setCharges(0.00);
		mainWallet2.setTds(0.00);
		mainWallet2.setCreditAmount(0.00);
		mainWallet2.setDebitAmount(0.00);
		mainWallet2.setIsActive('N');
		mainWallet2.setCreditType(null);
		mainWallet2.setDebitType(null);

		User userRegistration3 = new User();
		userRegistration3.setApplicantName("Ragahv Nanda");
		userRegistration3.setEmailid("nanada.raghav096@gmail.com");
		userRegistration3.setMobileno("8545102362");
		userRegistration3.setDateOfBirth(Utility.convertStringToDate("02-10-1996"));
		userRegistration3.setBankingServiceStatus(YesNO.NO);
		userRegistration3.setIsActive('Y');
		userRegistration3.setRole(UserRole.MERCHANT.getRoleName());
		userRegistration3.setPassword(StringUtil.generateDefaultPassword(userRegistration3.getApplicantName()));
		userRegistration3.setUserIdentificationNo(Utility.generateRandomfiveDigitNo());
		userRegistration3.setUsername("IR" + StringUtil.getLastSixDigitOfMobileNo(userRegistration3.getMobileno()));
		userRegistration3.setParentUsername("AD098742");

//		User parentDetails = userRepository.getAdminDetails();
//		if (null != parentDetails) {
//			userRegistration2.setParentUsername(parentDetails.getUsername());
//  	}

		MainWallet mainWallet3 = new MainWallet();
		mainWallet3.setUserName(userRegistration3.getUsername());
		mainWallet3.setCurrentBalance(0.00);
		mainWallet3.setCommissionCredit(0.00);
		mainWallet3.setCharges(0.00);
		mainWallet3.setTds(0.00);
		mainWallet3.setCreditAmount(0.00);
		mainWallet3.setDebitAmount(0.00);
		mainWallet3.setIsActive('Y');
		mainWallet3.setCreditType(null);
		mainWallet3.setDebitType(null);

		return Stream.of(Arguments.of(userRegistration1, mainWallet1), Arguments.of(userRegistration2, mainWallet2),
				Arguments.of(userRegistration3, mainWallet3));
	}
	
	
	static Stream<? extends Arguments> getUserDetailsTestArguments(){
		return Stream.of(Arguments.of("ravi.coll123@gmail.com","9874102562",'Y'),
				         Arguments.of("kumari.geeta0123@gmail.com","8520102652",'Y'),
				         Arguments.of("simran.kumari097@gmail.com","9852102300",'N'));
	}
}

package com.starbanking.DAOTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.starbanking.DAO.UserRepository;
import com.starbanking.DAOImpl.UserRepositoryImpl;
import com.starbanking.Enum.EnumsStatus.UserRole;
import com.starbanking.Enum.EnumsStatus.YesNO;
import com.starbanking.Model.MainWallet;
import com.starbanking.Model.User;
import com.starbanking.Utility.StringUtil;
import com.starbanking.Utility.Utility;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryImplTest {

	private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImplTest.class);

	UserRepository userRepos;

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@BeforeAll
	public void initalSetUp() {
		logger.info("Userdetail SetUp Started");
		userRepos = new UserRepositoryImpl(entityManager);
	}

	@DisplayName("Test Save User and their mainWalet details")
	@Test
	public void saveUserDetailsTest() {
		logger.info("saveUserDetails");
		User userRegistration = new User();
		userRegistration.setApplicantName("Rinku Singh");
		userRegistration.setEmailid("rinku.singh453x@gmail.com");
		userRegistration.setMobileno("8541023210");
		userRegistration.setDateOfBirth(Utility.convertStringToDate("20-10-1998"));
		userRegistration.setBankingServiceStatus(YesNO.NO);
		userRegistration.setIsActive('Y');
		userRegistration.setRole(UserRole.MERCHANT.getRoleName());
		userRegistration.setPassword(
				passwordEncoder.encode(StringUtil.generateDefaultPassword(userRegistration.getApplicantName())));
		userRegistration.setUserIdentificationNo(Utility.generateRandomfiveDigitNo());
		userRegistration.setUsername("IR" + StringUtil.getLastSixDigitOfMobileNo(userRegistration.getMobileno()));

		User parentDetails = userRepos.getAdminDetails();
		if (null != parentDetails) {
			userRegistration.setParentUsername(parentDetails.getUsername());
		}

		MainWallet mainWallet = new MainWallet();
		mainWallet.setUserName(userRegistration.getUsername());
		mainWallet.setCurrentBalance(0.00);
		mainWallet.setCommissionCredit(0.00);
		mainWallet.setCharges(0.00);
		mainWallet.setTds(0.00);
		mainWallet.setCreditAmount(0.00);
		mainWallet.setDebitAmount(0.00);
		mainWallet.setIsActive('Y');
		mainWallet.setCreditType(null);
		mainWallet.setDebitType(null);
		boolean status = userRepos.saveUserDetails(userRegistration, mainWallet);
		Assertions.assertTrue(status);
	}

	@DisplayName("Test getAdminDetails method")
	@Test
	public void getAdminDetailsTest() {
		logger.info("getAdminDetails");
		User user = userRepos.getAdminDetails();
		Assertions.assertNotNull(user);
	}

	@DisplayName("Test findUserByUsername method")
	@Test
	public void getUserDetailsTest() {
		logger.info("getUserDetails");
		User user = userRepos.getUserDetails("sauravkumar.123@gmail.com", "9691098742", 'Y');
		Assertions.assertNotNull(user);
	}

	@DisplayName("Test findUserByUsername method")
	@Test
	public void findUserByUsernameTest() {
		logger.info("findUserByUsername");
		User user = userRepos.findUserByUsername("IR023210");
		Assertions.assertNotNull(user);
	}

	@AfterAll
	public void endSetUp() {
		userRepos = null;
		logger.info("SetUp Ended");
	}

}

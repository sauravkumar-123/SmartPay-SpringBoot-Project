package com.starbanking.DAOTest;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.starbanking.DAO.UserRepository;
import com.starbanking.Enum.EnumsStatus.UserRole;
import com.starbanking.Enum.EnumsStatus.YesNO;
import com.starbanking.Model.MainWallet;
import com.starbanking.Model.User;
import com.starbanking.Utility.StringUtil;
import com.starbanking.Utility.Utility;

//@RunWith(MockitoJUnitRunner.class)  --> Junit4
@ExtendWith(MockitoExtension.class) // --> Junit5
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryMockTest {

	private static final Logger logger = LoggerFactory.getLogger(UserRepositoryMockTest.class);

	UserRepository userRepos;

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	@BeforeAll
	public void initalSetUp() {
		logger.info("UserRepository MockTest SetUp Started");
		userRepos = mock(UserRepository.class);
	}

	@DisplayName("Test saveUserDetails method")
	@Test
	public void saveUserDetailsMockTest() {
		logger.info("saveUserDetails");
		User userRegistration1 = new User();
		userRegistration1.setApplicantName("Nirmal Kumar Aalnkar");
		userRegistration1.setEmailid("nirmal.kumar045@gmail.com");
		userRegistration1.setMobileno("6520102360");
		userRegistration1.setDateOfBirth(Utility.convertStringToDate("11-06-1974"));
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
		mainWallet1.setIsActive('Y');
		mainWallet1.setCreditType(null);
		mainWallet1.setDebitType(null);

		given(userRepos.saveUserDetails(userRegistration1, mainWallet1)).willReturn(true);
		Assertions.assertTrue(userRepos.saveUserDetails(userRegistration1, mainWallet1));
	}

	@DisplayName("Test getAdminDetails method")
	@Test
	public void getAdminDetailsMockTest() {
		logger.info("getAdminDetails");
		User userRegistration1 = new User();
		userRegistration1.setApplicantName("Radha Shayam");
		userRegistration1.setEmailid("radheshyam095@gmail.com");
		userRegistration1.setMobileno("8541206590");
		userRegistration1.setDateOfBirth(Utility.convertStringToDate("10-02-1995"));
		userRegistration1.setBankingServiceStatus(YesNO.NO);
		userRegistration1.setIsActive('Y');
		userRegistration1.setRole(UserRole.MERCHANT.getRoleName());
		userRegistration1.setPassword(StringUtil.generateDefaultPassword(userRegistration1.getApplicantName()));
		userRegistration1.setUserIdentificationNo(Utility.generateRandomfiveDigitNo());
		userRegistration1.setUsername("IR" + StringUtil.getLastSixDigitOfMobileNo(userRegistration1.getMobileno()));
		userRegistration1.setParentUsername("AD098742");
		given(userRepos.getAdminDetails()).willReturn(userRegistration1);
		// assertThat(userRepos.getAdminDetails(), is(userRegistration1));
		Assertions.assertNotNull(userRepos.getAdminDetails());
	}

	@DisplayName("Test getUserDetails method")
	@Test
	public void getUserDetailsMockTest() {
		logger.info("getUserDetails");
		when(userRepos.getUserDetails("shrikrishna085@gmail.com", "8596201540", 'Y')).thenReturn(new User());
		Assertions.assertNotNull(userRepos.getUserDetails("shrikrishna085@gmail.com", "8596201540", 'Y'));
	}

	@DisplayName("Test findUserByUsername method")
	@Test
	public void findUserByUsernameMockTest() {
		logger.info("findUserByUsername");
		when(userRepos.findUserByUsername("IR054123")).thenReturn(null);
		Assertions.assertNull(userRepos.findUserByUsername("IR054123"));
	}

	@AfterAll
	public void endSetUp() {
		userRepos = null;
		logger.info("SetUp Ended");
	}
}
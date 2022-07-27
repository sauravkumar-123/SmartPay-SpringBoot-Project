package com.Smartpay.DAOTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.Smartpay.DAO.UserRepository;
import com.Smartpay.DAOImpl.UserRepositoryImpl;
import com.Smartpay.Model.MainWallet;
import com.Smartpay.Model.User;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryImplParametrizeTest {

	private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImplParametrizeTest.class);

	UserRepository userRepos;

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	@BeforeAll
	public void initalSetUp() {
		logger.info("Userdetail SetUp Started");
		userRepos = new UserRepositoryImpl(entityManager);
	}

	@DisplayName("Test Save User and their mainWalet details")
	@ParameterizedTest
	@MethodSource("com.starbanking.DAOTest.UserRepositoryImplTestArgumentSource#saveUserDetailsTestArguments")
	public void saveUserDetailsTest(User user, MainWallet mainWallet) {
		logger.info("saveUserDetailsTest");
		boolean status = userRepos.saveUserDetails(user, mainWallet);
		Assertions.assertTrue(status);
	}

	@DisplayName("Test getUserDetails method")
	@ParameterizedTest
	@MethodSource("com.starbanking.DAOTest.UserRepositoryImplTestArgumentSource#getUserDetailsTestArguments")
	public void getUserDetailsTest(String emailId, String mobno, char isActive) {
		logger.info("getUserDetailsTest");
		User user = userRepos.getUserDetails(emailId, mobno, isActive);
		Assertions.assertNotNull(user);
	}

	@DisplayName("Test findUserByUsername method")
	@ParameterizedTest
	@ValueSource(strings = { "IR102362", "IR236510", "IR154580", "IR023210" })
	public void findUserByUsernameTest(String username) {
		logger.info("findUserByUsernameTset");
		User user = userRepos.findUserByUsername(username);
		Assertions.assertNotNull(user);
	}

	@AfterAll
	public void endSetUp() {
		userRepos = null;
		logger.info("SetUp Ended");
	}
}

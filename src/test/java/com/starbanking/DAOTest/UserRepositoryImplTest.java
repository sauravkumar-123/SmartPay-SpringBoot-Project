package com.starbanking.DAOTest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.starbanking.DAO.UserRepository;
import com.starbanking.DAOImpl.UserRepositoryImpl;
import com.starbanking.Model.MainWallet;
import com.starbanking.Model.User;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryImplTest {

	private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImplTest.class);
	
	UserRepository userRepos;

	@BeforeAll
	public void initalSetUp() {
		logger.info("Userdetail SetUp Started");
		userRepos = new UserRepositoryImpl();
	}

	@ParameterizedTest
	@MethodSource("com.starbanking.DAOTest.UserRepositoryImplTestArgumentSource#saveUserDetailsTestArguments")
	public void saveUserDetailsTest(User user, MainWallet mainWallet) {
		boolean status=userRepos.saveUserDetails(user, mainWallet);
		Assertions.assertTrue(status);
	}
	
	@ParameterizedTest
	@ValueSource(strings = { "AD098742", "AD7825620" })
	public void findUserByUsernameTest(String username) {
		User user = userRepos.findUserByUsername(username);
		Assertions.assertNotNull(user);
	}

	@AfterAll
	public void endSetUp() {
		userRepos=null;
		logger.info("SetUp Ended");
	}

}

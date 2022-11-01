package com.Smartpay.Base;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {

	public static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	public EntityManager entityManager;

	@BeforeEach
	public void startTest() {
		logger.info("Start of Method JUnit Test");
	}

	@AfterEach
	public void endTest() {
		logger.info("End of Method JUnit Test");
	}
}

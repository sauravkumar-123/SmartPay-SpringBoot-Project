package com.Smartpay.DAOImpl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.Smartpay.Base.BaseTest;
import com.Smartpay.DAO.MerchantRepository;
import com.Smartpay.Model.Merchant;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MerchantRepositoryImplTest extends BaseTest {

	@Autowired
	private MerchantRepository merchantRepository;

	@BeforeAll
	public void init() {
		logger.info("MerchantRepository Methods Test Start....");
		merchantRepository = new MerchantRepositoryImpl(super.entityManager);
	}

	@DisplayName("Test SaveMerchantProfile")
	@Test
	void testSaveMerchantProfile() {
		logger.info("testSaveMerchantProfile");
	}

	@DisplayName("Test FindMerchantByUserId")
	@Test
	void testFindMerchantByUserId() {
		logger.info("testFindMerchantByUserId");
	}

	@DisplayName("Test FindMerchantByMerchantId")
	@Test
	void testFindMerchantByMerchantId() {
		logger.info("testFindMerchantById");
		Merchant result = merchantRepository.findMerchantByMerchantId("402880e984afcd930184afdd5dac000a");
		Assertions.assertNotNull(result);
	}

	@AfterAll
	public void finish() {
		logger.info("MerchantRepository Methods Test Start....");
		merchantRepository = null;
	}
}

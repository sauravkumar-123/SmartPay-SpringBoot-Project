package com.Smartpay.FileUpload.DaoImpl;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.Smartpay.Base.BaseTest;
import com.Smartpay.FileUpload.MerchantDocuments;
import com.Smartpay.FileUpload.Dao.MerchantDocumentsRepository;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MerchnatDocumentsRepositoryImplTest extends BaseTest {

	@Autowired
	private MerchantDocumentsRepository merchantDocumentsRepository;

	@BeforeAll
	public void init() {
		logger.info("MerchantDocumentsRepository Methods Test Start....");
		merchantDocumentsRepository = new MerchnatDocumentsRepositoryImpl(super.entityManager);
	}

	@DisplayName("Test GetDocDetailsByMerchantId")
	@Test
	void testGetDocDetailsByMerchantId() {
		logger.info("testGetDocDetailsByMerchantId");
		MerchantDocuments merchantDocuments = merchantDocumentsRepository
				.getDocDetailsByMerchantId("402880e984afcd930184afdd5dac000a");
		Assertions.assertNotNull(merchantDocuments);
	}

	@Test
	void testSaveDocumentsDetail() {
		fail("Not yet implemented");
	}

	@AfterAll
	public void finish() {
		logger.info("MerchantDocumentsRepository Methods Test Start....");
		merchantDocumentsRepository = null;
	}
}

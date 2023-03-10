/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.smartpay.fileupload.serviceimpl;

import com.smartpay.fileupload.model.MerchantDocuments;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author saura
 */
public class DocumentsUploadServiceImplTest {
    
    public DocumentsUploadServiceImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of uploadDocumentsForBankingService method, of class DocumentsUploadServiceImpl.
     */
    @Test
    public void testUploadDocumentsForBankingService() {
        System.out.println("uploadDocumentsForBankingService");
        String identificationNo = "";
        MultipartFile[] files = null;
        DocumentsUploadServiceImpl instance = new DocumentsUploadServiceImpl();
        MerchantDocuments expResult = null;
        MerchantDocuments result = instance.uploadDocumentsForBankingService(identificationNo, files);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

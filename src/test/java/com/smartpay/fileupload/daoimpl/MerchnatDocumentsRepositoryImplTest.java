/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.smartpay.fileupload.daoimpl;

import com.smartpay.fileupload.model.MerchantDocuments;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author saura
 */
public class MerchnatDocumentsRepositoryImplTest {
    
    public MerchnatDocumentsRepositoryImplTest() {
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
     * Test of getDocDetailsByMerchantId method, of class MerchnatDocumentsRepositoryImpl.
     */
    @Test
    public void testGetDocDetailsByMerchantId() {
        System.out.println("getDocDetailsByMerchantId");
        String identificationNo = "";
        MerchnatDocumentsRepositoryImpl instance = new MerchnatDocumentsRepositoryImpl();
        MerchantDocuments expResult = null;
        MerchantDocuments result = instance.getDocDetailsByMerchantId(identificationNo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveDocumentsDetail method, of class MerchnatDocumentsRepositoryImpl.
     */
    @Test
    public void testSaveDocumentsDetail() {
        System.out.println("saveDocumentsDetail");
        MerchantDocuments docdetails = null;
        MerchnatDocumentsRepositoryImpl instance = new MerchnatDocumentsRepositoryImpl();
        MerchantDocuments expResult = null;
        MerchantDocuments result = instance.saveDocumentsDetail(docdetails);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.smartpay.daoimpl;

import com.smartpay.dto.MerchantOnboardingDto;
import com.smartpay.enums.EnumsStatus;
import com.smartpay.model.Merchant;
import java.util.List;
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
public class MerchantRepositoryImplTest {
    
    public MerchantRepositoryImplTest() {
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
     * Test of saveMerchantProfile method, of class MerchantRepositoryImpl.
     */
    @Test
    public void testSaveMerchantProfile() {
        System.out.println("saveMerchantProfile");
        Merchant merchant = null;
        MerchantRepositoryImpl instance = new MerchantRepositoryImpl();
        Merchant expResult = null;
        Merchant result = instance.saveMerchantProfile(merchant);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findMerchantByUserId method, of class MerchantRepositoryImpl.
     */
    @Test
    public void testFindMerchantByUserId() {
        System.out.println("findMerchantByUserId");
        String userIdentificationNo = "";
        MerchantRepositoryImpl instance = new MerchantRepositoryImpl();
        Merchant expResult = null;
        Merchant result = instance.findMerchantByUserId(userIdentificationNo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findMerchantByMerchantId method, of class MerchantRepositoryImpl.
     */
    @Test
    public void testFindMerchantByMerchantId() {
        System.out.println("findMerchantByMerchantId");
        String identificationNo = "";
        MerchantRepositoryImpl instance = new MerchantRepositoryImpl();
        Merchant expResult = null;
        Merchant result = instance.findMerchantByMerchantId(identificationNo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateDocumentsUploadStatus method, of class MerchantRepositoryImpl.
     */
    @Test
    public void testUpdateDocumentsUploadStatus() {
        System.out.println("updateDocumentsUploadStatus");
        String identificationNo = "";
        EnumsStatus.DocumentsUploadStatus status = null;
        MerchantRepositoryImpl instance = new MerchantRepositoryImpl();
        instance.updateDocumentsUploadStatus(identificationNo, status);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fetchMerchantByOnboardingStatus method, of class MerchantRepositoryImpl.
     */
    @Test
    public void testFecthMerchantByOnboardingStatus() {
        System.out.println("fecthMerchantByOnboardingStatus");
        EnumsStatus.YesNO status = null;
        MerchantRepositoryImpl instance = new MerchantRepositoryImpl();
        List<MerchantOnboardingDto> expResult = null;
        List<MerchantOnboardingDto> result = instance.fetchMerchantByOnboardingStatus(status);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateOnboardAndaepsStatus method, of class MerchantRepositoryImpl.
     */
    @Test
    public void testUpdateOnboardAndaepsStatus() {
        System.out.println("updateOnboardAndaepsStatus");
        String merchantId = "";
        String onboardId = "";
        String onboardStatus = "";
        EnumsStatus.YesNO aepsStatus = null;
        MerchantRepositoryImpl instance = new MerchantRepositoryImpl();
        instance.updateOnboardAndaepsStatus(merchantId, onboardId, onboardStatus, aepsStatus);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

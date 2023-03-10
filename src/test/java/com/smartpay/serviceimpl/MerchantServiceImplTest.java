/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.smartpay.serviceimpl;

import com.smartpay.model.Merchant;
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
public class MerchantServiceImplTest {
    
    public MerchantServiceImplTest() {
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
     * Test of updateUserProfileToMerchant method, of class MerchantServiceImpl.
     */
    @Test
    public void testUpdateUserProfileToMerchant() {
        System.out.println("updateUserProfileToMerchant");
        String userName = "";
        Merchant merchant = null;
        MerchantServiceImpl instance = new MerchantServiceImpl();
        Merchant expResult = null;
        Merchant result = instance.updateUserProfileToMerchant(userName, merchant);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

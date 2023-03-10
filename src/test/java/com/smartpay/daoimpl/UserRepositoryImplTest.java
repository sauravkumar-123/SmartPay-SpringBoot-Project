/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.smartpay.daoimpl;

import com.smartpay.enums.EnumsStatus;
import com.smartpay.model.User;
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
public class UserRepositoryImplTest {
    
    public UserRepositoryImplTest() {
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
     * Test of saveUserDetails method, of class UserRepositoryImpl.
     */
    @Test
    public void testSaveUserDetails() {
        System.out.println("saveUserDetails");
        User user = null;
        UserRepositoryImpl instance = new UserRepositoryImpl();
        User expResult = null;
        User result = instance.saveUserDetails(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAdminDetails method, of class UserRepositoryImpl.
     */
    @Test
    public void testGetAdminDetails() {
        System.out.println("getAdminDetails");
        UserRepositoryImpl instance = new UserRepositoryImpl();
        User expResult = null;
        User result = instance.getAdminDetails();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserDetailsByEmailOrMobno method, of class UserRepositoryImpl.
     */
    @Test
    public void testGetUserDetailsByEmailOrMobno() {
        System.out.println("getUserDetailsByEmailOrMobno");
        String emailId = "";
        String mobNo = "";
        UserRepositoryImpl instance = new UserRepositoryImpl();
        User expResult = null;
        User result = instance.getUserDetailsByEmailOrMobno(emailId, mobNo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findUserByUsername method, of class UserRepositoryImpl.
     */
    @Test
    public void testFindUserByUsername() {
        System.out.println("findUserByUsername");
        String username = "";
        UserRepositoryImpl instance = new UserRepositoryImpl();
        User expResult = null;
        User result = instance.findUserByUsername(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateBankingServiceStatus method, of class UserRepositoryImpl.
     */
    @Test
    public void testUpdateBankingServiceStatus() {
        System.out.println("updateBankingServiceStatus");
        String userIdNo = "";
        EnumsStatus.YesNO status = null;
        UserRepositoryImpl instance = new UserRepositoryImpl();
        instance.updateBankingServiceStatus(userIdNo, status);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateUserLoginPassword method, of class UserRepositoryImpl.
     */
    @Test
    public void testUpdateUserLoginPassword() {
        System.out.println("updateUserLoginPassword");
        String userIdNo = "";
        String pwd = "";
        UserRepositoryImpl instance = new UserRepositoryImpl();
        boolean expResult = false;
        boolean result = instance.updateUserLoginPassword(userIdNo, pwd);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

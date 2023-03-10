/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.smartpay.utility;

import com.smartpay.response.TwoFactorResponse;
import java.util.Date;
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
public class UtilityTest {
    
    public UtilityTest() {
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
     * Test of generateRandomfiveDigitNo method, of class Utility.
     */
    @Test
    public void testGenerateRandomfiveDigitNo() {
        System.out.println("generateRandomfiveDigitNo");
        long expResult = 0L;
        long result = Utility.generateRandomfiveDigitNo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of convertStringToDate method, of class Utility.
     */
    @Test
    public void testConvertStringToDate() {
        System.out.println("convertStringToDate");
        String inputDate = "";
        Date expResult = null;
        Date result = Utility.convertStringToDate(inputDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sendLoginDetailsToUserMobno method, of class Utility.
     */
    @Test
    public void testSendLoginDetailsToUserMobno() {
        System.out.println("sendLoginDetailsToUserMobno");
        String applicantName = "";
        String mobNo = "";
        String username = "";
        TwoFactorResponse expResult = null;
        TwoFactorResponse result = Utility.sendLoginDetailsToUserMobno(applicantName, mobNo, username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sendLoginOTP method, of class Utility.
     */
    @Test
    public void testSendLoginOTP() {
        System.out.println("sendLoginOTP");
        String MobNo = "";
        TwoFactorResponse expResult = null;
        TwoFactorResponse result = Utility.sendLoginOTP(MobNo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of verifyLoginOTP method, of class Utility.
     */
    @Test
    public void testVerifyLoginOTP() {
        System.out.println("verifyLoginOTP");
        String sessionId = "";
        String inputOtp = "";
        TwoFactorResponse expResult = null;
        TwoFactorResponse result = Utility.verifyLoginOTP(sessionId, inputOtp);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

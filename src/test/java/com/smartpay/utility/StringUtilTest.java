/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.smartpay.utility;

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
public class StringUtilTest {
    
    public StringUtilTest() {
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
     * Test of getLastSixDigitOfMobileNo method, of class StringUtil.
     */
    @Test
    public void testGetLastSixDigitOfMobileNo() {
        System.out.println("getLastSixDigitOfMobileNo");
        String input = "";
        String expResult = "";
        String result = StringUtil.getLastSixDigitOfMobileNo(input);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateDefaultPassword method, of class StringUtil.
     */
    @Test
    public void testGenerateDefaultPassword() {
        System.out.println("generateDefaultPassword");
        String input = "";
        String expResult = "";
        String result = StringUtil.generateDefaultPassword(input);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLast4digit method, of class StringUtil.
     */
    @Test
    public void testGetLast4digit() {
        System.out.println("getLast4digit");
        String input = "";
        String expResult = "";
        String result = StringUtil.getLast4digit(input);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

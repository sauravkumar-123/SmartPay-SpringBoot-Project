/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.smartpay.controller;

import com.smartpay.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

/**
 *
 * @author saura
 */
public class AuthControllerTest {
    
    public AuthControllerTest() {
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
     * Test of sendLoginOTP method, of class AuthController.
     */
    @Test
    public void testSendLoginOTP() {
        System.out.println("sendLoginOTP");
        String username = "";
        String password = "";
        AuthController instance = new AuthController();
        ResponseEntity<Response> expResult = null;
        ResponseEntity<Response> result = instance.sendLoginOTP(username, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loginUser method, of class AuthController.
     */
    @Test
    public void testLoginUser() {
        System.out.println("loginUser");
        String username = "";
        String password = "";
        String sessionId = "";
        String inputOtp = "";
        AuthController instance = new AuthController();
        ResponseEntity<Response> expResult = null;
        ResponseEntity<Response> result = instance.loginUser(username, password, sessionId, inputOtp);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of forgotPasswordOTPsent method, of class AuthController.
     */
    @Test
    public void testForgotPasswordOTPsent() {
        System.out.println("forgotPasswordOTPsent");
        String username = "";
        AuthController instance = new AuthController();
        ResponseEntity<Response> expResult = null;
        ResponseEntity<Response> result = instance.forgotPasswordOTPsent(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of forgotPasswordOTPverify method, of class AuthController.
     */
    @Test
    public void testForgotPasswordOTPverify() {
        System.out.println("forgotPasswordOTPverify");
        String username = "";
        String sessionId = "";
        String inputOtp = "";
        String password = "";
        AuthController instance = new AuthController();
        ResponseEntity<Response> expResult = null;
        ResponseEntity<Response> result = instance.forgotPasswordOTPverify(username, sessionId, inputOtp, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOAuthDetails method, of class AuthController.
     */
    @Test
    public void testGetOAuthDetails() {
        System.out.println("getOAuthDetails");
        OAuth2AuthenticationToken token = null;
        AuthController instance = new AuthController();
        ResponseEntity<Response> expResult = null;
        ResponseEntity<Response> result = instance.getOAuthDetails(token);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

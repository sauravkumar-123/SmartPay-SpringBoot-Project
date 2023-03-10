/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.smartpay.controller;

import com.smartpay.model.Merchant;
import com.smartpay.model.User;
import com.smartpay.response.Response;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author saura
 */
public class UserControllerTest {
    
    public UserControllerTest() {
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
     * Test of registerUser method, of class UserController.
     */
    @Test
    public void testRegisterUser() {
        System.out.println("registerUser");
        User user = null;
        UserController instance = new UserController();
        ResponseEntity<Response> expResult = null;
        ResponseEntity<Response> result = instance.registerUser(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateUserTOMerchantProfile method, of class UserController.
     */
    @Test
    public void testUpdateUserTOMerchantProfile() {
        System.out.println("updateUserTOMerchantProfile");
        String userName = "";
        Merchant merchantRequest = null;
        UserController instance = new UserController();
        ResponseEntity<Response> expResult = null;
        ResponseEntity<Response> result = instance.updateUserTOMerchantProfile(userName, merchantRequest);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of uploadMerchantDocuments method, of class UserController.
     */
    @Test
    public void testUploadMerchantDocuments() {
        System.out.println("uploadMerchantDocuments");
        String identificationNo = "";
        MultipartFile aadharCard = null;
        MultipartFile panCard = null;
        MultipartFile cancelCheque = null;
        UserController instance = new UserController();
        ResponseEntity<Response> expResult = null;
        ResponseEntity<Response> result = instance.uploadMerchantDocuments(identificationNo, aadharCard, panCard, cancelCheque);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of downloadImageFile method, of class UserController.
     */
    @Test
    public void testDownloadImageFile() {
        System.out.println("downloadImageFile");
        String fileName = "";
        HttpServletRequest request = null;
        UserController instance = new UserController();
        ResponseEntity<Resource> expResult = null;
        ResponseEntity<Resource> result = instance.downloadImageFile(fileName, request);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

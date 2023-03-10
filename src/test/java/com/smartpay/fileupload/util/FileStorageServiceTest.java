/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.smartpay.fileupload.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author saura
 */
public class FileStorageServiceTest {
    
    public FileStorageServiceTest() {
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
     * Test of storeFile method, of class FileStorageService.
     */
    @Test
    public void testStoreFile() {
        System.out.println("storeFile");
        MultipartFile[] files = null;
        String aadharlast4digit = "";
        FileStorageService instance = null;
        String[] expResult = null;
        String[] result = instance.storeFile(files, aadharlast4digit);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadFileAsResource method, of class FileStorageService.
     */
    @Test
    public void testLoadFileAsResource() {
        System.out.println("loadFileAsResource");
        String fileName = "";
        FileStorageService instance = null;
        Resource expResult = null;
        Resource result = instance.loadFileAsResource(fileName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

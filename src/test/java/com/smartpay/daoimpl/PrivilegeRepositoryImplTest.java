/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.smartpay.daoimpl;

import com.smartpay.model.Privileges;
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
public class PrivilegeRepositoryImplTest {
    
    public PrivilegeRepositoryImplTest() {
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
     * Test of savePrivilege method, of class PrivilegeRepositoryImpl.
     */
    @Test
    public void testSavePrivilege() {
        System.out.println("savePrivilege");
        Privileges privilege = null;
        PrivilegeRepositoryImpl instance = new PrivilegeRepositoryImpl();
        instance.savePrivilege(privilege);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findPrivilegeByName method, of class PrivilegeRepositoryImpl.
     */
    @Test
    public void testFindPrivilegeByName() {
        System.out.println("findPrivilegeByName");
        String privilegeName = "";
        PrivilegeRepositoryImpl instance = new PrivilegeRepositoryImpl();
        Privileges expResult = null;
        Privileges result = instance.findPrivilegeByName(privilegeName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

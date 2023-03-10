/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.smartpay.daoimpl;

import com.smartpay.model.Role;
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
public class RoleRepositoryImplTest {
    
    public RoleRepositoryImplTest() {
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
     * Test of saveRoles method, of class RoleRepositoryImpl.
     */
    @Test
    public void testSaveRoles() {
        System.out.println("saveRoles");
        Role role = null;
        RoleRepositoryImpl instance = new RoleRepositoryImpl();
        instance.saveRoles(role);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findRoleByName method, of class RoleRepositoryImpl.
     */
    @Test
    public void testFindRoleByName() {
        System.out.println("findRoleByName");
        String roleName = "";
        RoleRepositoryImpl instance = new RoleRepositoryImpl();
        Role expResult = null;
        Role result = instance.findRoleByName(roleName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

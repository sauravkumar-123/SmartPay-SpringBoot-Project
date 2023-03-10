/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.smartpay.serviceimpl;

import com.smartpay.model.Privileges;
import com.smartpay.model.Role;
import java.util.Set;
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
public class RolesAndPrivilegesServiceImplTest {
    
    public RolesAndPrivilegesServiceImplTest() {
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
     * Test of createWritePrivileges method, of class RolesAndPrivilegesServiceImpl.
     */
    @Test
    public void testCreateWritePrivileges() {
        System.out.println("createWritePrivileges");
        RolesAndPrivilegesServiceImpl instance = new RolesAndPrivilegesServiceImpl();
        Privileges expResult = null;
        Privileges result = instance.createWritePrivileges();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createReadPrivileges method, of class RolesAndPrivilegesServiceImpl.
     */
    @Test
    public void testCreateReadPrivileges() {
        System.out.println("createReadPrivileges");
        RolesAndPrivilegesServiceImpl instance = new RolesAndPrivilegesServiceImpl();
        Privileges expResult = null;
        Privileges result = instance.createReadPrivileges();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createUpdatePrivileges method, of class RolesAndPrivilegesServiceImpl.
     */
    @Test
    public void testCreateUpdatePrivileges() {
        System.out.println("createUpdatePrivileges");
        RolesAndPrivilegesServiceImpl instance = new RolesAndPrivilegesServiceImpl();
        Privileges expResult = null;
        Privileges result = instance.createUpdatePrivileges();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createDeletePrivileges method, of class RolesAndPrivilegesServiceImpl.
     */
    @Test
    public void testCreateDeletePrivileges() {
        System.out.println("createDeletePrivileges");
        RolesAndPrivilegesServiceImpl instance = new RolesAndPrivilegesServiceImpl();
        Privileges expResult = null;
        Privileges result = instance.createDeletePrivileges();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createAdminRole method, of class RolesAndPrivilegesServiceImpl.
     */
    @Test
    public void testCreateAdminRole() {
        System.out.println("createAdminRole");
        Set<Privileges> adminPrivilegesSet = null;
        RolesAndPrivilegesServiceImpl instance = new RolesAndPrivilegesServiceImpl();
        Role expResult = null;
        Role result = instance.createAdminRole(adminPrivilegesSet);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createMerchantRole method, of class RolesAndPrivilegesServiceImpl.
     */
    @Test
    public void testCreateMerchantRole() {
        System.out.println("createMerchantRole");
        Set<Privileges> merchantPrivilegesSet = null;
        RolesAndPrivilegesServiceImpl instance = new RolesAndPrivilegesServiceImpl();
        Role expResult = null;
        Role result = instance.createMerchantRole(merchantPrivilegesSet);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createDistributorRole method, of class RolesAndPrivilegesServiceImpl.
     */
    @Test
    public void testCreateDistributorRole() {
        System.out.println("createDistributorRole");
        Set<Privileges> distributorPrivilegesSet = null;
        RolesAndPrivilegesServiceImpl instance = new RolesAndPrivilegesServiceImpl();
        Role expResult = null;
        Role result = instance.createDistributorRole(distributorPrivilegesSet);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createMasterDistributor method, of class RolesAndPrivilegesServiceImpl.
     */
    @Test
    public void testCreateMasterDistributor() {
        System.out.println("createMasterDistributor");
        Set<Privileges> masterDistributorPrivilegesSet = null;
        RolesAndPrivilegesServiceImpl instance = new RolesAndPrivilegesServiceImpl();
        Role expResult = null;
        Role result = instance.createMasterDistributor(masterDistributorPrivilegesSet);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

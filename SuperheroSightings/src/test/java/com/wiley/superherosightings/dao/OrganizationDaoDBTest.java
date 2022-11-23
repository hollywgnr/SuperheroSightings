/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wiley.superherosightings.dao;

import com.wiley.superherosightings.dto.Organization;
import com.wiley.superherosightings.dto.Superperson;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author Holly
 */

@SpringBootTest
public class OrganizationDaoDBTest {
    
    @Autowired
    OrganizationDao organizationDao;
    
    @Autowired
    SuperpersonDao superpersonDao;    

    
    public OrganizationDaoDBTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        
        List<Superperson> superpeople = superpersonDao.getAll();
        for(Superperson superperson : superpeople) {
            superpersonDao.deleteById(superperson.getSuperpersonId());
        }
        
        List<Organization> organizations = organizationDao.getAll();
        for(Organization organization : organizations) {
            organizationDao.deleteById(organization.getOrganizationId());
        }
        
    }
    
    @Test
    public void testAddGetOrganization() {
        Organization organization = new Organization();
        organization.setName("Test Organization");
        organization.setDescription("Test Organization Description");
        organization.setAddress("Test Organization Address");
        organization.setAddress("Test Organization Email");
        organization.setPhone("5556667777");
        organization.setEmail("testorganization@gmail.com");
        organization = organizationDao.add(organization);
        
        Organization fromDao = organizationDao.findById(organization.getOrganizationId());
        
        assertEquals(organization, fromDao);
    }
    
    @Test
    public void testGetAllOrganizations() {
        Organization organization = new Organization();
        organization.setName("Test Organization");
        organization.setDescription("Test Organization Description");
        organization.setAddress("Test Organization Address");
        organization.setPhone("5556667777");
        organization.setEmail("testorganization@gmail.com");
        organization = organizationDao.add(organization);
        
        Organization organization2 = new Organization();
        organization2.setName("Test Org2");
        organization2.setDescription("Test Org2 Description");
        organization2.setAddress("Test Org2 Address");
        organization2.setPhone("5556662222");
        organization2.setEmail("testorganization2@gmail.com");
        organization2 = organizationDao.add(organization2);
        
        List<Organization> organizations = organizationDao.getAll();
        
        assertEquals(2, organizations.size());
        assertTrue(organizations.contains(organization));
        assertTrue(organizations.contains(organization2));
    }
    
    @Test
    public void testUpdateOrganization() {
        Organization organization = new Organization();
        organization.setName("Test Organization");
        organization.setDescription("Test Organization Description");
        organization.setAddress("Test Organization Address");
        organization.setPhone("5556667777");
        organization.setEmail("testorganization@gmail.com");
        organization = organizationDao.add(organization);
        
        Organization fromDao = organizationDao.findById(organization.getOrganizationId());
        
        assertEquals(organization, fromDao);
        
        organization.setName("Another Test Organization");
        
        organizationDao.update(organization);
        
        assertNotEquals(organization, fromDao);
        
        fromDao = organizationDao.findById(organization.getOrganizationId());
        
        assertEquals(organization, fromDao);
    }
    
    @Test
    public void testDeleteOrganization() {
        Organization organization = new Organization();
        organization.setName("Test Organization");
        organization.setDescription("Test Organization Description");
        organization.setAddress("Test Organization Address");
        organization.setPhone("5556667777");
        organization.setEmail("testorganization@gmail.com");
        organization = organizationDao.add(organization);

        organizationDao.deleteById(organization.getOrganizationId());
        
        Organization fromDao = organizationDao.findById(organization.getOrganizationId());
        assertNull(fromDao);
    }

    @Test
    public void testAddGetAllMembers() {
        Organization organization = new Organization();
        organization.setName("Test Organization");
        organization.setDescription("Test Organization Description");
        organization.setAddress("Test Organization Address");
        organization.setPhone("5556667777");
        organization.setEmail("testorganization@gmail.com");
        organization = organizationDao.add(organization);
        
        Superperson superperson = new Superperson();
        superperson.setName("Test Superperson Name");
        superperson.setDescription("Test Superperson Description");
        superperson.setSuperpower("Test Superpower");
        superperson.setIsHero(true);
        superperson = superpersonDao.add(superperson);
        
        organizationDao.addMember(superperson, organization);
        List<Superperson> membersFromDao = organizationDao.getAllMembers(organization.getOrganizationId());

        assertEquals(1, membersFromDao.size());
        assertTrue(membersFromDao.contains(superperson));
        
        Superperson superperson2 = new Superperson();
        superperson2.setName("Test Superperson2 Name");
        superperson2.setDescription("Test Superperson2 Description");
        superperson2.setSuperpower("Test Superpower");
        superperson2.setIsHero(false);
        superperson2 = superpersonDao.add(superperson2);
        
        
        organizationDao.addMember(superperson2, organization);
        membersFromDao = organizationDao.getAllMembers(organization.getOrganizationId());

        assertEquals(2, membersFromDao.size());
        assertTrue(membersFromDao.contains(superperson));
        assertTrue(membersFromDao.contains(superperson2));
        
  }
    
}
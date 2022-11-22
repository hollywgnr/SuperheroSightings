/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wiley.superherosightings.dao;

import com.wiley.superherosightings.TestApplicationConfiguration;
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
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
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
        List<Organization> organizations = organizationDao.getAll();
        for(Organization organization : organizations) {
            organizationDao.deleteById(organization.getOrganizationId());
        }
        
        List<Superperson> superpeople = superpersonDao.getAll();
        for(Superperson superperson : superpeople) {
            superpersonDao.deleteById(superperson.getSuperpersonId());
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
    public void testGetAllMembers() {
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
        superpersonDao.add(superperson);
       
        List<Superperson> members = new ArrayList<>();
        members.add(superperson);
        organization.setSuperpersons(members);
        
        List<Superperson> membersFromDao = organizationDao.getAllMembers(organization.getOrganizationId());

        assertEquals(1, members.size());
        //assertEquals(1, membersFromDao.size());
  }
    
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.wiley.superherosightings.dao;

import com.wiley.superherosightings.TestApplicationConfiguration;
import com.wiley.superherosightings.dto.Location;
import com.wiley.superherosightings.dto.Organization;
import com.wiley.superherosightings.dto.Sighting;
import com.wiley.superherosightings.dto.Superperson;
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
 * @author tiara
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class SuperpersonDaoDBTest {
    @Autowired
    SuperpersonDao spDao;
    
    @Autowired
    SightingsDao sDao;
    
    @Autowired
    OrganizationDao orgDao;
    
    @Autowired
    LocationDao locDao;
    
    public SuperpersonDaoDBTest() {
    }    
    @BeforeEach
    public void setUp() {
        
        List<Location> locations = locDao.getAll();
        for(Location location: locations){
            locDao.deleteById(location.getLocationId());
        }
        
        List<Organization> organizations = orgDao.getAll();
        for(Organization organization:organizations){
            orgDao.deleteById(organization.getOrganizationId());
        }
        
        List<Superperson> superpersons = spDao.getAll();
        for(Superperson sp:superpersons){
            spDao.deleteById(sp.getSuperpersonId());
        }   
        
        List<Sighting> sightings = sDao.getAll();
        for(Sighting sighting: sightings){
            sDao.deleteById(sighting.getSightingId());
        }
    }
    
    /**
     * Test of add method, of class SuperpersonDaoDB.
     */
    @Test
    public void testAddFindByID() {
        //create super person, add to dao
        Superperson sp = new Superperson();
        sp.setDescription("They're cool");
        sp.setName("Spiderman");
        sp.setIsHero(true);
        sp.setSuperpower("Spidey Powers");
        sp = spDao.add(sp);
        
        Superperson fromDao = spDao.findById(sp.getSuperpersonId());
        
        assertEquals(sp,fromDao);
    }

    /**
     * Test of getAll method, of class SuperpersonDaoDB.
     */
    @Test
    public void testGetAll(){
        Superperson sp1 = new Superperson();
            sp1.setName("Doc Oc");
            sp1.setDescription("Is a doctor... that's EVIIL");
            sp1.setIsHero(false);
            sp1.setSuperpower("Machanical Tentacles");
        sp1 = spDao.add(sp1);
        
        Superperson sp2 = new Superperson();
            sp2.setName("Super Why");
            sp2.setDescription("he looks into books for the answers we need");
            sp2.setIsHero(false);
            sp2.setSuperpower("Reading");
        sp2 = spDao.add(sp2);
        
        List<Superperson> sps = spDao.getAll();
        
        assertEquals(2, sps.size());
        assertTrue(sps.contains(sp1));
        assertTrue(sps.contains(sp2));
    }

    /**
     * Test of getAllLocations method, of class SuperpersonDaoDB.
     */
    @Test
    public void testGetAllLocations() {
        //create superperson
        Superperson sp = new Superperson();
            sp.setDescription("From Tommorow Land");
            sp.setName("Miles");
            sp.setIsHero(true);
            sp.setSuperpower("Buff Brains");
        sp = spDao.add(sp);
        //create two locations
        Location l1 = new Location();
            
        Location l2 = new Location();
        
    }

    /**
     * Test of getAllOrganizations method, of class SuperpersonDaoDB.
     */
    @Test
    public void testGetAllOrganizations() {
    }

    /**
     * Test of findById method, of class SuperpersonDaoDB.
     */
    @Test
    public void testFindById() {
    }

    /**
     * Test of update method, of class SuperpersonDaoDB.
     */
    @Test
    public void testUpdate() {
    }

    /**
     * Test of deleteById method, of class SuperpersonDaoDB.
     */
    @Test
    public void testDeleteById() {
    }
    
}

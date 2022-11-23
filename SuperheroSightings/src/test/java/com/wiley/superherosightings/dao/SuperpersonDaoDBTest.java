/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.wiley.superherosightings.dao;


import com.wiley.superherosightings.dto.Location;
import com.wiley.superherosightings.dto.Organization;
import com.wiley.superherosightings.dto.Sighting;
import com.wiley.superherosightings.dto.Superperson;
import java.time.LocalDateTime;
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
@SpringBootTest
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
        List<Organization> organizations = orgDao.getAll();
        for (Organization organization : organizations) {
            orgDao.deleteById(organization.getOrganizationId());
        }
        
        List<Location> locations = locDao.getAll();
        for(Location location: locations){
            locDao.deleteById(location.getLocationId());
        }
        List<Sighting> sightings = sDao.getAll();
        for(Sighting sighting: sightings){
            sDao.deleteById(sighting.getSightingId());
        }
                
        List<Superperson> superpersons = spDao.getAll();
        for(Superperson sp:superpersons){
            spDao.deleteById(sp.getSuperpersonId());
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
            l1.setAddress("wowo");
            l1.setDescription("it's weird");
            l1.setLattitude(25.5);
            l1.setLongitude(24.2);
            l1.setName("wowo");
        l1 = locDao.add(l1);
        
        Location l2 = new Location();
            l2.setAddress("wowo");
            l2.setDescription("it's weird");
            l2.setLattitude(25.5);
            l2.setLongitude(24.2);
            l2.setName("wowo");
        l2 = locDao.add(l2);
        
        //create sightings with locations
        Sighting s1 = new Sighting();
            s1.setLocationId(l1.getLocationId());
            s1.setSightingTime(LocalDateTime.now());
            s1.setSuperpersonId(sp.getSuperpersonId());
            s1 = sDao.add(s1);
        Sighting s2 = new Sighting();
            s2.setLocationId(l2.getLocationId());
            s2.setSightingTime(LocalDateTime.now());
            s2.setSuperpersonId(sp.getSuperpersonId());
            s2 = sDao.add(s2);
            
        List<Location> spLocations = spDao.getAllLocations(sp.getSuperpersonId());
        
        assertEquals(2,spLocations.size());
        assertTrue(spLocations.contains(l1));
        assertTrue(spLocations.contains(l2));
        
    }

    /**
     * Test of getAllOrganizations method, of class SuperpersonDaoDB.
     */
    @Test
    public void testGetAllOrganizations() {
        //create superperson
        Superperson sp = new Superperson();
            sp.setDescription("From Tommorow Land");
            sp.setName("Miles");
            sp.setIsHero(true);
            sp.setSuperpower("Buff Brains");
        sp = spDao.add(sp);
        //create two Organization 2
        Organization o1 = new Organization();
            o1.setAddress("Puppy kickers blvd");
            o1.setDescription("We're pretty evil");
            o1.setEmail("3v1lisc001@gmail.com");
            o1.setName("League Of Villians");
            o1.setPhone("8008888888");
            o1 = orgDao.add(o1);
            
        Organization o2 = new Organization();
            o2.setAddress("Puppy kickers blvd");
            o2.setDescription("We're pretty evil");
            o2.setEmail("3v1lisc001@gmail.com");
            o2.setName("League Of Villians");
            o2.setPhone("8008888888");
            o2 = orgDao.add(o2);
        //add superperson to organization each organization
        orgDao.addMember(sp, o1);
        orgDao.addMember(sp, o2);
        
        List<Organization> orgs = spDao.getAllOrganizations(sp.getSuperpersonId());
        
        assertEquals(2,orgs.size());
        assertTrue(orgs.contains(o1));
        assertTrue(orgs.contains(o2));
    }

    /**
     * Test of update method, of class SuperpersonDaoDB.
     */
    @Test
    public void testUpdate() {
        Superperson sp = new Superperson();
            sp.setDescription("Protector of the Universe");
            sp.setName("Steven Universe");
            sp.setIsHero(true);
            sp.setSuperpower("Shield");
        sp = spDao.add(sp);
        
        Superperson fromDao = spDao.findById(sp.getSuperpersonId());
        
        assertEquals(sp,fromDao);
        
        sp.setName("Steven Quartz Universe");
        spDao.update(sp);
        
        assertNotEquals(sp, fromDao);
        
        fromDao = spDao.findById(sp.getSuperpersonId());
        assertEquals(sp, fromDao);
    }

    /**
     * Test of deleteById method, of class SuperpersonDaoDB.
     */
    @Test
    public void testDeleteById() {
        //make person
        Superperson sp = new Superperson();
            sp.setDescription("Very vilinous");
            sp.setName("Professor Venomus");
            sp.setIsHero(false);
            sp.setSuperpower("Draining Power");
        sp = spDao.add(sp);
        
        //make organization
        Organization o2 = new Organization();
            o2.setAddress("Across from the plaza");
            o2.setDescription("Will Destroy the plaza");
            o2.setEmail("gr34tf4th3r@gmail.com");
            o2.setName("Boxman inc.");
            o2.setPhone("8007777777");
            o2 = orgDao.add(o2);
        //connect person to organization
        orgDao.addMember(sp, o2);
        
        //make location (to make sighting)
        Location l2 = new Location();
            l2.setAddress("Under the Plaza");
            l2.setDescription("It's like a swer");
            l2.setLattitude(7.25);
            l2.setLongitude(19.1);
            l2.setName("The Glorb Tree");
        l2 = locDao.add(l2);
        //make sighting
         Sighting s1 = new Sighting();
            s1.setLocationId(l2.getLocationId());
            s1.setSightingTime(LocalDateTime.now());
            s1.setSuperpersonId(sp.getSuperpersonId());
            s1 = sDao.add(s1);
            
        spDao.deleteById(sp.getSuperpersonId());
        Superperson fromDao =spDao.findById(sp.getSuperpersonId());
        
        assertNull(fromDao);
    }
    
}

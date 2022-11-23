/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wiley.superherosightings.dao;

import com.wiley.superherosightings.TestApplicationConfiguration;
import com.wiley.superherosightings.dto.Location;
import com.wiley.superherosightings.dto.Organization;
import com.wiley.superherosightings.dto.Sighting;
import com.wiley.superherosightings.dto.Superperson;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
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
public class SightingsDaoDBTest {
    @Autowired
    SightingsDao sightingsDao;
    // bringing other dao's to create complex objects in the appropriate tests
    @Autowired
    LocationDao locationDao;
    
    @Autowired
    OrganizationDao organizationDao;
    
    @Autowired
    SuperpersonDao superpersonDao;
    
    public SightingsDaoDBTest() {
        
    }
    
    @BeforeEach
    public void setUp() {
        
        List<Location> locations = locationDao.getAll();
        for(Location location: locations){
            locationDao.deleteById(location.getLocationId());
        }
         
        List<Superperson> superpersons = superpersonDao.getAll();
        for(Superperson sp:superpersons){
            superpersonDao.deleteById(sp.getSuperpersonId());
        }  
        
        List<Organization> organizations = organizationDao.getAll();
        for(Organization organization:organizations){
            organizationDao.deleteById(organization.getOrganizationId());
        }
        
        List<Sighting> sightings = sightingsDao.getAll();
        for(Sighting sighting: sightings){
            sightingsDao.deleteById(sighting.getSightingId());
        }
    }
    

    /**
     * Test of add method, of class SightingsDaoDB.
     */
    @Test
    public void testAddGetSighting() {
        // create location
        Location loc = new Location();
        loc.setAddress("180 Accers");
            loc.setDescription("Very gross");
            loc.setLattitude(58.5);
            loc.setLongitude(67.23);
            loc.setName("Mount Rushmore");
        int locId = locationDao.add(loc).getLocationId();
        
        // create superperson
        Superperson sp1 = new Superperson();
            sp1.setSuperpersonId(0);
            sp1.setDescription("Greatest Superhero Alive");
            sp1.setIsHero(true);
            sp1.setName("All Might");
            sp1.setSuperpower("One for All");
        int spId = superpersonDao.add(sp1).getSuperpersonId();

        Sighting sighting = new Sighting();
        sighting.setLocationId(locId);
        sighting.setSightingTime(LocalDateTime.parse("2015-01-01T11:30:12"));
        sighting.setSuperpersonId(spId);
        
        sighting = sightingsDao.add(sighting);
        
        Sighting fromDao = sightingsDao.findById(sighting.getSightingId());
        assertEquals(sighting, fromDao);
    }

    /**
     * Test of getAll method, of class SightingsDaoDB.
     */
    @Test
    public void testGetAll() {
        
        // create first location
        Location loc1 = new Location();
            loc1.setAddress("180 Accers");
            loc1.setDescription("Very gross");
            loc1.setLattitude(58.5);
            loc1.setLongitude(67.23);
            loc1.setName("Mount Rushmore");
        locationDao.add(loc1);
        
        // create second location
        Location loc2 = new Location();
            loc2.setAddress("Himalaya mountain range");
            loc2.setDescription("Very cold");
            loc2.setLattitude(27.9);
            loc2.setLongitude(86.9);
            loc2.setName("Mount Everest");
        locationDao.add(loc2);
        
        // create first superperson
        Superperson sp1 = new Superperson();
            sp1.setDescription("Greatest Superhero Alive");
            sp1.setIsHero(true);
            sp1.setName("All Might");
            sp1.setSuperpower("One for All");
        superpersonDao.add(sp1);
        
        // create first superperson
        Superperson sp2= new Superperson();
            sp2.setDescription("Water type");
            sp2.setIsHero(true);
            sp2.setName("Aquaman");
            sp2.setSuperpower("Talks to fish");
        superpersonDao.add(sp2);
        
        
        // create first sighting
        Sighting sighting1 = new Sighting();
        sighting1.setLocationId(loc1.getLocationId());
        sighting1.setSightingTime(LocalDateTime.parse("2015-01-01T11:30:12"));
        sighting1.setSuperpersonId(sp1.getSuperpersonId());
        sighting1 = sightingsDao.add(sighting1);
        
        // create second sighting
        Sighting sighting2 = new Sighting();
        sighting2.setLocationId(loc2.getLocationId());
        sighting2.setSightingTime(LocalDateTime.parse("2015-01-01T11:30:12"));
        sighting2.setSuperpersonId(sp2.getSuperpersonId());
        sighting2 = sightingsDao.add(sighting2);
        
        List<Sighting> sightings = sightingsDao.getAll();
        assertEquals(2, sightings.size());
        assertTrue(sightings.contains(sighting1));
        assertTrue(sightings.contains(sighting2));
        
    }

    /**
     * Test of getAllOnDate method, of class SightingsDaoDB.
     */
    @Test
    public void testGetAllOnDate() {
        Sighting sighting1 = new Sighting();
        // make location
        Location loc = new Location();
        loc.setAddress("180 Accers");
            loc.setDescription("Very gross");
            loc.setLattitude(58.5);
            loc.setLongitude(67.23);
            loc.setName("Mount Rushmore");
        locationDao.add(loc);
        
        // make superperson
        Superperson sp1 = new Superperson();
            sp1.setSuperpersonId(0);
            sp1.setDescription("Greatest Superhero Alive");
            sp1.setIsHero(true);
            sp1.setName("All Might");
            sp1.setSuperpower("One for All");
       superpersonDao.add(sp1);
        
        // finally make sighting
        sighting1.setLocationId(loc.getLocationId());
            sighting1.setSightingTime(LocalDateTime.parse("2015-01-01T11:30:12"));
            sighting1.setSuperpersonId(sp1.getSuperpersonId());
        sighting1 = sightingsDao.add(sighting1);
        
        
        Sighting sighting2 = new Sighting();  
        // make superperson
        Superperson sp2 = new Superperson();
            sp2.setSuperpersonId(1);
            sp2.setDescription("Second Greatest Superhero Alive");
            sp2.setIsHero(true);
            sp2.setName("Some Might");
            sp2.setSuperpower("Justice for All");
        superpersonDao.add(sp2);
        
        // finally make sighting
        sighting2.setLocationId(loc.getLocationId());
            sighting2.setSightingTime(LocalDateTime.parse("2015-01-01T11:33:12"));
            sighting2.setSuperpersonId(sp2.getSuperpersonId());
        sighting2 = sightingsDao.add(sighting2);
        
        List<Sighting> sightingsOnDate = sightingsDao.getAllOnDate(LocalDate.parse("2015-01-01"));
        assertEquals(sightingsOnDate.size(), 2);
        assertTrue(sightingsOnDate.contains(sighting1));
        assertTrue(sightingsOnDate.contains(sighting2));
        
    }


    /**
     * Test of update method, findById method, of class SightingsDaoDB.
     */
    @Test
    public void testUpdateFindById() {
        Sighting sighting = new Sighting();
        // make location
        Location loc = new Location();
        loc.setAddress("180 Accers");
            loc.setDescription("Very gross");
            loc.setLattitude(58.5);
            loc.setLongitude(67.23);
            loc.setName("Mount Rushmore");
        locationDao.add(loc);
        
        // make superperson
        Superperson sp1 = new Superperson();
            sp1.setSuperpersonId(0);
            sp1.setDescription("Greatest Superhero Alive");
            sp1.setIsHero(true);
            sp1.setName("All Might");
            sp1.setSuperpower("One for All");
       superpersonDao.add(sp1);
        
        // finally make sighting
        sighting.setLocationId(loc.getLocationId());
            sighting.setSightingTime(LocalDateTime.parse("2015-01-01T11:30:12"));
            sighting.setSuperpersonId(sp1.getSuperpersonId());
        sighting = sightingsDao.add(sighting);
        
        Sighting fromDao = sightingsDao.findById(sighting.getSightingId());
        
        assertEquals(sighting,fromDao);
        // test changing superperson
        sighting.setSightingTime(LocalDateTime.parse("2017-01-01T11:30:12"));
        sightingsDao.update(sighting);
        assertNotEquals(sighting,fromDao);
        
        // testing findById
        fromDao = sightingsDao.findById(sighting.getSightingId());
        assertEquals(sighting,fromDao);
        
    }

    /**
     * Test of deleteById method, of class SightingsDaoDB.
     */
    @Test
    public void testDeleteById() {
        Sighting sighting = new Sighting();
        
        // create location
        Location loc = new Location();
            loc.setAddress("180 Accers");
            loc.setDescription("Very gross");
            loc.setLattitude(58.5);
            loc.setLongitude(67.23);
            loc.setName("Mount Rushmore");
        locationDao.add(loc);
        
        // create superperson
        Superperson sp = new Superperson();
            sp.setDescription("They exist");
            sp.setIsHero(true);
            sp.setName("Deku");
            sp.setSuperpower("One for All");
        superpersonDao.add(sp);
        
        sighting.setLocationId(loc.getLocationId());
        sighting.setSightingTime(LocalDateTime.parse("2015-01-01T10:30:12"));
        sighting.setSuperpersonId(sp.getSuperpersonId());
        sighting = sightingsDao.add(sighting);
                
        sightingsDao.deleteById(sighting.getSightingId());
        Sighting fromDao = sightingsDao.findById(sighting.getSightingId());
        assertNull(fromDao);
    }
    
}
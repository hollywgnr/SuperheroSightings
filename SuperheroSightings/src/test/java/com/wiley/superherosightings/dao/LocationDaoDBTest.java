/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wiley.superherosightings.dao;

import com.wiley.superherosightings.dto.Location;
import com.wiley.superherosightings.dto.Sighting;
import com.wiley.superherosightings.dto.Superperson;
import java.time.LocalDateTime;
import java.util.List;
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
public class LocationDaoDBTest {
    
    @Autowired
    LocationDao locationDao;
    
    @Autowired
    SightingsDao sightingsDao;
    
    @Autowired
    SuperpersonDao superpersonDao;
    
    public LocationDaoDBTest() {
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
        
        List<Location> locations = locationDao.getAll();
        for(Location location : locations) {
            locationDao.deleteById(location.getLocationId());
        }
        
        List<Sighting> sightings = sightingsDao.getAll();
        for(Sighting sighting : sightings) {
            sightingsDao.deleteById(sighting.getSightingId());
        }
        
    }
    
    @Test
    public void testAddGetLocation() {
        Location location = new Location();
        location.setName("Test Location");
        location.setDescription("Test Location Description");
        location.setAddress("Test Location Address");
        location.setLattitude(-100.0);
        location.setLongitude(40.0);
        location = locationDao.add(location);
        
        Location fromDao = locationDao.findById(location.getLocationId());
        
        assertEquals(location, fromDao);
    }
    
    @Test
    public void testGetAllLocations() {
        Location location = new Location();
        location.setName("Test Location");
        location.setDescription("Test Location Description");
        location.setAddress("Test Location Address");
        location.setLattitude(-100.0);
        location.setLongitude(40.0);
        location = locationDao.add(location);
        
        Location location2 = new Location();
        location2.setName("Test Location2");
        location2.setDescription("Test Location2 Description");
        location2.setAddress("Test Location2 Address");
        location2.setLattitude(-100.1);
        location2.setLongitude(40.1);
        location2 = locationDao.add(location2);
        
        List<Location> locations = locationDao.getAll();
        
        assertEquals(2, locations.size());
        assertTrue(locations.contains(location));
        assertTrue(locations.contains(location2));
    }
    
    @Test
    public void testUpdateLocation() {
        Location location = new Location();
        location.setName("Test Location");
        location.setDescription("Test Location Description");
        location.setAddress("Test Location Address");
        location.setLattitude(-100.0);
        location.setLongitude(40.0);
        location = locationDao.add(location);
        
        Location fromDao = locationDao.findById(location.getLocationId());
        
        assertEquals(location, fromDao);
        
        location.setName("Another Test Location");
        
        locationDao.update(location);
        
        assertNotEquals(location, fromDao);
        
        fromDao = locationDao.findById(location.getLocationId());
        
        assertEquals(location, fromDao);
    }
    
    @Test
    public void testDeleteLocation() {
        Location location = new Location();
        location.setName("Test Location");
        location.setDescription("Test Location Description");
        location.setAddress("Test Location Address");
        location.setLattitude(-100.0);
        location.setLongitude(40.0);
        location = locationDao.add(location);
        
        Superperson superperson = new Superperson();
        superperson.setName("Test Superperson Name");
        superperson.setDescription("Test Superperson Description");
        superperson.setSuperpower("Test Superpower");
        superperson.setIsHero(true);
        superpersonDao.add(superperson);
      
        locationDao.deleteById(location.getLocationId());
        
        Location fromDao = locationDao.findById(location.getLocationId());
        assertNull(fromDao);
    }
    
    @Test
    public void testGetAllSuperheros() {
        // make a location
        Location location = new Location();
        location.setName("Test Location");
        location.setDescription("Test Location Description");
        location.setAddress("Test Location Address");
        location.setLattitude(-100.0);
        location.setLongitude(40.0);
        locationDao.add(location);
        
        // make a superperson
        Superperson superperson = new Superperson();
        superperson.setName("Test Superperson Name");
        superperson.setDescription("Test Superperson Description");
        superperson.setSuperpower("Test Superpower");
        superperson.setIsHero(true);
        superpersonDao.add(superperson);
        
        // make a sighting
        Sighting sighting = new Sighting();
        sighting.setLocationId(location.getLocationId());
        sighting.setSuperpersonId(superperson.getSuperpersonId());
        sighting.setSightingTime(LocalDateTime.now());
        sightingsDao.add(sighting);
        
        // call getAllSuperheros
        List<Superperson> sightedSuperheros = locationDao.getAllSuperheros(location);
        assertEquals(1, sightedSuperheros.size());
    }
    
}
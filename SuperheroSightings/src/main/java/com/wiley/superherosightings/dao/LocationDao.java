/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wiley.superherosightings.dao;

import com.wiley.superherosightings.dto.Location;
import com.wiley.superherosightings.dto.Superperson;
import java.util.List;

/**
 *
 * @author Holly
 */
public interface LocationDao {
    
    Location add(Location location);
    List<Location> getAll();
    // method returns a list of all superheros seen at a particular location
    List<Superperson> getAllSuperheros(Location location);
    Location findById(int id);
    // true if item exists and is updated
    boolean update(Location location);
    // true if item exists and is deleted
    boolean deleteById(int id);
    
}

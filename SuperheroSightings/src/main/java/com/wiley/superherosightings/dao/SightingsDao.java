/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wiley.superherosightings.dao;

import com.wiley.superherosightings.dto.Sighting;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Holly
 */
public interface SightingsDao {
    
    Sighting add(Sighting sighting);
    List<Sighting> getAll();
    List<Sighting> getAllOnDate(LocalDateTime date);
    Sighting findById(int id);
    void update(Sighting sighting);
    void deleteById(int id);
    
}

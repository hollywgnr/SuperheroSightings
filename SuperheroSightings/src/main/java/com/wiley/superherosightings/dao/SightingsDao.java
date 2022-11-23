/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wiley.superherosightings.dao;

import com.wiley.superherosightings.dto.Sighting;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Holly
 */
public interface SightingsDao {
    
    Sighting add(Sighting sighting);
    List<Sighting> getAll();
    List<Sighting> getAllOnDate(LocalDate date);
    Sighting findById(int id);
    boolean update(Sighting sighting);
    boolean deleteById(int id);
    
}

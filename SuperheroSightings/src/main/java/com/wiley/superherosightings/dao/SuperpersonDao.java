/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wiley.superherosightings.dao;

import com.wiley.superherosightings.dto.Location;
import com.wiley.superherosightings.dto.Organization;
import com.wiley.superherosightings.dto.Sighting;
import com.wiley.superherosightings.dto.Superperson;
import java.util.List;

/**
 *
 * @author Holly
 */
public interface SuperpersonDao {
    
    Superperson add(Superperson superperson);
    List<Superperson> getAll();
    List<Location> getAllLocations(int superpersonId);
    List<Organization> getAllOrganizations(int superpersonId);
    Superperson findById(int id);
    boolean update(Superperson superperson);
    boolean deleteById(int id);
    
}

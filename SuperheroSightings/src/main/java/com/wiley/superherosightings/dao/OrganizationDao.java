/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wiley.superherosightings.dao;

import com.wiley.superherosightings.dto.Organization;
import com.wiley.superherosightings.dto.Superperson;
import java.util.List;

/**
 *
 * @author Holly
 */
public interface OrganizationDao {
    
    Organization add(Organization organization);
    List<Organization> getAll();
    void addMember(Superperson superperson, Organization organization);
    List<Superperson> getAllMembers(int organizationId);
    Organization findById(int id);
    // true if item exists and is updated
    boolean update(Organization organization);
    // true if item exists and is deleted
    boolean deleteById(int id);
    
}

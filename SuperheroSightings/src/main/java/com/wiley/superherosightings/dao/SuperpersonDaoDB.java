/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wiley.superherosightings.dao;

import com.wiley.superherosightings.dao.LocationDaoDB.LocationMapper;
import com.wiley.superherosightings.dao.OrganizationDaoDB.OrganizationMapper;
import com.wiley.superherosightings.dto.Location;
import com.wiley.superherosightings.dto.Organization;
import com.wiley.superherosightings.dto.Superperson;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tiara
 */
@Repository
public class SuperpersonDaoDB implements SuperpersonDao {

    @Autowired
    JdbcTemplate jdbc;
    
    //adds superperson to database and returns that superperson that was added. (the id will be auto generated)
    @Override
    @Transactional
    public Superperson add(Superperson sp) {
        final String INSERT_SUPERPERSON = "INSERT INTO superperson(name,description,superpower,is_hero) VALUES (?,?,?,?)";
        jdbc.update(INSERT_SUPERPERSON, sp.getName(), sp.getDescription(), sp.getSuperpower(), sp.isHero());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sp.setSuperpersonId(newId);
        
        return sp;
    }
    //returns all superpersons from database
    @Override
    public List<Superperson> getAll() {
        final String GET_ALL_SUPERPERSONS = "SELECT * FROM superperson";
        return jdbc.query(GET_ALL_SUPERPERSONS, new SuperpersonMapper());
    }
    //returns all locations a superperson has seen been in. Empty list if none
    @Override
    public List<Location> getAllLocations(int superpersonId) {
        try {
            final String GET_ALL_LOCATIONS = "SELECT l.location_id,l.`name`,l.`description`,l.address,l.lattitude,l.longitude "
                    + "FROM location l "
                    + "JOIN sighting s ON l.location_id = s.location_id "
                    + "JOIN superperson sp ON s.superperson_id = sp.superperson_id "
                    + "WHERE sp.superperson_id = ?";
            return jdbc.query(GET_ALL_LOCATIONS, new LocationMapper(), superpersonId);
        } catch (DataAccessException ex) {
            //return empty list if none
            List<Location> emptyList= new ArrayList<>();
            return emptyList;
        }
    }
    //returns all the organizations the superperson is a part off. Returns an empty list if none
    @Override
    @Transactional
    public List<Organization> getAllOrganizations(int superpersonId) {
        try {
            //this won't give the list of superheros (that's in the bridge table)
            final String GET_ALL_ORGANIZATIONS = "SELECT o.organization_id, o.`name`, o.`description`, o.address, o.phone, o.email "
                    + "FROM `organization` o "
                    + "JOIN organization_superperson osp ON osp.organization_id = o.organization_id "
                    + "JOIN superperson sp ON sp.superperson_id = osp.superperson_id "
                    + "WHERE sp.superperson_id = ?;";
            List<Organization> orgs = jdbc.query(GET_ALL_ORGANIZATIONS, new OrganizationMapper(), superpersonId);

            //Iterate through all orgs then add the list of super persons to them manually 
            for (Organization org : orgs) {
                final String GET_ALL_SUPERHEROS = "SELECT DISTINCT su.superperson_id,su.`name`,su.`description`,su.superpower,su.is_hero"
                        + " FROM organization o"
                        + " JOIN organization_superperson os ON o.organization_id = os.organization_id"
                        + " JOIN superperson su ON su.superperson_id = su.superperson_id"
                        + " WHERE o.organization_id = ?;";
                List<Superperson> sps = jdbc.query(GET_ALL_SUPERHEROS, new SuperpersonMapper(), org.getOrganizationId());
                org.setSuperpersons(sps);
            }
            return orgs;
        } catch (DataAccessException ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public Superperson findById(int id) {
        try {
            final String FIND_BY_ID = "SELECT * FROM superperson WHERE superperson_id = ?";
            return jdbc.queryForObject(FIND_BY_ID, new SuperpersonMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }
    //updates the superperson at the inputed objects ID to the values of the inputed object. Returns false if doesn't work
    @Override
    public boolean update(Superperson sp) {
        try {
            final String UPDATE_SUPERPERSON = "UPDATE superperson SET name = ?,description = ?,superpower=?,is_hero=? "
                    + "WHERE superperson_id = ?";
            jdbc.update(UPDATE_SUPERPERSON, sp.getName(), sp.getDescription(),
                    sp.getSuperpower(), sp.isHero(), sp.getSuperpersonId());
            return true;
        } catch (DataAccessException ex) {
            return false;
        }
    }

    //attempts to delete object, if fails returns false
    @Override
    @Transactional
    public boolean deleteById(int id) {
        try {
            //delete from organization_superperson
            final String DELETE_FROM_ORGANIZATION = "DELETE FROM organization_superperson "
                    + "WHERE superperson_id = ?";
            jdbc.update(DELETE_FROM_ORGANIZATION, id);
            //delete from sighting
            final String DELETE_FROM_SIGHTING = "DELETE FROM sighting "
                    + "WHERE superperson_id = ?";
            jdbc.update(DELETE_FROM_SIGHTING, id);
            //finally delete superperson 
            final String DELETE_BY_ID = "DELETE FROM superperson WHERE superperson_id = ?";
            jdbc.update(DELETE_BY_ID, id);

            return true;
        } catch (DataAccessException ex) {
            return false;
        }
    }

    //helper functions
        //helps map datatables into in memory objects
    public static final class SuperpersonMapper implements RowMapper<Superperson> {

        @Override
        public Superperson mapRow(ResultSet rs, int rowNum) throws SQLException {
            Superperson sp = new Superperson();
            sp.setDescription(rs.getString("description"));
            sp.setIsHero(rs.getBoolean("is_hero"));
            if(rs.wasNull()){
                sp.setIsHero(null);
            }
            sp.setName(rs.getString("name"));
            sp.setSuperpersonId(rs.getInt("superperson_id"));
            sp.setSuperpower(rs.getString("superpower"));
            return sp;
        }

    }
}

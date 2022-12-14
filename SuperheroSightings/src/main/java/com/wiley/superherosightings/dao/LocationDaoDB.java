/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wiley.superherosightings.dao;

import com.wiley.superherosightings.dao.SuperpersonDaoDB.SuperpersonMapper;
import com.wiley.superherosightings.dto.Location;
import com.wiley.superherosightings.dto.Superperson;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Holly
 */
@Repository
public class LocationDaoDB implements LocationDao {

    @Autowired
    JdbcTemplate jdbc;
    /**
     * adds a location to the database
     * @param location
     * @return the successfully added location
     */
    @Override
    @Transactional
    public Location add(Location location) {
        final String INSERT_LOCATION = "INSERT INTO location(name, description, address, lattitude, longitude) " +
                "VALUES(?,?,?,?,?)";
        jdbc.update(INSERT_LOCATION,
                location.getName(),
                location.getDescription(),
                location.getAddress(),
                location.getLattitude(),
                location.getLongitude());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        location.setLocationId(newId);
        return location;
    }

    /**
     * get all locations in the database
     * @return a list of all locations in the database
     */
    @Override
    public List<Location> getAll() {
        final String GET_ALL_LOCATIONS = "SELECT * FROM location";
        return jdbc.query(GET_ALL_LOCATIONS, new LocationMapper());
    }

    /**
     * returns a list of all superheros at a particular location
     * @param location
     * @return list of 'superpersons'
     */
    @Override
    public List<Superperson> getAllSuperheros(Location location) {
        final String GET_ALL_SUPERHEROS = "SELECT su.superperson_id,su.`name`,su.`description`,su.superpower,su.is_hero"
                + " FROM superperson su"
                + " JOIN sighting si ON si.superperson_id = su.superperson_id"
                + " JOIN location l ON l.location_id = si.location_id"
                + " WHERE l.location_id = ?;";
        return jdbc.query(GET_ALL_SUPERHEROS, new SuperpersonMapper(), location.getLocationId());
    }

    /**
     * Find a location with the specified locationId in the database
     * @param id
     * @return the location with the specified locationId
     * returns null on error
     */
    @Override
    public Location findById(int id) {
        try {
            final String GET_LOCATION_BY_ID = "SELECT * FROM location WHERE location_id = ?";
            return jdbc.queryForObject(GET_LOCATION_BY_ID, new LocationMapper(), id);
        } catch(DataAccessException ex) {
            return null;
        }  
    }

    /**
     * Update a location in database with the provided values
     * @param location
     * @return true on success
     */
    @Override
    public boolean update(Location location) {
        final String UPDATE_LOCATION = "UPDATE location SET name = ?, description = ?, " +
                "address = ?, lattitude = ?, longitude = ? WHERE location_id = ?";
        jdbc.update(UPDATE_LOCATION,
                location.getName(),
                location.getDescription(),
                location.getAddress(),
                location.getLattitude(),
                location.getLongitude(),
                location.getLocationId());
        return true;
    }

    /**
     * delete a location with a specified locationId
     * @param id
     * @return true on success
     */
    @Override
    @Transactional 
    public boolean deleteById(int id) { 
        try{
            final String DELETE_SIGHTING = "DELETE FROM sighting WHERE location_id = ?";
            jdbc.update(DELETE_SIGHTING, id);

            final String DELETE_LOCATION = "DELETE FROM location WHERE location_id = ?";
            jdbc.update(DELETE_LOCATION, id);
        }catch(DataAccessException ex){
            return false;
        }
        return true;
    }
    
    /**
     * A helper class for mapping database tables to in memory location objects
     */
    public static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int index) throws SQLException {
            Location location = new Location();
            location.setLocationId(rs.getInt("location_id"));
            location.setName(rs.getString("name"));
            location.setDescription(rs.getString("description"));
            location.setAddress(rs.getString("address"));
            location.setLattitude(rs.getDouble("lattitude"));
            location.setLongitude(rs.getDouble("longitude"));
            
            return location;
        }
    }
    
}

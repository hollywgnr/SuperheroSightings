/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wiley.superherosightings.dao;

import com.wiley.superherosightings.dto.Sighting;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
public class SightingsDaoDB implements SightingsDao {

    @Autowired
    JdbcTemplate jdbc;
    
    //adds a new sighting to database and returns the sighting that was added (id will be auto generated)
    @Override
    @Transactional
    public Sighting add(Sighting sighting) {
        final String ADD_SIGHTING = "INSERT INTO sighting(location_id,superperson_id,sighting_time)VALUES(?,?,?)";
        jdbc.update(ADD_SIGHTING, sighting.getLocationId(), sighting.getSuperpersonId(), Timestamp.valueOf(sighting.getSightingTime()));
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sighting.setSightingId(newId);
        return sighting;
    }
    //returns all sightings in database
    @Override
    public List<Sighting> getAll() {
        final String SELECT_ALL_SIGHTINGS = "SELECT * FROM sighting";
        return jdbc.query(SELECT_ALL_SIGHTINGS, new SightingMapper());
    }
    //returns all sightings in database that happened on a particular day
    @Override
    public List<Sighting> getAllOnDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateStr = date.format(formatter);
        final String SELECT_ALL_ON_DATE = "SELECT * FROM sighting WHERE CAST(sighting_time AS date) =?";
        return jdbc.query(SELECT_ALL_ON_DATE, new SightingMapper(), dateStr);
    }
    //returns Sighting object from database based on id num, returns null if not found
    @Override
    public Sighting findById(int id) {
        try {
            final String SELECT_BY_ID = "SELECT * FROM sighting WHERE sighting_id = ?";
            return jdbc.queryForObject(SELECT_BY_ID, new SightingMapper(), id);
        } catch(DataAccessException ex) {
            return null;
        }  
    }
    
    //only cares about the id that is directly written in that parameter, true or false based on success
    @Override
    public boolean update(Sighting sighting) {
        try {
            final String UPDATE_SIGHTING = "UPDATE sighting SET location_id = ?,"
                    + "superperson_id = ?,sighting_time = ? WHERE sighting_id = ?";
            jdbc.update(UPDATE_SIGHTING, sighting.getLocationId(),
                    sighting.getSuperpersonId(), sighting.getSightingTime(), sighting.getSightingId());
            return false;
        } catch (DataAccessException ex) {
            return false;
        }
    }
    //deletes item at id, if successfull returns true
    @Override
    public boolean deleteById(int id) {
        try{
        final String DELETE_BY_ID = "DELETE FROM sighting WHERE sighting_id = ?";
        jdbc.update(DELETE_BY_ID,id);
        return true;
        }catch(DataAccessException ex){
            return false;
        }
    }

    //helper function
    public static final class SightingMapper implements RowMapper<Sighting> {

        //returns a sighting object with info from the database
        @Override
        public Sighting mapRow(ResultSet rs, int rowNum) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setLocationId(rs.getInt("location_id"));
            sighting.setSightingId(rs.getInt("sighting_id"));
            sighting.setSuperpersonId(rs.getInt("superperson_id"));
            if (rs.getTimestamp("sighting_time") != null) {
                sighting.setSightingTime(rs.getTimestamp("sighting_time").toLocalDateTime());
            }
            return sighting;

        }

    }

}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wiley.superherosightings.dao;

import com.wiley.superherosightings.dto.Location;
import com.wiley.superherosightings.dto.Organization;
import com.wiley.superherosightings.dto.Superperson;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public void add(Superperson sp) {
        final String INSERT_SUPERPERSON = "INSERT INTO superperson(name,description,superpower,is_hero) VALUES (?,?,?,?)";
        jdbc.update(INSERT_SUPERPERSON, sp.getName(), sp.getDescription(), sp.getSuperpower(), sp.isIsHero());
    }

    @Override
    public List<Superperson> getAll() {
        final String GET_ALL_SUPERPERSONS = "SELECT * FROM superperson";
        return jdbc.query(GET_ALL_SUPERPERSONS, new SuperpersonMapper());
    }

    @Override
    public List<Location> getAllLocations(int superpersonId) {
        final String GET_ALL_LOCATIONS = "SELECT l.`name` FROM location l"
                + "JOIN sighting s ON l.location_id = s.location_id"
                + "JOIN superperson sp ON s.superperson_id = sp.superperson_id"
                + "WHERE sp.superperson_id = ?";
        return jdbc.query(GET_ALL_LOCATIONS, new LocationMapper(), superpersonId);
    }

    @Override
    public List<Organization> getAllOrganizations(int superpersonId) {
        final String GET_ALL_ORGANIZATIONS = "SELECT o.organization_id,o.`name`,o.`description`,o.address,o.phone,o.email"
                + " FROM `organization` o"
                + "JOIN organization_superperson osp ON osp.organization_id = o.organization_id"
                + "JOIN superperson sp ON sp.superperson_id = osp.superperson_id"
                + "WHERE sp.superperson_id = ?;";
        return jdbc.query(GET_ALL_ORGANIZATIONS, new OrganizationMapper(), superpersonId);
    }

    @Override
    public Superperson findById(int id) {
        final String FIND_BY_ID = "SELECT * FROM superperson WHERE superperson_id = ?";
        return jdbc.queryForObject(FIND_BY_ID, new SuperpersonMapper(), id);
    }

    @Override
    public void update( Superperson sp) {
        final String UPDATE_SUPERPERSON = "UPDATE superperson SET name = ?,description = ?,superpower=?,is_hero=? "
                + "WHERE superperson_id = ?";
        jdbc.update(UPDATE_SUPERPERSON, sp.getName(), sp.getDescription(), 
                sp.getSuperpower(), sp.isIsHero(),sp.getSuperpersonId());
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        //delete from organization_superperson
        final String DELETE_FROM_ORGANIZATION = "DELETE FROM organization_superperson "
                + "WHERE superperson_id = ?";
        jdbc.update(DELETE_FROM_ORGANIZATION,id);  
        //delete from sighting
        final String DELETE_FROM_SIGHTING = "DELETE FROM sighting "
                + "WHERE superperson_id = ?";
        jdbc.update(DELETE_FROM_SIGHTING,id);
        //finally delete superperson 
        final String DELETE_BY_ID = "DELETE FROM superperson WHERE superperson_id = ?";
        jdbc.update(DELETE_BY_ID, id);
    }

    //helper functions
    public static final class SuperpersonMapper implements RowMapper<Superperson> {

        @Override
        public Superperson mapRow(ResultSet rs, int rowNum) throws SQLException {
            Superperson sp = new Superperson();
            sp.setDescription(rs.getString("description"));
            sp.setIsHero(rs.getBoolean("is_hero"));
            sp.setName(rs.getString("name"));
            sp.setSuperpersonId(rs.getInt("superperson_id"));
            sp.setSuperpower(rs.getString("superpower"));
            return sp;
        }

    }

    public static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int rowNum) throws SQLException {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

    }
    public static final class LocationMapper implements RowMapper<Location>{

        @Override
        public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
        
    }

}

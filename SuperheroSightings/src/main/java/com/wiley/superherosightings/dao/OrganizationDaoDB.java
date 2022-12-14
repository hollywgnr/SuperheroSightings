/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wiley.superherosightings.dao;

import com.wiley.superherosightings.dao.SuperpersonDaoDB.SuperpersonMapper;
import com.wiley.superherosightings.dto.Organization;
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
public class OrganizationDaoDB implements OrganizationDao {
    
    @Autowired
    JdbcTemplate jdbc;

    
    /**
     * adds an organization to the database
     * @param organization
     * @return the added organization
     */
    @Override
    @Transactional
    public Organization add(Organization organization) {
        final String INSERT_ORGANIZATION = "INSERT INTO `organization`(`name`, `description`, address, phone, email) " +
                "VALUES(?,?,?,?,?)";
        jdbc.update(INSERT_ORGANIZATION,
                organization.getName(),
                organization.getDescription(),
                organization.getAddress(),
                organization.getPhone(),
                organization.getEmail());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        organization.setOrganizationId(newId);
        insertOrganizationSuperperson(organization);
        return organization;
    }

    /**
     * gets all organizations in the database
     * @return a list of organizations
     */
    @Override
    public List<Organization> getAll() {
        final String GET_ALL_ORGANIZATIONS = "SELECT * FROM organization";
        return jdbc.query(GET_ALL_ORGANIZATIONS, new OrganizationMapper());
    }

    /**
     * returns a list of all 'superpersons' at a particular location
     * @param organizationId
     * @return a list of 'superpersons'
     */
    @Override
    public List<Superperson> getAllMembers(int organizationId) {
        final String GET_ALL_SUPERHEROS = "SELECT DISTINCT su.superperson_id,su.`name`,su.`description`,su.superpower,su.is_hero"
                + " FROM organization o"
                + " JOIN organization_superperson os ON o.organization_id = os.organization_id"
                + " JOIN superperson su ON su.superperson_id = su.superperson_id"
                + " WHERE o.organization_id = ?;";
        return jdbc.query(GET_ALL_SUPERHEROS, new SuperpersonMapper(), organizationId);
    }
    
    /**
     * adds a 'superperson' to an organization
     * @param superperson
     * @param organization 
     */
    @Override
    @Transactional
    public void addMember(Superperson superperson, Organization organization) {
        // add in memory
        organization.addSuperperson(superperson);
        // add to database
        insertOrganizationSuperperson(organization, superperson);
    }

    /**
     * Find an organization with the specified organizationId in the database
     * @param id
     * @return the organization with the specified organizationId
     * returns null on error
     */
    @Override
    public Organization findById(int id) {
        try {
            final String GET_ORGANIZATION_BY_ID = "SELECT * FROM `organization` WHERE organization_id = ?";
            return jdbc.queryForObject(GET_ORGANIZATION_BY_ID, new OrganizationMapper(), id);
        } catch(DataAccessException ex) {
            return null;
        } 
    }

    /**
     * Update an organization in database with the provided values
     * @param organization
     * @return true on success
     */
    @Override
    @Transactional
    public boolean update(Organization organization) {
        final String UPDATE_LOCATION = "UPDATE organization SET name = ?, description = ?, " +
                "address = ?, phone = ?, email = ? WHERE organization_id = ?";
        jdbc.update(UPDATE_LOCATION,
                organization.getName(),
                organization.getDescription(),
                organization.getAddress(),
                organization.getPhone(),
                organization.getEmail(),
                organization.getOrganizationId());
        insertOrganizationSuperperson(organization);
        return true;
    }

    /**
     * delete an organization with a specified organizationId
     * @param id
     * @return true on success
     */
    @Override
    @Transactional
    public boolean deleteById(int id) {
        try{
            final String DELETE_ORGANIZATION_SUPERPERSON = "DELETE FROM organization_superperson WHERE organization_id = ?";
            jdbc.update(DELETE_ORGANIZATION_SUPERPERSON, id);

            final String DELETE_ORGANIZATION = "DELETE FROM organization WHERE organization_id = ?";
            jdbc.update(DELETE_ORGANIZATION, id);
        }catch(DataAccessException ex){
            return false;
        }
        return true;
    }
    
    // helper functions for insering into the organization_superperson bridge table
    private void insertOrganizationSuperperson(Organization org){
        final String INSERT_ORG_SUP = "INSERT INTO organization_superperson"
                + "(organization_id,superperson_id) VALUES(?,?)";
        for(Superperson sp:org.getSuperpersons()){
            jdbc.update(INSERT_ORG_SUP,org.getOrganizationId(),sp.getSuperpersonId());
        }
    }
    private void insertOrganizationSuperperson(Organization org, Superperson sp){
        final String INSERT_ORG_SUP = "INSERT INTO organization_superperson"
                + "(organization_id,superperson_id) VALUES(?,?)";
        jdbc.update(INSERT_ORG_SUP,org.getOrganizationId(),sp.getSuperpersonId());
    }

    /**
     * A helper class for mapping database tables to in memory organization objects
     */
    public static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int index) throws SQLException {
            Organization organization = new Organization();
            organization.setOrganizationId(rs.getInt("organization_id"));
            organization.setName(rs.getString("name"));
            organization.setDescription(rs.getString("description"));
            organization.setAddress(rs.getString("address"));
            organization.setPhone(rs.getString("phone"));
            organization.setEmail(rs.getString("email"));
            
            return organization;
        }
    }
    
}

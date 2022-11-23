/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wiley.superherosightings.controller;

import com.wiley.superherosightings.dto.Location;
import com.wiley.superherosightings.dto.Superperson;
import java.time.LocalDateTime;

/**
 *
 * @author Holly
 */
public class SightingInfo {
    LocalDateTime sightingTime;
    String locationName;
    String superpersonName;

    public LocalDateTime getSightingTime() {
        return sightingTime;
    }

    public void setSightingTime(LocalDateTime sightingTime) {
        this.sightingTime = sightingTime;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getSuperpersonName() {
        return superpersonName;
    }

    public void setSuperpersonName(String superpersonName) {
        this.superpersonName = superpersonName;
    }


    
}

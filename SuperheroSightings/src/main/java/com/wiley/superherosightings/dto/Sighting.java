/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wiley.superherosightings.dto;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author tiara
 */
public class Sighting {
    int sightingId;
    int locationId;
    int superpersonId;
    LocalDateTime sightingTime;

    public int getSightingId() {
        return sightingId;
    }

    public void setSightingId(int sightingId) {
        this.sightingId = sightingId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getSuperpersonId() {
        return superpersonId;
    }

    public void setSuperpersonId(int superpersonId) {
        this.superpersonId = superpersonId;
    }

    public LocalDateTime getSightingTime() {
        return sightingTime;
    }

    public void setSightingTime(LocalDateTime sightingTime) {
        this.sightingTime = sightingTime;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.sightingId;
        hash = 17 * hash + this.locationId;
        hash = 17 * hash + this.superpersonId;
        hash = 17 * hash + Objects.hashCode(this.sightingTime);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sighting other = (Sighting) obj;
        if (this.sightingId != other.sightingId) {
            return false;
        }
        if (this.locationId != other.locationId) {
            return false;
        }
        if (this.superpersonId != other.superpersonId) {
            return false;
        }
        if (!Objects.equals(this.sightingTime, other.sightingTime)) {
            return false;
        }
        return true;
    }
    
}

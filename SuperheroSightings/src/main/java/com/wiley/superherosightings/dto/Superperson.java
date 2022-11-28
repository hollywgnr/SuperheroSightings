/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wiley.superherosightings.dto;

import java.util.Objects;

/**
 *
 * @author tiara
 */
public class Superperson {
    private int superpersonId;
    private String name;
    private String description;
    private String superpower;
    private Boolean isHero;

    public int getSuperpersonId() {
        return superpersonId;
    }

    public void setSuperpersonId(int superpersonId) {
        this.superpersonId = superpersonId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSuperpower() {
        return superpower;
    }

    public void setSuperpower(String superpower) {
        this.superpower = superpower;
    }

    public Boolean isHero() {
        return isHero;
    }

    public void setIsHero(Boolean isHero) {
        this.isHero = isHero;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.superpersonId;
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.description);
        hash = 29 * hash + Objects.hashCode(this.superpower);
        hash = 29 * hash + (this.isHero ? 1 : 0);
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
        final Superperson other = (Superperson) obj;
        if (this.superpersonId != other.superpersonId) {
            return false;
        }
        if (this.isHero != other.isHero) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return Objects.equals(this.superpower, other.superpower);
    } 
    
}
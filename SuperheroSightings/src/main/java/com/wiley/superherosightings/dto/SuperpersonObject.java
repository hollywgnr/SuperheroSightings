/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.wiley.superherosightings.dto;

/**
 *
 * @author tiara
 */
//created to be able to send information to html while avoid problems of null
    public class SuperpersonObject{
        int superpersonId;
        String name;
        String description;
        String superpower;
        String alignment;
        
        public SuperpersonObject(Superperson sp){
            superpersonId = sp.getSuperpersonId();
            name = sp.getName();
            description = sp.getDescription();
            superpower = sp.getSuperpower();
            if(sp.isHero()!=null) {
                if(sp.isHero()){
                    alignment = "Hero";
                }else{
                    alignment = "Villain";
                }
            } else {
                alignment = "Unknown";
            }
                    
        }
        
        public int getSuperpersonId() {
            return superpersonId;
        }

        public void setSuperperosnId(int superperosnId) {
            this.superpersonId = superperosnId;
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

        public String getAlignment() {
            return alignment;
        }

        public void setAlignment(String alignment) {
            this.alignment = alignment;
        }
        
    }

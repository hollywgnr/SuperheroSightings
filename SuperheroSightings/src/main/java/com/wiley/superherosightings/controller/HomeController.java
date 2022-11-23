/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wiley.superherosightings.controller;

import com.wiley.superherosightings.dao.LocationDao;
import com.wiley.superherosightings.dao.SightingsDao;
import com.wiley.superherosightings.dao.SuperpersonDao;
import com.wiley.superherosightings.dto.Location;
import com.wiley.superherosightings.dto.Sighting;
import com.wiley.superherosightings.dto.Superperson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Holly
 */
@Controller
public class HomeController {
    
    @Autowired
    SightingsDao sightingsDao;
    
    @Autowired
    LocationDao locationDao;
    
    @Autowired
    SuperpersonDao superpersonDao;
    
    
    @GetMapping("index")
    public String displayRecentSightings(Model model){

        List<Sighting> sightings = sightingsDao.getAll();
        List<SightingInfo> sightingsWithInfo = new ArrayList<>();
        int count = 0;
        for(Sighting sighting : sightings){
            if(count < 10){
                SightingInfo si = new SightingInfo();
                si.setSightingTime(sighting.getSightingTime());
                si.setLocationName(locationDao.findById(sighting.getLocationId()).getName());
                si.setSuperpersonName(superpersonDao.findById(sighting.getSuperpersonId()).getName());

                sightingsWithInfo.add(si);
            }
            count++;
        }
        model.addAttribute("sightings", sightingsWithInfo);
        
        return "index";
    }
    
    @GetMapping("superpersons")
    public String displaySuperpersons(Model model){

        List<Superperson> superpersons = superpersonDao.getAll();
        model.addAttribute("superpersons", superpersons);
        return "superpersons";
    }
    
    @PostMapping("addSuperperson")
    public String addSuperperson(HttpServletRequest request) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String superpower = request.getParameter("superpower");
        String isHero = request.getParameter("isHero");
        
        Superperson superperson = new Superperson();
        superperson.setName(name);
        superperson.setDescription(description);
        superperson.setSuperpower(superpower);
        superperson.setIsHero(Boolean.parseBoolean(isHero));
        
        superpersonDao.add(superperson);
        
        return "redirect:/superpersons";
    }
    
}

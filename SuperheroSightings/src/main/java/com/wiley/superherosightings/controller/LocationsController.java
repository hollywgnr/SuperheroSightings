/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wiley.superherosightings.controller;

import com.wiley.superherosightings.dao.LocationDao;
import com.wiley.superherosightings.dao.SightingsDao;
import com.wiley.superherosightings.dto.Location;
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
public class LocationsController {

    @Autowired
    LocationDao locationDao;

    @GetMapping("locations")
    public String displayLocations(Model model) {
        List<Location> locations = locationDao.getAll();
        List<LocationInfo> locationsWithInfo = new ArrayList<>();
        for (Location location : locations) {
            LocationInfo li = new LocationInfo();
            li.setName(location.getName());
            li.setAddress(location.getAddress());
            int count = locationDao.getAllSuperheros(location).size();
            li.setSightingCount(count);
            locationsWithInfo.add(li);
        }
        model.addAttribute("locations", locationsWithInfo);
        return "locations";
    }

    @PostMapping("addLocation")
    public String addLocation(HttpServletRequest request) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String address = request.getParameter("address");
        String longitude = request.getParameter("longitude");
        String latitude = request.getParameter("latitude");

        Location location = new Location();
        location.setName(name);
        location.setDescription(description);
        location.setAddress(address);
        location.setLongitude(Double.parseDouble(longitude));
        location.setLattitude(Double.parseDouble(latitude));

        locationDao.add(location);

        return "redirect:/locations";
    }
}

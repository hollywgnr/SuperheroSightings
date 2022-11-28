/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wiley.superherosightings.controller;

import com.wiley.superherosightings.dao.SuperpersonDao;
import com.wiley.superherosightings.dto.Superperson;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Holly
 */
@Controller
public class SuperpowersController {
    
    @Autowired
    SuperpersonDao superpersonDao;
    
    
    @GetMapping("superpowers")
    public String displaySuperpowers(Model model) {

        List<Superperson> superpersons = superpersonDao.getAll();
        model.addAttribute("superpowers", superpersons);
        return "superpowers";
    }
    
}

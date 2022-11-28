/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wiley.superherosightings.controller;

import com.wiley.superherosightings.dao.SuperpersonDao;
import com.wiley.superherosightings.dto.Superperson;
import com.wiley.superherosightings.dto.SuperpersonObject;
import java.util.ArrayList;
import java.util.List;
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
public class SuperpersonsController {

    @Autowired
    SuperpersonDao superpersonDao;

    @GetMapping("superpersons")
    public String displaySuperpersons(Model model) {

        List<Superperson> superpersons = superpersonDao.getAll();
        //add all to superperson object
        List<SuperpersonObject> spos = new ArrayList<>();
        for(Superperson sp:superpersons){
            spos.add(new SuperpersonObject(sp));
        }
        model.addAttribute("superpersons", spos);
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

    @GetMapping("deleteSuperperson")
    public String deleteSuperperson(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        superpersonDao.deleteById(id);

        return "redirect:/superpersons";
    }

}

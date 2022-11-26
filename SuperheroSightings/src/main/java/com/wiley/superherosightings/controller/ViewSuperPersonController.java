/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.wiley.superherosightings.controller;

import com.wiley.superherosightings.dao.SuperpersonDao;
import com.wiley.superherosightings.dto.Superperson;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author tiara
 */
@Controller
public class ViewSuperPersonController {

    @Autowired
    SuperpersonDao superpersonDao;

    @GetMapping("superperson")
    public String displayAll(Model model) {
        List<Superperson> allSups = superpersonDao.getAll();
        model.addAttribute("superpersons", allSups);

        return "superperson";
    }

    @GetMapping("superperson/{id}")
    public String displaySuperperson(@PathVariable("superpersonId") int id, Model model) {
        //model.addAttribute("superperson",superpersonDao.findById(id));
        List<Superperson> allSups = superpersonDao.getAll();
        for (Superperson sp : allSups) {
            model.addAttribute("superperson", sp);
        }
        return "superperson";
    }
    @GetMapping("viewSuperperson")
    public String viewSuperperson(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Superperson superperson = superpersonDao.findById(id);
        
        model.addAttribute("superperson", superperson);
        return "viewSuperperson";
    }

}

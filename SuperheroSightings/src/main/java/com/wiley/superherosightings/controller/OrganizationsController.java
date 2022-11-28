/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wiley.superherosightings.controller;

import com.wiley.superherosightings.dao.OrganizationDao;
import com.wiley.superherosightings.dto.Organization;
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
public class OrganizationsController {

    @Autowired
    OrganizationDao organizationDao;

    @GetMapping("organizations")
    public String displayLocations(Model model) {
        List<Organization> organizations = organizationDao.getAll();

        model.addAttribute("organizations", organizations);
        return "organizations";
    }

    @PostMapping("addOrganization")
    public String addOrganization(HttpServletRequest request) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        Organization organization = new Organization();
        organization.setName(name);
        organization.setDescription(description);
        organization.setAddress(address);
        organization.setPhone(phone);
        organization.setEmail(email);

        organizationDao.add(organization);

        return "redirect:/organizations";
    }

}

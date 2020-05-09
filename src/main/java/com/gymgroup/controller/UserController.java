/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gymgroup.controller;

import com.gymgroup.dao.UserDao;
import com.gymgroup.entities.User;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Alex
 */
@Controller
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    UserDao udao;
    
    @GetMapping("/update")
    public String editUser(Model model, Principal principal){
        String name = principal.getName();
        System.out.println("PRINCIPAL NAME: " + name);
        User user = udao.findByUsername(name);
        model.addAttribute(user);
        
        
        return "editprofile";
    }
}
